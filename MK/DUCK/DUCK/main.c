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
int cnt_vert;


int find_path(int start);

int main(int argc, const char * argv[]) {
    FILE *fp;
    int test_cnt;
    int i, j, k;
    int least;
    //fp = fopen("/Users/K/work/algorithm/MK/DUCK/DUCK/input.txt","r");
    fp = stdin;
    fscanf(fp, "%d", &test_cnt);
    
    for ( i = 0 ; i < test_cnt ; i++)
    {
        fscanf(fp, "%d ", &least);
        least *= least;
        
        fscanf(fp, "%d %d ", &(arr[0].x),&(arr[0].y));
        fscanf(fp, "%d %d ", &(arr[1].x),&(arr[1].y));
        fscanf(fp, "%d ", &cnt_vert);
        arr[cnt_vert+1] = arr[1];
        
        for(j = 1 ; j <= cnt_vert ; j++)
            fscanf(fp, "%d %d ", &(arr[j].x),&(arr[j].y));
        
        for(j = 0 ; j <= (cnt_vert+1) ; j++)
        {
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
        
        for(j = 0 ; j <= (cnt_vert+1) ; j++)
        {
            printf("(%d,%d) ",arr[j].x,arr[j].y);
            for(k = 0 ; k <= (cnt_vert+1) ; k++)
            {
                printf("%d ",dist[j][k]);
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

int find_path(int start)
{
    if (start ==(cnt_vert +1)) return 1;
    int i;
    
    for(i = 0 ; i <= (cnt_vert+1) ; i++)
    {
        if(dist[start][i] == 1)
        {
            dist[start][i] = dist[i][start] = 2;
            if(find_path(i)) return 1;

            /*
            for(j = 0 ; j <= (cnt_vert+1) ; j ++)
            {
                if(dist[end][j] == 1)
                {
                    dist[end][j] = dist[j][end] = 2;
                    if(find_path(i,j)) return 1;
                   // dist[end][j] = dist[j][end] = 1;
                }
            }
             */
         //   dist[start][i] = dist[i][start] = 1;
        }
    }
    return 0;
}
