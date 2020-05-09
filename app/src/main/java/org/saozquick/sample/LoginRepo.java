package org.saozquick.sample;

import androidx.lifecycle.MutableLiveData;

import org.saozquick.commom.BaseDto;
import org.saozquick.commom.IBaseViewModelEvent;
import org.saozquick.repo.BaseRepository;

/**
 * @ClassName: LoginRepo
 * @Description: LoginRepo
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public class LoginRepo extends BaseRepository<LoginDto> {

    public LoginRepo(IBaseViewModelEvent modelEvent) {
        super(modelEvent);
    }

    private MutableLiveData<BaseDto<LoginDto>> loginResult = new MutableLiveData<>();

    public MutableLiveData<BaseDto<LoginDto>> getLoginResult() {
        return loginResult;
    }

    /**
     * 登录
     */
    public void login(LoginVo loginVo) {
        loginResult = request(Api.login(loginVo)).send().get();
    }
}
