package org.saozquick.net;

/**
 * @ClassName: ServerException
 * @Description: 基础服务器返回的Exception
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public class ServerException extends RuntimeException {
    private int code;
    private String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
