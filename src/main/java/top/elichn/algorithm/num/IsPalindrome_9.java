package top.elichn.algorithm.num;

/**
 * <p>Title: IsPalindrome_9</p>
 * <p>Description: 回文数 https://leetcode-cn.com/problems/palindrome-number/</p>
 *
 * @author eli
 * @version 1.0
 * @date 2021/1/10
 */
public class IsPalindrome_9 {

    public static void main(String[] args) {
        int num = 120;
        System.out.println(isPalindrome(num));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int num = x;
        int result = 0;
        while (num != 0) {
            int tmp = num % 10;
            result = result * 10 + tmp;
            num = num / 10;
        }
        if (x == result) {
            return true;
        }
        return false;
    }
}
