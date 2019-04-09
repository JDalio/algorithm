package heap;

public class Heap
{
    private static int left(int i)
    {
        return 2 * i;
    }

    private static int right(int i)
    {
        return 2 * i + 1;
    }

    private static int parent(int i)
    {
        return i / 2;
    }

    private static void swap(int[] heap, int i, int j)
    {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public static void maxHeapify(int[] heap, int i, int length)
    {
        int l = left(i), r = right(i);
        int largest;
        largest = l <= length && heap[l] > heap[i] ? l : i;
        largest = r <= length && heap[r] > heap[largest] ? r : largest;
        if (largest != i)
        {
            swap(heap, i, largest);
            maxHeapify(heap, largest, length);
        }
    }

    public static void buildMaxHeap(int[] heap)
    {
        for (int i = (heap.length - 1) / 2; i >= 1; i--)
            maxHeapify(heap, i, heap.length - 1);
    }

    public static void heapSort(int[] heap)
    {
        buildMaxHeap(heap);
        int length = heap.length - 1;
        for (int i = length; i >= 2; i--)
        {
            swap(heap, 1, i);
            maxHeapify(heap, 1, --length);
        }

    }

    public static int max(int[] heap)
    {
        return heap[1];
    }

    public static int extractMax(int[] heap)
    {
        int max = heap[1];
        heap[1] = heap[heap.length - 1];
        maxHeapify(heap, 1, heap.length - 2);
        return max;
    }

    public static void increaseKey(int[] heap, int i, int key)
    {
        heap[i] = key;
        while (i > 1 && heap[parent(i)] < heap[i])
        {
            swap(heap, parent(i), i);
            i = parent(i);
        }
    }
}
