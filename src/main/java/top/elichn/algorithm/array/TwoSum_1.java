package top.elichn.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: TwoSum_1</p>
 * <p>Description: 两数之和 https://leetcode-cn.com/problems/two-sum/</p>
 * <p>Company: </p>
 *
 * @author elichn
 * @version 1.0
 * @date 2020/12/21
 */
public class TwoSum_1 {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
