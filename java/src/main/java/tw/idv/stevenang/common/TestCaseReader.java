package tw.idv.stevenang.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCaseReader {
    public static List<TestCase> readTestCases(String resourcePath, String delimiter) {
        List<TestCase> testCases = new ArrayList<>();

        try (InputStream is = TestCaseReader.class.getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line;
            br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(delimiter);
                int[] data = parseIntArray(parts[0]);
                int target = Integer.parseInt(parts[1].trim());
                int[] expected = parseIntArray(parts[2]);
                testCases.add(new TestCase(data, target, expected));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return testCases;
    }

    private static int[] parseIntArray(String arrayStr) {
        if (arrayStr.trim().equals("[]")) {
            return new int[0];
        }

        String[] numbers = arrayStr.trim()
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "")
                .split(",");

        return Arrays.stream(numbers)
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}