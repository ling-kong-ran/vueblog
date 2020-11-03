package com.ling.vblog.exception;

import com.ling.vblog.entity.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)//未授权
    @ExceptionHandler(value = ShiroException.class)
    public AjaxResult handler(ShiroException e){
        log.error("运行时异常-----------------------[{}]",e);
        e.printStackTrace();
        return AjaxResult.error(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AjaxResult handler(MethodArgumentNotValidException e){
        e.printStackTrace();
        log.error("实体校验异常-----------------------[{}]",e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();

        return AjaxResult.error(objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public AjaxResult handler(RuntimeException e){
        e.printStackTrace();
        log.error("运行时异常-----------------------[{}]",e);
        return AjaxResult.error(e.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public AjaxResult handler(IllegalArgumentException e){
        e.printStackTrace();
        log.error("Assert异常-----------------------[{}]",e);
        return AjaxResult.error(e.getMessage());
    }
}
