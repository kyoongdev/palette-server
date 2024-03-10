# palette-server

## Introduction
음악 오픈 마켓으로서 작곡, 작사, 녹음, 믹스&마스터링, 앨범아트 5가지의 음원 제작 필수 5요소를 거래할 수 있는 서비스 입니다.

## Stacks
- Sprint Boot
- Java
- JPA
- MySQL

## Role
- PM : 프로젝트의 일정을 관리하고 FrontEnd 개발자와 기획적인 이슈를 정리하여 전달하는 역할을 맡았습니다.
- BackEnd : 프로젝트 진행 간 일부 기능에 대한 개발을 진행하였습니다.

## ERD
![palette](https://github.com/kyoongdev/roof-lupin-server/assets/68049802/acc471cf-23a2-4dbf-8cc1-9364cb2a7e44)

## Open API
- https://api-dev.pa1ette.com/swagger-ui/index.html#/

## Git Flow

- main : 메인 브랜치
- deploy/dev : 개발환경 CI/CD 전용 브랜치
- deploy/prod : 운영환경 CI/CD 전용 브랜치
- feature/{name} : 특정 피쳐 단위 개발 도메인 ex) feature/auth, feature/artists

**feature/{name}**에서 개발을 마치면 **main**에 Pull Request를 통해 Squash Merge 진행
