package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyBasicConfig {

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
        LogTraceBasicHandler handler = new LogTraceBasicHandler(new OrderRepositoryV1Impl(), logTrace);
        return (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(), new Class[]{OrderRepositoryV1.class}, handler);
    }

    @Bean
    public OrderServiceV1 orderServiceV11(LogTrace logTrace) {
        LogTraceBasicHandler handler = new LogTraceBasicHandler(new OrderServiceImpl(orderRepositoryV1(logTrace)), logTrace);
        return (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(), new Class[]{OrderServiceV1.class}, handler);
    }

    @Bean
    public OrderControllerV1 orderControllerV11(LogTrace logTrace) {
        LogTraceBasicHandler handler = new LogTraceBasicHandler(new OrderControllerV1Impl(orderServiceV11(logTrace)), logTrace);
        return (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(), new Class[]{OrderControllerV1.class}, handler);
    }

}
