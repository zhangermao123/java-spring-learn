package com.zw.utils;

import com.google.common.collect.Lists;
import com.zw.contant.ParamKey;
import com.zw.entity.request.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.utils
 * @descripation TODO
 * @date 2021-06-15
 */
@Component
@Slf4j
public class RedisUtils {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * @description 设置redis
     * @param username
     * @param jwt
     * @param ttl
     * @return void
     */
    public void set(String username,String jwt,long ttl){
        stringRedisTemplate.opsForValue().set(ParamKey.REDIS_JWT_KEY_PREFIX+username,jwt,ttl, TimeUnit.MILLISECONDS);
    }

    /**
     * @description 判断是否存在缓存
     * @param username
     * @return boolean
     */
    public boolean ifNull(String username){
        String redisKey = ParamKey.REDIS_JWT_KEY_PREFIX + username;
        Long expire = stringRedisTemplate.getExpire(redisKey, TimeUnit.MILLISECONDS);
        if (Objects.isNull(expire) || expire <= 0) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * @description 通过key 获取value值
     * @param username key
     * @return java.lang.String
     */
    public String get(String username){
        if(ifNull(username)){
            return  null;
        }
        String redisKey = ParamKey.REDIS_JWT_KEY_PREFIX + username;
        return stringRedisTemplate.opsForValue().get(redisKey);
    }

    /**
     * @description 单次删除
     * @param username
     * @return boolean
     */
    public boolean delete(String username){
       return stringRedisTemplate.delete(ParamKey.REDIS_JWT_KEY_PREFIX + username);
    }

    /**
     * @description 批量删除
     * @param list
     * @return void
     */
    public void delete(Collection<String> list){
         stringRedisTemplate.delete(list);
    }

    /**
     * @description 分页获取指定格式key，使用 scan 命令代替 keys 命令，在大数据量的情况下可以提高查询效率
     *
     * @param patternKey  key格式
     * @param currentPage 当前页码
     * @param pageSize    每页条数
     * @return 分页获取指定格式key
     */
    public PageResult<String> findKeysForPage(String patternKey, int currentPage, int pageSize) {
        ScanOptions options = ScanOptions.scanOptions().match(patternKey).build();
        RedisConnectionFactory factory = stringRedisTemplate.getConnectionFactory();
        RedisConnection rc = factory.getConnection();
        Cursor<byte[]> cursor = rc.scan(options);

        List<String> result = Lists.newArrayList();

        long tmpIndex = 0;
        int startIndex = (currentPage - 1) * pageSize;
        int end = currentPage * pageSize;
        while (cursor.hasNext()) {
            String key = new String(cursor.next());
            if (tmpIndex >= startIndex && tmpIndex < end) {
                result.add(key);
            }
            tmpIndex++;
        }

        try {
            cursor.close();
            RedisConnectionUtils.releaseConnection(rc, factory);
        } catch (Exception e) {
            log.warn("Redis连接关闭异常，", e);
        }

        return new PageResult<>(result, tmpIndex);
    }
}
