Part 1: File Reading

There are two data structures used in Part 1, namely ArrayLists and arrays.

The ArrayLists store the information from the csv file, isolating the tags and storing them separately in alphabetical order.

The array was utilized to store the contents of the csv file, allowing for the extraction of the tags from the entire contents of the file.

The algorithms utilized in Part 1 were insertion sorts. They were used to sort the csv file as it was read in and to sort the ArrayLists in alphabetical order, which was crucial for Part 2.

The running time for Part 1 is mainly dependent on the algorithms used, which is insertion sort with a worst-case running time of O(n^2). Therefore, Part 1 has a running time of O(n^2) (Exponential).

Part 2: Tag Popularity

In Part 2 of the assignment, ArrayLists were used to count all of the tags' occurrences.

The ArrayList was used, with its elements index matching an ArrayList that was sorted in alphabetical order. This was crucial for the assignment, which resulted in finding the three highest and three lowest occurring tags.

Because of the alphabetical order, finding the three highest was done by iterating through the front of the ArrayList, while finding the three lowest was done by iterating from the back of the ArrayList.

When finding the three lowest and highest tags, a linear search algorithm is used, which finds the indices of the lowest values in the ArrayList containing the count of all the tags, working from the front and back of the ArrayList.

The running time of this part of the assignment mainly depends on the function that counts all the occurrences of the tags and stores them in an ArrayList, which has a running time of O(n^2) because of its nested for loop. 

The other functions that find the three highest and lowest tags have a running time of O(n). Therefore, Part 2 has a running time of O(n^2) (Exponential).

Part 3: Tag Search

In Part 3 of the assignment, ArrayLists and arrays were used, with the array having the same function as the array in Part 1. The previously created ArrayLists were used to find tags and their counts.

There are two types of search algorithms used in Part 3, both linear. One is used to find tags with a certain count, and the other to find the number of occurrences of a specific tag.

The running time in Part 3 is mainly dependent on the search algorithms, with a running time of O(n) where n is the size of the input data that it searches over. Therefore, the running time is O(n) (linear).