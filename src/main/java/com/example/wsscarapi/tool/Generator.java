package com.example.wsscarapi.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class Generator {
    public UUID uuid(){
        UUID uuid = UUID.randomUUID();
        return uuid;
    }

    public String id(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
        return dateFormat.format(calendar.getTime())+(int)((Math.random()*9+1)*100000);
    }
}
