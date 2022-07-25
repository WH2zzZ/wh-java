package leetcode.manacher;

/**
 * @Author WangHan
 * @Create 2022/4/30 3:56 下午
 */
public class ManacherSolution {

    public static void main(String[] args) {
        String str = "ccc";
        System.out.println(maxLcpsLength(str));
    }

    private static String maxLcpsLength(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        char[] str = manacherString(s); // 1221 -> #1#2#2#1#
        int[] pArr = new int[str.length]; // 回文半径数组
        pArr[0] = 0;
        pArr[1] = 1;
        int c = 1; // 中心位置
        int r = 2; // 回文右边界
        int max = 1; // 最大回文半径
        int maxC = 1; // 最大回文半径的中心

        for (int i = 2; i < str.length; i++) {

            /**
             * 三种情况的判断
             */
            // 如果当前判断回文在回文右边界外，则需要暴力扩
            if (i >= r) {
                pArr[i] = plusLcpsLength(str, 1, i);
            } else {
                // 找出i'， 就是i围绕最大回文半径的中心的对称坐标位置
                int I = 2 * c - i;
                int ILength = pArr[I];
                // 如果i'的回文串在 c-r 内
                if (I - ILength < c - r) {
                    pArr[i] = pArr[I];
                }else if (I - ILength == c - pArr[c]) {
                    pArr[i] = plusLcpsLength(str, r - i, i);
                }else if (I - ILength > c - r) {
                    pArr[i] = r - i;
                }
            }

            if (r < pArr[i] + i) {
                r = pArr[i] + i;
                c = i;
            }
            if (pArr[i] > max) {
                max = pArr[i];
                maxC = i;

            }
            System.out.println("当前中心点:" + i + " 半径:" + pArr[i] +" 最右中心点:" + c + " 最右边界:" + r);
        }

        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = maxC - max; i < maxC + max; i++) {
            if ('#' != str[i]) {
                stringBuilder.append(str[i]);
            }
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    /**
     * 往外扩容回文串
     * @param str
     * @param initLength
     * @param currentIndex
     */
    private static int plusLcpsLength(char[] str, int initLength, int currentIndex) {
        while (currentIndex - initLength >= 0 && currentIndex + initLength < str.length) {
            if (str[currentIndex - initLength] == str[currentIndex + initLength]) {
                initLength++;
            } else {
                break;
            }
        }
        return initLength - 1;
    }

    private static char[] manacherString(String s) {
        char[] chars = new char[s.length() * 2 + 1];
        for (int i = 0; i < s.length(); i++) {
            chars[2 * i] = '#';
            chars[2 * i + 1] = s.charAt(i);
            chars[2 * i + 2] = '#';

        }
        System.out.println(chars);
        return chars;
    }

}
