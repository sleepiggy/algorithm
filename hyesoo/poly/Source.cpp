#include <stdio.h>
#define _CRT_SECURE_NO_WARNINGS

#define MOD 10000000
/* we don't use the index 0 */
int poly[101][101];

int main()
{
	int tc;
	FILE *input;
	input = stdin;
	//input = fopen("input.txt", "rt");

	fscanf(input, "%d", &tc);

	while (tc-- > 0) {
		int n = 0;
		fscanf(input, "%d", &n);

		/* intialization for array */
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				poly[i][j] = 0;
			}
		}
		
		/* i-polyomino */
		for (int i = 1; i <= n; i++) {
			/* j-bottom line */
			for (int j = 1; j <= i; j++) {
				if (i == j) poly[i][j] = 1;
				/* m is upper line directly of j*/ 
				for (int m = 1; m <= i - j; m++) {
					poly[i][j] += poly[i - j][m] * (j + m - 1);
					poly[i][j] %= MOD;
				}
			}
		}
		int result = 0;
		for (int i = 1; i <= n; i++) {
			result += poly[n][i];
		}
		printf("%d\n", result % MOD);
	}
}