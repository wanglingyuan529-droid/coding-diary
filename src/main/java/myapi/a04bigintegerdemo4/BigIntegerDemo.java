package myapi.a04bigintegerdemo4;

import java.math.BigInteger;

public class BigIntegerDemo {
    public static void main(String[] args) {
        BigInteger a = new BigInteger("1234567890123456789012345678901234567890");
        BigInteger b = new BigInteger("9876543210987654321098765432109876543210");
        System.out.println(a.add(b));
        System.out.println(a.subtract(b));
        System.out.println(a.multiply(b));
        System.out.println(b.divide(a));
        System.out.println(b.remainder(a));
        BigInteger c = BigInteger.valueOf(100);
        BigInteger d = BigInteger.valueOf(200);
        System.out.println(c.compareTo(b));
        System.out.println(d.compareTo(c));
        System.out.println(c.compareTo(c));
        BigInteger e = new BigInteger("123");
        System.out.println(e.intValue());
        System.out.println(e.floatValue());
        System.out.println(e.doubleValue());
        System.out.println(e.toString());
        System.out.println(factorial(100));

    }


    public static BigInteger factorial(int n) {
        BigInteger r = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            r = r.multiply(BigInteger.valueOf(i));
        }
        return r;
    }
}
