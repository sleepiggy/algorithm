//
//  main.c
//  EDITORWARS
//
//  Created by K on 2016. 11. 29..
//  Copyright © 2016년 K. All rights reserved.
//

#include <stdio.h>


typedef struct group_t
{
    int list_idx[2];
}Group;

typedef struct list_t
{
    int group_idx;
    int group_idx_order;
    int count;
}List;

typedef struct node_t
{
    int list_idx;
}Node;

#define MAX_CNT 100001

void merge_list(Group *group, List *list, int A, int B)
{
    // A , B should not same! A&B will be merged to A!
    list[A].count += list[B].count;
    group[list[B].group_idx].list_idx[list[B].group_idx_order] = 0;
    list[B].group_idx = 0;
    list[B].count = 0;
}

int ACK_list(Group *group, List *list, int A, int B)
{
    int A_dis = group[list[A].group_idx].list_idx[!(list[A].group_idx_order)];
    int B_dis = group[list[B].group_idx].list_idx[!(list[B].group_idx_order)];
    
    if (A_dis == B) return 0;
    else if (B_dis == A) return 0;
    
    merge_list(group, list, A,B);
    merge_list(group, list, A_dis, B_dis);
    printf(" ACK (%d,%d) \n", A, B);
    
    return 1;
}

int DIS_list(Group * group, List *list, int A, int B)
{
    int A_dis = group[list[A].group_idx].list_idx[!(list[A].group_idx_order)];
    int B_dis = group[list[B].group_idx].list_idx[!(list[B].group_idx_order)];
    
    if (A==B) return 0;
    merge_list(group, list, A,B_dis);
    merge_list(group, list, A_dis, B);
    printf(" DIS (%d,%d) \n", A, B);
    
    return 1;
}
int main(int argc, const char * argv[]) {
    
    FILE *fp;
    int test_cnt, pair_cnt, people_cnt;
    int x, y;
    int i;
    char str[10];
    int cnt, ret;
    
    
    fp = fopen("/Users/K/work/algorithm/MK/EDITORWARS/EDITORWARS/input.txt","r");
    //    fp = stdin;
    
    fscanf(fp, "%d ", &test_cnt);
    
    while(test_cnt)
    {
        Group group[MAX_CNT] = {{0,0},};
        List list[MAX_CNT] = {{0,0},};
        Node node[MAX_CNT] = {0,};
        
        fscanf(fp, "%d %d ", &people_cnt, &pair_cnt);
        cnt = 0;
        ret = 0;
        
        for(i = 1 ; i <= people_cnt; i++)
        {
            node[i].list_idx = i;
            list[i].group_idx = i;
            list[i].group_idx_order = 0;
            list[i].count = 1;
            group[i].list_idx[0] = i;
            group[i].list_idx[1] = 0;
        }
        
        while(pair_cnt)
        {
            fscanf(fp, "%s %d %d ",str,&x,&y);
            x++; y++;
            cnt++;
            
            if (str[0] == 'A')
                ret = ACK_list(group, list, x, y);
            else
                ret = DIS_list(group, list, x, y);
            pair_cnt--;
            if (ret == 0)
            {
                printf("CONTRADICTION AT %d\n",cnt);
                ret = 2;
                break;
            }
        }
        
        while(pair_cnt)
        {
            fscanf(fp,"%s %d %d ",str,&x,&y);
            pair_cnt--;
        }
        
        if (ret != 2)
        {
            cnt = 0;
            for(i = 1 ; i <= people_cnt; i++)
            {
                printf("group[%d].list_idx[0] = %d, list_idx[1] = %d",
                       i,group[i].list_idx[0],group[i].list_idx[0]);
                if((group[i].list_idx[0] == 0) && (group[i].list_idx[1] == 0))
                    continue;
                cnt++;
            }
            printf("MAX PARTY SIZE IS %d\n",cnt);
        }
        test_cnt--;
    }
    
    return 0;
}
