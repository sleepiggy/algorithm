//
//  main.c
//  QUANTIZE
//
//  Created by K on 2016. 8. 26..
//  Copyright © 2016년 K. All rights reserved.
//

#include <stdio.h>

#define ABSOLUTE(a) (((a) >= 0 )? (a) : -(a))

int num[100];
int diff[1001][1001];
int quantum[10];

int find_min_diff_sum(int level);
int min_num, max_num;
int calc_diff_sum();

int N,S;


int main(int argc, const char * argv[]) {
    
    FILE *fp;
    int test_cnt;
    int i, j;
    
    fp = fopen("/Users/K/work/algorithm/MK/QUANTIZE/QUANTIZE/input.txt","r");
    //fp = stdin;
    fscanf(fp, "%d ", &test_cnt);
    
    for(i = 1 ; i <= 1000 ; i++)
    {
        for(j = i ; j <= 1000 ; j++)
        {
            diff[j][i] = diff[i][j] = (j-i) * (j-i);
        }
    }
    for ( i = 0 ; i < test_cnt ; i++)
    {
        min_num = 1001;
        max_num = -1;

        fscanf(fp, "%d %d ", &N, &S);
        for( j = 0 ; j < N ; j ++)
        {
            fscanf(fp, "%d ", &(num[j]));
            if(num[j]>max_num) max_num = num[j];
            if(num[j]<min_num) min_num = num[j];
            
        }
        
        printf("%d\n", find_min_diff_sum(0));
    }
    fclose(fp);
    
    return 0;

}

int calc_diff_sum()
{
    int sum = 0, i, j;
    for(i = 0; i < N ; i ++)
    {
        int min = 1001;
        for ( j = 0 ; j < S ; j ++)
        {
            if(ABSOLUTE(num[i]-quantum[j]) < min) min = ABSOLUTE(num[i]-quantum[j]);
            printf("(%d,%d) = (%d), ",num[i], quantum[j], min);
        }
        sum += (min*min);
    }
    return sum;
}

int find_min_diff_sum(int level)
{
    int min_diff_sum = 2147483647;
    int i;
    
    
    if(level == S)
    {
        printf("\n");
        return calc_diff_sum();
    }

    for(i = min_num + level ; i <= max_num ; i++)
    {
        int result;
        quantum[level] = i;
        printf("%d ", i);
        result = find_min_diff_sum(level+1);
        if(result<min_diff_sum) min_diff_sum = result;
        
    }
    
    return min_diff_sum;
}