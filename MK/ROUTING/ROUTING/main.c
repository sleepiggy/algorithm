//
//  main.c
//  ROUTING
//
//  Created by K on 2016. 9. 28..
//  Copyright © 2016년 K. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>

#define MAX_VERTEX 10000
#define MAX_FLOAT 99999

double least[MAX_VERTEX];
int found[MAX_VERTEX];

void find_min_path();
int cnt;

typedef struct
{
    int vertex;
    double val;
    void *prev;
    void *next;
}list;

typedef struct{
    list *data;
    int cnt;
    list *last;
}enlist;

enlist arr[MAX_VERTEX];

void enlist_init(enlist *el)
{
    list *li;
    el->cnt = 0;
    
    el->last = 0;
    li = el->data;
    
    while(li)
    {
        list *next = li->next;
        free(li);
        li = next;
    }
    
    el->data = 0;
}

void enlist_add(int idx, int vertex, double val)
{
    list *temp = arr[idx].last;
    list *new = malloc(sizeof(list));
    arr[idx].last = new;
    new->val = val;
    new->prev = temp;
    new->vertex = vertex;
    new->next = 0;
    if(arr[idx].cnt == 0)
        arr[idx].data = new;
    else temp->next = new;
    arr[idx].cnt++;
}

list *enlist_exist(int idx, int vertex)
{
    list *li;
    li = arr[idx].data;
    while(li)
    {
        if( (li->vertex) == vertex) return li;
        li = (li->next);
    }
    return 0;
}

int main(int argc, const char * argv[]) {
    FILE *fp;
    int test_cnt;
    int edge_cnt;
    int i, j;
    //fp = fopen("/Users/K/work/algorithm/MK/ROUTING/ROUTING/input.txt","r");
    fp = stdin;
    fscanf(fp, "%d ", &test_cnt);
    
    for ( i = 0 ; i < test_cnt ; i++)
    {
        fscanf(fp, "%d %d ", &cnt, &edge_cnt);
        
        for(j = 0 ; j < cnt; j ++)
        {
            enlist_init(&(arr[j]));
            least[j] = -1;
            found[j] = 0;
        }
        found[0] = 1;
        
        for(j = 0 ; j < edge_cnt; j ++)
        {
            int p,q;
            double f;
            fscanf(fp, "%d %d %lf ",&p, &q, &f);
            enlist_add(p,q,f);
            enlist_add(q,p,f);
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
    double min = -1;
    int min_idx = 0;
    
    for(i = 1 ; i < cnt ; i ++)
    {
        if((found[i] == 0) && (least[i] != -1))
        {
            if((min > least[i]) || (min == -1))
            {
                min_idx = i;
                min = least[i];
            }
        }
        //   printf("%f(%d)(%f) ", least[i], found[i],min);
    }
    /*
    if (min == -1)
    {
        for(i = 1 ; i < cnt ; i ++)
        {
            if((found[i] == 0) && (least[i] == -1))
            {
                min_idx = i;
                min = least[i];
                break;
                
            }
        }
    }*/
   // printf(" %d\n", min_idx);
    
    return min_idx;
}
void find_min_path()
{
    int i, j,next = 0;
    double next_noise;
    
    for(j = 1 ; j < cnt; j ++)
    {
        next = find_least();
        
        if(next == 0) break;
        list *li = (arr[next].data);
        //        printf("\n%p(%p)\n",li,li->next);
        while(li)
        {
            i = li->vertex;
            if (found[i] == 1)
            {
                list *lir = li;
                if(li->prev)
                ((list*)(li->prev))->next = li->next;
                else
                    arr[next].data = li->next;
                if(li->next)
                ((list*)(li->next))->prev = li->prev;
                else
                    arr[next].last = li->prev;
                arr[next].cnt--;
                li = (li->next);
                free(lir);
                continue;
            }
            if (least[next] == -1)
            {
                li = (li->next);
                continue;
            }
            next_noise = (least[next] * (li->val));
            
            if (least[i] == -1) least[i] = next_noise;
            else if (next_noise < least[i]) least[i] = next_noise;
            li = (li->next);
        }
        
        found[next] = 1;
        //        printf("%d \n", next);
    }
    // printf(" \n");
}
