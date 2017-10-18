package com.ason.result;

/**
 * 返回码接口
 * Created by Ason on 2017/9/13.
 */
public interface ResponseInfo {
    /**
     * 获取返回码
     * @return
     */
    String getCode();

    /**
     * 获取返回信息
     * @return
     */
    String getMessage();
}
