package com.ruoyi.common.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

/**
 * json
 *
 * @author liuzhuoming
 */
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtil() {

    }

    public static <T> String toJSONString(T t) {
        try {
            return objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> String toPrettyJSONString(T t) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(t);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> List<T> toObjectList(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<T>>() {
            });
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static Map<String, Object> toMap(Object object) {
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(object), new TypeReference<Map<String, Object>>() {
            });
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
