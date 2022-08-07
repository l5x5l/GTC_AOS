# GTC_AOS
그릿지 테스트 안드로이드 instagram challenge

## 적용 패턴 
databinding과 MVVM을 적용하였고, 비동기 처리 작업으로는 코루틴을, 관찰 가능한 변수로는 shared flow를 변형한 event flow와 state flow를 사용했습니다.
event flow는 api 통신과 같은 이벤트들의 호출 결과를 관찰하는데 사용되며, state flow는 화면에 보이는 데이터들을 관찰하는데 사용됩니다.

## 패키지 구조 (코드)
+ base_component : 앱에 전반적으로 사용되는 클래스들이 배치됩니다.
+ custom_view : 직접 커스텀한 View, ViewGroup 클래스들이 배치됩니다.
+ data : 앱에서 사용되는 데이터 클래스들이 배치됩니다.
  + api : 스웨거에 정의된, 서버의 요청/응답으로 사용되는 데이터 클래스들이 배치됩니다.
  + in_app : 앱 UI를 표시하거나 enum class같은 앱 내부에서 사용되는 데이터 클래스들이 배치됩니다.
+ page : 각 화면에 해당하는 클래스들이 배치됩니다. 세부 배치는 아래처럼 되어있습니다.
  + [화면이름]
    + activity or fragment
    + viewModel
    + [해당 화면의 childFragment]
      + fragment
      + viewModel
+ recycler : recyclerView에 대한 클래스들이 배치됩니다.
  + adapter : 어댑터
  + item_decoration : ItemDecoration
  + paging3 : paging3 라이브러리와 관련된 PagingSource 클래스들이 배치됩니다.
  + viewholder : viewHolder
+ repository : api 통신에 사용되는 repository 클래스들이 배치됩니다.
+ retrofit_interface : retrofit 인터페이스들이 정의됩니다.
+ utils : 본 앱에서 사용하는 유틸 함수들이 정의됩니다.
  + KakaoLogin.kt : 카카오 로그인에 사용되는 함수가 정의되어 있습니다. (소셜 로그인 미구현으로 인해 사용하지 않습니다)
  + age.kt : 생년월일로부터 나이를 계산할 때 사용되는 함수가 정의되어 있습니다.
  + navigate.kt : 화면 이동과 관련된 특수한 케이스가 정의되어 있습니다.
  + regex.kt : 비밀번호, 아이디, 사용자 이름과 관련된 정규식 검사 함수가 정의되어 있습니다.
  + size.kt : dpToPx (dp를 px단위로 변경하는 함수)가 정의되어 있습니다.
  + time.kt : 시간 string을 현재로부터 경과한 시간으로 변경해주는 함수가 정의되어있습니다
  
## api 호출관련 특이사항
모든 api를 호출하기 전에는 base_component/BaseViewModel에 정의된 startLoadingDialogDebounce를 호출합니다.
해당 함수는 로딩다이얼로그를 발생시키는 변수를 0.5초 후 true로 변경합니다.
그리고 api response가 들어온 경우 BaseViewModel에 정의된 setLoadingDialogState(false)를 호출합니다.
해당 함수는 0.5초 후 값을 변화시키는 debounce Job을 해제시킨 후 로딩다이얼로그를 발생시키는 변수를 즉시 false로 변경합니다.
위에서 언급된 변수는 BaseActivity, BaseFragment에서 관찰하여 변수값에 따라 로딩 다이얼로그를 표시/해제합니다.

추가로, 네트워크관련 에러처리는 BaseViewModel의 coroutineExceptionHandler를 사용하여 처리했습니다.
