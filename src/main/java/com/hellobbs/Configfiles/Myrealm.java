package com.hellobbs.Configfiles;

import com.hellobbs.database.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class Myrealm extends AuthorizingRealm {

    @Autowired
    SqlSession sqlSession;

    //验证登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        HttpServletRequest request =  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String exception = (String) request.getAttribute("shiroLoginFailure");
        if (exception != null) {
            //这段很关键
            return null;
        }


        String getUsername = (String) token.getPrincipal();
        User u = sqlSession.selectOne("getuser", getUsername);
        if (getUsername == null) {
            return null;

        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
        simpleAuthenticationInfo = new SimpleAuthenticationInfo(u, u.getPassword(),null,u.getUsername());
        return simpleAuthenticationInfo;

    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //单独增加权限
        //authorizationInfo.addRole("vip2");
        //authorizationInfo.addStringPermission("userInfo:Del");

        //User user = (User) principalCollection.getPrimaryPrincipal();
        //for (Sysrole role : user.getRoleList()) {
        //    simpleAuthorizationInfo.addRole(role.getRole());
        //    for (Syspermission p : role.getPermissions()) {
        //        simpleAuthorizationInfo.addStringPermission(p.getPermission());
        //    }
        //}


        return simpleAuthorizationInfo;
    }


}
