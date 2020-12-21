package top.elichn.algorithm.string;

/**
 * <p>Title: ReverseString_344</p>
 * <p>Description: 反转字符串 https://leetcode-cn.com/problems/reverse-string/</p>
 * <p>Company: </p>
 *
 * @author elichn
 * @version 1.0
 * @date 2020/12/21
 */
public class ReverseString_344 {

    public static void main(String[] args) {
        String str = "abcdefg";
        char[] s = str.toCharArray();
        reverseString(s);
        for (char ch : s) {
            System.out.print(ch + " ");
        }
    }

    public static void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }
}
