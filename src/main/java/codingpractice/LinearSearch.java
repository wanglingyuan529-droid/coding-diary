package codingpractice;

public class LinearSearch {
    public static void main(String[] args) {
        int[] arr = {7, 23, 79, 81, 103};
        int target = 79;
        int idx = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                idx = i;
                break;
            }
        }
        System.out.println("Index of target element: " + idx);
    }
}
