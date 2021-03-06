# MaxBipartiteMatcher
Determines the maximum matching in any XY-bipartite graph.
Source for the algorithm can be found in chapter 3.2 of Douglas B. West's Introduction to Graph Theory.

### Setup

Click the v1.0.0 Launch underneath releases. Next, download the MaxBipartiteMatcher.jar.zip
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

The above output claims a a possible maximum matching is x0 with y0, x1 with y2, and x2 with y1 (which is correct).
Surely x0 with y2, x1 with y0, and x2 with y1 is also a maximum matching, but since
my implementation starts with the lowest indexed X and Y verticies (the book's algorithm doesn't specify an order to start with),
it will always provide the above output as the maximum matching.

To save time, feel free to create a simple text file in the same directory and use some input redirection.
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


It contains 9 vertices in the X partition and 8 vertices in the Y partition. 
The graph looks like this (just like the one in the book!).

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

Since the output is from the perspective of the vertices in X, any value with a -1 beside it simply means
the X vertex at that index had no matching vertex in the Y partition.
Here's what the output looks like on graph paper.

<img src="/README/Images/xy2.jpg" width="480" height="270">

### Some Interesting Examples
#### Complete

I'm not the most creative person, so I could only think about 4 interesting bipartite graphs.
First, lets take a look at a complete bipartite graph such as K<sub>3,4</sub>.

```
$ java -jar MaxBipartiteMatcher.jar
Enter the number of vertices in the independent set X:
3
Enter the number of vertices in the independent set Y:
4
Enter an |X| by |Y| matrix, where:
- a 1 means the ith vertex in X is adjacent to the jth vertex in Y
- a 0 means the ith vertex in X is not adjacent to the jth vertex in Y:
1 1 1 1
1 1 1 1
1 1 1 1
The maximum matching is:
0  0
1  1
2  2
```

The output is as expected. All three vertices in X are matched to the first unsaturated Y vertex the algorithm can find.
The fourth vertex in Y goes unmatched.

#### Wikipedia Has Everything

Well that wasn't so interesting. How about this one from [this page on wikipedia](https://en.wikipedia.org/wiki/Bipartite_graph)?

<img src="/README/Images/xywiki.png" width="600" height="600">

```
$ java -jar MaxBipartiteMatcher.jar
Enter the number of vertices in the independent set X:
5
Enter the number of vertices in the independent set Y:
4
Enter an |X| by |Y| matrix, where:
- a 1 means the ith vertex in X is adjacent to the jth vertex in Y
- a 0 means the ith vertex in X is not adjacent to the jth vertex in Y:
1 0 0 0
1 1 0 0
0 0 1 1
0 1 0 0
1 0 0 1
The maximum matching is:
0  0
1  1
2  2
3  -1
4  3
```

#### Less Trivial 

Well you probably could still have solved that one with your eyes. But how about we spice it up a little?
Here's a 10x10 matrix I randomly generated with a little python script.
What will the results be here?

```
10
10
0 1 1 1 1 0 1 1 0 1
1 1 0 1 0 1 0 0 0 1
0 1 1 1 0 1 0 1 0 0
0 0 1 1 1 0 0 1 1 0
0 1 1 0 1 0 1 1 0 0
1 1 1 1 1 0 0 0 0 0
1 0 1 0 0 0 1 0 1 0
1 0 1 0 1 1 1 0 1 0
0 1 1 0 1 1 0 0 0 0
0 0 0 0 1 0 1 0 1 0
```

```
$ java -jar MaxBipartiteMatcher.jar < input10x10.txt
Enter the number of vertices in the independent set X:
Enter the number of vertices in the independent set Y:
Enter an |X| by |Y| matrix, where:
- a 1 means the ith vertex in X is adjacent to the jth vertex in Y
- a 0 means the ith vertex in X is not adjacent to the jth vertex in Y:
The maximum matching is:
0  1
1  0
2  2
3  3
4  4
5  -1
6  6
7  5
8  -1
9  8
```

