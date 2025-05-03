package com.ruoyi.common.core.domain;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.utils.StringUtils;
import lombok.Data;

/**
 * 操作消息提醒
 *
 * @author liuzhuoming
 */
@Data
public class AjaxResultT<T> {
    private static final long serialVersionUID = 1L;
    /**
     * 状态码
     */
    private int code;
    /**
     * 返回内容
     */
    private String msg;
    /**
     * 数据对象
     */
    private T data;

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    private AjaxResultT() {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public AjaxResultT(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public AjaxResultT(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        if (StringUtils.isNotNull(data)) {
            this.data = data;
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static <T> AjaxResultT<T> success() {
        return AjaxResultT.success("操作成功", null);
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static <T> AjaxResultT<T> success(T data) {
        return AjaxResultT.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static <T> AjaxResultT<T> success(String msg, T data) {
        return new AjaxResultT<>(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static <T> AjaxResultT<T> error() {
        return AjaxResultT.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static <T> AjaxResultT<T> error(String msg) {
        return AjaxResultT.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static <T> AjaxResultT<T> error(String msg, T data) {
        return new AjaxResultT<>(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static <T> AjaxResultT<T> error(int code, String msg) {
        return new AjaxResultT<>(code, msg, null);
    }

}
