package com.ason.db;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 自定义 mapper 父类
 */
public interface SuperMapper<T> extends BaseMapper<T> {

    // 这里可以写自己的公共方法、注意不要让 mp 扫描到误以为是实体 Super 的操作
}
