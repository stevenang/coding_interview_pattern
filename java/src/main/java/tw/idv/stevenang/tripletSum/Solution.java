package tw.idv.stevenang.tripletSum;

import tw.idv.stevenang.common.TestCase;
import tw.idv.stevenang.common.TestCaseReader;
import tw.idv.stevenang.common.TripletTestCase;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static List<List<Integer>> bruteForce(List<Integer> data) {

        Set<String> resultSet = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            for (int j = i + 1; j < data.size(); j++) {
                for (int k = j + 1; k < data.size(); k++) {
                    if (data.get(i) + data.get(j) + data.get(k) == 0) {
                        // Sort the three numbers to create consistent string representation
                        List<Integer> temp = new ArrayList<>();
                        temp.add(data.get(i));
                        temp.add(data.get(j));
                        temp.add(data.get(k));
                        temp.sort(null);  // Sort using natural ordering

                        // Create string key for deduplication
                        String key = temp.toString();

                        if (!resultSet.contains(key)) {
                            resultSet.add(key);
                            result.add(temp);
                        }
                    }
                }
            }
        }

        // Adding this code to sort the output so we can standardize the test cases
        // In actual, we can eliminate this
        result.sort((list1, list2) -> {
             int compare = list1.get(0).compareTo(list2.get(0));
             if (compare != 0) return compare;
             compare = list1.get(1).compareTo(list2.get(1));
             if (compare != 0) return compare;
             return list1.get(2).compareTo(list2.get(2));
        });

        return result;
    }

    private static List<List<Integer>> pairSum(List<Integer> data, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Set<String> resultSet = new HashSet<>();
        int left = start;
        int right = data.size() - 1;
        while (left < right) {
            int sum = data.get(left) + data.get(right);
            if (sum == target) {
                List<Integer> temp = new ArrayList<>();
                temp.add(data.get(left));
                temp.add(data.get(right));
                temp.sort(null);

                // Create string key for deduplication
                String key = temp.toString();

                if (!resultSet.contains(key)) {
                    resultSet.add(key);
                    result.add(temp);
                }
                left++;
                while (left < right && data.get(left).equals(data.get(left-1))) {
                    left++;
                }
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        // Adding this code to sort the output so we can standardize the test cases
        // In actual, we can eliminate this
        result.sort((list1, list2) -> {
            int compare = list1.get(0).compareTo(list2.get(0));
            if (compare != 0) return compare;
            compare = list1.get(1).compareTo(list2.get(1));
            if (compare != 0) return compare;
            return list1.get(2).compareTo(list2.get(2));
        });

        return result;
    }

    public static List<List<Integer>> tripletSum(List<Integer> data) {

        List<List<Integer>> triplets = new ArrayList<>();
        data.sort(null);

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) > 0) {
                break;
            }
            if (i > 0 && data.get(i) == data.get(i - 1)) {
                continue;
            }
            List<List<Integer>> pairs = pairSum(data, i + 1, data.get(i) * -1);
            for (List<Integer> pair : pairs) {
                pair.addFirst(data.get(i));
                pair.sort(null);
                triplets.add(pair);
            }
        }

        return triplets;
    }

    public static void main(String[] args) {
        List<TripletTestCase> testCases = TestCaseReader.readTripletTestCases("/tw/idv/stevenang/tripletSum/data.csv", "\\|");
        for (TripletTestCase testCase : testCases) {
            List<List<Integer>> result = Solution.bruteForce(testCase.getData());
            assert result.size() == testCase.getExpected().size() : "BruteForce: Incorrect result size";
            assert result.equals(testCase.getExpected()) : "BruteForce: Incorrect result";

            result = Solution.tripletSum(testCase.getData());
            assert result.size() == testCase.getExpected().size() : "TripletSum: Incorrect result size";
            assert result.equals(testCase.getExpected()) : "TripletSum: Incorrect result";
        }
    }
}
