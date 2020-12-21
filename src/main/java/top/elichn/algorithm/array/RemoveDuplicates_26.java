package top.elichn.algorithm.array;

/**
 * <p>Title: RemoveDuplicates_26</p>
 * <p>Description: 删除排序数组中的重复项 https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/</p>
 * <p>Company: </p>
 *
 * @author elichn
 * @version 1.0
 * @date 2020/12/21
 */
public class RemoveDuplicates_26 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int result = removeDuplicates(nums);
        System.out.println(result);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public static int removeDuplicates(int[] nums) {
        // 慢指针
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
