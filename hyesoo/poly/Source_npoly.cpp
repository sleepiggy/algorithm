#include <stdio.h>
#define _CRT_SECURE_NO_WARNINGS

#define MOD 20090711
/* we don't use the index 0 */
int poly[101][101];
int result[101];

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
			result[i] = 0;
			for (int j = 1; j <= i; j++) {
				if (i == j) poly[i][j] = 1;
				/* m is upper line directly of j*/ 
				for (int m = 1; m <= i - j; m++) {
					poly[i][j] += poly[i - j][m] * (j + m - 1);
					poly[i][j] %= MOD;
				}
				result[i] += poly[i][j] % MOD;
			}
		}

		if (n % 2 != 0) printf("%d\n", result[n]);

		int sym_n = n;
		int result_sym = 0;
		int bridge = 0;
		while (1) {
			if (bridge == 0)
				result_sym += result[sym_n / 2];
			else
				result_sym += result[sym_n / 2] * (bridge + (sym_n / 2) - 1);
			
			result_sym %= MOD;

			sym_n -= 2;
			bridge += 2;
			if (sym_n <= 0) break;
		}

		printf("%d\n", (result[n] - result_sym - 1) % MOD);
	}
}