package example.boot.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    
    @Around("execution(* example.boot..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        Long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            Long end = System.currentTimeMillis();
            Long timems = end - start;
            System.out.println("Task : "+joinPoint.toString() +" Time: "+timems+"ms");
        }
    }
}
