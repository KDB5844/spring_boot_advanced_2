package hello.proxy.pureproxy.concreteproxy;

import hello.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import hello.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import hello.proxy.pureproxy.concreteproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void noProxy() {
        ConcreteClient concreteClient = new ConcreteClient(new ConcreteLogic());
        concreteClient.execute();
    }

    @Test
    void addProxy() {
        ConcreteClient concreteClient = new ConcreteClient(new TimeProxy(new ConcreteLogic()));
        concreteClient.execute();
    }

}
