package com.csy.hrserver.service;

import com.csy.hrserver.bean.Menu;
import com.csy.hrserver.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    public List<Menu>getAllMenu(){
        Map map = new HashMap();
       return menuMapper.query(map);
    }
}
