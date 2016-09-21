//
//  main.c
//  DUCK
//
//  Created by K on 2016. 9. 21..
//  Copyright © 2016년 K. All rights reserved.
//

#include <stdio.h>

typedef struct
{
    int x,y;
}
coord;

coord arr[102];
int dist[102][102];
int end;


int find_path(int now);

int main(int argc, const char * argv[]) {
    FILE *fp;
    int test_cnt;
    int i, j, k;
    int least;
    int cnt_vert;
    fp = fopen("/Users/K/work/algorithm/MK/DUCK/DUCK/input.txt","r");
    //fp = stdin;
    fscanf(fp, "%d", &test_cnt);
    
    for ( i = 0 ; i < test_cnt ; i++)
    {
        fscanf(fp, "%d ", &least);
        least *= least;
        
        fscanf(fp, "%d %d ", &(arr[0].x),&(arr[0].y));
        fscanf(fp, "%d %d ", &(arr[1].x),&(arr[1].y));
        fscanf(fp, "%d ", &cnt_vert);
        end = cnt_vert+1;
        arr[end] = arr[1];
 
        for(j = 1 ; j <= end ; j++)
        {
            if(j==end)
                ;
            else
                fscanf(fp, "%d %d ", &(arr[j].x),&(arr[j].y));
            for(k = 0 ; k < j ; k++)
            {
                int x = arr[j].x - arr[k].x;
                int y = arr[j].y - arr[k].y;
                
                if ((x*x + y*y) > least)
                    dist[j][k] = dist[k][j] = 0;
                else
                    dist[j][k] = dist[k][j] = 1;
            }
                dist[j][j] = 0;
        }
        /*
        for(i = 0 ; i <= end; i++)
        {
            printf("(%d,%d)", arr[i].x, arr[i].y);
            for(j=0;j<=end; j++)
            {
                printf("%d ",dist[i][j]);
            }
            printf("\n");
        }
         */
        if(find_path(0)) printf("YES\n");
        else printf("NO\n");

    }
    fclose(fp);
    
    return 0;
}

int find_path(int now)
{
    if (now==end) return 1;
    int i;
    
    for(i = 0 ; i <= end ; i++)
    {
        if(dist[now][i] == 1)
        {
            dist[now][i] = dist[i][now] =2;
            if(find_path(i)) return 1;
            else dist[now][i] = dist[i][now] =1;
        }
    }
    return 0;
}
