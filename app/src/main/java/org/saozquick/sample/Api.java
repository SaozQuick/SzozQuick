package org.saozquick.sample;

import org.saozquick.commom.BaseDto;
import org.saozquick.net.RequestRetrofit;
import org.saozquick.util.HttpVoUtil;

import io.reactivex.Observable;

/**
 * @ClassName: Api
 * @Description: java类作用描述
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public class Api {

    public static final String WAN_ROOT_URL = "https://www.wanandroid.com/";

    /**
     * 登录接口
     *
     * @param vo 登录实体
     * @return 返回 Observable
     */

    public static Observable<BaseDto<LoginDto>> login(LoginVo vo) {
        return RequestRetrofit.getInstance(ApiService.class, WAN_ROOT_URL).login(HttpVoUtil.convertVo2Params(vo));
    }

}
