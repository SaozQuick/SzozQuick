package org.saozquick.net;

import android.net.ParseException;
import android.util.MalformedJsonException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * @ClassName: ExceptionEngine
 * @Description: 基础客户端报错控制
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public class ExceptionEngine {
    /**
     * 未知错误
     */
    public static final int UN_KNOWN_ERROR = 9000;
    /**
     * 解析(服务器)数据错误
     */
    public static final int ANALYTIC_SERVER_DATA_ERROR = 9001;
    /**
     * 解析(客户端)数据错误
     */
    public static final int ANALYTIC_CLIENT_DATA_ERROR = 9002;
    /**
     * 网络连接错误
     */
    public static final int CONNECT_ERROR = 9003;
    /**
     * 网络连接超时
     */
    public static final int TIME_OUT_ERROR = 9004;
    /**
     * 网络连接超时
     */
    public static final int UNKNOWNHOSTEXCEPTION = 9005;

    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {
            HttpException httpExc = (HttpException) e;
            ex = new ApiException(e, httpExc.code());
            //均视为网络错误
            ex.setMsg("网络错误，请稍后再试");
            return ex;
            //服务器返回的错误
        } else if (e instanceof ServerException) {
            ServerException serverExc = (ServerException) e;
            ex = new ApiException(serverExc, serverExc.getCode());
            ex.setMsg(serverExc.getMsg());
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                //解析数据错误
                || e instanceof ParseException || e instanceof MalformedJsonException) {
            ex = new ApiException(e, ANALYTIC_SERVER_DATA_ERROR);
            ex.setMsg("客户端异常，请稍后再试");
            return ex;
            //连接网络错误
        } else if (e instanceof ConnectException) {
            ex = new ApiException(e, CONNECT_ERROR);
            ex.setMsg("网络连接错误，请稍后再试");
            return ex;
            //网络超时
        } else if (e instanceof SocketTimeoutException) {
            ex = new ApiException(e, TIME_OUT_ERROR);
            ex.setMsg("网络连接超时，请稍后再试");
            return ex;
            //网络异常
        } else if (e instanceof UnknownHostException) {
            ex = new ApiException(e, UNKNOWNHOSTEXCEPTION);
            ex.setMsg("网络异常，请检查您的网络连接");
            return ex;
        } else {  //未知错误
            ex = new ApiException(e, UN_KNOWN_ERROR);
            ex.setMsg("系统异常，请稍后再试");
            return ex;
        }
    }
}
