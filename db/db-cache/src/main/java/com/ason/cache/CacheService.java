package com.ason.cache;

import com.ason.db.mapper.rms.RmsUserMapper;
import com.ason.entity.rms.vo.RmsUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ason on 2017/9/27.
 */
@Service
public class CacheService {

    @Autowired
    private RmsUserMapper rmsUserMapper;

    @Cacheable(value = "usercache", key = "'selectUserById:id_'+#id")
    public RmsUserVo selectUserById(Integer id) {
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("id",id);
        RmsUserVo rmsUser = rmsUserMapper.selectOneUser(paramsMap);
        return rmsUser;
    }

    @Cacheable(value = "usercache", key = "'selectUserByAccout:account_'+#account")
    public RmsUserVo selectUserByAccout(String account) {
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("account",account);
        RmsUserVo rmsUser = rmsUserMapper.selectOneUser(paramsMap);
        return rmsUser;
    }
}
