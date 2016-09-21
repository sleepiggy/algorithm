package com.fwhalee.util;

import java.text.DecimalFormat;

public class Memory {
    
    
    static Runtime r = Runtime.getRuntime();
    
    
    /**
     * show memory state
     *
     * @return
     *
     * @modified 2016. 8. 5. by kbg0128
     */
    public static void showMemory() {
        // 메소드가 static으로 선언해야되는 이유는?
        
        DecimalFormat format = new DecimalFormat("###,###,###.##");
        
        //JVM이 현재 시스템에 요구 가능한 최대 메모리량, 이 값을 넘으면 OutOfMemory 오류가 발생 합니다.
        long max = r.maxMemory();
        
        //JVM이 현재 시스템에 얻어 쓴 메모리의 총량
        long total = r.totalMemory();
        
        //JVM이 현재 시스템에 청구하여 사용중인 최대 메모리(total)중에서 사용 가능한 메모리
        long free = r.freeMemory();
        
        System.out.println("Max:" + format.format(max) + ", Total:" + format.format(total) +
                ", Free:"+format.format(free) + ", Use:" + (format.format(total - free)));
        
    }
    
    
}
