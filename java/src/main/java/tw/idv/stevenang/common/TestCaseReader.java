package tw.idv.stevenang.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCaseReader {
    public static List<TwoSumTestCase> readTwoSumTestCases(String resourcePath, String delimiter) {
        List<TwoSumTestCase> testCases = new ArrayList<>();

        try (InputStream is = TestCaseReader.class.getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line;
            br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(delimiter);
                List<Integer> data = parseIntList(parts[0]);
                int target = Integer.parseInt(parts[1].trim());
                List<Integer> expected = parseIntList(parts[2]);
                testCases.add(new TwoSumTestCase(data, target, expected));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return testCases;
    }

    public static List<TripletTestCase> readTripletTestCases(String resourcePath, String delimiter) {
        List<TripletTestCase> testCases = new ArrayList<>();

        try (InputStream is = TestCaseReader.class.getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line;
            br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(delimiter);
                List<Integer> data = parseIntList(parts[0]);
                List<List<Integer>> expected = parseNestedIntList(parts[1]);
                testCases.add(new TripletTestCase(data, expected));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return testCases;
    }

    private static List<Integer> parseIntList(String arrayStr) {
        if (arrayStr.trim().equals("[]")) {
            return new ArrayList<>();
        }

        String[] numbers = arrayStr.trim()
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "")
                .split(",");

        return Arrays.stream(numbers)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    private static List<List<Integer>> parseNestedIntList(String arrayStr) {
        List<List<Integer>> result = new ArrayList<>();

        if (arrayStr.trim().equals("[]")) {
            return result;
        }

        // Remove outer brackets
        String content = arrayStr.trim().substring(1, arrayStr.length() - 1);

        // Split into individual lists
        List<String> innerLists = new ArrayList<>();
        int bracketCount = 0;
        StringBuilder currentList = new StringBuilder();

        for (char c : content.toCharArray()) {
            if (c == '[') bracketCount++;
            else if (c == ']') bracketCount--;

            currentList.append(c);

            if (bracketCount == 0 && currentList.length() > 0) {
                innerLists.add(currentList.toString());
                currentList = new StringBuilder();
            }
        }

        // Parse each inner list
        for (String innerList : innerLists) {
            if (!innerList.trim().isEmpty() && !innerList.equals(",")) {
                result.add(parseIntList(innerList));
            }
        }

        return result;
    }

    public static List<IsPalindromeTestCase> readIsPalindromeTestCase(String resourcePath, String delimiter) {
        List<IsPalindromeTestCase> testCases = new ArrayList<>();

        try (InputStream is = TestCaseReader.class.getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line;
            br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(delimiter);
                String data = parts[0];
                boolean expectedResult = Boolean.parseBoolean(parts[1]);
                testCases.add(new IsPalindromeTestCase(data, expectedResult));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return testCases;
    }

    public static List<LargestContainerTestCase> readLargestContainerTestCase(String resourcePath, String delimiter) {
        List<LargestContainerTestCase> testCases = new ArrayList<>();

        try (InputStream is = TestCaseReader.class.getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line;
            br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(delimiter);
                List<Integer> data = parseIntList(parts[0]);
                Integer expectedResult = Integer.parseInt(parts[1]);
                testCases.add(new LargestContainerTestCase(data, expectedResult));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return testCases;
    }
}