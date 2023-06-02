import java.util.HashMap;
import java.util.Random;

public class ExpectedCount {
    private static HashMap<Integer, Integer> produceExpectedCounts(Random r, int sampleSize, int sequenceLength) {
        HashMap<Integer, Integer> result = new HashMap<>();
        boolean[] b = new boolean[sequenceLength];

        for(int i = 0; i < sampleSize; ++i) {
            for(int j = 0; j < b.length; ++j) {
                b[j] = r.nextBoolean();
            }

            HashMap<Integer, Integer> m = Main.countConsecutives(b);

            for(int k : m.keySet()) {
                result.computeIfPresent(k, (integer, integer2) -> integer2 + m.get(k));
                result.putIfAbsent(k, m.get(k));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> h = new HashMap<>();
        final int ITERATIONS = 1000000;
        final int SAMPLE_SIZE = 4;
        final int SEQUENCE_LENGTH = 40;
        Random r = new Random();
        int count = 0;

        for(int i = 0; i < ITERATIONS; ++i) {
            HashMap<Integer, Integer> j = produceExpectedCounts(r, SAMPLE_SIZE, SEQUENCE_LENGTH);

            boolean c = false;

            for(int k : j.keySet()) {
                if(k >= 5) {
                    c = true;
                }

                h.computeIfPresent(k, (integer, integer2) -> integer2 + j.get(k));
                h.putIfAbsent(k, j.get(k));
            }

            if(c) ++count;
        }

        for(int k : h.keySet()) {
            System.out.println(k + ": " + ((double)h.get(k) / ITERATIONS));
        }

        System.out.println((double)count / ITERATIONS);
    }
}
/*
1: 42
2: 20.5
3: 10
4: 4.8725
5: 2.374
6: 1.156262
7: 0.562439
8: 0.274505
9: 0.133261
10: 0.064253
 */
//{1=43, 2=23, 3=6, 4=4, 5=5, 6=2} p = ~0.381
//{1=41, 2=22, 3=13, 4=4, 5=1, 6=1, 9=1} p = ~0.596
//{1=32, 2=19, 3=11, 4=8, 5=2, 7=1, 8=1} p = ~0.274
//{1=58, 2=30, 3=10, 4=3} p = ~0.00478
//{1=45, 2=31, 3=12, 4=3, 5=1} p = ~0.0500 or ~0.049978
//{1=51, 2=37, 3=9, 4=2} p = ~0.000474