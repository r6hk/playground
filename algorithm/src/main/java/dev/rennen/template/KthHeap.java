package dev.rennen.template;

/**
 * @author rennen.dev
 * @date 2024/10/2 11:13
 */
public class KthHeap {

    private final int[] heap;

    private final int k;

    private int size = 0;

    public KthHeap(int k) {
        this.k = k;
        heap = new int[k];
    }

    public int putAndReturn(int num) {
        if (size < k) {
            insertHeap(num);
        } else if (heap[0] < num) {
            heap[0] = num;
            adjustHeap();
        }
        return size < k ? -1 : heap[0];
    }

    private void adjustHeap() {
        int i = 0, j = 2 * i + 1;
        while (j < size) {
            if (j + 1 < size && heap[j + 1] < heap[j]) {
                j++;
            }
            // 满足最小堆，无需再迭代
            if (heap[i] <= heap[j]) break;
            swap(i, j);
            i = j;
            j = 2 * i + 1;
        }
    }

    private void insertHeap(int num) {
        int i = size;
        heap[i] = num;
        while (i > 0) {
            int j = (i - 1) / 2;
            if (!(heap[j] > heap[i])) break;
            swap(i, j);
            i = j;
        }
        size++;
    }

    private void swap(int i, int j) {
        int t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    public static void main(String[] args) {
        KthHeap test = new KthHeap(5);

        System.out.println(test.putAndReturn(4));
        System.out.println(test.putAndReturn(1));
        System.out.println(test.putAndReturn(9));
        System.out.println(test.putAndReturn(3));
        System.out.println(test.putAndReturn(6));
        System.out.println(test.putAndReturn(8));
        System.out.println(test.putAndReturn(6));
    }
}
