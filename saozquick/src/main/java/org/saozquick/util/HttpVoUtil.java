package org.saozquick.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.saozquick.commom.BaseVo;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @ClassName: HttpUtil
 * @Description: 把vo转换成map
 * @Author: andjun
 * @CreateDate: 2020/5/9
 * @Version: 1.0
 */
public class HttpVoUtil {
    /**
     * vo转换为Map
     *
     * @param vo
     * @return
     */
    public static RequestBody convertVo2Json(BaseVo vo) {
        Map maps = convertVo2Params(vo);
        JSONObject object = new JSONObject(maps);
        return RequestBody.create(MediaType.parse("Content-Type, application/json"),
                "{\"data\":" + object.toString() + "}");
    }


    /**
     * vo转换为Map
     *
     * @param vo
     * @return
     */
    public static Map<String, String> convertVo2Params(BaseVo vo) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        return gson.fromJson(gson.toJson(vo), type);
    }

    /**
     * 多文件转换为 Map<String, RequestBody> bodyMap
     *
     * @param files
     * @param mediaType 文件类型
     * @return
     */
    public static Map<String, RequestBody> convertVo2BodyMap(List<File> files, String mediaType) {
        Map<String, RequestBody> bodyMap = new HashMap<>();
        for (int i = 0; i < files.size(); i++) {
            bodyMap.put("file" + i + "\"; filename=\"" + files.get(i).getName(), RequestBody.create(MediaType.parse(mediaType), files.get(i)));
        }
        return bodyMap;
    }
}
