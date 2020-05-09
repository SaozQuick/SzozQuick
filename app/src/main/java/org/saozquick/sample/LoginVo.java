package org.saozquick.sample;

import org.saozquick.commom.BaseVo;

/**
 * @ClassName: LoginVo
 * @Description: 登陆vo
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public class LoginVo extends BaseVo {

    private String password;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
