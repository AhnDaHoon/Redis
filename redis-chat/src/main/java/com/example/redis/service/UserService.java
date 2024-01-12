package com.example.redis.service;

import com.example.redis.dto.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private ExternalApiService externalApiService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public UserProfile getUserProfile(String userId){
        String userName = null;

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String cachedName = ops.get("nameKey:" + userId);

        // 캐시에 있으면 캐시에서 가져오고 없으면 DB에서 조회
        if(!ObjectUtils.isEmpty(cachedName)){
            userName = cachedName;
        }else{
            userName = externalApiService.getUserName(userId);
            ops.set("nameKey:" + userId, userName, 5, TimeUnit.SECONDS);
        }

        int age = externalApiService.getUserAge(userId);

        return new UserProfile(userName, age);
    }
}
