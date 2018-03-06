package com.example.tony.myapplication;

import java.util.HashMap;

/**
 * Created by Tony on 3/3/2018.
 */

public class Infor {
    public static HashMap<Integer, String> infor = new HashMap<Integer, String>();

    public static void init() {
        for(int i = 1; i < 9; i++) {
            if(Infor.infor.get(i) == null)
                Infor.infor.put(i, "Notes"+i+": ");
        }
    }

}
