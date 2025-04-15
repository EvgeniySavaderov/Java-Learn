package homeworks.aspect;

import homeworks.dto.MessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;


@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut(value = "@annotation(Logs)")
    public void callLoggingAround() {

    }

    @Around(value = "callLoggingAround()")
    public Object loggingAround(ProceedingJoinPoint pjp) throws Throwable {
        log.info("Выполнение метода pjp.getArgs = {}", pjp.getArgs());
        log.info("Выполнение метода = {}", pjp.getStaticPart());
        return pjp.proceed();
    }
}
