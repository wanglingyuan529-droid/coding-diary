package codingpractice;

public class InterpolationSearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int find = 15;

        int lo = 0, hi = arr.length - 1, idx = -1;
        while (lo <= hi && find >= arr[lo] && find <= arr[hi]) {
            if (lo == hi) { if (arr[lo] == find) idx = lo; break; }

            int mid = lo + (hi - lo) * (find - arr[lo]) / (arr[hi] - arr[lo]);

            if (arr[mid] == find) { 
                idx = mid; break; 
            }
            else if (arr[mid] < find) lo = mid + 1;
            else hi = mid - 1;
        }   
        System.out.println("Index of target element: " + idx);
    }
}
