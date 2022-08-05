package com.softsquared.gridge_test.android.instagram_challenge.data.api.response

import com.softsquared.gridge_test.android.instagram_challenge.data.in_app.MyPageData

data class ResponseUserSignIn(val jwt : String, val loginId : String)

data class ResponseUserSignUp(val jwt : String)

data class ResponseUserKakaoSignIn(val jwt : String, val loginId : String)

data class ResponseMyPage(val loginId : String, val realName : String, val followerCount : Int, val followingCount : Int, val feedCount : Int) {
    companion object {
        fun toMyPageData(responseMyPage: ResponseMyPage) : MyPageData {
            return MyPageData(loginId = responseMyPage.loginId, realName = responseMyPage.realName,
                followerCount = responseMyPage.followerCount, followingCount = responseMyPage.followingCount, feedCount = responseMyPage.feedCount)
        }
    }
}