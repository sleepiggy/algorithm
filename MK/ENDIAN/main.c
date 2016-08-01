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
    int i, j;
    unsigned long in, out;
    
    //fp = fopen("/Users/K/work/algorithm/MK/ENDIAN/input.txt","r");
    fp = stdin;
    fscanf(fp, "%d ", &test_cnt);

    for ( i = 0 ; i < test_cnt ; i++)
    {
        out = 0;
        fscanf(fp, "%ld ", &in);
        for(j = 0 ; j < 4 ; j ++)
        {
            out += (in & 0xff);
            if(j == 3) break;
            in = (in >> 8);
            out = (out << 8);
        }
        printf("%ld\n",out);
    }
    fclose(fp);
    
    return 0;
}