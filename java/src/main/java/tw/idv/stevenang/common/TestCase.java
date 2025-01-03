package tw.idv.stevenang.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCase {
    private List<Integer> data;
    private int target;
    private List<Integer> expected;

    public TestCase(int[] data, int target, int[] expected) {
        this.data = Arrays.stream(data).boxed().toList();
        this.target = target;
        this.expected = Arrays.stream(expected).boxed().toList();
    }

    public List<Integer> getDataAsList() {
        return new ArrayList<>(data);
    }

    public int getTarget() {
        return target;
    }

    public List<Integer> getExpectedAsList() {
        return new ArrayList<>(expected);
    }

    @Override
    public String toString() {
        return "TestCase{data=" + data + ", target=" + target + ", expected=" + expected + '}';
    }
}