package ua.edu.ucu.stream;

import ua.edu.ucu.function.IntBinaryOperator;
import ua.edu.ucu.function.IntConsumer;
import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.function.IntUnaryOperator;

public class AsIntStream implements IntStream {
    private final int[] integerArray;

    private void checkIllegalArg() {
        if (integerArray.length == 0) {
            throw new IllegalArgumentException();
        }
    }

    private AsIntStream(int... values) {
        integerArray = values;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    @Override
    public Integer sum() {
        checkIllegalArg();
        int sum = 0;
        for (int val : integerArray) {
            sum += val;
        }
        return sum;
    }

    @Override
    public long count() {
        checkIllegalArg();
        return integerArray.length;
    }

    @Override
    public Double average() {
        return sum() / (double) count();
    }

    private Integer maxOrMin(int sign) {
        checkIllegalArg();
        int current = integerArray[0];
        for (int i = 0; i < count() - 1; i++) {
            int next = integerArray[i + 1];
            if (sign * Integer.compare(current, next) < 0) {
                current = next;
            }
        }
        return current;
    }

    @Override
    public Integer max() {
        return maxOrMin(1);
    }

    @Override
    public Integer min() {
        return maxOrMin(-1);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        int[] result = new int[(int) count()];
        int newSize = 0;
        int val;
        for (int i = 0; i < count(); i++) {
            val = integerArray[i];
            if (predicate.test(val)) {
                result[newSize] = val;
                newSize += 1;
            }
        }
        int[] finalResult = new int[newSize];
        for (int i = 0; i < newSize; i++) {
            finalResult[i] = result[i];
        }
        return new AsIntStream(finalResult);
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int val : integerArray) {
            action.accept(val);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        for (int i = 0; i < count(); i++) {
            integerArray[i] = mapper.apply(integerArray[i]);
        }
        return new AsIntStream(integerArray);
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        int flatSize = (int) (func.applyAsIntStream(
                integerArray[0]).count() * count());
        int[] result = new int[flatSize];
        int counter = 0;
        for (int i = 0; i < count(); i++) {
            IntStream flattened = func.applyAsIntStream(integerArray[i]);
            int[] flattenedArray = flattened.toArray();
            for (int val : flattenedArray) {
                result[counter++] = val;
            }

        }
        return new AsIntStream(result);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = identity;
        for (int val : integerArray) {
            result = op.apply(result, val);
        }
        return result;
    }

    @Override
    public int[] toArray() {
        return integerArray.clone();
    }
}
