package org.saozquick.sample;

import org.saozquick.commom.BaseDto;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @ClassName: ApiService
 * @Description: java类作用描述
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public interface ApiService {

    /**
     * wan android 登录测试
     *
     * @param map 登录键值
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseDto<LoginDto>> login(@FieldMap Map<String, String> map);
}
