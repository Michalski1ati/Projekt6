package silnia;

import java.util.ArrayList;
import java.util.List;

public class FactorialIterationRunnable implements Runnable {

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

            result = 1;

            for (long j = val; j > 1; j--) {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    return;
                }
                result *= j;
            }

            time = System.nanoTime() - time;

            avg += time;

        }

        avg /= repeats;

        for (FactorialResultListener li : l) {
            li.valueCalculated("Iteration value: " + result + ", calculate time: " + avg + " ns.");
        }

    }

}
