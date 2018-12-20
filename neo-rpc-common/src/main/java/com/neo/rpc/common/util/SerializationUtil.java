package com.neo.rpc.common.util;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: cp.Chen
 * @Date: 2018/12/20 15:24
 * @Description: 序列化工具类（基于 Protostuff 实现）
 */
public class SerializationUtil {
    private static final Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();
    private static final Objenesis objenesis = new ObjenesisStd(true);

    /**
     * 序列化（对象 -> 字节数组）
     * @param obj 需要序列化的对象
     * @param <T> 对象类型
     * @return
     */
    public static <T> byte[] serialize(T obj) {
        Class<T> cls = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(cls);
            byte[] b = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
            return b;
        }catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化（字节数组 -> 对象）
     * @param data 需要反序列化的字节数组
     * @param cls 目标类型的实例
     * @param <T> 目标类型
     * @return
     */
    public static <T> T deserialize(byte[] data, Class<T> cls) {
        try {
            T message = objenesis.newInstance(cls);
            Schema<T> schema = getSchema(cls);
            ProtostuffIOUtil.mergeFrom(data, message, schema);
            return message;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private static <T> Schema<T> getSchema(Class<T> cls) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(cls);
            cachedSchema.put(cls, schema);
        }
        return schema;
    }
}
