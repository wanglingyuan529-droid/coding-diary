package stringdemo;

public class StringDemo11 {
    static void main() {
        String strA = "abcde";
        String strB = "cdeab";
        System.out.println(check(strA, strB));
    }

    public static boolean check(String strA, String strB) {
        for (int i = 0; i < strA.length(); i++) {
            strA = rotate(strA);
            if (strA.equals(strB)) {
                return true;
            }
        }
        return false;
    }

    public static String rotate(String str) {
        char first = str.charAt(0);
        String end = str.substring(1);
        return end + first;
    }
}
