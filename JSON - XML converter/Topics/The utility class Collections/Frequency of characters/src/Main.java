import java.util.*;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        // put your code here
        Deque<String> states = new ArrayDeque<String>();

        states.add("Germany");
        states.add("France");
        states.push("UK");
        states.offerLast("Norway");


        String sPop = states.pop();
        String sPeek = states.peek();
        String sPeekLast = states.peekLast();
        states.offer(sPop);
        while (states.peek() != null) {
            System.out.print(states.pop());
        }
        System.out.println(sPeekLast);
//        String sPollLast = states.pollLast();

//        while (states.peek() != null) {
//            System.out.print(states.pop());
//        }

    }
}