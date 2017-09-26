package com.ason.db.mapper.rms;

import com.ason.entity.rms.po.RmsMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
  * 菜单表 Mapper 接口
 * </p>
 *
 * @since 2017-09-07
 */
@Mapper
@Component
public interface RmsMenuMapper extends BaseMapper<RmsMenu> {

}
