package top.elichn.algorithm.array;

/**
 * <p>Title: MaxSubArray_53</p>
 * <p>Description: 最大子序和 https://leetcode-cn.com/problems/maximum-subarray/</p>
 * <p>Company: </p>
 *
 * @author elichn
 * @version 1.0
 * @date 2020/12/21
 */
public class MaxSubArray_53 {

    public int maxSubArray(int[] nums) {
        int sum = 0;
        int result = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (sum > 0) {
                sum = sum + nums[i];
            } else {
                sum = nums[i];
            }
            result = Math.max(sum, result);
        }
        return result;
    }
}
