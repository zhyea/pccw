package org.chobit.cm.spring;

import org.chobit.cm.common.model.ResultWrapper;
import org.chobit.common.utils.JsonKit;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;


/**
 * @author robin
 */
@RestControllerAdvice
public class ResponseWrapperBodyAdvice implements ResponseBodyAdvice<Object> {


    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseWrapperBody.class;


    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE) || returnType.hasMethodAnnotation(ANNOTATION_TYPE);
    }


    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body instanceof ResultWrapper) {
            return body;
        }
        if (selectedConverterType.equals(StringHttpMessageConverter.class)) {
            return JsonKit.toJson(new ResultWrapper<>(body));
        }
        return new ResultWrapper<>(body);
    }
}
