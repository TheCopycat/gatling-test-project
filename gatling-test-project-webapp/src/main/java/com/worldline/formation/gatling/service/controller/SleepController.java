package com.worldline.formation.gatling.service.controller;

import com.worldline.formation.gatling.service.Sleep;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by a501768 on 10/03/2016.
 */
@RestController
public class SleepController {

    @RequestMapping("/sleep/{sleep}")
    public Sleep sleep(@PathVariable long sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Sleep(sleep);
    }
}
