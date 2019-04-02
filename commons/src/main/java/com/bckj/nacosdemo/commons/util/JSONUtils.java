package com.bckj.nacosdemo.commons.util;

import com.bckj.nacosdemo.commons.json.CustomObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {
    private static ObjectMapper objectMapper;

    static {
        init();
    }

    private static void init() {
        if (objectMapper == null)
            objectMapper = new CustomObjectMapper();
    }

    /**
     * 对象转json
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * json 转对象
     *
     * @param json
     * @param c
     * @return
     * @throws Exception
     */
    public static <T> T toObject(String json, Class c) throws Exception {
        return (T) objectMapper.readValue(json, c);
    }

}
