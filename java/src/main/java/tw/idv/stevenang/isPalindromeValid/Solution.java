package tw.idv.stevenang.isPalindromeValid;

import tw.idv.stevenang.common.IsPalindromeTestCase;
import tw.idv.stevenang.common.TestCaseReader;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static boolean bruteForce(String input) {

        List<Character> forward = new ArrayList<>();
        List<Character> backward = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            if (Character.isLetterOrDigit(input.charAt(i))) {
                forward.addLast(input.charAt(i));
                backward.addFirst(input.charAt(i));
            }
        }

        if (forward.size() != backward.size()) {
            return false;
        }

        for (int i = 0; i < forward.size(); i++) {
            if (forward.get(i) != backward.get(i)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isPalindromeValid(String input) {
        int left = 0;
        int right = input.length() - 1;

        while (left < right) {
            while ((left < right) && ! Character.isLetterOrDigit(input.charAt(left))) {
                left++;
            }
            while ((left < right) &&! Character.isLetterOrDigit(input.charAt(right))) {
                right--;
            }
            if (input.charAt(left) == input.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<IsPalindromeTestCase> testCases = TestCaseReader.readIsPalindromeTestCase(
                "/tw/idv/stevenang/isPalindromeValid/data.csv", "\\|");

        for (IsPalindromeTestCase testCase : testCases) {
            boolean result = bruteForce(testCase.getData());
            assert  result == testCase.isExpectedResult() :
                    "Brute force: Incorrect result. Expected: " +
                            testCase.isExpectedResult() + ". Actual: " + testCase.getData();

            System.out.println("PASSED Brute force: " + testCase.getData() + ". Result: " + result);

            result = isPalindromeValid(testCase.getData());
            assert  result == testCase.isExpectedResult() :
                    "Is Palindrome Valid: Incorrect result. Expected: " +
                            testCase.isExpectedResult() + ". Actual: " + testCase.getData();

            System.out.println("PASSED Is valid palindrome: " + testCase.getData() + ". Result: " + result);
        }
    }
}
