/**
 * Class to work with
 */
class Multiplicator {

	public static <T extends Copy<T>> Folder<T>[] multiply(Folder<T> folder, int arraySize) {
		// Method to implement
        Folder<T>[] copies = new Folder[arraySize];

        for (int i = 0; i < arraySize; ++i) {
            copies[i] = new Folder<>();
            copies[i].put(folder.get().copy());
        }

        return copies;
	}

}

// Don't change the code below
interface Copy<T> {
	T copy();
}

class Folder<T> {

    private T item;

    public void put(T item) {
    	this.item = item;
    }

    public T get() {
        return this.item;
    }
}

public class Main {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        class MyRunnable implements Runnable {

            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println(name + " BEFORE: " + threadLocal.get());

                threadLocal.set(name + " value");
                System.out.println(name + " AFTER: " + threadLocal.get());
            }
        };

        threadLocal.set("threadLocal main value");

        Thread thread1 = new Thread(new MyRunnable(), "first_thread");
        Thread thread2 = new Thread(new MyRunnable(), "second_thread");

        // first
        thread1.start();
        thread1.join();
        // second
        thread2.start();
        thread2.join();

        System.out.println("From the main thread: " + threadLocal.get());
    }
}