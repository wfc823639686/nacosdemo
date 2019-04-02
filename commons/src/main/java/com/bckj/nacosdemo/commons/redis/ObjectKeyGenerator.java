package com.bckj.nacosdemo.commons.redis;

import com.bckj.nacosdemo.commons.util.EncryptUtils;
import com.bckj.nacosdemo.commons.util.JSONUtils;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

public class ObjectKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object o, Method method, Object... objects) {
        if (objects != null && objects.length > 0)
            return o.getClass().getSimpleName().toLowerCase()
                    + ":" + method.getName().toLowerCase()
                    + ":" + EncryptUtils.MD5(JSONUtils.toJson(objects[0]));
        return method.getName().toLowerCase();
    }
}
