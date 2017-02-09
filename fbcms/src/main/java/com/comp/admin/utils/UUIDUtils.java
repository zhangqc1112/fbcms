package com.comp.admin.utils;

import java.util.UUID;

/**
 * Created by Tonny on 2017/2/7.
 */
public class UUIDUtils {

    public static String createUserId(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-","").toUpperCase();
    }


    public static void main(String args[]){
        System.out.print(createUserId());
    }
}
