package com.softsquared.gridge_test.android.instagram_challenge.page.write_feed

import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseViewModel

class WriteFeedViewModel : BaseViewModel() {

    var imageUrl = ""

    fun changeImageUrl(newImageUrl : String) {
        imageUrl = newImageUrl
    }
}