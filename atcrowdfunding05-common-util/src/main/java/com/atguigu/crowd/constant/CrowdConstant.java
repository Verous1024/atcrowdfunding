package com.atguigu.crowd.constant;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-16 上午 09:30
 */

/**
 *声明一个类存放常量，防止写错
 */
public class CrowdConstant {

    public static final String MESSAGE_LOGIN_FAILED = "抱歉！密码错误！请重新输入密码！";
    public static final String MESSAGE_LOGIN_FAILED_WITHOUT_ACCT = "抱歉！用户不存在！";
    public static final String MESSAGE_LOGIN_ACCT_ALREADY_IN_USE = "抱歉！这个账号已经被使用了!";
    public static final String MESSAGE_ACCESS_FORBIDEN = "请登陆以后再访问！";
    public static final String MESSAGE_STRING_INVALIDATE = "请不要传入空字符串！";

    public static final String ATTR_NAME_EXCEPTION = "exception";
    public static final String ATTR_NAME_LOGIN_ADMIN = "loginAdmin";
    public static final String MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE = "账号异常！账号非唯一账号";
    public static final String ATTR_NAME_PAGE_INFO = "pageInfo";
    public static final String ATTR_NAME_TRY_LOGIN_ACCT = "tryLoginAcct";
    public static final String ATTR_NAME_TRY_USER_PSWD = "tryUserPswd";
    public static final String MESSAGE_ACCESS_DENIED = "抱歉！您无权访问这些资源！请联系管理员授予权限";
}
