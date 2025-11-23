package com.example.jerry.global.config;

import com.example.jerry.domain.exception.PagingException;
import com.example.jerry.domain.exception.code.PagingErrorCode;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.annotation.*;
import java.util.List;

@Configuration
public class PageableConfig implements WebMvcConfigurer {

    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface PageableFromOne {}

    @Component
    public static class PageableFromOneResolver implements HandlerMethodArgumentResolver {

        @Override
        public boolean supportsParameter(MethodParameter parameter) {
            return parameter.hasParameterAnnotation(PageableFromOne.class)
                    && Pageable.class.isAssignableFrom(parameter.getParameterType());
        }

        @Override
        public Object resolveArgument(
                MethodParameter parameter,
                ModelAndViewContainer mavContainer,
                NativeWebRequest webRequest,
                WebDataBinderFactory binderFactory
        ) {

            String pageParam = webRequest.getParameter("page");
            int page;

            try {
                page = (pageParam == null) ? 1 : Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                throw new PagingException(PagingErrorCode.INVALID_PAGE);
            }

            if (page <= 0) {
                throw new PagingException(PagingErrorCode.INVALID_PAGE);
            }

            return PageRequest.of(page - 1, 10);
        }
    }

    private final PageableFromOneResolver resolver;

    public PageableConfig(PageableFromOneResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(resolver);
    }
}
