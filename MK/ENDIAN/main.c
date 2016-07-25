//
//  main.c
//  ENDIAN
//
//  Created by K on 2016. 7. 25..
//  Copyright © 2016년 K. All rights reserved.
//

#include <stdio.h>
#include <unistd.h>

int main(int argc, const char * argv[])
{
    FILE *fp;
    int test_cnt;
    
    fp = fopen("/Users/K/work/algorithm/MK/ENDIAN/input.txt","r");
    fscanf(fp, "%d ", &test_cnt);

    printf("%d", test_cnt);
    
    fclose(fp);
    
    return 0;
}