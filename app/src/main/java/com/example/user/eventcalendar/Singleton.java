package com.example.user.eventcalendar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 23.01.2017.
 */

public class Singleton {

    private List<EventModel> models;
    private static Singleton instance = null;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if(instance == null)
            instance = new Singleton();

        return instance;
    }

    public List<EventModel> getModels() {
        if(models == null)
            models = new ArrayList<>();
        return models;
    }

    public void setModels(List<EventModel> models) {
        this.models = models;
    }
}
