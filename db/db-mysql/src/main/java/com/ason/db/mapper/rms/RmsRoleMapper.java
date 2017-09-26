package com.ason.db.mapper.rms;

import com.ason.entity.rms.po.RmsRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
  * 角色表 Mapper 接口
 * </p>
 *
 * @since 2017-09-07
 */
@Mapper
@Component
public interface RmsRoleMapper extends BaseMapper<RmsRole> {

}
