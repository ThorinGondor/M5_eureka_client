package com.hystrix_demo.eureka_client.controller;

import com.hystrix_demo.eureka_client.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloServiceController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        discoveryClient.getServices();
        return "Hello Eureka Client!";
    }

    /**
     * Modify: 以下三个方法为Feign消费者提供更多的服务，Feign消费者将会调用这些服务
     */
    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello(@RequestParam String name){
        return "Hello! "+name+"\n";
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public User hello(@RequestHeader String name, @RequestHeader Integer age){
        return new User(name, age);
    }

    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    public String hello(@RequestBody User user){
        return "\nHello "+user.getName()+", "+user.getAge();
    }
}
