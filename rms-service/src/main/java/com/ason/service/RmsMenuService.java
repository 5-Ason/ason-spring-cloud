package com.ason.service;


import com.ason.db.mapper.rms.RmsMenuMapper;
import com.ason.entity.rms.po.RmsMenu;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @since 2017-09-07
 */
@Service
public class RmsMenuService extends ServiceImpl<RmsMenuMapper, RmsMenu> {
    private static final Log log = LogFactory.get();

    @Autowired
    private RmsMenuMapper rmsMenuMapper;
}
