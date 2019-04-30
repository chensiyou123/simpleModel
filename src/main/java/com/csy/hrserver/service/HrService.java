package com.csy.hrserver.service;

import com.csy.hrserver.bean.Hr;
import com.csy.hrserver.mapper.HrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class HrService implements UserDetailsService {

    @Autowired
    HrMapper hrMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Map map = new HashMap();
        map.put("username",s);
        List<Hr> hrs = hrMapper.query(map);
        if (CollectionUtils.isEmpty(hrs)) {
            return new Hr();//前后端分离这样处理方便点
            //throw new UsernameNotFoundException("用户名不对");
        }
        return hrs.get(0);
    }
}
