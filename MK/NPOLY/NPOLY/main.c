//
//  main.c
//  NPOLY
//
//  Created by K on 2016. 10. 23..
//  Copyright © 2016년 K. All rights reserved.
//

#include <stdio.h>

#define MOD 20090711
// poly[x][y] : The number of polyominos when y block exist and top floor block is (y-x+1).
int poly[101][101];

// symmetry[x][y] : The number of symmetries when the sum of numbers are y, last number is x.
int symmetry[101][101];

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
        
        // Calc poly;
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
        //Calc symmetry;
        
        for(i = now_n ; i <= max_n ; i ++)
        {
            for( j = 1 ; j <= i ; j ++)
            {

                if (( j == i) || (( 2 * j ) == i))
                {
                    symmetry[j][i] = 1;
//                    printf("(%d,%d) = %d\n",j,i,symmetry[j][i]);
                    continue;
                }
                if ( (2 * j) > i)
                {
                    symmetry[j][i] = 0;
//                    printf("(%d,%d) = %d\n",j,i,symmetry[j][i]);
                    continue;
                }
                int sum = 0;
                int sub = i - ( 2 * j);
                for( k = 1 ; k <= sub ; k++)
                {
                    sum += (symmetry[k][sub] * (k + j - 1));
                    sum %= MOD;

                }
                symmetry[j][i] = sum;
//                printf("(%d,%d) = %d\n",j,i,symmetry[j][i]);
            }
        }
        
        //Calc res;
        for ( j = 1 ; j <= n ; j ++)
        {
            res += poly[j][n];
            //  printf("+%d ",poly[j][n]);
            res -= symmetry[n-j+1][n];
             // printf("-%d + ",symmetry[n-j+1][n]);
        }
        res %= MOD;

        printf("%d\n",res);
    }

    
    return 0;
}
