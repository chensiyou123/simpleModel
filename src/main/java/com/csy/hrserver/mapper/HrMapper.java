package com.csy.hrserver.mapper;

import com.csy.hrserver.bean.Hr;
import com.csy.hrserver.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by sang on 2017/12/28.
 */
@Mapper
public interface HrMapper {

    /**
     * 条件查询hr
     * @param map
     * @return
     */
    List<Hr>query(Map map);
}
