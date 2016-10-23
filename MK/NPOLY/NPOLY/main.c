//
//  main.c
//  NPOLY
//
//  Created by K on 2016. 10. 23..
//  Copyright © 2016년 K. All rights reserved.
//

#include <stdio.h>

#define MOD 10000000
int poly[101][101];
int max_n = 1;
int now_n = 1;

int main(int argc, const char * argv[]) {
    
    FILE *fp;
    int test_cnt;
    int n;
    int i,j,k;
    int res;
    
//    fp = fopen("/Users/K/work/algorithm/MK/NPOLY/NPOLY/input.txt","r");
    fp = stdin;
    
    fscanf(fp, "%d ", &test_cnt);
    while(test_cnt)
    {
        test_cnt--;
        fscanf(fp, "%d ",&n);
        res = 0;
        if(n > max_n)
        {
            now_n = max_n;
            max_n = n;
        }
        
        for(i = now_n ; i <= max_n ; i++)
        {
            for (j = 1; j <= i ; j ++)
            {
                if ( j == 1 )
                {
                    poly[j][i] = 1;
                    continue;
                }
                int sum = 0;
                for( k = 1 ; k < j ; k ++)
                {
                    sum += poly[k][j-1] * (i-k);
                    sum %= MOD;
                }
                    
                poly[j][i] = sum;
            }
        }
        
        for ( j = 1 ; j <= n ; j ++)
        {
            res += poly[j][n];
            res %= MOD;
          //  printf("%d + ",poly[j][n]);
        }
        
        printf("%d\n",res);
    }

    
    return 0;
}
