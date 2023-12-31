package com.company.dinner.auth.controller.interceptor;

import com.company.dinner.auth.controller.support.AuthenticationContext;
import com.company.dinner.auth.controller.support.AuthenticationExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
public class ParseMemberIdFromTokenInterceptor implements HandlerInterceptor {

    private final LoginValidCheckerInterceptor loginValidCheckerInterceptor;
    private final AuthenticationContext authenticationContext;

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {
        if(AuthenticationExtractor.extract(request).isEmpty()) {
            authenticationContext.setAnonymous();
            return true;
        }

        return loginValidCheckerInterceptor.preHandle(request, response, handler);
    }
}
