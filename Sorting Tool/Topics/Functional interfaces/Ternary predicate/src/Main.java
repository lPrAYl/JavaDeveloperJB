
class Predicate {
    public static final TernaryIntPredicate ALL_DIFFERENT =
            (a, b, c) -> a != b && a != c && b != c; // Write a lambda expression here

    @FunctionalInterface
    public interface TernaryIntPredicate {
        // Write a method here
        abstract boolean test(int a, int b, int c);

    }
}
