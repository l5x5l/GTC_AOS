package com.softsquared.gridge_test.android.instagram_challenge.base_component

data class BaseApiResponse<T>(
    val isSuccess : Boolean = false,
    val code : Int = 0,
    val message : String ?= null,
    val result : T ?= null
)