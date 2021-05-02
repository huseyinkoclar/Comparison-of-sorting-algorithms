import java.util.*;

class main{
    
public static void main(String[] args){

    System.out.println("------------RANDOM ARRAY--------");
    System.out.println();
    measure(randomGenerateArr(1000));
    measure(randomGenerateArr(10000));
    measure(randomGenerateArr(100000));
    System.out.println();
    System.out.println("------------EQUAL ARRAY---------");
    System.out.println();
    measure(equalGenerateArr(1000));
    measure(equalGenerateArr(10000));
    measure(equalGenerateArr(100000));
    System.out.println();
    System.out.println("----------INCREASING ARRAY-----------");
    System.out.println();
    measure(increasingGenerateArr(1000));
    measure(increasingGenerateArr(10000));
    measure(increasingGenerateArr(100000));
    System.out.println();
    System.out.println("-----------DECREASING ARRAY----------");
    System.out.println();
    measure(decreasingGenerateArr(1000));
    measure(decreasingGenerateArr(10000));
    measure(decreasingGenerateArr(100000));
    }
    static Random random = new Random();
    static int[] randomGenerateArr(int capacity){
        int[] arr = new int[capacity];
        for(int i = 0; i<capacity;i++){
            arr[i] = random.nextInt(capacity);
        }
        return arr;
    }

    static int[] equalGenerateArr(int capacity){
        int[] arr = new int[capacity];
        int a = random.nextInt(capacity);
        for(int i = 0;i<capacity;i++){
            arr[i] = a;
        }
        return arr;
    }

    static int[] increasingGenerateArr(int capacity){
        int[] arr = new int[capacity];
        for(int i = 0; i<capacity;i++){
            arr[i] = i;
        }
        return arr;
    }
    static int[] decreasingGenerateArr(int capacity){
        int[] arr = new int[capacity];
        for(int i = capacity-2;i>-1;i--){
            arr[i] = i;
        }
        return arr;
    }

    static void measure(int[] arr){
        int[] copiedArray1 = arr.clone();
        int[] copiedArray2 = arr.clone();
        int[] copiedArray3 = arr.clone();
        long startTime = System.currentTimeMillis();
        SortingClass.heapSort(copiedArray1);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("heapSort with " + copiedArray1.length + " elements in " + estimatedTime + " ms");
        long startTime1 = System.currentTimeMillis();
        SortingClass.dualPivotQuickSort(copiedArray2,0,copiedArray2.length-1);
        long estimatedTime1 = System.currentTimeMillis() - startTime1;
        System.out.println("dualPivotQuickSort with " + copiedArray2.length + " elements in " + estimatedTime1 + " ms");
        long startTime2 = System. currentTimeMillis();
        SortingClass.bucketSort(copiedArray3);
        long estimatedTime2 = System. currentTimeMillis() - startTime2;
        System.out.println("bucketSort with " + copiedArray3.length + " elements in " + estimatedTime2 + " ms");
        



    }

    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }


}


class SortingClass{

    static void heapSort(int[] arrayToSort) {
        int n = arrayToSort.length;
 
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arrayToSort, n, i);
 
        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arrayToSort[0];
            arrayToSort[0] = arrayToSort[i];
            arrayToSort[i] = temp;
 
            // call max heapify on the reduced heap
            heapify(arrayToSort, i, 0);
        }
    }
 
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    static void heapify(int arr[], int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2
 
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;
 
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;
 
        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
 
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    static void dualPivotQuickSort(int[] arr,int low, int high)
    {
        if (low < high) { 
            // piv[] stores left pivot and right pivot.
            // piv[0] means left pivot and 
            // piv[1] means right pivot 
            int[] piv; 
            piv = partition(arr, low, high); 
            dualPivotQuickSort(arr, low, piv[0] - 1);
            dualPivotQuickSort(arr, piv[0] + 1, piv[1] - 1); 
            dualPivotQuickSort(arr, piv[1] + 1, high); } 
        } 
        
        
        static int[] partition(int[] arr, int low, int high)
         { 
             if (arr[low] > arr[high])
        swap(arr, low, high);
        
        // p is the left pivot, and q
        // is the right pivot.
        int j = low + 1;
        int g = high - 1, k = low + 1,
        p = arr[low], q = arr[high];
        
        while (k <= g) { 
            
            // If elements are less than the left pivot 
            if (arr[k] < p)
            { swap(arr, k, j); j++; }
              // If elements are greater than or equal 
              // to the right pivot 
              else if (arr[k] >= q)
        {
        while (arr[g] > q && k < g) g--; swap(arr, k, g); g--;
         if (arr[k] < p) 
         { swap(arr, k, j); j++; }
         }
          k++;
         } j--;
          g++; 
        // Bring pivots to their appropriate positions. 
        swap(arr, low, j); 
        swap(arr, high, g); 
        // Returning the indices of the pivots 
        // because we cannot return two elements 
        // from a function, we do that using an array. 
        return new int[] { j, g }; }


        static void swap(int[] arr, int i, int j)
        {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        
        static void bucketSort(int[] arr) {
            /* get max value of arr */
            int max = max(arr);
            /* get min value of arr */
            int min = min(arr);
            /* number of buckets */
            int numberOfBuckets = max - min + 1;
            List<List<Integer>> buckets = new ArrayList<>(numberOfBuckets);
             /* init buckets */
            for (int i = 0; i < numberOfBuckets; ++i) {
                buckets.add(new ArrayList<>());
            }
            /* store elements to buckets */
            for (int value : arr) {
                int hash = hash(value, min, numberOfBuckets);
                buckets.get(hash).add(value);
            }
            /* sort individual bucket */
            for (List<Integer> bucket : buckets) {
                Collections.sort(bucket);
            }
            /* concatenate buckets to origin array */
            int index = 0;
            for (List<Integer> bucket : buckets) {
                for (int value : bucket) {
                arr[index++] = value;
                }
            }

        }
        static int hash(int elem, int min, int numberOfBucket) {
            return (elem - min) / numberOfBucket;
          }

        public static int max(int[] arr) {
        int max = arr[0];
        for (int value : arr) {
            if (value > max) {
            max = value;
            }
        }
        return max;
        }

          public static int min(int[] arr) {
            int min = arr[0];
            for (int value : arr) {
              if (value < min) {
                min = value;
              }
            }
            return min;
          }






}
