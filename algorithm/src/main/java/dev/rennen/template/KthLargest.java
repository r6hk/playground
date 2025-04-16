package dev.rennen.template;

public class KthLargest {

    int[] heap;
    int size = 0;
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        heap = new int[k];
        for (int num : nums) {
            add(num);
        }
        System.out.println(1);
    }
    
    public int add(int val) {
        if (size < k) insert(val);
        else adjust(val);
        return heap[0];
    }

    void insert(int num) {
        int i = size;
        heap[size++] = num;
        while (i > 0) {
            int j = (i - 1) / 2;
            if (j >= 0 && heap[j] > heap[i]) swap(i, j);
            i = j;
        }
    }

    void adjust(int num) {
        if (num <= heap[0]) return;
        heap[0] = num;
        int i = 0, j = i * 2 + 1;
        while (j < size) {
            if (j + 1 < size && heap[j + 1] < heap[j]) j++;
            if (heap[i] > heap[j]) swap(i, j);
            else break;
            i = j;
            j = 2 * i + 1;
        }
    }

    void swap(int i, int j) {
        int t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    public static void main(String[] args) {
        KthLargest test = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(test.add(3));
        System.out.println(test.add(5));
        System.out.println(test.add(10));
        System.out.println(test.add(9));
        System.out.println(test.add(4));
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums, numsSize);
 * int param_1 = obj.add(val);
 */