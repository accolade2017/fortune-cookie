package jp.co.accolade.ft;

import java.lang.reflect.Method;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.accolade.ft.annotations.NoAuthentication;

@Aspect
@Component
public class LoginStateAdvisor {
    /** セッション. */
    @Autowired
    HttpSession session;

    @Around("within(jp.co.accolade.ft.controller.*..*)")
    public Object log(ProceedingJoinPoint point) throws Throwable {
        MethodSignature ms = (MethodSignature)point.getSignature();
        Method m = ms.getMethod();
        if (m.isAnnotationPresent(NoAuthentication.class)) {
            return point.proceed();
        }
        String storedUserId = (String)session.getAttribute("userId");
        if (storedUserId == null || storedUserId.isEmpty()) {
            // TODO 本来はログイン画面に遷移する
            throw new RuntimeException("権限がありません.");
        }
        return point.proceed();
    }
}
