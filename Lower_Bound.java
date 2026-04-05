public class Lower_Bound {
    public static int lowerBound(int[] arr, int key) {
        int low = 0, high = arr.length;  
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= key) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
