
class UseThreadLocal {
    public static ThreadLocal<Integer> makeThreadLocal(int counter) {
        // write your code here
        final ThreadLocal<Integer> threadLocalCounter = new ThreadLocal<>();
        threadLocalCounter.set(counter + 1);
        return threadLocalCounter.;
    }
}