package ms.customer.bank.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Consumer;

@Aspect
@Component
public class LoggingAdvice {

    private static final Logger log = LoggerFactory.getLogger(LoggingAdvice.class.getName());

    @Pointcut(value = "execution(* ms.customer.bank.*.*.*(..) )")
    public void myPointCut(){
    }

    @Around("myPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        if (result instanceof Mono)
            return ((Mono) result).doOnSuccess(getConsumer(joinPoint, start));
        return result;
    }

    public Consumer getConsumer(ProceedingJoinPoint joinPoint, long start) {
        return o -> {
            String response = "";
            if (Objects.nonNull(o))
                response = o.toString();
            log.info("Enter: {}.{}() with argument[s] = {}",
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
                    joinPoint.getArgs());
            log.info("Exit: {}.{}() had arguments = {}, with result = {}, Execution time = {} ms",
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
                    joinPoint.getArgs()[0],
                    response, (System.currentTimeMillis() - start));
        };
    }



//    @Around("myPointCut()")
//    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
//        ObjectMapper mapper = new ObjectMapper();
//
//        String methodName = pjp.getSignature().getName();
//        String className = pjp.getTarget().getClass().toString();
//        Object[] array = pjp.getArgs();
//
//        log.info("metodo invocado "  +
//                className + " : " +
//                methodName + "() arguments : " +
//                mapper.writeValueAsString(array));
//
//        Object object = pjp.proceed();
//        log.info(className + " : " +
//                methodName + "() Response : " +
//                mapper.writeValueAsString(object));
//
//        return object;
//    }
}
