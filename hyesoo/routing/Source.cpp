#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define MAXV 10000
#define MAXE 20000

typedef struct node {
	struct node *next;
	int v;
	union {
		double weight;
		int visited;
	};
} node_t;

typedef struct graph {
	double *dist; /* allocated by nvertice */
	int nvertice;
	node_t *edges; /* allocated by nvectice */
	int nedges;
	int *intree;
} graph_t;

void list_add(node_t *prev, node_t *n)
{
	n->next = prev->next;
	prev->next = n;
}

#define SUBMIT 1

int main(void)
{
	int tc = 0;
	FILE *input;
	int i;

	if (SUBMIT) {
 		scanf("%d", &tc);
	}
	else {
		//fopen_s(&input, "input.txt", "rt");
		//fscanf(input, "%d", &tc);
	}

	while (!!(tc--)) {
		graph_t g;
		if (SUBMIT) {
			scanf("%d %d", &g.nvertice, &g.nedges);
		} else {
			//fscanf(input, "%d %d", &g.nvertice, &g.nedges);
		}
		
		g.edges = (node_t *)malloc(sizeof(node_t) * g.nvertice);
		g.dist = (double *)malloc(sizeof(double) * g.nvertice);

		for (i = 0; i < g.nvertice; i++) {
			g.dist[i] = (0xFFFFFFFF / 2) - 1;
			g.edges[i].visited = 0;
			g.edges[i].v = 0;
			g.edges[i].next = NULL;
		}

		for (i = 0; i < g.nedges; i++) {
			int start, end;
			double w;
			node_t *n;

			if (SUBMIT) {
				scanf("%d %d %lf", &start, &end, &w);
			} else {
				//fscanf(input, "%d %d %lf", &start, &end, &w);
			}

			n = (node_t *)malloc(sizeof(node_t));
			n->weight = log(w);
			n->v = end;
			n->next = NULL;
			list_add(&g.edges[start], n);

			/* insert diverse direction */
			n = (node_t *)malloc(sizeof(node_t));
			n->weight = log(w);
			n->v = start;
			n->next = NULL;
			list_add(&g.edges[end], n);
		}

		int cntv = 0;
		g.dist[cntv] = 0;

		while (1) {
			g.edges[cntv].visited = 1;
			node_t *nearest = g.edges[cntv].next;
			while (nearest != NULL) {
				/* update faster distance */
				if (g.dist[nearest->v] > g.dist[cntv] + nearest->weight) {
					g.dist[nearest->v] = g.dist[cntv] + nearest->weight;
				}
				nearest = nearest->next;
			}
			if (cntv == (g.nvertice - 1))
				break;

			/* update next node */
			double min_dist = (0xFFFFFFFF/ 2) - 1;
			cntv = 0;
			for (i = 0; i < g.nvertice; i++) {
				if ((g.edges[i].visited == 0) && (g.dist[i] < min_dist)) {
					min_dist = g.dist[i];
					cntv = i;
				}
			}
			if (cntv == 0)
				break;
		}
		printf("%lf", exp(g.dist[g.nvertice - 1]));
	}

	return 0;
}
