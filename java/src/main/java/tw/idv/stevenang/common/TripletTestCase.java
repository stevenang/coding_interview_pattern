package tw.idv.stevenang.common;

import java.util.List;

public class TripletTestCase {
    private final List<Integer> data;
    private final List<List<Integer>> expected;

    public TripletTestCase(List<Integer> data, List<List<Integer>> expected) {
        this.data = data;
        this.expected = expected;
    }

    public List<Integer> getData() {
        return data;
    }

    public List<List<Integer>> getExpected() {
        return expected;
    }

    @Override
    public String toString() {
        return "TripletTestCase{" +
                "data=" + data +
                ", expected=" + expected +
                '}';
    }
}
