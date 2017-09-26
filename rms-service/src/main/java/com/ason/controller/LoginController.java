package com.ason.controller;

import com.ason.core.exception.RmsException;
import com.ason.core.exception.RmsExceptionEnum;
import com.ason.result.ResultBody;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ason on 2017/9/8.
 */
@RestController
public class LoginController {

    private static final Log log = LogFactory.get();

    /**
     * 登录页面
     * @return
     */
    @GetMapping(value = "/login")
    public String login() {
        return "进入登录页面";
    }

    /**
     * ajax登录请求
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public String submitLogin(@RequestParam(value = "account") String account,
                              @RequestParam(value = "password") String password,
                              @RequestParam(value = "remember", required = false, defaultValue = "off") String remember) {
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        if ("on".equals(remember)) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }
        //获取当前的Subject
        Subject currentSubject = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到ShiroRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            currentSubject.login(token);
        } catch (UnknownAccountException uae) {
            throw new RmsException(RmsExceptionEnum.USER_NOT_EXISTED);
        } catch (IncorrectCredentialsException ice) {
            throw new RmsException(RmsExceptionEnum.USER_PWD_ERROR);
        } catch (LockedAccountException lae) {
            log.error("LockedAccountException验证未通过,账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            log.info("对用户[" + account + "]进行登录验证..验证未通过,错误次数过多");
        }
        if (currentSubject.isAuthenticated()) {
            log.info("用户[" + account + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            //System.out.println(currentSubject.getPrincipal());
            return ResultBody.success(token);
        }
        return null;
    }

    /**
     * 退出
     * @return
     */
    @GetMapping(value = "/logout")
    public void logout() {
        //退出
        SecurityUtils.getSubject().logout();
    }
}
