package com.softsquared.gridge_test.android.instagram_challenge.data.in_app

// ResponseUserRepo 의 ResponseMyPage 와 동일
// 외부 api 의 데이터 형식을 그대로 사용하는 것이 이후 유지보수에 좋지 않을 것으로 생각되어 동일한 데이터 구조지만 별도로 생성했습니다.
data class MyPageData(val loginId : String = "", val realName : String = "",
                      val followerCount : Int = 0, val followingCount : Int = 0, val feedCount : Int = 0)