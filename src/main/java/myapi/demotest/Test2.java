package myapi.demotest;

public class Test2 {
    public static void main(String[] args) {
        System.out.println(parseInt("123"));         
        System.out.println(parseInt("0"));          
        System.out.println(parseInt("0123"));       
        System.out.println(parseInt("12a3"));       
        System.out.println(parseInt(""));           
        System.out.println(parseInt(null));         
        System.out.println(parseInt("1234567890")); 
        System.out.println(parseInt("12345678901"));
    }

    public static int parseInt(String s) {
        if (s == null || s.length() < 1 || s.length() > 10) {
            return -1;
        }

        if (s.charAt(0) == '0') {
            return -1;
        }

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c < '0' || c > '9') {
                return -1;
            }

            result = result * 10 + (c - '0');
        }

        return result;
    }
}
