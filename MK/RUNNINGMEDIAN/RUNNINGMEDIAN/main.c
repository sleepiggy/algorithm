//
//  main.c
//  RUNNINGMEDIAN
//
//  Created by K on 2016. 11. 8..
//  Copyright © 2016년 K. All rights reserved.
//

#include <stdio.h>

#define MOD_VAL 20090711
#define INITIAL_VAL 1983
#define MAX_IDX 10000

typedef struct node_t{
    int val;
    void *left;
    void *right;
}NODE;

NODE *root;
int left_cnt, right_cnt, left_depth, right_depth;
int max(int a, int b)
{
    return ((a>b)? a:b);
}

int min(int a, int b)
{
    return ((a<b)? a:b);
    
}
void insert(NODE *tree, NODE *node)
{
    NODE *now = root;
    NODE *prev = now;
    int depth = 0;
    int direction = 0; // left

    if (root->val > node->val) direction = 0;
    else direction = 1;

    while(now)
    {
        prev = now;
        if (now->val > node->val) now = now->left;
        else now = now->right;
        depth++;
    }
    
    if (prev->val > node->val) prev->left = node;
    else prev->right = node;
    
    if(direction == 0) { left_cnt++; left_depth = depth; }
    else {right_cnt++; right_depth = depth; }
}

void sort(NODE *tree)
{
    int idx = 3;
    int right_min;
    
    
    // sort 1 step
    
    if (left_cnt < right_cnt)
    {
    
    }
}
int main(int argc, const char * argv[]) {
    FILE *fp;
    int test_cnt;
    int num_cnt, a, b;
    int sum, i, val;
    
    fp = fopen("/Users/K/work/algorithm/MK/RUNNINGMEDIAN/RUNNINGMEDIAN/input.txt","r");
//    fp = stdin;
    
    fscanf(fp,"%d ", &test_cnt);
    while(test_cnt)
    {
        NODE tree[MAX_IDX] = {0,};
        fscanf(fp, "%d %d %d ", &num_cnt, &a, &b);
        
        tree[0].val = INITIAL_VAL;
        root = &(tree[0]);
        left_cnt = right_cnt = left_depth = right_depth = 0;
        

        for(i = 1 ; i < num_cnt ; i ++)
        {
            tree[i].val = ((tree[i-1].val * a ) + b ) % MOD_VAL;
            insert(tree, &(tree[i]));
            sort(tree);
            
        }
        printf("%ld\n", sum);
        test_cnt--;
    }
    return 0;
}
