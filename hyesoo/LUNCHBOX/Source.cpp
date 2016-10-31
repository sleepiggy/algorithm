#include <stdio.h>
#include <stdlib.h>
#include <math.h>

typedef struct {
	int prepare;
	int eating;
	int gap;
} lunchbox_t;

int main()
{
	FILE *input;
	input = stdin;
	//fopen_s(&input, "input.txt", "rt");

	int tc = 0;
	fscanf(input, "%d", &tc);

	while (tc--) {
		int member = 0;
		int *gaps;

		fscanf(input, "%d", &member);

		gaps = (int *)malloc(sizeof(int) * member);

		lunchbox_t *lunchbox = (lunchbox_t *) malloc(sizeof(lunchbox_t) * member);
		for (int i = 0; i < member; i++)
			fscanf(input, "%d", &lunchbox[i].prepare);
		
		for (int i = 0; i < member; i++) {
			fscanf(input, "%d", &lunchbox[i].eating);
			lunchbox[i].gap = lunchbox[i].prepare - lunchbox[i].eating;
			/* get the index for lunchbox in order of gap size */
			gaps[i] = i;

			for (int j = i - 1; j >= 0; j--) {
				int idx = gaps[j];
				int next_idx = gaps[j + 1];
				if (lunchbox[idx].gap > lunchbox[next_idx].gap) {
					int t;
					t = gaps[idx];
					gaps[idx] = gaps[next_idx];
					gaps[next_idx] = t;
				}
			}
		}
		
		/* select the smallest the diff node */
		int idx = 0;
		int micro = 0;
		int eating = 0;

		for (int i = 0; i < member; i++) {

			idx = i;

			if (i % 2 == 0) idx = i / 2;
			else idx = member - ((i + 1) / 2);

			idx = gaps[idx];
			micro += lunchbox[idx].prepare;
				
			if (micro < eating)
				eating += lunchbox[idx].eating;
			else if (micro >= eating)
				eating = micro + lunchbox[idx].eating;
		}
		printf("%d\n", eating);
		
	}
}