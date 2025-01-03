package tw.idv.stevenang.pairSum;

import tw.idv.stevenang.common.TestCase;
import tw.idv.stevenang.common.TestCaseReader;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static List<Integer> bruteForce(List<Integer> data, int target) {

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            for (int j = i + 1; j < data.size(); j++) {
                if (data.get(i) + data.get(j) == target) {
                    result.add(i);
                    result.add(j);
                    return result;
                }
            }
        }

        return result;
    }

    public static List<Integer> twoPointers(List<Integer> data, int target) {
        int left = 0;
        int right = data.size() - 1;
        List<Integer> result = new ArrayList<>();
        while (left < right) {
            int sum = data.get(left) + data.get(right);
            if (sum == target) {
                result.add(left);
                result.add(right);
                break;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }

    public static void main(String [] args) {
        List<TestCase> testCases = TestCaseReader.readTestCases("/tw/idv/stevenang/pairSum/data.csv", "\\|");
        for (TestCase testCase : testCases) {
            List<Integer> result = Solution.bruteForce(testCase.getDataAsList(), testCase.getTarget());
            assert result.size() == testCase.getExpectedAsList().size() : "BruteForce: Incorrect result size";
            assert result.equals(testCase.getExpectedAsList()) : "BruteForce: Incorrect result";

            result = Solution.twoPointers(testCase.getDataAsList(), testCase.getTarget());
            assert result.size() == testCase.getExpectedAsList().size() : "TwoPointers: Incorrect result size";
            assert result.equals(testCase.getExpectedAsList()) : "TwoPointers: Incorrect result";
        }
    }
}
