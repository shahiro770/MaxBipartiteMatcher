/**
 * @author Shahir Chowdhury
 * 2020/12/05
 * MaxMatcherMans.java
 *
 * This program takes in a modified adjacency matrix for an X,Y-bipartite graph
 * and outputs the maximum matching.
 */

package com.MaxMatcherMans;

import java.util.*;

public class Main {

    /**
     * @param args  Unused
     * @return      None
     *
     * Driver code that takes in input and prints the maximum matching for the user.
     */
    public static void main (String[] args) {
        int sizeX = -1;
        int sizeY = -1;
        boolean[][] graph;
        Scanner kb = new Scanner(System.in);

        while (sizeX < 0) {
            System.out.println("Enter the number of vertices in the independent set X:");
            sizeX = kb.nextInt();
            if (sizeX < 0) {
                System.out.println("A set can't be of negative size! (Or at least I haven't learned enough math to know otherwise)");
            }
        }

        while (sizeY < 0) {
            System.out.println("Enter the number of vertices in the independent set Y:");
            sizeY = kb.nextInt();
            if (sizeY < 0) {
                System.out.println("A set can't be of negative size! (Or at least I haven't learned enough math to know otherwise)");
            }
        }

        graph = new boolean[sizeX][sizeY];

        if (sizeX == 0 || sizeY == 0) {
            System.out.println("Maximum matching is of size 0");
        }
        else {
            System.out.println("Enter an |X| by |Y| matrix, where:\n- a 1 means the ith vertex in X is adjacent to the jth vertex in Y\n- a 0 means the ith vertex in X is not adjacent to the jth vertex in Y:");
            for (int i = 0; i < sizeX; i++) {
                for (int j = 0; j < sizeY; j++) {
                    int nextInt = kb.nextInt();
                    if (nextInt >= 1) {
                        graph[i][j] = true;
                    }
                    else{
                        graph[i][j] = false;
                    }
                }
            }

            int[] maxMatching = GetMaxBipartiteMatching(graph);
            System.out.println("The maximum matching is:");
            for (int i = 0; i < maxMatching.length; i++) {
                /*
                    Since the matching is printed out relative to all vertices in X, an output such as
                    0 -1
                    means the 0th vertex in X was not matched to any vertex in Y.
                */
                System.out.println(i + "  " + maxMatching[i]);
            }
        }

        kb.close();
    }

    /**
     * @param graph An |X| rows by |Y| columns 2d array (matrix), representing an X,Y-bipartite graph
     * @return      An array M, representing the maximum matching,
     *              where the ith value is the index of a given vertex in X and M[i] is the index of the
     *              matched vertex in Y
     *
     * This function assumes the graph has no dimensions of size 0
     * (if X or Y were empty, the main method will tell the user the size of the maximum matching is 0).
     * Note that all arrays in Java initialize with all values as false.
     */
    private static int[] GetMaxBipartiteMatching(boolean[][] graph) {
        int sizeX = graph.length;                   // size of the X partition of the graph
        int sizeY = graph[0].length;                // size of the Y partition of the graph
        int[] M = new int[sizeX];                   // the value k at index i means the ith vertex in X matches with kth vertex in Y (xy is in our matching)
        boolean[] unmatchedY = new boolean[sizeY];  // true if the corresponding vertex in Y is matched, false otherwise
        boolean[] U = new boolean[sizeX];           // true if the corresponding vertex in X is M-saturated, false otherwise (Note that it initializes with all vertices in X being considered unsaturated)
        boolean[] S = new boolean[sizeX];           // same as U
        boolean[] T;                                // indices of vertices in T subset of Y
        boolean[] markedS;                          // true if the corresponding vertex in S is considered "marked", false otherwise
        boolean foundMaxMatching = false;           // flag for if the maximum matching has been found
        boolean terminate = false;                  // flag to terminate the current application of the algorithm

        /*
            -1 indicates the vertex in X is not matched to any vertex in Y.
        */
        for (int i = 0; i < sizeX; i++) {
            M[i] = -1;
        }

        while (foundMaxMatching == false) {
            /*
                Initialize the algorithm.
                Note that while the original algorithm has S = U and T = ∅,
                T and U are not needed to test any meaningful conditions.
                However, to make it easier to relate this code to the algorithm from the book, I am keeping
                these variables in the program.
            */
            terminate = false;
            markedS = new boolean[sizeX];       // true if the corresponding vertex in S is marked, false otherwise
            T = new boolean[sizeY];             // true if the corresponding vertex in Y is in T, false otherwise (initialize as T = ∅)
            for (int i = 0; i < sizeX; i++) {
                S[i] = U[i];                    // S = U
                if (U[i] == true) {             // treat any vertex that is M-saturated as if it was already marked (which makes the algorithm ignore it as it iterates)
                    markedS[i] = true;
                }
            }

            for (int x = 0; x < sizeX; x++) {
                /*
                    Explore each vertex x (in X) that is contained in S (i.e. M-unsaturated) and unmarked
                */
                if (S[x] == false && markedS[x] == false) {     //
                    for (int y = 0; y < sizeY; y++) {
                        // System.out.println(x + " " + y + " " + graph[x][y] + " matching[x] " + matching[x] + " unmatchedY " + unmatchedY[y]);
                        /*
                            If the vertex y is in N(x), xy is not in M, and y is unsaturated,
                            then add xy to the matching and saturate x and y, and end this application of the algorithm.
                        */
                        if (graph[x][y] == true && M[x] != y && unmatchedY[y] == false) {
                            unmatchedY[y] = true;
                            U[x] = true;
                            M[x] = y;
                            terminate = true;
                            break;
                        }
                        /*
                            If the above condition fails, then y is already matched to some w in X,
                            so include y in T and w in S
                        */
                        else {
                            T[y] = true;
                            for (int w = 0; w < sizeX; w++) {
                                if (M[w] == y) {
                                    S[w] = true;
                                    break;
                                }
                            }
                        }
                    }
                    markedS[x] = true;  // mark x once all of its neighbours have been explored
                }
                if (terminate == true) {    // end the current application of the algorithm
                    break;
                }
            }

            /*
                If all x in S have been marked, then a maximum matching has been found.
                Setting foundMaxMatching to true will stop the function from reapplying the algorithm.
            */
            for (int x = 0; x < sizeX; x++) {
                if (markedS[x] == false) {
                    break;
                }
                if (x == sizeX - 1) {
                    foundMaxMatching = true;
                }
            }
        }

        return M;
    }
}