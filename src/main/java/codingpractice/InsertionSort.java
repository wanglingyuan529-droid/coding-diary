package codingpractice;

public class InsertionSort {
    public static void main(String[] args) {
        int[] ins = { 5, 2, 9, 1, 7 };

        for (int i = 1; i < ins.length; i++) {
            int cur = ins[i];
            int j = i - 1;
            while (j >= 0 && ins[j] > cur) {
                ins[j + 1] = ins[j]; 
                j--;
            }
            ins[j + 1] = cur; 
        }
        for (int i = 0; i < ins.length; i++) {
            System.out.print(ins[i] + " ");
        }
    }
}
