package multithread;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

public class Emample7_threadpoolPerSubscription {
	private static long start = System.currentTimeMillis();

	public static void main(String[] args) throws InterruptedException {
		ExecutorService poolA = Executors.newFixedThreadPool(5);
		Scheduler schedulerA = Schedulers.from(poolA);
		RxGroceries rxGroceries = new RxGroceries();
		Observable<BigDecimal> totalPrice = Observable
				.just("bread", "butter", "milk", "tomato", "cheese")
				.flatMap(
						prod -> rxGroceries.purchase(prod, 1).subscribeOn(
								schedulerA)).reduce(BigDecimal::add).single();
		totalPrice.subscribe(x -> System.out.println("Total price:" + x));
		
		Observable
		.just("bread", "butter", "milk", "tomato", "cheese")
		.flatMap(
				prod -> rxGroceries.purchase(prod, 1).subscribeOn(
						schedulerA)).toList();
		System.out.println("done");
		poolA.shutdown();
	}

}
