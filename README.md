# NhnExam
환경 설정
1. Git, SVN 을 사용 (Git에 연동 완료)
2. mvn clean package 했을 때, JUnit 을 수행 (maven-surefire-plugin 사용하여 maven test, maven install시에 junit 기동 확인)
3. java –jar was.jar 해서 실행 (maven-shade-plugin 사용하여 uber-jar 생성 후 window에서 확인)
4. Java 표준 라이브러리 외 다른 네트워크 프레임워크(예, Netty)를 사용하지 말아주세요 (jackson, lombock, typesafe  사용)


스펙
1. HTTP/1.1 의 Host 헤더를 해석 (logback을 이용하여 콘솔에 출력되도록 함) 
2. 다음 사항을 설정 파일로 관리하세요. (serverConfig.json 파일에서 관리하도록 함)
3. 403, 404, 500 오류를 처리합니다 (HttpHandler 클래스에서 처리하도록 함)
4. HTTP_ROOT 디렉터리의 상위 디렉터리에 접근, 확장자가 .exe 인 파일을 요청시에 403 코드 반환 (완료)
5. logback을 이용한 로깅 작업 (하루 단위로 분리 되도록 설정 완료, Test용은 debug, 꼭 필요한 정보는 info로 출력 로그 레벨은 info, 
error시에는 e.getMessage가 로그 파일에 출력되도록 작성)
6. SimpleServlet 구현 (모든 Servlert이 SimpleServlet을 상속받아 구현)
7. URL 을 SimpleServlet 구현체로 매핑, 클래스 파일로 매핑하지만, 추후 설정 파일을 이용해서 매핑하는 것도 고려 (mappingConfig.json에 간단한 매핑을
한 후에 SimpleServlet을 상속받아 새로운 Servlert을 구현하도록 설정)
8. 현재 시각을 출력하는 SimpleServlet 구현체를 작성 (하나의 프로젝트에 넣어두었습니다. localhost:8080/time 시에 호출)
9. 앞에서 구현한 여러 스펙을 검증하는 테스트 케이스를 JUnit4 를 이용해서 작성 (부족하지만 몇몇 테스트 케이스를 junit 4로 구한하였습니다.)


