package com.worldline.formation.gatling.service.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by A501768 on 24/02/2016.
 */
@RestController
public class FiboController {

    private Map<Integer,Long> fibos = new HashMap<>();

    @RequestMapping("/fibo/{index}")
    String getFibo(@PathVariable int index) {
       return fibo(index)+"";
    }

    private long fibo(int value) {
        if (fibos.containsKey(value)) {
            return fibos.get(value);
        }
        if (value == 0 || value == 1) {
            return 1;
        } else {
            long result = fibo(value-1)+fibo(value-2);
            fibos.putIfAbsent(value,result);
            return result;
        }
    }
}
