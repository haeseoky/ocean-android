# Ocean - 안드로이드 앱

## 프로젝트 개요
Ocean은 해양 생태계에 대한 정보를 제공하는 Jetpack Compose와 Kotlin을 활용한 안드로이드 애플리케이션입니다. 이 앱은 MVVM 아키텍처 패턴과 Material 3 디자인 가이드라인을 따르며, 최신 안드로이드 개발 기술을 사용합니다.

## 기술 스택
- **언어**: Kotlin
- **UI 프레임워크**: Jetpack Compose
- **아키텍처 패턴**: MVVM (Model-View-ViewModel)
- **상태 관리**: StateFlow
- **디자인 시스템**: Material 3
- **최소 SDK**: 34 (Android 14)
- **타겟 SDK**: 35

## 구현된 기능
- 기본 앱 구조 셋업 및 MVVM 아키텍처 구현
- 홈 화면의 해양 아이템 목록 표시
- 목록 항목 클릭 이벤트 처리 준비

## 프로젝트 구조
```
app/
├── data/           # 데이터 레이어 (repository)
├── domain/         # 도메인 레이어 (models 등)
└── ui/             # UI 레이어 (화면, 컴포넌트)
    ├── home/       # 홈 화면 관련 컴포넌트
    └── theme/      # 앱 테마 정의
```

## 추가 예정 기능
- 상세 화면 구현
- 데이터 저장소 연동
- 검색 기능
- 다크모드 테마 최적화

## 개발 환경
- Android Studio
- Gradle 8.10.2
- JDK 11
