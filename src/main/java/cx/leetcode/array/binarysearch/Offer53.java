package cx.leetcode.array.binarysearch;

public class Offer53 {
    public int search(int[] nums, int target) {
        return findRight(nums, target) - findRight(nums, target - 1);
    }

    private int findRight(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        //只有将寻找右边界的判断条件删掉，就可以变为寻找target-1的右边界，这个值加1就是target的左边界
//        if (right < 0 || nums[right] != target) {
//            return -1;
//        }
        return right;
    }

    public static void main(String[] args) {
        Offer53 offer53 = new Offer53();
        int[] nums = new int[]{0, 1, 3, 5, 5, 7};
        int tar = 5;
        System.out.println(offer53.findRight(nums, 4));
    }

}
