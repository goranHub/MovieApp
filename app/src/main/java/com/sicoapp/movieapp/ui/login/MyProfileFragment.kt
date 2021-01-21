package com.sicoapp.movieapp.ui.login

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.remote.firebase.FireStoreClass
import com.sicoapp.movieapp.data.remote.firebase.model.User
import com.sicoapp.movieapp.databinding.FragmentMyProfileBinding
import com.sicoapp.movieapp.ui.BaseFragment
import com.sicoapp.movieapp.utils.PICK_IMAGE_REQUEST_CODE
import com.sicoapp.movieapp.utils.READ_STORAGE_PERMISSION_CODE
import com.sicoapp.movieapp.utils.USER_IMAGE
import com.sicoapp.movieapp.utils.USER_NAME
import kotlinx.android.synthetic.main.fragment_my_profile.*

class MyProfileFragment() : BaseFragment() {

    var selectedImageUri: Uri? = null

    private lateinit var userRemote: User

    private var profileImageURL: String = ""

    private lateinit var binding: FragmentMyProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMyProfileBinding.inflate(inflater)

        FireStoreClass().loadUserData(this)

        binding.ivProfileUserImage.setOnClickListener {

            if (context?.let { context ->
                    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) }
                    == PackageManager.PERMISSION_GRANTED
            ) {
                imageChooser()
            } else {
                activity?.let { fragmentActivity ->
                    ActivityCompat.requestPermissions(
                        fragmentActivity,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        READ_STORAGE_PERMISSION_CODE
                    )
                }
            }
        }

        binding.btnUpdate.setOnClickListener {
            if (selectedImageUri != null) {
                uploadUserImage()
            } else {
                showProgressDialog(resources.getString(R.string.please_wait))
                updateUserProfile()
            }
        }
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode == Activity.RESULT_OK
                && requestCode == PICK_IMAGE_REQUEST_CODE
                && intent!!.data != null
        ) {
            selectedImageUri = intent.data!!
            binding.data  = selectedImageUri.toString()

        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == READ_STORAGE_PERMISSION_CODE) {
            //If permission is granted
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                imageChooser()
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(
                        context,
                        "you denied the permission",
                        Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun setUserDataInUI(user: User) {
        userRemote = user
        binding.data  = user.image
        binding.etName.setText(user.name)
        binding.etEmail.setText(user.email)
    }

    private fun uploadUserImage() {

        showProgressDialog(resources.getString(R.string.please_wait))

        if (selectedImageUri != null) {

            val storageReference: StorageReference = FirebaseStorage.getInstance().reference.child(
                    "USER_IMAGE" + System.currentTimeMillis() + "."
                            + fileExtension( selectedImageUri)
            )

            storageReference.putFile(selectedImageUri!!)
                    .addOnSuccessListener {
                            snapshot ->

                        // Get the downloadable url from the snapshot
                        snapshot.metadata!!.reference!!.downloadUrl
                                .addOnSuccessListener { uri ->
                                    profileImageURL = uri.toString()
                                    updateUserProfile()
                                }
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(
                                context,
                                exception.message,
                                Toast.LENGTH_LONG
                        ).show()

                        hideProgressDialog()
                    }
        }
    }

    private fun updateUserProfile() {

        val userHashMap = HashMap<String, Any>()

        if (profileImageURL.isNotEmpty() && profileImageURL != userRemote.image) {
            userHashMap[USER_IMAGE] = profileImageURL
        }

        if (binding.etName.text.toString() != userRemote.name) {
            userHashMap[USER_NAME] = binding.etName.text.toString()
        }

        // Update the database
        FireStoreClass()
            .updateUserProfileData(this@MyProfileFragment, userHashMap)
    }

    fun profileUpdateSuccess() {
        hideProgressDialog()
        Toast.makeText(context, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
    }

    private fun imageChooser() {
        //select image of phone storage
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        // Launches the image selection of phone storage using the constant code.
        this.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }

    private fun fileExtension(uri: Uri?): String? {
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity?.contentResolver?.getType(uri!!))
    }
}