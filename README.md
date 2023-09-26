# palette-server

## Docker Compose 세팅

(Docker가 설치되어있다면 넘어가셔도 좋습니다.)
Window : https://docs.docker.com/desktop/install/windows-install/
Mac : https://docs.docker.com/desktop/install/mac-install/
위 링크를 통해 도커를 설치합니다.

## Mysql 명세

docker-compose.yml 파일 참고 - MYSQL_ROOT_PASSWORD=PalleteSecret123 - MYSQL_USER=pallete - MYSQL_DATABASE=pallete - MYSQL_PASSWORD=PalleteSecret123

## 서버 실행

서버 실행은 다음과 같은 과정을 통해 진행합니다.

```
$ sh ./docker/start-docker.sh
>>> [Y/N]질문에는 Y를 입력
```

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
(임시 LocalHost:8080 포트 swagger url)
http://localhost:8080/swagger-ui/index.html
## Git Flow

- main : 메인 브랜치
- deploy/dev : 개발환경 CI/CD 전용 브랜치
- deploy/prod : 운영환경 CI/CD 전용 브랜치
- feature/{name} : 특정 피쳐 단위 개발 도메인 ex) feature/auth, feature/artists

**feature/{name}**에서 개발을 마치면 **main**에 Pull Request를 통해 Squash Merge 진행