I went to draw this out originally, but unfortunately it got super messy after I made one mistake (and practically I don't think anyone would want to see 50 edges in a tangled mess).
I still believe this is a maximum matching for such a graph, or at least I couldn't find a matching of size greater than 8.

But can we go any bigger?

#### There's No Escaping 

```
100
100
1 0 1 0 1 1 0 1 1 1 1 0 1 0 0 0 1 1 1 0 0 1 1 0 0 1 1 0 0 1 0 1 0 0 0 0 0 1 0 0 1 1 0 1 0 1 1 0 1 0 1 0 1 0 0 1 0 0 0 1 0 1 0 1 0 0 0 1 1 0 0 0 0 1 1 1 0 1 0 1 1 0 1 1 0 1 0 1 0 0 1 1 0 0 0 1 0 0 0 1
1 1 1 1 0 0 0 1 1 0 0 0 1 1 0 1 0 1 0 0 0 1 1 1 1 1 1 1 0 0 1 0 0 0 1 1 0 1 1 1 0 1 0 1 1 0 0 0 0 1 0 0 1 0 0 0 1 0 0 0 0 1 0 0 1 1 1 1 1 0 1 1 1 1 0 0 0 0 0 1 0 0 1 1 0 1 1 0 0 0 0 1 1 1 0 1 0 1 1 1
0 1 1 1 0 1 0 1 0 0 0 1 1 0 1 0 1 1 0 0 0 1 0 1 1 0 1 0 1 0 0 1 1 0 0 0 1 1 1 1 1 1 0 1 1 1 0 0 0 0 1 0 1 0 0 0 0 1 1 0 0 1 1 1 0 1 1 0 0 1 0 1 0 0 1 0 0 1 0 1 0 1 1 1 0 1 1 1 1 0 1 0 1 1 0 0 0 0 0 0
0 0 1 0 1 0 1 1 1 0 0 0 0 1 0 1 1 0 0 1 1 0 0 1 1 0 0 0 1 0 0 0 0 1 0 1 1 0 1 1 0 0 1 1 0 0 0 1 0 1 1 0 0 1 0 1 1 0 1 0 0 1 0 1 1 0 0 1 0 0 1 0 1 0 1 0 0 0 0 0 1 1 1 0 0 0 0 1 1 0 1 0 0 0 0 1 0 0 1 0
0 0 0 1 0 0 0 0 1 1 0 0 1 0 1 0 1 1 0 0 0 0 0 1 0 0 0 1 1 0 1 0 1 0 1 0 1 1 0 1 0 0 1 0 1 0 1 0 0 1 0 0 1 1 0 0 1 0 1 0 0 1 0 1 0 0 0 1 1 0 1 1 0 1 1 0 1 0 1 1 1 0 1 0 0 0 1 0 1 0 0 1 0 1 0 1 1 0 0 1
1 0 1 1 1 1 1 0 1 1 1 1 0 0 0 1 0 0 1 1 1 0 0 1 0 0 0 0 0 0 0 1 1 1 1 0 1 1 0 0 1 0 1 0 1 1 1 1 0 0 1 0 1 1 0 0 0 0 0 1 1 1 0 0 0 0 0 0 0 0 1 0 1 0 1 1 0 1 1 0 0 0 1 0 1 0 1 1 1 1 1 0 1 1 1 0 0 0 0 1
1 0 0 1 1 0 0 0 0 0 0 0 0 0 1 1 0 1 0 1 0 0 1 1 0 1 0 0 0 0 0 0 0 0 0 1 0 0 0 0 1 0 1 1 0 1 1 1 0 1 1 1 0 0 0 0 1 1 1 0 0 0 0 1 0 1 1 0 1 1 0 1 0 1 1 1 1 1 1 1 1 0 0 1 1 1 0 0 1 0 1 0 0 1 0 0 1 1 0 0
1 0 1 0 0 0 0 0 1 1 0 0 0 0 1 0 0 0 1 0 0 0 0 0 1 1 0 1 1 1 0 1 1 1 1 1 1 0 1 0 0 1 1 1 1 1 1 1 1 0 1 0 0 0 0 0 0 0 0 0 1 1 1 1 1 0 0 1 0 0 0 0 0 1 0 1 0 1 0 1 1 1 0 1 0 1 1 0 1 1 0 1 1 1 0 0 1 1 1 0
0 1 1 0 1 0 0 1 0 0 0 1 1 1 0 1 1 1 1 1 0 0 1 1 1 0 0 0 1 1 0 1 1 1 1 0 1 0 1 1 1 1 0 1 1 1 0 0 1 0 1 0 0 1 0 1 1 1 0 0 0 1 0 0 0 1 1 1 0 0 1 0 0 1 1 0 0 0 0 1 0 1 0 1 0 1 0 0 0 1 1 0 0 0 0 1 0 0 1 1
1 0 0 1 1 1 0 1 0 1 0 0 1 1 0 1 0 1 1 0 1 1 0 1 1 0 1 1 0 1 0 0 0 0 1 0 0 0 0 0 1 1 1 1 0 0 1 1 0 1 0 1 1 0 1 0 0 0 0 1 0 0 0 1 0 0 1 0 1 1 0 0 1 1 1 0 0 1 1 0 0 1 0 1 1 1 1 1 0 0 0 1 1 1 1 0 1 0 1 1
0 0 1 1 1 1 0 0 0 1 0 0 0 0 1 1 0 1 0 0 1 0 1 0 0 1 0 1 0 0 0 0 0 0 1 0 0 1 0 0 1 0 1 0 1 0 0 1 0 1 0 1 0 0 1 1 1 0 1 0 0 0 0 1 1 0 0 1 0 0 0 0 1 0 0 0 0 0 0 1 1 0 1 0 1 1 0 0 1 1 1 0 1 0 1 1 0 0 1 1
1 1 1 1 0 1 0 1 0 1 1 1 0 1 0 0 1 0 0 1 1 0 0 0 1 1 0 1 1 1 1 1 0 0 1 0 0 0 0 0 0 1 0 0 1 0 1 1 1 1 1 0 0 1 0 0 1 0 1 0 0 0 0 0 0 1 0 1 0 0 1 0 0 1 1 0 0 0 0 1 0 1 0 0 1 0 1 1 0 1 0 1 0 1 0 0 1 1 1 1
1 0 1 0 1 1 0 0 1 1 1 0 0 1 1 0 0 0 0 0 1 1 1 0 0 1 1 1 1 1 0 1 1 0 0 1 0 0 0 1 1 0 1 0 0 1 0 0 1 1 0 1 1 0 0 0 1 1 0 0 1 1 1 0 0 0 1 1 1 1 0 0 1 0 0 1 0 0 1 1 0 0 0 1 0 1 0 0 1 1 0 1 0 1 1 1 1 0 0 0
1 0 1 0 0 0 0 1 1 0 1 1 0 0 0 0 0 0 1 1 0 1 1 0 1 1 0 0 0 1 0 0 1 0 0 1 0 1 0 0 1 1 0 0 1 0 1 1 0 1 1 1 0 0 0 0 1 1 0 1 1 0 0 0 0 1 1 0 1 1 1 1 1 0 1 0 1 1 0 1 1 1 0 1 0 1 1 0 0 1 0 0 0 1 1 1 0 0 0 0
0 0 0 0 0 0 1 0 1 1 1 1 1 0 1 1 1 0 0 1 0 1 0 0 0 1 1 1 1 0 0 1 0 0 1 0 1 0 1 0 0 0 1 1 1 1 0 1 0 0 1 0 1 0 1 1 1 1 0 0 0 0 1 0 1 0 1 1 0 1 1 1 0 1 0 0 1 0 1 0 0 1 1 1 1 0 0 1 0 0 0 0 1 1 0 1 1 1 0 0
0 1 0 0 1 1 0 0 0 1 1 1 1 1 0 1 1 1 0 0 1 1 1 1 1 1 0 0 1 0 1 0 1 0 0 0 1 0 1 1 1 1 1 1 0 0 0 0 1 1 1 1 1 1 1 1 0 1 1 1 1 1 0 1 0 0 1 1 1 1 1 0 0 0 1 0 0 0 1 1 0 1 0 1 1 0 1 0 0 1 0 0 0 1 0 0 1 1 1 0
1 0 1 0 0 1 1 1 1 0 0 1 1 0 1 0 1 1 1 0 0 1 1 0 0 1 1 1 0 1 1 1 1 0 1 0 1 1 0 0 1 1 1 1 1 0 1 0 1 0 1 1 1 1 0 0 0 0 1 0 1 0 0 0 0 0 1 0 0 0 1 0 1 1 0 0 0 0 1 0 0 0 1 0 1 1 1 0 1 0 0 1 1 0 0 1 1 0 1 1
0 1 0 1 0 1 1 1 0 1 0 0 1 0 0 0 1 0 0 1 1 0 0 1 0 1 0 1 1 0 1 1 1 0 0 1 1 0 0 0 1 1 1 1 1 1 1 0 1 1 1 0 1 1 1 1 0 0 0 1 0 0 1 0 1 1 0 0 1 0 1 0 0 0 0 0 1 1 0 1 1 1 1 1 1 1 1 0 0 0 0 0 1 0 1 1 1 1 0 0
1 0 1 0 1 0 1 1 0 1 1 0 1 1 1 0 1 0 1 0 1 1 0 1 0 0 0 1 1 1 1 0 0 1 1 1 0 0 0 1 0 1 1 0 0 0 1 1 1 0 0 1 0 1 1 1 0 1 1 0 0 1 1 1 1 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 0 0 1 1 1 1 0 1 0 1 1 1 1 1 0 1 0 1 0 0
0 0 1 1 1 0 0 0 1 1 1 0 1 0 1 0 0 0 0 1 1 0 1 1 0 0 1 1 0 0 0 0 0 0 1 1 1 0 1 1 0 0 0 0 0 0 0 1 0 0 1 0 1 0 0 1 1 0 0 1 1 0 1 1 1 0 1 0 0 0 1 1 0 1 1 1 1 1 1 1 0 1 0 0 0 0 0 0 1 1 1 0 1 1 1 0 1 1 1 0
0 0 0 0 0 1 0 1 1 1 0 1 0 0 0 1 0 1 1 0 0 0 0 0 0 1 0 1 0 0 0 1 0 1 0 0 1 1 1 0 0 0 1 0 0 0 0 0 0 0 1 1 0 0 0 0 0 1 0 1 0 0 0 1 1 1 0 0 0 0 1 0 0 0 1 0 1 1 1 1 1 1 1 1 0 1 0 0 1 0 1 0 0 0 1 1 1 1 1 1
0 1 1 0 1 1 1 0 0 0 0 1 0 0 1 0 1 1 1 0 1 1 0 1 0 0 1 1 1 1 1 0 0 0 1 0 0 0 1 1 1 0 1 0 1 0 0 0 0 1 0 1 1 1 1 1 1 0 0 1 0 1 0 1 1 0 0 0 0 1 0 0 1 0 1 0 0 1 1 1 0 0 1 1 0 1 1 1 1 1 1 0 0 0 1 0 1 1 0 0
1 1 0 0 1 1 1 0 0 1 0 0 0 1 0 1 1 0 0 0 1 0 0 0 0 0 1 1 0 1 1 1 0 0 0 1 1 0 1 1 1 1 0 1 1 0 0 1 1 1 0 1 0 1 0 0 1 0 1 0 1 0 1 0 0 0 0 0 0 0 1 0 1 0 1 0 0 0 1 0 0 0 0 1 1 0 1 1 1 0 0 0 1 0 0 1 0 1 0 1
0 1 1 0 1 1 0 0 1 0 1 1 0 1 1 1 1 1 1 1 1 1 0 0 1 1 1 0 0 1 1 1 1 1 0 1 0 1 1 0 0 1 1 1 0 1 1 0 1 0 1 1 1 0 1 0 0 0 1 1 1 1 0 1 0 0 0 0 0 1 0 1 0 1 1 0 0 1 1 1 1 1 0 0 0 0 0 0 0 1 1 1 0 1 0 0 0 0 1 0
0 1 0 0 0 1 0 1 1 0 0 0 1 1 0 0 1 0 0 0 0 1 0 1 0 1 0 0 1 1 0 0 0 1 0 1 0 1 1 1 1 0 0 0 1 1 1 1 1 0 1 0 0 1 1 1 1 1 1 1 1 0 1 1 1 1 1 0 1 1 0 1 1 1 1 0 1 0 1 0 0 0 1 1 1 0 1 0 0 1 1 1 1 1 0 0 0 0 0 0
1 0 1 1 1 1 0 0 1 1 0 0 0 0 1 1 1 1 0 1 0 0 0 0 0 0 1 0 0 1 1 1 1 1 1 1 0 0 0 1 0 1 1 1 0 1 0 1 0 0 1 0 1 0 1 1 1 0 0 1 0 1 0 1 0 0 1 0 0 0 1 1 0 0 1 0 1 0 0 1 0 0 1 0 0 0 0 1 0 1 0 0 1 0 0 0 0 0 1 0
1 1 0 1 1 1 1 1 1 1 0 1 1 1 0 1 0 1 0 0 0 1 1 1 0 0 1 0 0 1 1 0 0 0 1 1 1 1 1 0 0 1 0 1 1 0 0 0 0 1 0 1 1 0 1 1 0 1 1 0 0 0 1 0 1 0 0 1 0 1 1 0 1 0 1 0 0 0 0 1 1 0 0 1 0 1 1 1 1 1 1 1 1 1 1 1 0 1 0 0
0 1 0 1 0 0 0 0 1 1 0 1 0 1 0 1 1 1 0 1 0 0 0 1 1 1 1 0 0 1 1 0 1 0 1 0 1 1 1 0 1 1 1 1 0 1 1 1 0 1 1 0 0 1 1 1 1 1 0 1 1 0 1 1 0 1 0 0 1 0 1 1 1 0 1 0 1 0 0 1 0 0 0 1 1 1 0 0 0 0 1 0 1 0 0 0 0 1 0 0
1 1 1 0 1 1 1 1 1 0 0 0 0 0 0 1 0 0 0 1 0 1 1 1 0 1 1 0 1 1 1 1 1 0 1 1 1 0 1 1 0 0 0 0 1 1 1 1 1 1 1 1 0 0 0 0 1 0 1 1 1 1 1 1 1 0 1 1 1 0 1 1 1 0 1 1 0 0 1 0 1 0 0 0 0 1 0 0 0 1 1 1 1 0 0 0 1 1 0 1
1 1 1 0 0 1 0 0 1 1 0 1 1 0 1 0 0 0 1 0 0 1 0 0 1 1 0 0 0 0 0 1 0 0 0 0 1 0 1 0 0 0 1 0 1 0 0 1 1 0 1 0 0 0 1 0 1 1 0 1 0 0 0 1 0 0 0 0 0 0 0 1 0 0 1 0 0 0 0 1 0 0 0 0 1 0 0 1 0 0 0 0 0 1 0 0 0 1 0 1
1 1 1 0 1 0 1 1 1 0 1 1 1 0 0 0 1 0 0 0 0 0 1 0 1 0 0 0 0 0 1 1 0 0 0 0 0 1 1 1 0 0 1 1 1 1 1 1 1 0 1 0 1 1 0 0 1 1 1 1 0 1 1 1 1 0 0 1 1 0 1 1 1 1 1 0 1 0 0 1 1 1 0 0 1 0 1 1 1 1 0 0 0 0 0 0 0 1 1 1
0 0 1 0 0 1 0 1 1 1 0 0 0 1 0 0 0 1 0 0 1 1 1 0 0 1 1 0 0 0 1 1 0 1 1 1 0 1 1 1 0 1 0 0 1 0 0 1 1 1 0 1 1 1 0 0 1 1 1 1 0 1 1 0 1 1 0 1 0 0 1 1 0 0 1 1 1 1 1 0 1 0 0 0 0 0 1 0 0 1 0 0 0 0 0 0 0 0 0 0
1 0 0 0 1 0 0 1 1 0 0 1 0 0 1 0 0 0 0 1 0 0 0 0 0 1 1 0 0 1 0 0 0 1 0 1 0 0 1 0 1 0 1 1 0 1 0 1 0 0 0 1 1 0 0 0 0 0 1 0 1 1 0 0 1 1 0 0 0 0 1 1 1 0 0 0 0 1 0 1 0 1 1 0 1 0 0 1 0 1 0 0 1 0 1 1 1 1 0 0
0 0 1 1 0 0 1 0 1 0 1 0 1 0 1 0 1 1 1 1 1 0 1 1 0 1 1 1 1 0 0 0 1 0 0 1 0 0 0 1 0 1 0 1 1 0 1 0 1 1 1 0 1 0 1 0 0 1 0 1 1 1 1 1 0 1 1 0 1 0 0 0 0 0 0 0 1 0 0 0 1 0 1 0 0 0 1 1 1 1 1 1 1 1 1 0 1 0 0 0
0 1 0 0 0 1 0 0 1 1 1 0 0 0 1 0 1 0 1 0 1 0 0 1 1 0 0 1 0 1 1 0 0 1 1 1 0 1 0 1 1 1 1 0 0 0 0 1 1 1 0 1 1 1 1 1 1 0 1 1 0 0 0 1 0 1 1 1 1 0 1 0 1 0 1 0 0 1 0 0 1 0 0 0 0 0 1 0 1 0 1 0 1 1 0 1 0 0 1 1
0 1 1 0 0 0 0 0 1 1 1 1 1 1 0 1 0 1 0 1 0 1 1 1 0 1 0 1 1 1 1 1 1 0 0 0 0 1 1 0 0 0 0 1 0 1 1 0 0 1 1 1 0 0 1 1 1 0 1 0 1 0 1 0 1 1 1 1 1 1 0 1 1 0 1 1 1 0 0 0 1 1 1 1 0 0 0 1 1 1 1 0 1 1 1 0 0 1 1 0
1 0 1 0 0 0 1 1 0 0 0 0 0 1 0 0 0 0 0 0 0 1 1 0 1 0 0 0 0 0 1 1 1 1 0 1 1 1 0 1 0 1 1 1 1 1 0 0 0 1 0 1 0 0 0 0 1 0 0 1 0 1 0 0 0 0 1 0 0 1 0 1 1 1 1 1 1 0 1 1 1 0 1 1 1 0 1 0 1 1 1 1 1 0 1 0 0 1 1 0
0 1 1 0 1 1 0 0 0 0 0 0 1 0 1 0 1 0 0 0 0 1 1 1 0 1 0 0 0 0 0 0 0 0 1 1 0 1 0 1 1 0 0 0 1 0 0 1 0 0 1 0 1 0 1 1 1 1 0 0 0 0 1 1 1 0 0 1 1 0 0 1 0 0 0 1 0 1 0 1 0 0 1 1 1 0 0 1 0 0 1 0 0 1 0 1 1 1 0 0
0 1 0 1 0 1 0 1 1 0 1 1 0 0 0 1 1 1 0 1 1 0 1 0 1 0 1 0 1 0 1 1 0 1 1 0 1 1 1 1 0 0 1 0 0 0 1 0 0 1 1 1 0 1 1 1 0 1 0 1 1 0 1 1 1 0 1 1 0 1 1 1 1 0 1 0 1 0 0 0 0 0 0 1 0 1 1 1 0 0 1 1 0 0 0 0 0 0 0 1
0 1 0 0 0 0 1 1 0 0 0 1 0 1 1 0 1 1 0 0 0 0 1 0 1 0 1 0 0 0 0 1 0 1 0 0 0 1 0 1 1 0 0 1 0 0 0 1 1 1 1 0 1 1 0 1 1 1 1 1 0 0 1 0 0 0 0 0 0 1 0 1 0 0 0 1 1 1 1 1 1 1 0 1 1 0 0 0 1 0 1 1 1 1 0 1 0 1 1 1
0 1 1 1 0 0 1 1 0 1 0 0 1 1 0 0 0 0 0 1 0 1 0 1 0 0 1 1 0 0 0 1 1 0 0 1 0 0 0 1 1 0 1 1 0 0 0 1 1 1 1 1 0 1 1 0 0 0 1 1 1 0 0 1 0 0 0 0 0 0 1 1 0 1 1 1 1 0 0 0 1 0 1 1 0 1 0 0 0 0 1 1 0 0 0 0 0 1 1 0
1 0 1 1 1 1 1 1 1 0 1 0 1 0 0 0 1 0 0 1 0 1 0 1 1 0 0 0 0 0 0 0 0 0 1 1 0 1 0 0 1 0 0 0 0 0 1 1 1 0 1 1 0 1 0 1 1 1 1 1 0 1 1 1 0 1 0 0 0 1 0 0 0 0 1 1 0 0 1 0 0 1 1 0 0 1 0 1 1 1 1 1 1 0 0 1 1 0 0 1
1 0 0 1 0 0 0 1 1 1 0 0 0 0 1 1 0 1 1 0 0 0 0 1 0 0 1 0 0 0 1 0 0 0 0 0 1 1 0 0 1 1 0 1 1 1 1 1 1 1 1 1 1 0 1 0 1 0 1 1 0 0 1 0 1 0 1 1 1 0 1 1 0 1 0 0 1 0 1 1 0 0 1 1 0 1 1 0 1 1 0 1 1 0 0 1 0 1 0 1
1 1 1 0 0 1 0 0 0 1 0 1 1 0 0 0 1 1 1 0 1 1 1 0 0 1 1 1 1 0 0 1 1 0 0 1 1 1 0 1 0 0 1 1 0 1 0 0 0 1 1 0 1 1 1 0 0 1 0 1 0 0 1 0 1 0 1 0 1 0 1 0 0 0 1 1 0 0 0 0 0 1 0 1 1 0 0 0 1 0 0 1 1 1 0 1 1 1 1 0
1 1 1 0 0 0 1 0 0 1 0 1 0 1 1 0 1 0 0 1 0 0 0 0 1 1 0 0 0 0 0 0 1 0 0 1 0 0 0 1 1 1 0 0 0 0 0 0 0 1 1 0 0 1 0 0 1 0 1 0 0 0 1 0 0 0 1 1 0 1 1 0 1 0 0 1 0 1 0 0 1 1 0 1 0 0 0 1 1 0 1 0 0 0 1 0 1 0 0 0
0 1 0 0 1 0 1 1 0 0 1 1 1 0 1 1 0 1 0 0 1 1 0 1 0 0 0 0 0 1 1 1 1 0 0 1 0 0 0 0 1 0 1 1 0 0 1 1 1 1 1 0 1 1 0 1 0 1 0 0 0 0 0 1 0 0 0 0 1 1 1 1 1 1 1 1 0 0 1 1 1 0 1 1 1 1 0 1 0 1 1 0 1 0 0 0 0 0 1 1
1 0 0 1 1 1 0 1 0 0 0 0 0 1 1 0 0 0 1 1 1 1 0 1 1 1 1 1 0 0 0 0 1 0 0 1 0 0 0 0 0 1 0 0 0 1 0 0 0 1 1 1 1 1 0 1 1 1 0 1 1 0 1 0 0 0 1 0 0 0 0 1 1 1 1 0 0 1 0 1 1 0 0 0 0 1 1 0 1 0 0 1 0 0 0 1 1 1 1 0
0 1 1 1 0 1 0 0 1 1 0 1 0 1 1 0 0 1 1 1 0 0 1 0 0 0 0 1 1 1 1 1 1 1 0 0 0 1 1 1 0 0 0 1 1 1 0 1 1 1 0 1 0 0 1 1 1 0 1 0 1 0 0 0 0 1 1 0 0 1 0 0 0 1 1 1 1 1 0 0 0 0 0 1 1 0 0 1 0 0 1 0 0 0 0 0 0 1 1 1
1 1 0 1 0 1 1 1 1 0 1 1 1 1 1 0 0 1 1 0 1 1 0 0 1 1 0 0 0 1 1 0 0 1 1 1 1 1 0 1 0 1 1 0 1 1 0 1 1 0 0 1 0 1 0 1 1 1 1 0 0 1 0 0 0 0 1 0 0 0 1 1 1 1 0 0 1 0 0 0 0 0 0 0 1 1 1 1 1 1 0 0 0 0 1 1 0 1 0 0
1 0 0 0 0 1 0 1 1 0 0 0 1 1 0 0 0 0 0 0 1 1 0 0 1 0 1 1 1 0 0 0 1 1 0 0 1 1 1 0 1 0 1 0 1 0 1 1 0 1 0 1 0 1 0 1 0 1 0 0 0 0 1 0 1 1 1 0 1 0 0 0 1 1 0 0 1 0 1 1 0 0 1 1 1 1 0 0 1 1 0 1 1 1 0 1 1 0 1 0
0 0 1 0 1 1 0 0 1 1 1 0 1 1 1 0 0 1 0 1 1 0 1 1 1 0 0 0 1 1 1 0 1 1 1 0 0 0 0 0 0 1 1 1 1 1 1 0 0 1 0 1 1 1 1 0 0 1 0 0 0 0 0 1 1 1 0 0 1 1 0 1 1 1 1 0 0 1 1 1 1 0 0 1 1 1 1 0 1 0 1 1 1 1 0 0 0 1 0 0
0 1 1 1 0 1 1 0 0 1 1 1 0 0 0 0 0 0 1 0 0 0 1 1 0 1 1 1 0 0 0 0 1 1 1 1 1 1 1 0 1 1 1 1 0 0 0 0 1 0 1 1 0 1 0 1 0 1 0 1 0 0 0 1 1 0 0 1 0 0 0 1 1 1 1 1 0 1 0 0 0 1 1 1 1 1 0 1 0 1 0 0 0 1 0 1 0 0 1 1
1 0 0 0 1 0 1 1 1 1 0 1 0 1 0 0 1 0 0 0 1 0 1 1 1 1 1 1 0 0 1 0 1 0 1 0 1 1 0 0 0 1 0 0 0 0 0 1 0 1 1 0 1 0 0 0 1 0 0 0 0 1 1 0 0 0 0 1 1 1 0 0 1 0 1 0 0 0 0 1 0 0 0 0 0 1 1 1 0 0 0 1 0 1 0 0 0 0 0 0
1 0 1 0 0 1 0 1 1 0 0 1 0 1 0 1 0 1 1 1 0 0 0 0 0 0 1 0 0 1 1 0 1 1 1 0 0 0 1 1 1 0 1 1 0 1 1 0 1 0 0 0 0 1 1 1 1 1 1 1 1 0 1 0 1 1 1 0 0 1 1 0 0 0 0 0 1 0 1 1 1 1 1 0 1 1 1 0 1 0 0 0 0 1 0 1 0 1 0 0
0 1 0 1 1 0 0 1 1 1 1 1 0 1 1 1 0 1 0 1 0 1 0 1 1 0 0 1 1 1 0 1 0 1 0 1 0 0 0 1 1 1 0 0 0 1 1 1 1 1 1 1 0 1 0 1 0 0 0 1 0 1 0 0 0 0 1 0 0 0 1 0 1 1 0 0 1 0 0 0 1 0 0 1 0 1 0 1 0 0 0 1 1 0 0 1 1 1 0 0
1 0 0 0 0 0 1 1 1 1 0 1 1 0 0 1 1 0 0 1 1 1 1 1 0 1 0 1 0 0 1 1 0 0 1 0 1 1 1 0 0 0 1 0 0 0 0 1 1 0 1 1 0 0 0 0 0 1 1 1 1 0 0 1 0 1 1 0 1 1 1 1 0 0 0 0 0 1 1 0 1 0 0 0 0 1 1 1 0 0 1 1 0 0 1 0 1 0 1 0
1 0 1 0 0 0 1 0 0 0 1 1 0 1 1 1 1 0 1 0 0 1 0 0 1 0 0 0 1 1 1 0 0 1 1 0 1 1 0 0 1 1 1 1 1 0 0 0 0 1 1 1 1 0 0 0 0 1 1 0 0 1 1 1 1 0 1 1 1 1 0 0 1 0 0 0 1 0 1 1 0 0 1 0 1 1 0 0 0 1 0 1 1 0 1 0 0 0 0 1
0 0 1 1 0 1 0 1 1 1 0 0 1 0 0 0 0 1 0 0 1 0 0 0 0 1 1 0 0 1 0 0 1 1 0 0 1 0 1 0 1 1 0 0 0 0 1 0 1 1 1 0 1 1 0 0 1 1 0 1 0 1 0 1 0 1 1 1 0 0 1 1 0 1 1 0 0 0 0 1 1 1 0 1 0 0 1 1 1 0 0 1 0 1 1 0 1 1 1 1
1 1 1 0 1 0 0 0 1 1 1 0 0 0 1 0 1 1 0 0 0 1 0 0 0 1 0 1 0 0 0 0 0 1 0 1 0 1 0 1 1 1 1 1 0 0 0 1 1 0 0 1 1 0 0 1 1 1 0 0 0 0 0 0 0 1 1 0 0 0 0 0 0 1 1 1 1 1 1 0 1 1 1 1 0 1 1 1 0 0 1 1 0 0 1 1 1 1 1 1
0 1 1 0 1 1 0 0 0 1 1 1 1 0 0 1 0 1 0 0 1 0 1 1 1 1 1 0 1 0 0 1 1 0 1 1 1 0 1 0 0 0 1 0 1 0 1 0 0 0 0 1 0 1 1 0 1 0 0 1 1 1 0 0 1 0 1 1 1 1 1 0 0 1 1 0 1 0 1 0 1 1 1 0 0 0 1 0 1 0 0 1 1 0 0 1 0 1 0 0
0 1 1 0 0 0 1 0 0 0 1 1 1 0 1 1 1 1 0 1 0 0 1 1 0 1 0 1 1 0 1 0 0 1 0 1 1 1 0 0 1 0 1 1 1 1 1 0 0 0 0 1 1 0 0 1 1 1 0 1 0 0 1 0 1 0 1 1 1 1 0 1 1 1 0 1 1 1 0 0 1 0 0 0 1 1 1 1 0 1 0 1 0 1 1 1 0 0 1 0
1 1 1 1 0 1 0 1 1 1 0 1 1 1 1 0 0 0 1 0 0 1 0 1 1 0 0 0 0 1 1 1 0 0 1 1 0 0 1 1 0 0 1 1 0 1 0 0 0 0 0 0 0 0 0 0 1 1 0 1 1 1 0 0 0 0 1 1 0 0 1 0 0 1 1 0 0 1 1 0 0 0 0 1 0 1 0 1 1 1 0 1 1 0 0 1 0 0 1 1
1 1 1 1 1 0 1 0 1 1 0 0 1 0 0 1 1 1 1 1 1 0 1 1 1 1 1 1 1 0 1 1 1 1 0 0 1 0 1 1 0 0 1 0 0 0 1 1 1 0 0 0 0 0 0 1 0 0 0 1 0 0 0 0 1 1 0 1 1 1 1 1 0 1 0 0 1 0 1 0 1 0 1 1 0 1 1 0 1 0 0 0 0 1 1 0 1 0 1 0
0 1 1 1 1 0 0 1 0 0 0 0 0 1 0 1 0 0 1 0 0 1 0 1 0 0 1 0 0 0 0 0 1 1 0 1 0 1 0 0 0 1 0 0 1 0 1 1 0 0 0 0 1 1 1 0 1 1 0 0 1 0 1 0 0 0 0 1 1 1 0 1 1 0 1 0 1 1 1 1 1 0 0 0 1 0 1 1 0 1 0 0 1 0 1 1 1 0 0 0
1 1 0 1 1 1 1 1 0 0 1 1 0 0 1 0 1 0 0 0 0 1 1 0 0 0 0 0 1 0 1 0 1 0 1 0 0 1 0 0 1 0 1 1 0 1 1 1 0 1 1 1 1 1 1 0 0 1 0 0 1 0 1 0 1 1 0 0 1 0 0 0 0 0 0 1 0 0 1 1 0 0 0 0 0 0 0 0 1 0 0 0 1 0 1 0 1 1 1 0
0 1 0 0 0 0 0 0 1 0 1 1 0 0 1 0 0 0 1 0 0 0 1 0 0 0 1 1 0 0 1 1 0 1 1 0 1 1 1 0 1 0 1 1 0 1 0 0 1 1 0 0 1 0 1 1 1 1 0 0 0 1 0 1 0 1 1 1 0 1 1 0 1 1 0 1 0 1 1 0 0 0 0 0 1 1 0 1 0 1 1 1 1 0 0 0 0 0 0 1
0 1 0 1 0 0 1 1 1 0 1 0 0 1 1 0 1 0 1 0 0 1 1 1 0 0 0 0 1 0 1 1 0 0 1 1 0 0 1 0 1 0 1 0 1 1 1 1 0 1 0 0 1 1 1 1 0 0 0 0 1 1 1 1 0 1 0 0 1 1 0 1 1 0 1 0 0 1 0 0 0 1 0 0 1 1 0 0 1 0 0 0 1 1 1 1 1 0 1 0
1 1 1 1 0 1 1 0 0 1 0 0 1 1 1 1 0 1 0 1 0 1 1 0 1 0 1 0 0 0 0 1 0 0 0 0 1 0 0 0 1 0 1 0 0 1 0 0 1 0 1 1 1 0 0 0 1 0 0 0 0 0 1 1 1 0 0 1 1 0 0 0 1 1 1 1 0 0 1 0 1 0 0 0 1 1 0 0 0 1 1 1 0 0 1 1 0 0 1 1
0 0 0 0 0 1 0 0 0 1 1 1 0 1 1 1 0 1 1 1 0 0 0 0 0 1 1 0 0 0 0 0 1 0 0 0 0 0 1 0 1 0 1 1 1 1 0 0 0 0 1 1 0 1 1 1 1 1 0 0 0 1 1 0 1 0 0 1 0 1 1 1 1 1 0 1 0 1 1 1 0 1 1 1 0 0 1 0 1 1 0 1 1 1 0 1 1 1 1 0
1 1 1 1 0 1 1 1 1 1 0 0 0 1 1 1 0 0 1 0 0 0 1 1 0 1 0 1 0 0 0 1 0 1 1 1 1 1 1 1 1 0 1 0 0 0 0 0 0 1 0 1 0 0 0 1 1 0 0 0 1 1 0 0 0 0 1 0 0 1 1 1 1 0 1 0 0 0 1 0 0 1 0 0 1 1 1 1 0 0 0 1 1 1 1 1 0 1 1 0
0 0 0 1 0 1 1 1 1 0 1 1 1 1 0 1 1 1 1 1 1 0 0 0 1 0 1 0 0 0 0 1 0 0 0 0 1 0 1 1 1 0 1 1 1 1 1 0 1 0 0 0 0 0 1 1 0 0 1 1 0 0 0 0 1 0 0 0 1 1 1 1 0 0 0 1 1 0 1 0 0 1 0 1 1 0 1 0 1 1 0 0 1 1 1 1 0 0 1 1
0 1 0 0 0 0 1 0 1 1 1 1 0 1 0 1 1 1 0 1 0 1 0 0 1 1 0 1 0 1 0 0 0 1 1 1 0 1 1 0 0 0 0 0 1 1 0 0 0 1 0 0 1 1 0 0 0 1 0 1 1 0 0 1 0 0 1 1 1 1 0 0 0 1 0 0 1 0 1 1 0 0 0 0 1 0 1 0 1 1 1 0 1 1 1 1 0 1 1 0
0 0 1 1 0 1 1 1 1 1 1 1 0 1 0 0 1 0 1 1 0 0 0 1 1 1 0 0 0 0 1 1 1 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0 0 0 0 1 1 1 0 1 0 1 1 0 1 0 1 1 0 0 1 0 0 1 0 1 1 1 0 1 0 1 0 0 0 0 1 0 1 1 1 1 0 1 1 0 1 0 0 1 1 0 0 1
1 0 1 1 0 1 1 0 0 1 0 0 1 1 1 0 0 1 0 0 1 0 0 0 0 0 0 0 0 1 1 0 1 1 0 1 0 0 1 0 1 0 1 1 1 1 0 1 0 0 0 0 1 1 0 0 1 0 0 0 1 0 1 0 1 1 1 1 0 0 1 1 0 1 1 0 1 0 0 1 0 1 0 0 0 0 1 0 1 1 1 0 0 0 0 0 1 0 0 1
1 1 1 0 0 1 0 0 1 0 1 0 0 1 1 0 1 0 0 1 0 0 1 1 1 1 0 1 0 0 0 0 0 0 1 1 1 1 1 0 1 0 0 1 0 0 0 0 1 0 1 1 0 0 1 1 0 0 0 0 1 0 0 1 0 1 1 1 0 1 0 1 0 0 1 0 0 0 1 0 0 1 0 1 1 0 1 0 1 0 1 0 0 1 1 1 0 0 1 0
1 0 0 0 0 1 0 1 0 0 0 0 0 0 0 0 1 0 1 0 1 1 1 1 0 0 1 0 1 0 0 1 0 1 0 0 0 1 1 0 1 0 0 0 1 1 0 0 1 0 0 1 1 0 0 1 1 0 1 1 1 1 1 0 1 1 1 1 1 0 1 1 1 0 0 0 1 1 0 1 1 1 1 1 0 0 1 0 1 0 1 0 0 0 0 0 0 1 0 1
1 1 1 1 0 0 1 0 1 1 1 1 1 0 0 1 1 0 1 0 1 0 1 1 1 1 0 0 1 0 1 1 0 1 1 1 1 0 1 1 1 0 1 0 1 1 0 0 0 0 0 1 0 0 0 1 0 0 1 0 1 0 1 0 0 1 1 0 0 0 0 0 1 1 1 1 0 1 0 0 0 1 0 1 1 1 0 1 0 0 0 0 1 0 1 0 0 1 0 1
0 1 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 1 0 0 1 1 1 0 1 1 0 1 1 1 0 1 0 1 0 0 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 1 1 0 1 1 0 0 1 1 1 0 1 1 1 1 1 0 1 0 1 0 1 0 0 0 0 0 0 1 0 1 0 1 1 1 0 0 0 0 1 1 0 0 1 0 1 0 0 1
0 0 1 0 0 0 1 0 0 0 1 1 0 1 1 1 0 1 0 0 0 0 1 1 1 0 1 1 0 0 0 0 0 0 0 1 0 0 0 1 0 1 0 1 0 0 1 1 1 1 1 1 1 1 1 0 0 0 1 0 0 1 1 1 0 0 1 1 1 0 0 0 1 1 1 0 0 1 0 0 1 1 1 0 1 0 1 1 0 1 0 0 0 0 1 1 0 1 0 1
1 0 0 1 1 0 0 1 1 1 1 0 1 0 1 0 0 0 0 1 1 1 0 1 1 1 1 0 0 0 1 1 0 1 1 0 1 0 0 0 1 0 1 1 1 1 0 0 1 0 0 1 1 1 1 0 0 0 1 1 0 1 0 1 1 1 0 1 1 0 0 0 1 1 1 1 0 1 1 0 0 1 1 1 0 0 1 1 1 0 1 1 0 0 1 1 1 1 1 0
1 1 1 0 1 0 1 1 1 1 1 1 0 0 1 0 1 0 1 0 0 0 0 0 0 0 0 0 1 1 1 0 1 0 1 0 0 1 1 1 1 1 1 1 1 0 0 0 1 1 0 1 1 1 1 1 0 1 1 0 1 1 1 1 1 1 1 1 1 0 1 0 1 0 0 1 0 1 0 1 0 0 1 0 1 1 0 1 0 1 1 1 1 1 0 0 0 0 1 1
0 1 0 1 0 1 0 1 1 1 1 0 0 1 0 0 1 1 1 0 1 1 1 1 1 1 0 1 1 0 0 0 1 1 1 1 1 1 0 1 1 1 0 1 0 1 0 0 0 1 0 1 0 0 0 0 1 1 0 0 1 0 1 1 0 0 1 1 0 1 1 0 1 1 0 0 1 1 0 1 1 0 1 0 1 1 1 0 1 0 0 1 1 1 0 0 0 0 0 1
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 0 1 1 1 1 0 0 0 0 0 1 0 0 0 0 0 1 1 1 0 0 1 0 0 1 1 1 1 0 1 1 0 1 1 1 0 1 0 1 1 0 1 0 1 1 0 1 0 0 0 0 0 0 1 1 1 1 0 0 0 1 1 1 0 1 1 1 0 0 1 1 1 0 0 1 1 1 0 1 0 1 1 1 1
1 0 1 1 0 1 0 0 1 1 1 1 1 0 1 1 0 1 1 1 0 1 0 0 0 0 1 0 1 1 0 0 0 0 1 1 0 0 1 0 1 0 0 1 0 1 1 1 1 1 0 0 1 0 0 0 0 0 0 0 1 0 1 0 1 1 1 1 1 0 0 0 1 0 0 1 1 1 0 1 0 0 0 1 0 0 0 0 1 1 0 1 0 0 0 0 0 0 1 0
0 1 1 1 1 1 0 1 0 1 0 1 0 0 1 0 1 1 0 1 0 1 1 0 0 0 1 0 1 0 1 0 0 1 0 0 0 0 1 0 1 0 1 0 0 1 0 0 0 1 1 0 1 0 0 1 0 1 0 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1 0 0 0 1 0 0 1 0 1 0 1 0 1 1 1 1 1 1 0 0 1 0 0
1 0 0 0 0 1 0 1 0 0 1 1 1 1 1 1 1 1 0 0 1 0 0 1 0 0 1 1 1 1 1 1 1 0 0 0 1 1 0 1 1 1 1 0 1 0 0 0 0 1 0 0 1 0 0 0 0 0 0 0 0 1 1 1 0 1 1 0 0 0 1 1 0 0 0 1 1 0 1 1 0 0 0 1 0 0 0 0 1 1 1 1 0 0 0 0 0 1 1 1
1 0 0 0 0 0 0 0 0 1 1 0 0 0 0 0 0 1 0 0 1 1 1 1 0 1 1 1 1 0 0 0 1 1 1 1 1 1 0 1 0 1 0 1 0 1 1 0 1 1 1 0 0 1 1 0 0 1 0 1 0 0 1 1 0 0 1 1 1 0 0 0 0 1 0 1 1 0 0 1 0 1 0 0 0 0 0 1 0 0 0 1 0 0 0 0 0 0 1 0
0 1 1 0 0 1 0 1 1 0 1 1 1 1 0 0 0 0 1 0 0 0 1 1 0 1 1 1 0 1 0 0 0 1 1 1 0 1 1 1 1 1 0 0 1 0 0 0 1 1 1 1 0 0 1 1 1 0 1 0 0 0 0 0 1 1 1 0 1 0 1 1 1 1 1 0 0 1 1 0 0 1 1 1 1 0 0 0 1 1 1 0 0 0 1 0 1 0 0 0
1 1 1 0 1 0 0 1 1 1 1 0 0 1 0 0 0 1 0 0 0 1 1 1 1 0 0 1 0 0 1 1 0 0 0 1 1 1 0 0 0 0 1 1 1 1 0 1 1 0 0 1 0 0 0 1 1 0 0 0 1 0 0 0 1 1 1 1 0 1 0 1 0 0 1 0 1 1 1 1 0 0 1 0 1 1 0 1 0 0 1 1 0 0 1 1 1 0 0 1
1 0 1 1 1 0 0 1 0 0 1 0 1 1 1 0 1 0 0 1 1 0 0 0 1 0 1 1 1 1 0 0 0 1 0 0 1 0 0 0 1 1 0 0 1 1 0 1 1 1 0 0 1 0 1 1 0 0 0 0 1 0 0 0 1 1 1 0 1 1 0 1 0 0 1 1 1 0 1 1 1 1 0 1 0 0 1 1 1 0 0 0 0 1 1 0 0 1 1 1
1 0 0 1 0 0 1 0 1 0 0 1 1 1 1 0 1 1 1 0 1 0 0 0 0 0 1 1 1 1 0 0 0 0 0 1 1 0 1 0 1 0 1 0 0 0 0 0 1 0 0 0 0 1 0 1 0 0 0 0 0 0 0 0 0 0 0 0 1 1 1 1 0 0 0 1 1 1 1 1 1 0 0 1 0 0 1 0 1 1 0 0 1 0 1 1 1 1 1 1
1 0 0 0 0 1 0 1 0 0 1 0 0 1 0 0 0 0 0 0 1 1 1 1 0 0 0 1 0 1 1 1 1 0 1 1 0 1 1 1 0 1 1 0 1 0 1 0 1 0 0 0 1 0 1 1 1 0 1 0 1 1 1 1 1 1 0 1 0 1 0 1 1 0 1 0 1 1 0 0 1 1 0 1 0 1 1 1 0 0 1 1 1 1 0 1 1 1 0 1
1 0 0 1 0 0 0 0 0 1 0 0 1 1 0 0 1 1 1 1 1 0 1 1 1 0 1 0 0 1 0 0 1 1 1 1 0 0 1 1 0 1 1 0 1 1 1 0 0 1 1 1 1 1 1 0 0 1 0 0 1 0 0 1 0 1 0 0 1 0 1 1 0 1 0 1 0 1 0 0 0 0 1 0 1 1 0 0 0 0 1 1 0 1 0 0 1 0 0 1
1 0 0 0 1 0 1 1 0 1 0 1 0 1 0 1 1 1 0 1 0 1 0 0 0 1 0 0 1 1 0 1 1 0 0 0 1 1 1 0 0 1 1 1 1 1 1 0 1 0 0 0 1 0 0 1 1 1 1 1 1 1 1 1 0 0 0 1 1 0 1 1 0 0 0 1 0 0 0 0 0 0 0 0 1 0 1 0 0 1 0 0 1 0 0 1 1 1 1 0
0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 1 0 1 0 1 1 0 1 1 1 1 0 0 1 1 0 1 0 1 0 1 1 0 0 0 1 1 0 0 0 0 0 0 1 1 0 0 1 0 1 1 1 0 1 0 1 1 1 0 0 1 0 1 0 0 1 1 1 1 0 1 0 0 1 1 1 1 0 0 1 1 1 1 1 1 0 1 0 0 0 1 0 1 0
0 0 1 0 0 0 0 0 1 1 1 0 1 0 0 0 0 1 0 1 0 1 1 1 0 1 1 0 0 0 1 1 0 1 0 0 1 0 1 1 0 1 1 1 1 1 0 0 0 1 0 0 1 0 0 0 1 1 0 0 1 0 1 1 0 0 1 1 1 0 1 0 0 0 0 0 1 0 1 0 0 1 1 1 0 1 0 1 1 1 0 1 0 1 1 0 1 0 0 0
1 1 0 0 1 0 0 1 1 1 1 1 1 1 1 1 1 1 0 0 1 0 0 1 1 1 1 0 1 0 0 0 0 1 0 1 1 0 0 0 1 1 1 0 1 1 1 0 1 0 0 0 0 1 1 1 1 1 0 0 1 1 0 1 0 0 0 1 0 0 0 1 1 1 1 0 1 1 0 1 0 1 0 1 1 0 1 0 1 0 1 1 0 1 1 1 0 0 1 0
1 1 1 1 0 1 0 1 0 1 1 1 0 1 1 0 1 1 0 1 0 1 1 1 1 1 1 0 1 1 0 0 1 0 1 1 1 1 0 1 0 1 1 0 0 1 0 1 1 0 1 1 1 1 1 1 0 1 0 0 1 1 1 0 1 0 1 0 1 0 1 0 1 0 0 0 1 1 1 1 0 1 1 1 0 1 1 0 0 1 1 1 1 1 1 1 0 1 0 1
0 1 0 1 0 0 0 0 1 0 0 1 0 0 0 0 1 0 1 0 0 1 1 1 0 0 0 1 1 0 1 0 1 1 1 0 1 1 0 0 1 0 1 1 1 1 0 0 1 1 0 1 0 1 0 1 1 1 0 0 0 0 0 1 0 1 0 0 1 1 0 1 1 1 0 1 0 0 1 0 0 0 1 0 0 0 0 1 0 0 1 1 1 0 1 1 1 0 0 1
0 1 1 0 1 0 1 1 1 0 0 0 0 0 1 0 1 0 0 1 0 0 0 1 0 1 0 0 1 0 0 1 0 1 0 1 0 0 0 0 0 1 0 0 0 1 0 1 0 1 1 0 0 0 1 0 0 0 1 0 0 0 1 1 1 1 0 0 1 1 1 1 1 1 0 1 1 0 0 0 1 0 1 1 0 1 1 1 0 0 0 1 0 1 0 1 0 1 0 1
```

Behold! The matrix is enormous! 100 vertices in the X partition, 100 vertices in the Y partition, and
thousands of edges in between. Just scrolling through the matrix will make you feel like you're *in* The Matrix. Will the algorithm survive?

```
$ java -jar MaxBipartiteMatcher.jar < input100x100.txt
Enter the number of vertices in the independent set X:
Enter the number of vertices in the independent set Y:
Enter an |X| by |Y| matrix, where:
- a 1 means the ith vertex in X is adjacent to the jth vertex in Y
- a 0 means the ith vertex in X is not adjacent to the jth vertex in Y:
The maximum matching is:
0  0
1  1
2  2
3  4
4  3
5  5
6  14
7  8
8  7
9  9
10  15
11  10
12  13
13  11
14  6
15  12
16  16
17  19
18  18
19  20
20  17
21  21
22  26
23  24
24  23
25  29
26  22
27  25
28  28
29  31
30  30
31  33
32  35
33  27
34  34
35  32
36  36
37  37
38  38
39  39
40  40
41  46
42  41
43  42
44  49
45  43
46  45
47  44
48  47
49  51
50  52
51  48
52  50
53  53
54  55
55  57
56  58
57  56
58  65
59  54
60  59
61  60
62  64
63  62
64  68
65  61
66  63
67  67
68  69
69  66
70  70
71  73
72  71
73  74
74  78
75  72
76  75
77  79
78  77
79  81
80  82
81  76
82  80
83  83
84  85
85  88
86  87
87  84
88  90
89  86
90  89
91  91
92  93
93  92
94  96
95  94
96  95
97  97
98  99
99  -1
```

Now to my shock, the program matched all but 1 vertex for both X and Y.
It also took around 2.87 milliseconds, attesting to the algorithm's O(mn) time complexity (though I don't know enough about time complexity to say if this is efficient).
While there's no hope of me drawing this out to verify correctness, I'd wager 99 matches is probably a maximum matching.

Hopefully these examples were interesting enough to show off the power of this algorithm. Thank you for reading.
In case you need a direct link to the code and don't want to navigate the directories, you can find my implementation [here](https://github.com/shahiro770/MaxBipartiteMatcher/blob/master/src/com/MaxMatcherMans/Main.java).
