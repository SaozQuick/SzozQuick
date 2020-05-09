package org.saozquick.sample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import org.saozquick.commom.BaseDto;
import org.saozquick.viewmodel.BaseViewModel;

import kotlin.Function;

/**
 * @ClassName: LoginViewModel
 * @Description: LoginViewModel
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public class LoginViewModel extends BaseViewModel {

    /**
     * Home 仓库层对象
     */
    private LoginRepo loginRepo;

    public LoginViewModel() {
        this.loginRepo = new LoginRepo(this);
    }

    /**
     * 发起登录
     *
     * @param username 用户名
     * @param password 用户密码
     */
    public void doLogin(String username, String password) {
        LoginVo vo = new LoginVo();
        vo.setPassword(password);
        vo.setUsername(username);
        loginRepo.login(vo);
    }

    /**
     * 获取登录结果
     */
    public LiveData<BaseDto<LoginDto>> getLoginResult() {
        return loginRepo.getLoginResult();
    }

}
