package codingpractice;

public class BinarySearch {
    public static void main(String[] args) {
        int [] arr = {7, 23, 79, 81, 103, 127, 131, 147};
        int lo = 0, hi = arr.length - 1;
        int find = 131, pos = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] == find) {
                pos = mid;
                break;
            } else if (arr[mid] < find) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        System.out.println("Index of target element: " + pos);
    }
}
