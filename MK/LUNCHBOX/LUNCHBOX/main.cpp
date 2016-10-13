//
//  main.c
//  LUNCHBOX
//
//  Created by K on 2016. 10. 14..
//  Copyright © 2016년 K. All rights reserved.
//

#include <stdio.h>
#include <queue>

using namespace std;

typedef struct box_s
{
    int idx;
    int eat_time;
}BOX;

struct cmp{
bool operator()(BOX a, BOX b)
{
    return (a.eat_time < b.eat_time);
}
};

priority_queue<BOX, vector<BOX>, cmp> pq;
int micro_time[10000];

int main(int argc, const char * argv[]) {
    FILE *fp;
    int test_cnt, box_cnt;
    int i,j;
    BOX tempbox;
    int now_micro;
    int max_time, expect_time;
    
    //fp = fopen("/Users/K/work/algorithm/MK/LUNCHBOX/LUNCHBOX/input.txt","r");
    fp = stdin;
    
    fscanf(fp, "%d ",&test_cnt);
    for (i = 0 ; i < test_cnt; i++)
    {
        now_micro = 0;
        max_time = -1;
        fscanf (fp, "%d ",&box_cnt);
        for(j = 0 ; j < box_cnt; j ++)
        {
            fscanf(fp,"%d ",&(micro_time[j]));
        }
        
        for(j = 0 ; j < box_cnt; j ++)
        {
            tempbox.idx = j;
            fscanf(fp,"%d ",&(tempbox.eat_time));
            pq.push(tempbox);
        }

        for(j = 0 ; j < box_cnt ; j++)
        {
            tempbox = pq.top();
           // printf("(%d,%d)\t",micro_time[tempbox.idx],tempbox.eat_time);
            expect_time = now_micro + micro_time[tempbox.idx] + tempbox.eat_time;
            if (( max_time == -1) || (max_time < expect_time)) max_time = expect_time;
            now_micro += micro_time[tempbox.idx];
            pq.pop();
        }
        printf("%d\n",max_time);
    }
    
    return 0;
}
