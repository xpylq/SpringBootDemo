package yzh.spring.boot.config.excpetion;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by youzhihao on 16/5/2.
 * 这是自定义的一个日常处理类,统一处理controller抛出的异常信息
 * 使用@ControllerAdvice注解来用于指定处理某些controller,这里指定yzh.spring.boot.controller包下的所有controller类都处理
 */
@ControllerAdvice(basePackages = "yzh.spring.boot.controller")
public class CustomExceptionHandler extends ExceptionHandlerExceptionResolver {

    /**
     * @ExceptionHandler注解用来处理指定异常
     */
    @ExceptionHandler(value = RuntimeException.class)
    public String handleRuntimeException(HttpServletRequest request, Throwable ex) {
        System.out.println(ex.getMessage());
        return "/error/runtimeExcpetionPage.html";
    }

}
