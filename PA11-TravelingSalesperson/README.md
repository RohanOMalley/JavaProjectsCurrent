
Run on big11.mtx

heuristic: cost = 3.4, visitOrder = [1, 8, 2, 9, 11, 3, 4, 5, 10, 6, 7], 9 milliseconds
mine: cost = 3.4, visitOrder = [1, 8, 2, 9, 11, 3, 4, 5, 10, 6, 7], 1 milliseconds
backtrack: cost = 1.4, visitOrder = [1, 7, 8, 2, 3, 4, 6, 10, 5, 9, 11], 97 milliseconds

First off, heurisitc takes the classic approach of just looping through each neighboring
node when you get to each node. This process can take awhile and not find the fastest path.
Then the backtracking algorithim will compare the costs of the trips to try and find the 
minimum trip at that point. This can take awhile becuase many recursive calls are needed to
get to the final answer. For my algorithim, I thought to check if the city is available before
taking the weight then comparing it. This allows the for loop to run much more quickly than
the heuristic model.