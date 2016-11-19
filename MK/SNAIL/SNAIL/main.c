//
//  main.c
//  SNAIL
//
//  Created by K on 2016. 11. 16..
//  Copyright © 2016년 K. All rights reserved.
//

#include <stdio.h>

#define MAX_CNT 1001

long C[MAX_CNT] = {1,};
long double rain[MAX_CNT] = {1.0L,};
long double norain[MAX_CNT] = {1.0L,};

int main(int argc, const char * argv[]) {
    
    FILE *fp;
    int test_cnt, m, n, i;
    long double prob;
    
        fp = fopen("/Users/K/work/algorithm/MK/SNAIL/SNAIL/input.txt","r");
//    fp = stdin;
    
    fscanf(fp, "%d ", &test_cnt);
    
    for(i = 1 ; i < MAX_CNT ; i++)
    {
        rain[i] = (rain[i-1] * 0.75L);
        norain[i] = (norain[i-1] * 0.25L);
    }
//    printf("%.15Lf, %.15Lf\n", rain[100], norain[100]);
    while(test_cnt)
    {
        fscanf(fp, "%d %d ", &n, &m);
        C[0] = 1;
        
        prob = 0.0L;
        
        for(i = 0 ; i <= m ; i ++)
        {
            if(i!=0) C[i] = (C[i-1] * (m+1-i) / i);
            if((i+m) >= n)
            {
                prob += ((long double)C[i] * rain[i] * norain[m-i]);
//                printf("rain[%d] = %.10Lf, norain[%d] = %.10Lf\n",i-1,rain[i-1], m-i, norain[m-i]);

            }
        }
        printf("%.10Lf\n",prob);
        test_cnt--;
    }

    return 0;
}
