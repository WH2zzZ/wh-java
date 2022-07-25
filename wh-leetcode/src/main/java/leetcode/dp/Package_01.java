package leetcode.dp;

/**
 * @Author WangHan
 * @Create 2022/2/25 2:08 上午
 */
public class Package_01 {

    public static void main(String[] args) {
        bag_problem(new int[]{1, 3, 4}, new int[]{15, 20, 30}, 4);
    }

    public static void bag_problem(int[] weight, int[] value, int bagWeight) {
        int[][] dp = new int[value.length][bagWeight + 1];

        for (int j = 0; j < weight[0]; j++) {
            dp[0][j] = 0;
        }
        for (int j = weight[0]; j <= bagWeight; j++) {
            dp[0][j] = value[0];
        }

        for (int i = 1; i < value.length; i++) {
            for (int j = 1; j <= bagWeight; j++) {
                if (weight[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }
        System.out.println(dp[value.length - 1][bagWeight]);
    }
}
