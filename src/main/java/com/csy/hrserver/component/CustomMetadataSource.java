package com.csy.hrserver.component;

import com.csy.hrserver.bean.Menu;
import com.csy.hrserver.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService menuService;
    AntPathMatcher antPathMatcher = new AntPathMatcher();


    /**
     * 根据当前路径中包含的路径与权限菜单路径对比，如果匹配上则选出该角色
     * @param o 包含了当前访问的路径
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Menu> allMenu = menuService.getAllMenu();
        for (Menu menu : allMenu) {
            if (antPathMatcher.match(menu.getUrl(), requestUrl) &&menu.getRoles().size()>0) {
                List<String>roles = menu.getRoles().stream().map(s->s.getName()).distinct().collect(Collectors.toList());
                String[] array = roles.toArray(new String[roles.size()]);
                return SecurityConfig.createList(array);
            }
        }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
