package com.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    // setFruit?name=banana

    @GetMapping("/setFruit")
    public String setFruit(@RequestParam String name){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("fruit", name);

        return "saved.";
    }

    @GetMapping("/getFruit")
    public String getFruit(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String fruit = ops.get("fruit");

        return fruit;
    }
}
