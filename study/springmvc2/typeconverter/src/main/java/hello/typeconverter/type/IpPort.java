package hello.typeconverter.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

// "127.0.0.1:8888
@Getter
@EqualsAndHashCode
public class IpPort {

    private String ip;
    private int port;

    public IpPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
