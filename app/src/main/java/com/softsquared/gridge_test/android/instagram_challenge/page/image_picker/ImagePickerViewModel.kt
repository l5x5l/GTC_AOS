package com.softsquared.gridge_test.android.instagram_challenge.page.image_picker

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.softsquared.gridge_test.android.instagram_challenge.base_component.BaseViewModel
import com.softsquared.gridge_test.android.instagram_challenge.base_component.MutableEventFlow
import com.softsquared.gridge_test.android.instagram_challenge.base_component.asEventFlow
import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.ImagePickerData
import com.softsquared.gridge_test.android.instagram_challenge.repository.LocalImageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ImagePickerViewModel : BaseViewModel() {

    private lateinit var localRepository : LocalImageRepository

    private val _currentPosition = MutableStateFlow(-1)
    val currentPosition = _currentPosition.asStateFlow()

    private val _loadImageListResult = MutableEventFlow<Boolean>()
    val loadImageListResult = _loadImageListResult.asEventFlow()

    val imageList = ArrayList<ImagePickerData>()

    /**
     * localImageRepository 는 context 를 사용하므로, 본 viewModel 을 사용하는 activity or fragment 로부터
     * context 를 받아 localRepository 를 생성하는 과정을 수행합니다.
     */
    fun initRepository(context : Context) {
        localRepository = LocalImageRepository(context)
    }

    fun loadImageList(){
        viewModelScope.launch {
            val newImageList = localRepository.getImageUrls()
            imageList.clear()
            imageList.addAll(newImageList)
            if (newImageList.isNotEmpty()) {
                imageList[0].isSelected = true
                _currentPosition.value = 0
            }
            _loadImageListResult.emit(value = true)
        }

    }

    fun changeCurrentPosition (position : Int) {
        _currentPosition.value = position
    }
}