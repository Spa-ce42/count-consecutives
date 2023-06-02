import java.util.Random;

public class RandomSequenceGenerator {
    public static void main(String[] args) {
        Random r = new Random();
        for(int i = 0; i < 40; ++i) {
            System.out.print(r.nextBoolean() ? "H " : "T ");
        }
        System.out.println();
        for(int i = 0; i < 17; ++i) {
            System.out.print("H ");
        }

        for(int i = 0; i < 16; ++i) {
            System.out.print("T ");
        }
    }
}
