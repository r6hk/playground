package dev.rennen.leetcode;

public class Test407 {
    public int trapRainWater(int[][] heightMap) {
        int rowLen = heightMap.length;
        int colLen = heightMap[0].length;
        int[][] row1 = new int[rowLen][colLen];
        int[][] row2 = new int[rowLen][colLen];
        int[][] col1 = new int[rowLen][colLen];
        int[][] col2 = new int[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (j == 0) row1[i][j] = heightMap[i][j];
                else row1[i][j] = Math.max(row1[i][j - 1], heightMap[i][j]);
            }
            for (int j = colLen - 1; j >= 0; j--) {
                if (j == colLen - 1) row2[i][j] = heightMap[i][j];
                else row2[i][j] = Math.max(row2[i][j + 1], heightMap[i][j]);
            }
        }
        for (int j = 0; j < colLen; j++) {
            for (int i = 0; i < rowLen; i++) {
                if (i == 0) col1[i][j] = heightMap[i][j];
                else col1[i][j] = Math.max(col1[i - 1][j], heightMap[i][j]);
            }
            for (int i = rowLen - 1; i >=0; i--) {
                if (i == rowLen - 1) col2[i][j] = heightMap[i][j];
                else col2[i][j] = Math.max(col2[i + 1][j], heightMap[i][j]);
            }
        }
        int res = 0;
        int[][] maxFlat = new int[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (i == 0 || j == 0) maxFlat[i][j] = heightMap[i][j];
                else maxFlat[i][j] = max(
                        min(
                                row1[i][j],
                                row2[i][j],
                                col1[i][j],
                                col2[i][j],
                                maxFlat[i - 1][j],
                                maxFlat[i][j - 1]),
                        heightMap[i][j]);
            }
        }
        for (int i = rowLen - 1; i >= 0; i--) {
            for (int j = colLen - 1; j >= 0; j--) {
                if (i == rowLen - 1 || j == colLen - 1) maxFlat[i][j] = heightMap[i][j];
                else maxFlat[i][j] = max(min(maxFlat[i + 1][j], maxFlat[i][j + 1], maxFlat[i][j]), heightMap[i][j]);
            }
        }
        for (int i = 1; i < rowLen - 1; i++) {
            for (int j = 1; j < colLen - 1; j++) {
                res += (maxFlat[i][j] - heightMap[i][j]);
            }
        }
        return res;
    }

    private int min(int... nums) {
        int res = Integer.MAX_VALUE;
        for (int num : nums) {
            res = Math.min(res, num);
        }
        return res;
    }

    private int max(int... nums) {
        int res = Integer.MIN_VALUE;
        for (int num : nums) {
            res = Math.max(res, num);
        }
        return res;
    }

    public static void main(String[] args) {
        Test407 test = new Test407();
        System.out.println(test.trapRainWater(new int[][]{
                {19383,10886,12777,16915,17793,18335,15386,10492,16649,11421},
                {12362,27,8690,59,7763,3926,540,3426,9172,5736},
                {15211,5368,2567,6429,5782,1530,2862,5123,4067,3135},
                {13929,9802,4022,3058,3069,8167,1393,8456,5011,8042},
                {16229,7373,4421,4919,3784,8537,5198,4324,8315,4370},
                {16413,3526,6091,8980,9956,1873,6862,9170,6996,7281},
                {12305,925,7084,6327,336,6505,846,1729,1313,5857},
                {16124,3895,9582,545,8814,3367,5434,364,4043,3750},
                {11087,6808,7276,7178,5788,3584,5403,2651,2754,2399},
                {19932,5060,9676,3368,7739,12,6226,8586,8094,7539}}));
    }

}
