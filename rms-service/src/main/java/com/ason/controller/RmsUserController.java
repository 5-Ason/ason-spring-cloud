package com.ason.controller;

import com.ason.RedisService;
import com.ason.cache.CacheService;
import com.ason.constant.ApiConstant;
import com.ason.entity.rms.po.RmsUser;
import com.ason.result.ResultBody;
import com.ason.service.RmsUserService;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @since 2017-09-07
 */
@RestController
@RequestMapping(ApiConstant.RMS_USER)
public class RmsUserController {
    private static final Log log = LogFactory.get();

    @Autowired
    private RmsUserService rmsUserService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private CacheService cacheService;

    /**
     * 添加用户
     */
    @PostMapping(value = "", produces = "application/json;charset=UTF-8")
    @RequiresPermissions("user:add")
    public String addUser(@Valid RmsUser rmsUser, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return ResultBody.error(bindingResult.getFieldError().getDefaultMessage());
        }
        rmsUserService.addOrUpdateUser(rmsUser);
        return ResultBody.success();
    }

    /**
     * 获取用户列表
     */
    @GetMapping(value = "", produces = "application/json;charset=UTF-8")
    @RequiresPermissions("user:list")
    public String findAllUser(
            @RequestParam(value = "showCount", required = false, defaultValue = "10") Integer showCount,
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage) throws Exception {
        return ResultBody.success(rmsUserService.selectUserList(currentPage, showCount));
    }

    /**
     * 修改用户
     */
    @PutMapping(value = "", produces = "application/json;charset=UTF-8")
    public String updateUserById(@Valid @RequestBody RmsUser rmsUser, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return ResultBody.error(bindingResult.getFieldError().getDefaultMessage());
        }
        rmsUserService.addOrUpdateUser(rmsUser);
        return ResultBody.success();
    }

    /**
     * 查询单个用户
     */
    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public String findUserById(@PathVariable("id") Integer id) throws Exception {
        return ResultBody.success(rmsUserService.selectUserById(id));
    }

    /**
     * 查询单个用户
     */
    @PostMapping(value = "/account", produces = "application/json;charset=UTF-8")
    public String findUserByAccout(@RequestParam("account") String account) throws Exception {
        return ResultBody.success(rmsUserService.selectUserByAccout(account));
    }

    /**
     * 删除用户
     */
    @DeleteMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public String deleteUserById(@PathVariable("id") Integer id) throws Exception {
        if (rmsUserService.deleteById(id)) {
            return ResultBody.success();
        }
        return ResultBody.error();
    }

    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
    public String redisTest(){
        StringBuffer sb = new StringBuffer();
        redisService.set("str", "str");
        sb.append("str=").append(redisService.get("str").toString()).append(",");
        redisService.hmSet("hmset","key","val");
        sb.append("hmset=").append(redisService.hmGet("hmset","key")).append(",");
        redisService.lPush("list","val");
        sb.append("list=").append(redisService.lRange("list",0,1).toString()).append(",");
        redisService.add("set","val");
        sb.append("set=").append(redisService.setMembers("set").toString()).append(",");
        redisService.zAdd("zset","val1",1);
        redisService.zAdd("zset","val2",2);
        sb.append("zset=").append(redisService.rangeByScore("zset",1,2)).append(",");
        return sb.toString();
    }
}
