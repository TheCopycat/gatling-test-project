package com.worldline.formation.gatling.service;

/**
 * Created by a501768 on 20/05/2016.
 */
public class Sleep {
    private Long sleepTime;

    public Sleep(Long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public Long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Long sleepTime) {
        this.sleepTime = sleepTime;
    }
}
