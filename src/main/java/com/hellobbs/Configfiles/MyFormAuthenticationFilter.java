package com.hellobbs.Configfiles;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    //重构登录成功后跳转到指定的地方
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.getAndClearSavedRequest(request);
        WebUtils.redirectToSavedRequest(request, response, getSuccessUrl());
        //WebUtils.issueRedirect(request, response, "/ok");
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpSession getsession = ((HttpServletRequest) request).getSession();
        String captruecode = (String) getsession.getAttribute("captruecode");
        String verification = request.getParameter("captruecode");
        if (captruecode != null && verification != null) {
            if (!verification.contentEquals(captruecode)) {
                request.setAttribute("shiroLoginFailure", "UnknownAccountException");
            }
        }

        return super.onAccessDenied(request, response, mappedValue);
    }

}
