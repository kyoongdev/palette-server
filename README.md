# palette-server

## 폴더 구조

```
📦src
┣ 📂main
┃ ┣ 📂java/com/study/palette
┃ ┃ ┣ 📂common
┃ ┃ ┃ ┗ 📂config
┃ ┃ ┃ ┗ 📜SwaggerConfig.java
┃ ┃ ┣ 📂temp
┃ ┃ ┃ ┗ 📂controller
┃ ┃ ┃ ┣ 📜TempController.java
┃ ┃ ┃ ┗ 📜TempController2.java
┃ ┃ ┗ 📜PaletteApplication.java
┃ ┗ 📂resources
┃ ┃ ┗ 📜application.yml
┃ ┃
┗ 📂test/java/com/study/palette
┃ ┗ 📜PaletteApplicationTests.java
```

## Lint 설정

## 환경 변수 관리

## 실행 방법

## Swagger 주소

## Git Flow

- main : 메인 브랜치
- deploy/dev : 개발환경 CI/CD 전용 브랜치
- deploy/prod : 운영환경 CI/CD 전용 브랜치
- feature/{name} : 특정 피쳐 단위 개발 도메인 ex) feature/auth, feature/artists

**feature/{name}**에서 개발을 마치면 **main**에 Pull Request를 통해 Squash Merge 진행
