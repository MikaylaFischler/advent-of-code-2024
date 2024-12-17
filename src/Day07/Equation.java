package Day07;

import java.util.ArrayList;

public class Equation {

    private final long testValue;
    private final ArrayList<Long> values;

    private final int binPermLimit; // binary permutations limit

    public Equation(long testValue, ArrayList<Long> values) {
        this.testValue = testValue;
        this.values = values;

        // a number is a permutation bitmask of all bit combinations
        this.binPermLimit = 0x1 << (values.size() - 1);
    }

    public long getTestValue() { return testValue; }

    public boolean checkValidBiOp() {
        for (int i = 0; i < binPermLimit; i++) {
            if (compute(i) == testValue) return true;
        }

        return false;
    }

    public boolean checkValidTriOp() {
        ArrayList<Long> list = new ArrayList<>(values);
        ArrayList<Long> results = new ArrayList<>();

        computeAll(list.removeFirst(), list, results);

        return results.contains(testValue);
    }

    private long compute(int mask) {
        long value = values.getFirst();

        for (int i = 1; i < values.size(); i++) {
            if ((mask & (0x1 << (i - 1))) == 0) {
                value += values.get(i);
            } else {
                value *= values.get(i);
            }
        }

        return value;
    }

    private void computeAll(long value, ArrayList<Long> list, ArrayList<Long> results) {
        if (list.isEmpty()) {
            results.add(value);
        } else {
            long a = value + list.getFirst();
            long b = value * list.getFirst();
            long c = Long.parseLong(Long.toString(value) + list.getFirst());

            list.removeFirst();

            computeAll(a, new ArrayList<>(list), results);
            computeAll(b, new ArrayList<>(list), results);
            computeAll(c, new ArrayList<>(list), results);
        }
    }

    @Override
    public String toString() {
        return "Equation{" + testValue + ", values=" + values + '}';
    }
}
