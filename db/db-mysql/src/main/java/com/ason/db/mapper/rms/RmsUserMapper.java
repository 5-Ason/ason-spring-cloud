package com.ason.db.mapper.rms;

import com.ason.entity.rms.po.RmsUser;
import com.ason.entity.rms.vo.RmsUserVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
  * 用户表 Mapper 接口
 * </p>
 *
 * @since 2017-09-07
 */
@Mapper
@Component
public interface RmsUserMapper extends BaseMapper<RmsUser> {
    List<RmsUserVo> selectUserList(Pagination page);

    RmsUserVo selectOneUser(Map<String,Object> paramMap);
}
