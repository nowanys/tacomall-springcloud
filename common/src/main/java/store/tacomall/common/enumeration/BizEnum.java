/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-16 16:32:58
 * @LastEditTime: 2020-06-16 16:43:12
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: \tacomall-springcloud\common\src\main\java\cn\codingtalk\tacomallcommon\enumeration\BizEnum.java
 * @Just do what I think it is right
 */
package store.tacomall.common.enumeration;

public enum BizEnum {
    OK(2000, "正确响应"), FALSE(2001, "服务异常"), USER_NOT_EXIST(2100, "用户不存在"), USER_NOT_LOGGED_IN(2101, "用户未登陆");

    private int code;
    private String message;

    private BizEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(int code) {
        for (BizEnum c : BizEnum.values()) {
            if (c.getCode() == code) {
                return c.message;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
