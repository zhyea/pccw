package org.chobit.cm.spring;


import org.chobit.cm.common.model.ResultWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.chobit.cm.spring.ApiErrorCode.BAD_REQUEST;
import static org.chobit.cm.spring.ApiErrorCode.MISSING_PARAMETER;


/**
 * @author robin
 */
@ControllerAdvice
public class ApiExceptionAdvisor {


    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionAdvisor.class);



    /**
     * Api异常返回值处理
     *
     * @param e 异常信息
     * @return 封装后的异常返回值
     */
    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultWrapper<Object> apiExceptionHandler(ApiException e) {
        ResultWrapper<Object> r = new ResultWrapper<>(e.getErrorCode(), null);
        r.setMessage(e.getMessage());
        r.setCode(MISSING_PARAMETER.code);
        return r;
    }

    /**
     * Api异常返回值处理
     *
     * @param e 异常信息
     * @return 封装后的异常返回值
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultWrapper<Object> exceptionHandler(Exception e) {

        ResultWrapper<Object> r = new ResultWrapper<>(500, null);
        r.setMessage("内部异常");

        if (e instanceof MissingServletRequestParameterException) {
            String parameterName = ((MissingServletRequestParameterException) e).getParameterName();
            r.setCode(BAD_REQUEST.code);
            r.setMessage("请求参数错误，参数名:" + parameterName);
        }
        logger.error("发现内部异常：{}", r.getMessage(), e);
        return r;
    }

}
