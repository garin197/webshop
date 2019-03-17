package com.github.webshop.util;

import java.util.HashMap;
import java.util.Map;

public class HashMapUtil {
    public static Map<String, Object> getFormatMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", "");
        map.put("count", 1000);
        return map;
    }

    public static Map<String, Object> getFormatMap(String massage) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", massage);
        map.put("count", 1000);
        return map;
    }

    public static Map<String, Object> getFormatMap(Integer count) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", "");
        map.put("count", count);
        return map;
    }

    public static Map<String, Object> getFormatMap(String massage, Integer count) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", massage);
        map.put("count", count);
        return map;
    }

    public static Map<String, Object> getFormatMap(Integer code, String massage, Integer count) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("message", massage);
        map.put("count", count);
        return map;
    }

}
