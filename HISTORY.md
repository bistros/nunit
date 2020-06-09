### 작업 기록
1. 기능 단위의 Commit 는 아님. PR 이후 Rebase 했기 때문에 PR 단위의 리뷰가 좀 더 편함
2. JUnit 을 확장하는 형태이기 때문에 가능한 라이브러리를 사용하지 않음 (slf4j, mockito 등도 제외)
3. 그 외 정보 
   * CI : https://travis-ci.com/github/bistros/nunit/builds
   * Gradle Plugin : Checkstyle, PMD, nebula, 
   * IDE : Intellij 

### Pull Requests
  * 환경) Project 생성 [Link](https://github.com/bistros/nunit/pull/2)
  * 환경) Ci 설정 [Link](https://github.com/bistros/nunit/pull/4)
  * 환경) Checkstyle,PMD 설정 [Link](https://github.com/bistros/nunit/pull/8)
  * 개선) 가장 단순한 형태의 Runner 구현 [Link](https://github.com/bistros/nunit/pull/9)
  * 개선) 가장 단순한 형태의 Repeat 구현 [Link](https://github.com/bistros/nunit/pull/10)
  * 버그) Ignore, Repeat 가 같이 적용 될 때 Ignore 가 여러번 실행 되는 버그 수정 [Link](https://github.com/bistros/nunit/pull/14)     
  * 개선) N 번 반복 실행 될 때 노출되는 메소드 명에 회차를 표시, 음수 방어 코드 추가 [Link](https://github.com/bistros/nunit/pull/15)
  * 개선) Statement 를 상속하는 Repeat 를 구현하고  테스트 메소드를 제어할 Statement Chain 에 등록함 [Link](https://github.com/bistros/nunit/pull/16)
  * 버그) Repeat 가 동작하는 순서 조정 [Link](https://github.com/bistros/nunit/pull/18)
  
   
  
  
  
  
  
  
  


