# mvvm-sample

Android MVVM 패턴을 이용하여 구현된 Sample code 입니다.
- Kakao API를 이용하여 이미지 / 비디오의 Thumbnail을 가져온 후, RecyclerView로 출력합니다.
- 원하는 Thumbnail을 선택하여 추가하면 내 보관함에 추가됩니다.

* 정상동작 하려면, Kakao Developer (https://developers.kakao.com/) 에서 앱 등록이 필요합니다.
* 앱 등록 후, app/build.gradle의KAKAO_REST_API_KEY 영역을 업데이트 해주세요.

## Language
- Java

## Library
- ViewPager
- Data Binding
- ViewModel
- RxJava
- OkHttpClient
- Gson
- Picasso
