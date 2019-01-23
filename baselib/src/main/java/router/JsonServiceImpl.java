package router;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * 作者：liuhai
 * 时间：2019/1/22:14:09
 * 邮箱：185587041@qq.com
 * 说明：AROUTER传对象用GOSN来序列化
 */
@Route(path = "/service/json")
public class JsonServiceImpl implements SerializationService {

    private Gson mGson;

    @Override
    public void init(Context context) {
        mGson = new Gson();

    }

    @Override
    public <T> T json2Object(String text, Class<T> clazz) {
        checkJson();
        return mGson.fromJson(text, clazz);
    }

    @Override
    public String object2Json(Object instance) {
        checkJson();
        return mGson.toJson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        checkJson();
        return mGson.fromJson(input, clazz);
    }

    public void checkJson() {
        if (mGson == null) {
            mGson = new Gson();
        }
    }
}