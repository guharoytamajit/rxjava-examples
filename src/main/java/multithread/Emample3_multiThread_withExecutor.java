package multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

public class Emample3_multiThread_withExecutor {
	private static long start = System.currentTimeMillis();

	// You should treat subscribeOn() only in special cases when underlying
	// Observable is known to be synchronous(not when asynchronous)

	// However subscribeOn() is still much better solution than hand-crafted
	// threading inside create():
	public static void main(String[] args) throws InterruptedException {
		log("Starting");
		ExecutorService poolA = Executors.newFixedThreadPool(10);
		Scheduler schedulerA = Schedulers.from(poolA);
		final Observable<String> obs = simple();
		log("Created");
		final Observable<String> obs2 = obs.map(x -> x).filter(x -> true);
		log("Transformed");
		// obs2.subscribe() is blocked by Observable.create() if we have
		// Thread.sleep() in Observable.create() then subscribe() has to wait
		obs2.subscribeOn(schedulerA).subscribe(x -> log("obs2 Got " + x),
				Throwable::printStackTrace, () -> log("obs2 Completed"));
		obs.subscribeOn(schedulerA).subscribe(x -> log("obs Got " + x),
				Throwable::printStackTrace, () -> log("obs Completed"));
		log("Exiting");

		// poolA.awaitTermination(6, TimeUnit.SECONDS);
		poolA.shutdown();
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
