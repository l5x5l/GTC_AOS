package com.softsquared.gridge_test.android.instagram_challenge.page.write_feed

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import com.softsquared.gridge_test.android.instagram_challenge.base_component.*
import com.softsquared.gridge_test.android.instagram_challenge.repository.FeedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream

class WriteFeedViewModel : BaseViewModel() {

    private val storageRef by lazy { FirebaseStorage.getInstance().reference }
    var imageUrl = ""
    var feedText = ""

    private val _uploadFeedResult = MutableEventFlow<Int>()
    val uploadFeedResult = _uploadFeedResult.asEventFlow()

    private val repository = FeedRepository.getInstance()

    fun changeImageUrl(newImageUrl : String) {
        imageUrl = newImageUrl
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    fun tryUploadImage(){
        viewModelScope.launch(Dispatchers.IO) {
            startLoadingDialogDebounce()
            val stream = FileInputStream(File(imageUrl))
            val imageRef = storageRef.child("prove/${GlobalApplication.getLoginId()}/prove_${System.currentTimeMillis()}")
            val uploadTask = imageRef.putStream(stream)
            uploadTask.addOnSuccessListener { _ ->
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    tryUploadFeed(uri)
                }
            }.addOnFailureListener {

            }
        }
    }

    private fun tryUploadFeed(uri: Uri){
        viewModelScope.launch {
            val response = repository.postCreateFeed(feedText = feedText, contentsUrls = arrayListOf(uri.toString()))
            setLoadingDialogState(false)
            _uploadFeedResult.emit(response.code)
        }
    }
}