package tw.idv.stevenang.common;

import java.util.List;

public class TwoSumTestCase {
    private final List<Integer> data;
    private final int target;
    private final List<Integer> expected;

    public TwoSumTestCase(List<Integer> data, int target, List<Integer> expected) {
        this.data = data;
        this.target = target;
        this.expected = expected;
    }

    public List<Integer> getData() {
        return data;
    }

    public int getTarget() {
        return target;
    }

    public List<Integer> getExpected() {
        return expected;
    }

    @Override
    public String toString() {
        return "TwoSumTestCase{" +
                "data=" + data +
                ", target=" + target +
                ", expected=" + expected +
                '}';
    }
}
