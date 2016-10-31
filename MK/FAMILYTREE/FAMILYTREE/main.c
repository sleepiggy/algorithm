//
//  main.c
//  FAMILYTREE
//
//  Created by K on 2016. 10. 24..
//  Copyright © 2016년 K. All rights reserved.
//

#include <stdio.h>

#define MAX_CNT 100001

int parent[MAX_CNT];
int depth[MAX_CNT] = {0,};

int find(int a, int b, int d)
{
    int a_parent = parent[a];
    int b_parent = parent[b];
    int res = 0;
    
    //    printf("(%d,%d,%d)\n",a,b,d);
    
    if(a==b)
    {
        if (a == 0)
        {
            if(depth[a] ==0) res = 2 * d;
            else res = depth[a] + d;
            //            printf("(%d,%d)",depth[0],d);
        }
        else
        {
            res = 2 * d;
        }
        goto FINAL;
        
    }
    
    
    if((depth[a] > 0))
    {
        if(a!=0)
        {
            res = (depth[a] + d );
            goto FINAL;
        }
    }
    else
    {
        depth[a] = d;
    }
    
    
    if ((depth[b] > 0) )
    {
        if(b!=0)
        {
            res = (depth[b] + d);
            //   printf("2.res : %d\n", res);
            goto FINAL;
        }
    }
    else
    {
        depth[b] = d;
    }
    
    if(res == 0)
    {
        res = find(a_parent, b_parent, d+1);
        //    printf("3.res : %d\n", res);
        
    }
FINAL:
    depth[a] = 0;
    depth[b] = 0;
    
    return res;
}

int main(int argc, const char * argv[]) {
    
    int test_cnt;
    int family_cnt;
    int pair_cnt;
    int a,b;
    int i;
    FILE *fp;
    
    //    fp = fopen("/Users/K/work/algorithm/MK/FAMILYTREE/FAMILYTREE/input.txt","r");
    fp = stdin;
    
    fscanf(fp, "%d ",&test_cnt);
    
    while(test_cnt)
    {
        fscanf(fp, "%d %d ",&family_cnt, &pair_cnt);
        
        for(i = 1 ; i < family_cnt; i++)
            fscanf(fp, "%d ",&(parent[i]));
        
        for(i = 1 ; i <= pair_cnt ; i ++)
        {
            fscanf(fp, "%d %d ",&a, &b);
            
            printf("%d\n",find(a,b,1) - 2);
            
        }test_cnt--;
    }
    
    return 0;
}
