import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static HashMap<Integer, Integer> countConsecutives(boolean[] b) {
        HashMap<Integer, Integer> result = new HashMap<>();
        int runSize = 1;
        boolean runType = b[0];
        boolean c;



        for(int i = 1; i < b.length; ++i) {
            c = b[i];

            if(runType == c) {
                ++runSize;
                continue;
            }

            result.computeIfPresent(runSize, (integer, integer2) -> integer2 + 1);
            result.putIfAbsent(runSize, 1);
            runSize = 1;
            runType = c;
        }

        result.computeIfPresent(runSize, (integer, integer2) -> integer2 + 1);
        result.putIfAbsent(runSize, 1);

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String s;
        boolean[] b;
        HashMap<Integer, Integer> h = new HashMap<>();

        while((s = br.readLine()) != null) {
            String[] t = s.split("\\s+");
            b = new boolean[t.length];

            int i = 0;
            for(String u : t) {
                if(u.equalsIgnoreCase("H")) {
                    b[i++] = true;
                    continue;
                }

                b[i++] = false;
            }

            HashMap<Integer, Integer> j = countConsecutives(b);
            for(int k : j.keySet()) {
                h.computeIfPresent(k, (integer, integer2) -> integer2 + j.get(k));
                h.putIfAbsent(k, j.get(k));
            }
        }

        System.out.println(h);
    }
}
