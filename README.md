# 대한민국 도시 음식 앱

## 프로젝트 개요
이 앱은 대한민국의 주요 도시들과 각 도시의 유명한 음식을 소개하는 안드로이드 애플리케이션입니다. 각 도시별로 대표적인 음식과 간단한 설명을 제공하여 한국의 다양한 지역 음식을 쉽게 탐색할 수 있게 합니다.

## 기술 스택
- **언어**: Kotlin
- **UI 프레임워크**: Jetpack Compose
- **아키텍처 패턴**: MVVM (Model-View-ViewModel)
- **디자인 시스템**: Material 3
- **최소 SDK**: 34 (Android 14)
- **타겟 SDK**: 35

## 주요 기능
- 대한민국 주요 도시 목록 표시
- 각 도시별 유명 음식 정보 제공
- 간결하고 직관적인 UI로 쉽게 탐색 가능
- Material 3 디자인 가이드라인 준수

## 구현된 기능
- MVVM 아키텍처를 기반으로 한 프로젝트 구조
- 8개 주요 도시(서울, 부산, 인천, 대구, 광주, 대전, 울산, 제주) 정보
- 각 도시별 유명 음식과 설명 표시
- Material 3 기반의 모던한 UI

## 프로젝트 구조
```
app/
├── data/                # 데이터 레이어
│   └── repository/      # 데이터 레포지토리
├── domain/              # 도메인 레이어
│   └── model/           # 데이터 모델 클래스
│       ├── CityInfo.kt  # 도시 정보 모델
│       └── OceanItem.kt # 일반 아이템 모델
└── ui/                  # UI 레이어
    ├── home/            # 홈 화면 컴포넌트
    │   ├── HomeScreen.kt    # 도시 목록 화면
    │   └── HomeViewModel.kt # 홈 화면 ViewModel
    └── theme/           # 앱 테마 정의
```

## 설치 및 실행 방법
1. Android Studio를 사용하여 프로젝트를 엽니다.
2. 필요한 SDK가 설치되어 있는지 확인합니다 (최소 SDK 34).
3. 에뮬레이터 또는 실제 기기에서 앱을 실행합니다.

## 앱 사용 방법
1. 앱을 실행하면 대한민국 주요 도시 목록이, 각 도시의 유명 음식과 함께 표시됩니다.
2. 각 도시 카드를 클릭하면 추후 상세 정보를 볼 수 있도록 확장될 예정입니다.

## 추가 예정 기능
- 도시별 상세 페이지 구현
- 도시 위치를 표시하는 지도 통합
- 더 많은 도시와 음식 정보 추가
- 검색 기능 추가
- 사용자 즐겨찾기 기능
- 다크 테마 지원 강화

## 개발 환경
- Android Studio
- Gradle 8.10.2
- JDK 11

## 오류 해결 방법
현재 앱을 빌드할 때 다음과 같은 의존성 문제가 발생할 수 있습니다:
```
Unresolved reference 'compose'
Unresolved reference 'viewModel'
```

이 문제를 해결하려면 app/build.gradle.kts 파일에 다음 의존성을 추가하세요:
```kotlin
// ViewModel Compose 의존성
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
// 코루틴 의존성
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
```

## 라이선스
이 프로젝트는 MIT 라이선스에 따라 배포됩니다.
