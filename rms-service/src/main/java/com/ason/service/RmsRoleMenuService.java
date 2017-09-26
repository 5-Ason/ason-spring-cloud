package com.ason.service;


import com.ason.db.mapper.rms.RmsRoleMenuMapper;
import com.ason.entity.rms.po.RmsRoleMenu;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @since 2017-09-07
 */
@Service
public class RmsRoleMenuService extends ServiceImpl<RmsRoleMenuMapper, RmsRoleMenu> {
    private static final Log log = LogFactory.get();

    @Autowired
    private RmsRoleMenuMapper rmsRoleMenuMapper;
}
