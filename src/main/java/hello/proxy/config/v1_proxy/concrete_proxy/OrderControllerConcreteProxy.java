package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderControllerConcreteProxy extends OrderControllerV2 {

    private final OrderControllerV2 orderControllerV2;
    private final LogTrace trace;

    public OrderControllerConcreteProxy(OrderControllerV2 orderControllerV2, LogTrace trace) {
        super(null);
        this.orderControllerV2 = orderControllerV2;
        this.trace = trace;
    }

    @Override
    public String request(String itemId) {

        TraceStatus status = null;

        try {
            status = trace.begin("OrderController.request()");
            String result = orderControllerV2.request(itemId);
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return orderControllerV2.noLog();
    }
}
