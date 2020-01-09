package com.data.masterandslave.util;

/**
 * <p>Copyright © JinNuoFeng Network Technology Co.,Ltd.</p>
 * RESTful API 统一响应体的状态码
 * @author Alan on 2017/11/1.
 */
public class ResultCode {
    /**
     * 成功
     */
    public static final int SUCCESS = 200;
    /**
     * 失败
     */
    public static final int FAILED = 400;
    /**
     * 服务端异常
     */
    public static final int SERVER_ERROR = 500;

    /**
     * 没有登录系统
     */
    public static final int USE_FORBIDDEN = 401;
    /**
     * 没有当前系统权限
     */
    public static final int SYS_FORBIDDEN = 403;
    /**
     * 没有菜单权限
     */
    public static final int MENU_FORBIDDEN = 404;
    /**
     * 没有数据权限
     */
    public static final int DATA_FORBIDDEN = 405;
    /**
     * 信息错误或不匹配
     */
    public static final int ERROR_FORBIDDEN = 406;
}
