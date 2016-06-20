package multithread;

import rx.Observable;

public class Emample2_multiThread_withRunnable {
	private static long start = System.currentTimeMillis();

	public static void main(String[] args) {
		log("Starting");
		final Observable<String> obs = simple();
		log("Created");
		final Observable<String> obs2 = obs.map(x -> x).filter(x -> true);
		log("Transformed");

		obs2.subscribe(x -> log("obs2 Got " + x), Throwable::printStackTrace,
				() -> log("obs2 Completed"));
		obs.subscribe(x -> log("obs Got " + x), Throwable::printStackTrace,
				() -> log("obs Completed"));
		log("Exiting");
	}

	// using your own thread inside create() is not recommented rather you
	// should use declarative approach provided by Rx
	static Observable<String> simple() {
		return Observable.create(subscriber -> {
			log("Subscribed");
			Runnable code = () -> {
				sleep(2000);
				subscriber.onNext("A");
				subscriber.onNext("B");
				subscriber.onCompleted();
			};
			new Thread(code).start();
		});
	}

	static void log(Object label) {

		System.out.println(System.currentTimeMillis() - start + "\t| "
				+ Thread.currentThread().getName() + "\t| " + label);
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
