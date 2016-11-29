//
//  main.c
//  SNAIL
//
//  Created by K on 2016. 11. 16..
//  Copyright © 2016년 K. All rights reserved.
//

#include <stdio.h>

#define MAX_CNT 1001

double C[MAX_CNT] = {1,};
double rain[MAX_CNT] = {1.0L,};
double norain[MAX_CNT] = {1.0L,};

int main(int argc, const char * argv[]) {
    
    FILE *fp;
    int test_cnt, m, n, i;
    double prob;
    
    //    fp = fopen("/Users/K/work/algorithm/MK/SNAIL/SNAIL/input.txt","r");
    fp = stdin;
    
    fscanf(fp, "%d ", &test_cnt);
    
    for(i = 1 ; i < MAX_CNT ; i++)
    {
        rain[i] = (rain[i-1] * 0.75);
        norain[i] = (norain[i-1] * 0.25);
    }
    //    printf("%.15Lf, %.15Lf\n", rain[100], norain[100]);
    while(test_cnt)
    {
        fscanf(fp, "%d %d ", &n, &m);
        C[0] = 1;
        
        prob = 0;
        
        if ( m > n ) prob = 1;
        else if ( (m*2) < n) prob = 0;
        else  {
            
            for(i = 0 ; i <= m ; i ++)
            {
                if(i!=0 && (i <= m/2))
                    C[i] = (C[i-1]  * (double)(m+1-i)/ (double)i);
                else if (i!=0)
                    
                    C[i] = C[m-i];
                if((i+m) >= n)
                    
                    prob += ((double)C[i] * rain[i] * norain[m-i]);
                
            }
            
        }
        printf("%.10f\n",prob);
        test_cnt--;
    }
    
    return 0;
}
