package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// interface 를 이용한 초기화 방법 => 지금은 거의 사용하지 않음
// public class NetworkClient implements InitializingBean, DisposableBean
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    // 이렇게 하고 Config 파일에서 Bean 에 initMethod, destroyMethod 를 지정해준다
    // 이렇게 하면 장점은 여기서 스프링에 의존하지 않고 작성이 가능하기 때문

    // 근데 다 필요없고 PostConstruct 와 PreDestroy 를 사용하자 => 스프링에서도 권장하는 기능
    // 여전히 스프링에 종속적인 기능이 아님 => JSR-250 자바 표준, 따라서 스프링이 아니라 다른 프레임워크에서도 잘 작동함
    // 그래서 ComponentScan 과 잘 어울림 => Bean 에 등록되는게 아니니까
    // 근데 단점이 있음 => 외부 라이브러리에는 적용하지 못한다, 외부 라이브러리를 초기화, 종료 해야 하면 @Bean 의 기능을 사용해야한다
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }

    // interface 를 이용한 초기화
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메시지");
//    }
//
//    @Override
//    public void destroy() {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }
}
