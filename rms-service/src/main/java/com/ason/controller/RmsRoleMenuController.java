package com.ason.controller;


import com.ason.service.RmsRoleMenuService;
import com.ason.service.RmsRoleService;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色权限表 前端控制器
 * </p>
 *
 * @since 2017-09-07
 */
@RestController
@RequestMapping("/rmsRoleMenu")
public class RmsRoleMenuController {
    private static final Log log = LogFactory.get();

    @Autowired
    private RmsRoleMenuService rmsRoleMenuService;
}
