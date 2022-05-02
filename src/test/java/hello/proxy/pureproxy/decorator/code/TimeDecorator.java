package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.util.TimeZone;

@Slf4j
public class TimeDecorator extends AbstractComponent {

    public TimeDecorator(Component component) {
        super(component);
    }

    @Override
    public String operation() {
        log.info("TimeDecorator 실행");
        long startTime = System.currentTimeMillis();
        String result = super.operation();
        long endTime = System.currentTimeMillis();
        log.info("TimeDecorator 종료 result time={}", endTime - startTime);
        return result;
    }
}
