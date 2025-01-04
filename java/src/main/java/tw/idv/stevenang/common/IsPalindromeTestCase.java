package tw.idv.stevenang.common;

public class IsPalindromeTestCase {
    private final String data;
    private final boolean expectedResult;

    public IsPalindromeTestCase(String data, boolean expectedResult) {
        this.data = data;
        this.expectedResult = expectedResult;
    }

    public String getData() {
        return data;
    }

    public boolean isExpectedResult() {
        return expectedResult;
    }

    @Override
    public String toString() {
        return "IsPalindromeTestCase{" +
                "data='" + data + '\'' +
                ", expectedResult=" + expectedResult +
                '}';
    }
}
