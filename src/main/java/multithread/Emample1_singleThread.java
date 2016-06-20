package multithread;

import rx.Observable;

public class Emample1_singleThread {
	private static long start = System.currentTimeMillis();

	public static void main(String[] args) {
		log("Starting");
		final Observable<String> obs = simple();
		log("Created");
		final Observable<String> obs2 = obs.map(x -> x).filter(x -> true);
		log("Transformed");
		// obs2.subscribe() is blocked by Observable.create() if we have
		// Thread.sleep() in Observable.create() then subscribe() has to wait
		obs2.subscribe(x -> log("obs2 Got " + x), Throwable::printStackTrace,
				() -> log("obs2 Completed"));
		obs.subscribe(x -> log("obs Got " + x), Throwable::printStackTrace,
				() -> log("obs Completed"));
		log("Exiting");
	}

	static Observable<String> simple() {
		return Observable.create(subscriber -> {
			sleep(2000);
			log("Subscribed");
			subscriber.onNext("A");
			subscriber.onNext("B");
			subscriber.onCompleted();
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
