package org.saozquick.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.saozquick.commom.Constants.Server.TIME_OUT;

/**
 * @ClassName: RequestRetrofit
 * @Description: java类作用描述
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public class RequestRetrofit {
    /**
     * okHttpClient
     */
    private static OkHttpClient okHttpClient;

    /**
     * Retrofit
     */
    private static Retrofit retrofit;

    /**
     * @param url 请求Base URL
     * @return ApiService
     */
    public static <T> T getInstance(Class<T> apiService, String url) {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            //添加日志拦截器
            builder.addInterceptor(interceptor);
            //设置服务器读超时时间
            builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
            //设置服务器链接超时时间
            builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
            //设置失败重连
            builder.retryOnConnectionFailure(true);
            //添加自定义拦截器
            //todo
            //build
            okHttpClient = builder.build();
        }

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit.create(apiService);
    }
}

