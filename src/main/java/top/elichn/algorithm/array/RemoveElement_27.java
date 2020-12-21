package top.elichn.algorithm.array;

/**
 * <p>Title: RemoveElement_27</p>
 * <p>Description: 移除元素 https://leetcode-cn.com/problems/remove-element/</p>
 * <p>Company: </p>
 *
 * @author elichn
 * @version 1.0
 * @date 2020/12/21
 */
public class RemoveElement_27 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int value = 3;
        int result = removeElement(nums, value);
        System.out.println(result);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public static int removeElement(int[] nums, int val) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
}
