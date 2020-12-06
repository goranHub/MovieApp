package com.sicoapp.movieapp.utils

/**
 * @author ll4
 * @date 12/6/2020
 */
interface CallbackFragmentViewModelAdapter {
    //od fragmenta do viewModela
    fun navigateToNextScren(postID: Int)
    //od viewModela do adaptera
    fun onItemClicked(postID : Int)
}