/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-16 16:33:07
 * @LastEditTime: 2020-06-16 16:50:06
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: \tacomall-springcloud\common\src\main\java\cn\codingtalk\tacomallcommon\enumeration\ExceptionEnum.java
 * @Just do what I think it is right
 */
package store.tacomall.common.enumeration;

public enum ExceptionEnum {

    SERVER_ERROR(4000, "服务器发送未知错误"), RPC_MEMBER_ERROR(4100, "微服务member发生错误"), RPC_OP_ERROR(4101, "微服务op发生错误");

    private int code;
    private String message;

    private ExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(int code) {
        for (ExceptionEnum c : ExceptionEnum.values()) {
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