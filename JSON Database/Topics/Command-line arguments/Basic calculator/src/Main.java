
class Problem {
    public static void main(String[] args) {
        switch (args[0]) {
            case "+" -> System.out.println(Integer.parseInt(args[1]) + Integer.parseInt(args[2]));
            case "-" -> System.out.println(Integer.parseInt(args[1]) - Integer.parseInt(args[2]));
            case "*" -> System.out.println(Integer.parseInt(args[1]) * Integer.parseInt(args[2]));
            default -> System.out.println("Unknown operator");
        }
    }
}
