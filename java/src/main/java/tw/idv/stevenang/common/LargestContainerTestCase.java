package tw.idv.stevenang.common;

import java.util.List;

public class LargestContainerTestCase {
    private final List<Integer> data;
    private final Integer expected;

    public LargestContainerTestCase(List<Integer> data, Integer expected) {
        this.data = data;
        this.expected = expected;
    }

    public List<Integer> getData() {
        return data;
    }

    public Integer getExpected() {
        return expected;
    }

    @Override
    public String toString() {
        return "LargestContainerTestCase [data=" + data + ", expected=" + expected + "]";
    }
}
