//
//  main.c
//  LIS
//
//  Created by K on 2016. 9. 1..
//  Copyright © 2016년 K. All rights reserved.
//

#include <stdio.h>
#define MAX_COUNT 501

int num[MAX_COUNT];
int num_cnt;
int prev[MAX_COUNT];
int cnt[MAX_COUNT];

void find_resolution();

int main(int argc, const char * argv[]) {
    
    FILE *fp;
    int test_cnt;
    int i, j;
    
    fp = fopen("/Users/K/work/algorithm/MK/LIS/LIS/input.txt","r");
   // fp = stdin;
    fscanf(fp, "%d ", &test_cnt);
    
    for ( i = 0 ; i < test_cnt ; i++)
    {
        fscanf(fp, "%d ", &num_cnt);
        for(j = 1 ; j <= num_cnt ; j++)
            fscanf(fp, "%d ", &(num[j]));
        
        find_resolution();
        printf("%d\n", cnt[num_cnt]);
    }
    fclose(fp);
    
    return 0;
    
}

void find_resolution()
{
    int i,j;
    for( i = 1 ; i <= num_cnt ; i++)
    {
        int max_cnt = 0, max_idx = 0;
        for(j = i ; j > 0 ; j--)
        {
            if((num[j] < num[i]) && (cnt[j] > max_cnt))
            {
                max_idx = j;
                max_cnt = cnt[j];
            }
        }
        if(max_cnt == 0)
        {
            cnt[i] = 1;
            prev[i] = 0;
        }
        else
        {
            cnt[i] = max_cnt+1;
            prev[i] = max_idx;
        }
      //  printf("[%d] num: %d, prev : %d, count : %d \n",i,num[i],prev[i], cnt[i]);
        
    }
}