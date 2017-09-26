package com.ason.service;


import com.ason.db.mapper.rms.RmsRoleMapper;
import com.ason.entity.rms.po.RmsRole;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @since 2017-09-07
 */
@Service
public class RmsRoleService extends ServiceImpl<RmsRoleMapper, RmsRole> {
    private static final Log log = LogFactory.get();

    @Autowired
    private RmsRoleMapper rmsRoleMapper;
}
