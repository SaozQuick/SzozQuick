package org.saozquick.sample;

import java.util.List;

/**
 * @ClassName: LoginDto
 * @Description: LoginDto
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public class LoginDto {
    /**
     * data : {"admin":false,"chapterTops":[],"collectIds":[],"email":"","icon":"","id":54993,"nickname":"andjun","password":"","publicName":"andjun","token":"","type":0,"username":"andjun"}
     * errorCode : 0
     * errorMsg :
     */
    @Override
    public String toString() {
        return "WanLoginDto{" +
                "admin=" + admin +
                ", email='" + email + '\'' +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", publicName='" + publicName + '\'' +
                ", token='" + token + '\'' +
                ", type=" + type +
                ", username='" + username + '\'' +
                ", chapterTops=" + chapterTops +
                ", collectIds=" + collectIds +
                '}';
    }

    /**
     * admin : false
     * chapterTops : []
     * collectIds : []
     * email :
     * icon :
     * id : 54993
     * nickname : andjun
     * password :
     * publicName : andjun
     * token :
     * type : 0
     * username : andjun
     */

    private boolean admin;
    private String email;
    private String icon;
    private int id;
    private String nickname;
    private String password;
    private String publicName;
    private String token;
    private int type;
    private String username;
    private List<?> chapterTops;
    private List<?> collectIds;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<?> getChapterTops() {
        return chapterTops;
    }

    public void setChapterTops(List<?> chapterTops) {
        this.chapterTops = chapterTops;
    }

    public List<?> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<?> collectIds) {
        this.collectIds = collectIds;
    }
}

/**
 * { "data": { "admin": false, "chapterTops": [], "collectIds": [], "email": "", "icon": "", "id": 54993, "nickname": "andjun", "password": "", "publicName": "andjun", "token": "", "type": 0, "username": "andjun" }, "errorCode": 0, "errorMsg": "" }
 * <p>
 * { "data": null, "errorCode": -1, "errorMsg": "账号密码不匹配！" }
 * <p>
 * { "data": null, "errorCode": -1, "errorMsg": "账号密码不匹配！" }
 * <p>
 * { "data": null, "errorCode": -1, "errorMsg": "账号密码不匹配！" }
 * <p>
 * { "data": null, "errorCode": -1, "errorMsg": "账号密码不匹配！" }
 */

/**
 * { "data": null, "errorCode": -1, "errorMsg": "账号密码不匹配！" }
 */
