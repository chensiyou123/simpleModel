package com.csy.hrserver.mapper;

import com.csy.hrserver.bean.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by sang on 2017/12/28.
 */
@Mapper
public interface MenuMapper {
    /**
     * 条件查询菜单
     * @param map
     * @return
     */
    List<Menu>query(Map map);

}
