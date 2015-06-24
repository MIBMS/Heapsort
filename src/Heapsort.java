// MA314 LT2015 Homework 4 
/*
 * Ilham MA314 Class 2 Exercise 4
 * Did a few more functions to test the implemented bubbleup method
 */
class HeapTest
{
    public static void main(String[] args)
    {
        Heap a = new Heap(20, 20);
        a.randomizeArray(100);
        a.outArray("\nBefore sorting:");
        a.insertionSort();
        a.outArray("After Insertion Sort:");
        a.isHeap();
        a.buildHeap();
        a.outArray("The sorted array is turned into a maxheap using buildHeap method:");
        a.isHeap();
        int i = (int)(20*Math.random());
        int initvalue = a.value[i];
        int val = (int)(100*Math.random());
        a.change(i, val);
        a.outArray("Value at random position in the heap is changed into "
        		+ "a random number using change method:");
    	System.out.println("Value was changed at position " + i + " from " + 
    	initvalue + " to "+ val);
        a.isHeap();
        
// UNCOMMENT THE FOLLOWING AFTER YOU HAVE DONE (d) AND (e)
//        a.randomizeArray(100);
//        a.outArray("\nBefore sorting:");
//        a.heapSort();
//        a.outArray("After Heap Sort:");

        // Experiment with other sizes here:
        Heap b = new Heap(100000, 100000);
        b.randomizeArray(10000);
        System.out.println("\nStarting Insertion Sort on an array of size "
                + b.size + "....");
        b.insertionSort();
        System.out.println("Done!");
        if (b.isSorted())
            System.out.println("Insertion Sort worked.");
        else
            System.out.println("Insertion Sort failed.");

// UNCOMMENT THE FOLLOWING AFTER YOU HAVE DONE (d), (e) AND (f)
//        // Experiment with other sizes here:
          Heap c = new Heap(100000, 100000);
          c.randomizeArray(10000);
         System.out.println("\nStarting Heap Sort on an array of size "
                  + c.size + "....");
          c.heapSort();
          System.out.println("Done!");
          if (c.isSorted())
              System.out.println("Heap Sort worked.");
          else
              System.out.println("Heap Sort failed.");
    }
}

class Heap
{   // The Heap class is a data structure consisting of 2 pieces of data:
    // an array and a heapsize. However, because we let the first array 
    // index be 1, the length of the array will be one more than its size.
    // So we also create a variable containing its size.
    public int heapSize;
    public int size;
    public int[] value;

    public Heap(int heapSize, int size)
    // constructor method 
    // initialises the heapSize and size and creates 
    // the array
    {
        this.heapSize = heapSize;
        this.size = size;
        this.value = new int[size + 1];
    }

    public void outHeap(String message)
    {
        System.out.println(message);
        for (int i=1; i <= heapSize; i++)
            System.out.print(value[i] + " ");
        System.out.println();
    }

    public void outArray(String info)
    {
        System.out.println(info);
        for (int i=1; i <= size; i++)
            System.out.print(value[i] + " ");
        System.out.println();
    }

    public void randomizeArray(int max)
    {
        for (int i=1; i <= size; i++)
            value[i] = (int)(max * Math.random());
    }

    public void insertionSort()
    { // sorts value[1] to value[size]
      // instead of 0 to size - 1  

        // (a) INSERT YOUR OWN CODE HERE
		for (int j=2; j <= size; j++) 
		{
            // insert a[j] into the sorted sequence  a[0]...a[j-1]
			int key = value[j];
			int i = j-1;
			// find i so that key >= a[i] and move the 
			//elements a[i+1]...a[j-1] one to the right
			while ( i >= 0 && key < value[i] ) 
			{
				value[i+1] = value[i];
				i--;
			}
			// now either i<0 or key >= a[i]
			value[i+1]=key;
		}
    }

    public boolean isSorted()
    { // returns true if this.value[] is sorted from smallest to largest.
      // returns false if not.  

        // (b) INSERT YOUR OWN CODE HERE AND REMOVE THE FOLLOWING:
    	boolean sortedSoFar = true;	
    	for (int i=1; i<size; i++)
    		if (value[i]>value[i+1])
    		{
    			sortedSoFar = false;
    			//troubleshooting...
    			//System.out.println(i);
    			//System.out.println(value[i]);
    			//System.out.println(value[i+1]);
    			break;
    		}
    	return sortedSoFar;
    }

    public void siftdown(int i)
    {
        int temp = value[i];
        int child = maxchild(i);
        while (child != 0 && temp < value[child])
        {
            value[i] = value[child];
            i = child;
            child = maxchild(i);
        }
        value[i]=temp;
    }

    int maxchild(int i)
    { // Returns the child of node i with largest value.
      // Returns 0 if i has no children.

        // (d) INSERT YOUR OWN CODE HERE AND REMOVE THE FOLLOWING:
    	int child = 2*i;
    	if (child > heapSize)
    		return 0;
    	if (child == heapSize)
    		return child;
    	if (value[child] >= value[child+1])
    		return child;
    	else
    		return child + 1;
    }

    public void buildHeap()
    {
        // (e) INSERT YOUR OWN CODE HERE
    	for (int i = heapSize/2; i >= 1; i--)
    		siftdown(i);
    }

    public void heapSort()
    {
        // (f) INSERT YOUR OWN CODE HERE
    	buildHeap();
    	while (heapSize > 1)
    	{
    		int temp = value[1];
    		value[1] = value[heapSize];
    		value[heapSize] = temp;
    		heapSize--;
    		siftdown(1);    		
    	}
    		
    }

    public int extractMax()
    {
        // (g) INSERT YOUR OWN CODE HERE AND REMOVE THE FOLLOWING:
    	int max = value[1];
    	value [1] = value[heapSize];
    	heapSize--;
    	siftdown(1);
        return max;
    }

    public void bubbleup(int i)
    {
        int temp = value[i];
        int parent = i/2;
        while (parent != 0 && temp > value[parent])
        {
            value[i] = value[parent];
            i = parent;
            parent = parent/2;
        }
        value[i]=temp;
    }
    
    public void change(int i, int val)
    {
    	//replaces value[i] with new value val in a valid maxheap
    	if (value[i]>val)
    	{
    		value[i] = val;
    		siftdown(i);
    	}
    	else
    	{
    		value[i] = val;
    		bubbleup(i);
    	}
    		
    }
    
    public void isHeap()
    {
    	//checks whether a given array is a heap
    	boolean isHeap = true;
    	int i = size;
    	while (i>=2 && isHeap)
    	{
    		if (value[i/2] < value[i])
    			isHeap = false;
    		i--;
    	}
    	if (isHeap)
    		System.out.println("Verification: Array above is a Heap");
    	else
    		System.out.println("Verification: Array above is not a Heap");
    }

}

