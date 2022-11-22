import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Setup inside array
        int[] a0 = new int[]{1, 2};
        int[] a1 = new int[]{2, 3};

        //hash factor
        int p = 2;

        // Initialize hash result for inside arrays
        int hash_a0 = 0;
        int hash_a1 = 0;

        // Calculate hash by iterating through following formula:
        //    hash = hash_previous * p + element
        for (int i = 0; i < 2; i++) {
            hash_a0 = hash_a0 * p + a0[i];
            hash_a1 = hash_a1 * p + a1[i];
        }

        // Calculate hash for outside array
        int hash_b0 = 0 * p + hash_a0;
        int hash_b1 = hash_b0 * p + hash_a1;

        // Print results
        System.out.println("Inside array hash: " + "[" + hash_a0 + ", " + hash_a1 + "]");
        System.out.println("Outside array hash: " + "[" + hash_b0 + ", " + hash_b1 + "]");
        System.out.println("Hash Result (last computed value): " + hash_b1);
    }
}