# MaxBipartiteMatcher
Determines the maximum matching in any XY-bipartite graph.
Source for the algorithm can be found in in chapter 3.2 of Douglas B. West's Introduction to Graph Theory.

### Setup

Click the v1.0.0 Launch underneath releases. From there download the MaxBipartiteMatcher.jar.zip
and unzip it in your desired directory. Finally, navigate to that directory in your favourite command-line interface
and run the following command to start the program.

```java -jar MaxBipartiteMatcher.jar```

### Input and Samples

The program will prompt you for the number of vertices in the X partition and then the number of vertices in the Y partition.
From there, enter in a modified adjacency matrix (|X| rows by |Y| columns).

The program will then output the resulting maximum matching from the perspective of the X vertices.
For example:

```
$ java -jar MaxBipartiteMatcher.jar
Enter the number of vertices in the independent set X:
3
Enter the number of vertices in the independent set Y:
3
Enter an |X| by |Y| matrix, where:
- a 1 means the ith vertex in X is adjacent to the jth vertex in Y
- a 0 means the ith vertex in X is not adjacent to the jth vertex in Y:
1 1 1
1 0 1
0 1 0
The maximum matching is:
0  0
1  2
2  1
```

To save time inputting feel free to create a simple text file in the same directory use some input redirection.
Say you made an input file called `input.txt` and wrote in the following text.

```
9
8
1 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0
1 0 0 0 0 0 0 0
1 1 1 0 0 0 0 0
0 1 1 0 0 0 0 0
0 0 1 1 0 0 0 0
0 1 0 0 1 0 1 0
0 1 0 0 0 1 0 1
```


It contains 9 vertices in the X partition and 8 vertices in the Y partition. The graph looks just like this.

<img src="/README/Images/xy1.jpg" width="480" height="270">

If you were to run the program with the above input using input redirection you'll see the following
(don't worry about it skipping through the input prompts, input redirection just does that).

```
$java -jar MaxBipartiteMatcher.jar < input.txt
Enter the number of vertices in the independent set X:
Enter the number of vertices in the independent set Y:
Enter an |X| by |Y| matrix, where:
- a 1 means the ith vertex in X is adjacent to the jth vertex in Y
- a 0 means the ith vertex in X is not adjacent to the jth vertex in Y:
The maximum matching is:
0  0
1  -1
2  1
3  -1
4  2
5  -1
6  3
7  4
8  5
```

Since the output is from the perspective of the vertices in X, any X vertex with a -1 beside it simply means it had no
matching vertex in the Y partition.
Here's what the output looks like on graph paper.

<img src="/README/Images/xy2.jpg" width="480" height="270">
