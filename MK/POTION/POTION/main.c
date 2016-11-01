//
//  main.c
//  POTION
//
//  Created by K on 2016. 11. 1..
//  Copyright © 2016년 K. All rights reserved.
//

#include <stdio.h>
#define QUEUE_SIZE 257
#define MAX_INGRE 201

int ingre_cnt;

typedef struct _ingredient
{
    int should;
    int already;
}INGRE;

INGRE  data[MAX_INGRE];
int queue[QUEUE_SIZE];
int last;

void swap(int idxa, int idxb)
{
    int temp = queue[idxb];
    queue[idxb] = queue[idxa];
    queue[idxa] = temp;
}

void push(int a)
{
    int idx = last;
    queue[idx] = a;
    
    while((queue[idx] < queue[idx/2]) && idx>1)
    {
        swap(idx,idx/2);
        idx = idx/2;
    }
    last++;
}

int pop()
{
    int temp = queue[1];
    int idx = 1;
    
    last--;
    queue[1] = queue[last];
    
    while((2*idx)<last)
    {
        if((queue[idx] > queue[idx*2]) && (queue[idx*2] <= queue[idx*2+1]))
        {
            swap(idx,idx*2);
            idx = idx*2;
            continue;
        }
        else if(queue[idx] > queue[idx*2 + 1])
        {
            swap(idx,idx*2+1);
            idx = idx*2+1;
            continue;
        }
        break;
    }
    return temp;
}

int find_GCD()
{
    int res = 1;
    int now, i;
    now = pop();
    while(1)
    {
        if(now == 1)
        {
            now = pop();
            continue;
        }
        for(i = 1 ; i < last; i ++)
        {
            if (queue[i] % now) break;
            else queue[i] = (queue[i] / now);
        }
        if(i == last) res *= now;
        else break;
        
        now = pop();
    }
    return res;
}

int main(int argc, const char * argv[]) {
    FILE *fp;
    int test_cnt, i, max_multi;

//    fp = fopen("/Users/K/work/algorithm/MK/POTION/POTION/input.txt","r");
    fp = stdin;
    
    fscanf(fp, "%d ", &test_cnt);
    while(test_cnt)
    {
        last = 1;
        max_multi=1;
        fscanf(fp, "%d ", &ingre_cnt);
        
        for(i = 1; i <= ingre_cnt; i ++)
        {
            fscanf(fp, "%d ", &(data[i].should));
            push(data[i].should);
        }
//        printf("(last:%d)",last);

        for(i = 1; i <= ingre_cnt; i ++)
        {
            fscanf(fp, "%d ", &(data[i].already));
//            printf("%d ",pop());
        }
        
        int GCD = find_GCD();

        for(i = 1; i <= ingre_cnt; i ++)
        {
            (data[i].should /= GCD);
            if(GCD > max_multi) max_multi = GCD;
        }
//        printf("(GCD:%d)", GCD);

        for(i = 1; i <= ingre_cnt; i ++)
        {
            int temp,mod;
            temp = data[i].already /data[i].should;
            mod = data[i].already %data[i].should;
            if( mod ) temp++;
            if(temp > max_multi)
            {
                max_multi = temp;
            }
        }
//        max_multi++;
        
        
        for(i = 1; i <= ingre_cnt; i ++)
        {
            data[i].should *= max_multi;
            int diff =data[i].should - data[i].already;
            if(diff<0) diff = 0;
            if(i == 1 )printf("%d",diff);
            else printf(" %d",diff);
        }
 
        printf("\n");
        test_cnt--;
    }
    return 0;
}
