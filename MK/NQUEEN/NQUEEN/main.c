//
//  main.c
//  NQUEEN
//
//  Created by K on 2016. 8. 17..
//  Copyright © 2016년 K. All rights reserved.
//


#include <stdio.h>
#include <unistd.h>

// for padding array
#define MAX_N 14

void find_piece(int line, int n);

int N, cnt;
int arr[MAX_N][MAX_N] = {0,};

int main(int argc, const char * argv[])
{
    FILE *fp;
    int test_cnt;
    int i;
    
    //fp = fopen("/Users/K/work/algorithm/MK/NQUEEN/NQUEEN/input.txt","r");
    fp = stdin;
    fscanf(fp, "%d ", &test_cnt);
    
    for ( i = 0 ; i < test_cnt ; i++)
    {
        fscanf(fp, "%d ", &N);
        cnt = 0;
        
        find_piece(1,N);
        
        printf("%d\n", cnt);
    }
    fclose(fp);
    
    return 0;
}


void find_piece(int line, int n)
{
    int i;
    
    if (line > n)
    {
        cnt++;
        return;
    }
    
    for(i = 1 ; i <= n ; i ++)
    {
        int x, y;
        
        x = 1; y = i;
        
        //check vertical line
        while(x<=n)
        {
            if(arr[x][y] == 1) break;
            x++;
        }
        if(x<=n) continue;
        
        
            //check left-top diagonol
            
            x = line; y = i;
            if ( x < y )
            {
                y -= (x-1);
                x = 1;
            }
            else
            {
                x -= (y - 1);
                y =1;
            }
        
            while((x<=n)&&(y<=n))
            {
                if(arr[x][y]==1) break;
                x++;
                y++;
            }
            if((x <= n) && (y<=n)) continue;
        
            //check right-top diagonol
            
            x = line; y = i;
        
            int sum = x+y;

            if(sum == (n+1))
            {
                x = 1;
                y = n;
            }
            else if(sum<(n+1))
            {
                y += (x-1);
                x = 1;
            }
            else
            {
                x-=(n-y);
                y = n;
            }
        
            while((x<=n)&&(y>=1))
            {
                if(arr[x][y]==1) break;
                x++;
                y--;
            }
            if((x <= n) && (y>=1)) continue;
        

        // all pass!
            {
                arr[line][i] = 1;
                find_piece(line+1, n);
                arr[line][i] = 0;
                
            }
        
    }
}