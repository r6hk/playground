package dev.rennen.template;

/**
 * @author rennen.dev
 * @date 2024/10/27 20:30
 */
public class UnionFind {

    /**
     * 初始化并查集
     * @param parent 一个数组，用来表示并查集
     */
    private static void init(int[] parent) {
        int n = parent.length;
        for (int i = 0; i < n; i++) {
            parent[i]  = i;
        }
    }

    /**
     * 将元素 a 和 元素 b 所在的集合合并成一个集合
     * @param a 元素 a 序号
     * @param b 元素 b 序号
     * @param parent 并查集数组
     */
    private static void union(int a, int b, int[] parent) {
        int rootA = find(a, parent);
        int rootB = find(b, parent);
        if (rootA != rootB) {
            // 下面的写法是为了之后迭代查找祖先时「引用链」更短，可避免超时
            if (rootA < rootB) parent[rootB] = rootA;
            else parent[rootA] = rootB;
        }
    }

    /**
     * 查询元素 x 的祖先
     * @param x 元素 x 序号
     * @param parent 并查集数组
     * @return 元素 x 祖先的序号
     */
    private static int find(int x, int[] parent) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }

    /**
     * 查找当前并查集一共有多少个集合
     * @param parent 并查集集合
     * @return 当前并查集一共有多少个集合
     */
    private static int unionCount(int[] parent) {
        int n = parent.length, count = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] != i) {
                count++;
            }
        }
        return count;
    }
}
