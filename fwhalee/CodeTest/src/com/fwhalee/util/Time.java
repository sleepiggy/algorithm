package com.fwhalee.util;

public class Time {
    
    
    public static void checkRunningTime() {
        
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        System.out.println( "running time: " + ( endTime - startTime ) / 1000.0 );
        
    }
    
    
}
