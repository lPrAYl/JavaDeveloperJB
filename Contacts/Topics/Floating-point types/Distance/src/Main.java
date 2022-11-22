import java.io.Serializable;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

class Main {
    final double variable = 10;
    Serializable serializable = new Serializable() {
        double applyFun(double x) {
            return x + variable;
        }
    };


    Callable<String> callable = new Callable<>() {
        @Override
        public String call() throws Exception {
            Thread.sleep(100);
            return "hello";
        }
    };



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here

        double distance = scanner.nextDouble();
        double time = scanner.nextDouble();

        System.out.println(distance / time);
    }
}