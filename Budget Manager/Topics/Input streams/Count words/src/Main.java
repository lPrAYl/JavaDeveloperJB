import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // start coding here
        String line = reader.readLine().trim();
        reader.close();

        if (line.isEmpty()) {
            System.out.println(0);
        } else {
            String[] s = line.split("\\s+");
            System.out.println(s.length);
        }
    }
}