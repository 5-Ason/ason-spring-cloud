package com.ason.shiro;

import com.ason.entity.rms.po.RmsRole;
import com.ason.entity.rms.po.RmsUser;
import com.ason.service.RmsMenuService;
import com.ason.service.RmsRoleService;
import com.ason.service.RmsUserService;
import com.ason.utils.BlankUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by Ason on 2017/9/8.
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private RmsUserService rmsUserService;

    @Autowired
    private RmsRoleService rmsRoleService;

    @Autowired
    private RmsMenuService rmsMenuService;

    /**
     * 授权(验证权限时调用)
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     *
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.err.println("===========进入doGetAuthorizationInfo==========");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //从principalCollection获取主身份信息
        //将getPrimaryPrincipal()返回的值强制转换为真实身份信息【在doGetAuthenticationInfo()认证通过填充到SimpleAuthenticationInfo中的身份信息】
        String account = (String) principalCollection.getPrimaryPrincipal();
        // TODO: 2017/9/15 查询待优化
        RmsUser rmsUser = rmsUserService.selectOne(new EntityWrapper().where("account = {0}", account));
        RmsRole rmsRole = rmsRoleService.selectById(rmsUser.getRoleId());
        // 用户拥有的角色
        authorizationInfo.addRole(rmsRole.getName());
        // 用户拥有的权限
        authorizationInfo.addStringPermission("user:list");
        authorizationInfo.addStringPermission("user:add");
        return authorizationInfo;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户的输入的账号.
        String account = (String) authenticationToken.getPrincipal();
        //查询用户信息
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        RmsUser rmsUser = rmsUserService.selectOne(new EntityWrapper().where("account = {0}", account));

        if (!BlankUtil.isBlank(rmsUser)) { //账号存在
           return new SimpleAuthenticationInfo(account, rmsUser.getPassword(), rmsUser.getName());
        }
        return null;
    }
}
