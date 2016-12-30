package silnia;

import java.util.ArrayList;
import java.util.List;

public class FactorialRecursionRunnable implements Runnable {

    private int val;
    private int repeats;
    private List<FactorialResultListener> l = new ArrayList<FactorialResultListener>();

    public void setValue(int val, int repeats) {
        this.val = val;
        this.repeats = repeats;
    }

    public void addFactorialResultListener(FactorialResultListener l) {
        this.l.add(l);
    }

    public void removeFactorialResultListener(FactorialResultListener l) {
        this.l.remove(l);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

        long avg = 0;
        long result = 0;

        for (int i = 0; i < repeats; i++) {

            long time = System.nanoTime();

            try {
                result = recursionCalculate(val);
            } catch (InterruptedException e) {
                return;
            }

            time = System.nanoTime() - time;

            avg += time;

        }

        avg /= repeats;

        for (FactorialResultListener li : l) {
            li.valueCalculated("Recursion value: " + result + ", calculate time: " + avg + " ns.");
        }

    }

    private long recursionCalculate(int i) throws InterruptedException {

        if (i <= 1) {
            return 1;
        }

        Thread.sleep(0);

        return i * recursionCalculate(i - 1);

    }

}
