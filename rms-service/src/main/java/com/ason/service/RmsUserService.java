package com.ason.service;

import com.ason.cache.CacheService;
import com.ason.core.exception.RmsException;
import com.ason.core.exception.RmsExceptionEnum;
import com.ason.db.mapper.rms.RmsUserMapper;
import com.ason.entity.rms.po.RmsUser;
import com.ason.entity.rms.vo.RmsUserVo;
import com.ason.enums.RmsUserSexEnum;
import com.ason.enums.RmsUserStatusEnum;
import com.ason.utils.BlankUtil;
import com.ason.utils.TimeUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.date.DatePattern;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @since 2017-09-07
 */
@Service
public class RmsUserService extends ServiceImpl<RmsUserMapper, RmsUser> {
    private static final Log log = LogFactory.get();

    @Autowired
    private RmsUserMapper rmsUserMapper;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private SqlSessionFactory factory;

    /**
     * 查询用户信息
     */
    public Page<Map<String, Object>> selectUserList(Integer currentPage, Integer showCount) {
        Page<Map<String, Object>> page = new Page<>(currentPage, showCount);
        List<Map<String, Object>> rmsUserList = new ArrayList<>();
        for (RmsUserVo rmsUserVo : rmsUserMapper.selectUserList(page)) {
            Map<String, Object> ruv = new HashMap();
            ruv.put("id", rmsUserVo.getId());
            ruv.put("name", rmsUserVo.getName());
            ruv.put("account", rmsUserVo.getAccount());
            ruv.put("password", rmsUserVo.getPassword());
            ruv.put("phone", rmsUserVo.getPhone());
            ruv.put("birthday", rmsUserVo.getBirthday());
            ruv.put("sex", RmsUserSexEnum.getByStatus(rmsUserVo.getSex()).getRemark());
            ruv.put("status", RmsUserStatusEnum.getByStatus(rmsUserVo.getStatus()).getRemark());
            ruv.put("createTime", TimeUtil.getDateByTimestamp(rmsUserVo.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN));
            ruv.put("createUserName", rmsUserVo.getCreateUserName());
            ruv.put("deptName", rmsUserVo.getDeptName());
            ruv.put("roleName", rmsUserVo.getRoleName());
            rmsUserList.add(ruv);
        }
        page.setRecords(rmsUserList);
        return page;

    }

    /**
     * 添加/修改用户
     */
    public void addOrUpdateUser(RmsUser rmsUser) {
        // 添加
        if (BlankUtil.isBlank(rmsUser.getId())) {
            // 判断账号是否重复
            RmsUser theUser = selectOne(new EntityWrapper().where("account = {0}", rmsUser.getAccount()));
            if (!BlankUtil.isBlank(theUser)) {
                throw new RmsException(RmsExceptionEnum.USER_ALREADY_REG);
            } else {
                rmsUser.setCreateTime(TimeUtil.getSecondTimestamp());
                rmsUser.setStatus(RmsUserStatusEnum.OPEN.getStatus());
                insert(rmsUser);
            }
        } else { //修改
            updateById(rmsUser);
        }
    }

    /**
     * mybatis的一级进行测试，只查询了一次
     * @param id
     * @return
     */
    public RmsUserVo selectUserById(Integer id) {
        // 自动提交事务
        SqlSession sqlSession = factory.openSession(true);
        RmsUserMapper rmsUserMapper = sqlSession.getMapper(RmsUserMapper.class);
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("id",id);
        RmsUserVo rmsUser = rmsUserMapper.selectOneUser(paramsMap);
        rmsUserMapper.selectOneUser(paramsMap);
        rmsUserMapper.selectOneUser(paramsMap);
        rmsUserMapper.selectOneUser(paramsMap);
        return rmsUser;
//        return cacheService.selectUserById(id);
    }

    public RmsUserVo selectUserByAccout(String account) {
        return cacheService.selectUserByAccout(account);
    }
}














