class CountDownCounter {

    int count;

    public CountDownCounter(int initial) {
        this.count = initial;
    }

    public synchronized void decrement() {
        count--;
    }

    public int getCount() {
        return this.count;
    }
}

class Worker extends Thread {

    private final CountDownCounter counter;

    public Worker(CountDownCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10_000; i++) {
            counter.decrement();
        }
    }
}

class Main {
    public static void main(String[] args) {
        CountDownCounter countDownCounter = new CountDownCounter(100000);

        Worker worker1 = new Worker(countDownCounter);
        Worker worker2 = new Worker(countDownCounter);
        Worker worker3 = new Worker(countDownCounter);
        Worker worker4 = new Worker(countDownCounter);

        worker1.start();
        worker2.start();
        worker3.start();
        worker4.start();

        System.out.println(countDownCounter.getCount());
    }
}