package com.sicoapp.movieapp.ui.profil

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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.sicoapp.movieapp.EntryActivity
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.remote.firebase.FireStoreClass
import com.sicoapp.movieapp.databinding.DrawerHeaderBinding
import com.sicoapp.movieapp.databinding.FragmentMyProfileBinding
import com.sicoapp.movieapp.ui.BaseFragment
import com.sicoapp.movieapp.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_entry.*

@AndroidEntryPoint
class MyProfileFragment : BaseFragment() {

    var selectedImageUri: Uri? = null
    private var profileImageURL: String = ""
    private lateinit var binding: FragmentMyProfileBinding
    private lateinit var drawerBinding: DrawerHeaderBinding
    private val viewModel: MyProfileViewModel by viewModels()
    private lateinit var headerProfilImageView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FireStoreClass().loadUserDataMyProfile(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMyProfileBinding.inflate(inflater)

        hideProgressDialogUpdateSuccess()

        binding.ivProfileUserImage.setOnClickListener {

            if (context?.let { context ->
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                } == PackageManager.PERMISSION_GRANTED
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
                uploadImageToFireStorage()
            } else {
                showProgressDialog(resources.getString(R.string.please_wait))
                updateProfileToFireCollection()
            }
        }


        viewModel.statusProfileUpdateSuccess.observe(viewLifecycleOwner, Observer { status ->
            status?.let {
                viewModel.statusProfileUpdateSuccess.value = null
                Toast.makeText(context, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
                hideProgressDialog()
            }
        })

        drawerBinding = DrawerHeaderBinding.inflate(inflater)

        return binding.root
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

    fun hideProgressDialogUpdateSuccess() {
        viewModel.hideProgressDialog.observe(viewLifecycleOwner, { it ->
            if (it == true) {
                dialog.dismiss()
                viewModel.hideProgressDialog.value = null
            } else {
                viewModel.hideProgressDialog.value = null
            }
        }
        )
    }



    fun uploadImageToFireStorage() {
        showProgressDialog(resources.getString(R.string.please_wait))
        if (selectedImageUri != null) {
            val storageReference: StorageReference = FirebaseStorage.getInstance().reference.child(
                "USER_IMAGE" + System.currentTimeMillis() + "."
                        + fileExtension(selectedImageUri)
            )
            storageReference.putFile(selectedImageUri!!)
                .addOnSuccessListener { snapshot ->
                    // Get the downloadable url from the snapshot
                    snapshot.metadata!!.reference!!.downloadUrl
                        .addOnSuccessListener { uri ->
                            profileImageURL = uri.toString()
                            updateProfileToFireCollection()
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


    fun updateProfileToFireCollection() {

        val userHashMap = HashMap<String, Any>()

        if (profileImageURL.isNotEmpty() && profileImageURL != viewModel.userModel.value?.image ?: return) {
            userHashMap[USER_IMAGE] = profileImageURL
        }
        if (binding.etName.text.toString() != viewModel.userModel.value?.name) {
            userHashMap[USER_NAME] = binding.etName.text.toString()
        }
        if (binding.etEmail.text.toString() != viewModel.userModel.value?.email) {
            userHashMap[USER_EMAIL] = binding.etEmail.text.toString()
        }
        // Update the database
        FireStoreClass()
            .updateUserProfileData(viewModel, userHashMap)
    }


    /*
        this.startActivityForResult put the image in
        intent.data off onActivityResultwith the same PICK_IMAGE_REQUEST_CODE
     */
    private fun imageChooser() {
        //select image of phone storage
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        // Launches the image selection of phone storage using the constant code.
        this.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode == Activity.RESULT_OK
            && requestCode == PICK_IMAGE_REQUEST_CODE
            && intent!!.data != null
        ) {
            selectedImageUri = intent.data!!
            //from device to xml layout
            binding.image = selectedImageUri.toString()
        }
    }

    private fun fileExtension(uri: Uri?): String? {
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity?.contentResolver?.getType(uri!!))
    }

}