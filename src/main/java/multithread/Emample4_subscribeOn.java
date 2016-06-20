package multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

public class Emample4_subscribeOn {
	private static long start = System.currentTimeMillis();

	public static void main(String[] args) throws InterruptedException {
		log("Starting");
		ExecutorService poolA = Executors.newFixedThreadPool(10);
		Scheduler schedulerA = Schedulers.from(poolA);
		final Observable<String> obs = simple();
		log("Created");
		// applying subscribeOn() in any point of the chain is same as applying
		// subscribeOn() after obs.So every thing after log("Created"); will be
		// executed by thread pool.
		// if we apply multiple subscribeOn() in chain first one will be
		// considered
		obs.map(x -> {
			log("from map");
			return x;
		})
				.subscribeOn(schedulerA)
				.map(x -> {
					log("from map2");
					return x;
				})
				.subscribe(x -> log("obs2 Got " + x),
						Throwable::printStackTrace, () -> log("obs2 Completed"));

		poolA.shutdown();
	}

	static Observable<String> simple() {
		return Observable.create(subscriber -> {
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
