package com.softsquared.gridge_test.android.instagram_challenge.base_component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.HttpException

open class BaseViewModel : ViewModel() {
    private val _isShowLoadingDialog = MutableStateFlow(false)
    val isShowLoadingDialog = _isShowLoadingDialog.asStateFlow()

    private val _networkErrorMessage = MutableEventFlow<String>()
    val networkErrorMessage = _networkErrorMessage.asEventFlow()

    private var loadingDialogDebounceJob : Job ?= null

    protected val networkExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        stopLoadingDialogDebounce()
        when(throwable){
            is HttpException -> {
                viewModelScope.launch {
                    _networkErrorMessage.emit(throwable.message())
                    setLoadingDialogState(false)
                }
            }
            else -> {
                viewModelScope.launch {
                    _networkErrorMessage.emit(throwable.message ?: "unknown")
                    setLoadingDialogState(false)
                }
            }
        }
    }

    suspend fun startLoadingDialogDebounce(debounceTime : Long = 500L){
        loadingDialogDebounceJob?.cancel()
        loadingDialogDebounceJob = viewModelScope.launch {
            delay(debounceTime)
            _isShowLoadingDialog.value = true
        }
    }

    private fun stopLoadingDialogDebounce() {
        loadingDialogDebounceJob?.cancel()
    }

    fun setLoadingDialogState(isShow : Boolean) {
        stopLoadingDialogDebounce()
        _isShowLoadingDialog.value = isShow
    }

}