//
//  main.c
//  ROUTING
//
//  Created by K on 2016. 9. 28..
//  Copyright © 2016년 K. All rights reserved.
//

#include <stdio.h>

#define MAX_VERTEX 1000
#define MAX_FLOAT 1000.0f

float arr[MAX_VERTEX][MAX_VERTEX];

float least[MAX_VERTEX];
int found[MAX_VERTEX];

void find_min_path();
int cnt;

int main(int argc, const char * argv[]) {
    FILE *fp;
    int test_cnt;
    int edge_cnt;
    int i, j, k;
    fp = fopen("/Users/K/work/algorithm/MK/ROUTING/ROUTING/input.txt","r");
    //fp = stdin;
    fscanf(fp, "%d ", &test_cnt);
    
    for ( i = 0 ; i < test_cnt ; i++)
    {
        fscanf(fp, "%d %d ", &cnt, &edge_cnt);
        
        if(cnt > 1000)
        {
            printf("1.0\n");
            continue;
        }
        for(j = 0 ; j < cnt; j ++)
        {
            for(k = 0 ; k < cnt; k ++)
                arr[j][k] = 0;
            least[j] = MAX_FLOAT;
            found[j] = 0;
        }
        found[0] = 1;
        
        for(j = 0 ; j < edge_cnt; j ++)
        {
            int p,q;
            float f;
            fscanf(fp, "%d %d %f ",&p, &q, &f);
            arr[p][q]=arr[q][p]=f;
            if(p==0) least[q] = f;
            else if(q==0) least[p] = f;
        }
        
        find_min_path();
        
        
        printf("%.10f\n",least[cnt-1]);
        
        /*
         //for(j = 0 ; j < cnt; j ++)
         {
         for(k = 0 ; k < cnt; k ++)
         printf("%.10f ",least[k]);
         printf("\n");
         }
        */
    }
    return 0;
}


int find_least()
{
    int i;
    float min = MAX_FLOAT;
    int min_idx = 0;

    for(i = 1 ; i < cnt ; i ++)
    {
        if((found[i] == 0) && (min > least[i]))
        {
            min_idx = i;
            min = least[i];
        }
    //    printf("%f(%d)(%f) ", least[i], found[i],min);
    }
  //  printf("\n");
    
    return min_idx;
}
void find_min_path()
{
    int i, j,next = 0;
    float next_noise;

    for(j = 1 ; j < cnt; j ++)
    {
        next = find_least();

        for(i = 0 ; i < cnt ; i ++)
        {
            if (found[i] != 0) continue;
            if (arr[next][i] == 0) continue;
            if (least[next] == MAX_FLOAT) continue;
            next_noise = (least[next] * arr[next][i]);
            
            if(next_noise < least[i]) least[i] = next_noise;
        }
        
        found[next] = 1;
      //  printf("%d \n", next);
    }
   // printf(" \n");
}






