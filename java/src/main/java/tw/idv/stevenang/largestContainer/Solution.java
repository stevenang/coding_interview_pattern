package tw.idv.stevenang.largestContainer;

import tw.idv.stevenang.common.LargestContainerTestCase;
import tw.idv.stevenang.common.TestCaseReader;

import java.util.List;

public class Solution {

    public static int bruteForce(int[] arr) {
        int max_water = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int length = j - i;
                int water = Math.min(arr[i], arr[j]) * length;
                max_water = Math.max(max_water, water);
            }
        }

        return max_water;
    }

    public static int getLargestContainer(int[] arr) {
        int max_water = 0;
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int length = right - left;
            int water = Math.min(arr[left], arr[right]) * length;
            max_water = Math.max(max_water, water);
            if (arr[left] < arr[right]) {
                left++;
            } else if (arr[left] > arr[right]) {
                right--;
            } else {
                left++;
                right--;
            }
        }

        return max_water;
    }

    public static void main(String[] args) {
        List<LargestContainerTestCase> testCases = TestCaseReader.readLargestContainerTestCase(
                "/tw/idv/stevenang/largestContainer/data.csv", "\\|");

        for (LargestContainerTestCase tc : testCases) {
            int[] data = tc.getData().stream().mapToInt(Integer::valueOf).toArray();
            int expected = tc.getExpected();
            int actual = Solution.bruteForce(data);
            assert expected == actual : "Brute force: incorrect result. Expected: " + expected + ", Actual: " + actual ;
            System.out.println("Brute force: Calculating largest container for " + tc.getData() + ". Result: " + actual);
            actual = Solution.getLargestContainer(data);
            assert expected == actual : "Largest container: incorrect result. Expected: " + expected + ", Actual: " + actual ;
            System.out.println("Largest container: Calculating largest container for " + tc.getData() + ". Result: " + actual);

        }
    }
}
