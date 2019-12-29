# Algorithms Notes

---

# Order of growth

1 , log N , N , Nlog N , N2 , N3 , 2N , log * N

---

# Union Find

In a collection find if two points are connected
union() (join two points)
find() (find if two points are connected)

- Quick find ( array with same value if they are of same set i.e connected )
- Quick union ( create a tree in union, find the root of the element for find )
- Weighted quick union ( add root of small tree to large tree -- log N)
- Path Compression ( once an elements root is found attach the element to root -- log * N)

---

# Sorting Algos

Any object if it implements abstract methods swap() and compare() we can sort a collection of those objects.

- Selection sort ( find the smallest and move to the start )
- Insertion sort ( compare adjacent elements and move smaller to the left )
- Shell sort ( Same as Insertion but move more than one spot )

---

# Shuffling

Uniform random permutation

- Shuffle sort ( assign a random number to each item and sort using sorting algorithms
- Knuth shuffle ( In iteration i, pick integer r between 0 and i uniformly at random and swap a[i] and a[r] -- linear)