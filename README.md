# Sogong-IMS
소공도 관광지 통합관리시스템 구축 프로젝트


## 개발환경

언어 : Java

WAS : Tomcat(Servlet/Jsp)

## 시작하기 앞서

안녕하세요 이번 프로젝트에서 구현팀장을 맞았습니다.
구현을 진행하기 앞서서 개발 환경을 맞춰야 하기 때문에

필요한 항목들을 알려드리겠습니다. github와 gradle에서
프로젝트를 진행할 것이기 때문에 IDE 환경은 각자 편하신대로
진행하셔도 됩니다. 

대부분 VScode에서 코드를 해본 경험이 있으시다고 했으므로
다음과 같은 extension이 필요합니다.

- jsp
- lombok annotation support
- gradle extension pack
- java extension pack
- tomcat for java

# gradle

프로젝트 관리를 위해서 gradle을 사용하려고 합니다. gradle은 다음
주소로 가서 다운로드 받으실 수 있습니다.

1. https://gradle.org/next-steps/?version=6.4.1&format=all

2. C 드라이브에 Gradle이라는 폴더를 생성하셔서 다운로드 받으신 파일을
C:\Gradle\ 위치에 넣어주시면 됩니다.
그러면 경로가 C:\Gradle\gradle-6.4.1-all 다음과 같으실겁니다.

3. 환경변수에 들어가셔서 path에 C:\Gradle\gradle-6.4.1-all\bin 을 추가하시면
됩니다. 컴퓨터 환경에 따라서 C:\가 아닐수도 있습니다.

4. cmd 창을 들어가셔서 gradle -v를 입력하셔서 정삭적으로 나오는지 확인하면
됩니다.

# groovy

gradle은 groovy라는 언어로 작동합니다. 그래서 groovy를 다음 주소로 가셔서
다운로드 받으시면 됩니다.

1. https://groovy.apache.org/download.html

2. C 드라이브에 Groovy라는 폴더를 생성하셔서 다운로드 받으신 파일을
C:\Groovy\ 위치에 넣어주시면 됩니다.
그러면 경로가 C:\Groovy\groovy-3.0.4 다음과 같으실겁니다.

3. 환경변수에 들어가셔서 path에 C:\Groovy\groovy-3.0.4\bin 을 추가하시면
됩니다. 컴퓨터 환경에 따라서 C:\가 아닐수도 있습니다.

# tomcat

Servlet을 동작하기위한 Container가 필요합니다. 여기서는 tomcat을 이용하도록 하겠습니다.

1. http://apache.mirror.cdnetworks.com/tomcat/tomcat-9/v9.0.36/bin/apache-tomcat-9.0.36-windows-x64.zip

2. 자주 사용하실 위치에 tomcat을 저장해주시면 됩니다. 

3. vscode에 들어가셔서 
