import java.util.ArrayList;
import java.util.LinkedList;

//
// Coded by Prudence Wong 2022-03-13
//
// Name:
// Student ID:
// University email address:
//
// Time Complexity and explanation:
// n denotes the number of vertices
//
//
// neighbourhood():
//	As we using bfs so time complexity is O(v+e)
//



class COMP108A2Graph {


	// input parameter: an integer distance
	// output: compute neighbourhood matrix for distance 
	static COMP108A2Output neighbourhood(int[][] adjMatrix, int gSize) {
		COMP108A2Output output = new COMP108A2Output(1, gSize);
        int l = adjMatrix[0].length;
        ArrayList<ArrayList<Integer>> adjListArray
        = new ArrayList<ArrayList<Integer>>(l);
 
        
        for (int i = 0; i < l; i++) {
            adjListArray.add(new ArrayList<Integer>());
        }
         
        int i, j;
        for (i = 0; i < adjMatrix[0].length; i++) {
            for (j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[i][j] == 1) {
                    adjListArray.get(i).add(j);
                }
            }
        }
        for ( i=0; i<gSize; i++) {
			for ( j=0; j<gSize; j++) {
				output.neighbourMatrix[i][j]=getShortestDistance(adjListArray,i,j,gSize);
			}
			System.out.println();
		}
        
		// do not remove this last statement
		return output;
	}

	
	static boolean  BFS(ArrayList<ArrayList<Integer>> adj, int src,
            int dest, int gSize, int pred[], int dist[]) {
		
		
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        
        boolean visited[] = new boolean[gSize];
 
       
        for (int i = 0; i < gSize; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }
 
        
        visited[src] = true;
        dist[src] = 0;
        queue.add(src);
 
        // bfs Algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (visited[adj.get(u).get(i)] == false) {
                    visited[adj.get(u).get(i)] = true;
                    dist[adj.get(u).get(i)] = dist[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));
                    if (adj.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
		
	}
	
	private static int getShortestDistance(
            ArrayList<ArrayList<Integer>> adj,
                    int s, int dest, int gSize) {
		int pred[] = new int[gSize];
		int dist[] = new int[gSize];
		
		if (BFS(adj, s, dest, gSize, pred, dist) == false) {
		   
		   return 0;
		}
		
		
		LinkedList<Integer> path = new LinkedList<Integer>();
		int crawl = dest;
		path.add(crawl);
		while (pred[crawl] != -1) {
		   path.add(pred[crawl]);
		   crawl = pred[crawl];
		}
		return dist[dest];	
	}
	
	// DO NOT change this method, you can use it if you want
	static void printSquareArray(int array[][], int size) {
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}


}

