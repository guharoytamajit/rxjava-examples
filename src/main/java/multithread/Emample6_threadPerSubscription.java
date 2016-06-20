package multithread;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

public class Emample6_threadPerSubscription {
	private static long start = System.currentTimeMillis();

	public static void main(String[] args) throws InterruptedException {
		ExecutorService poolA = Executors.newFixedThreadPool(10);
		Scheduler schedulerA = Schedulers.from(poolA);
		RxGroceries rxGroceries=new RxGroceries();
		Observable<BigDecimal> totalPrice = Observable
				.just("bread", "butter", "milk", "tomato", "cheese")
				.subscribeOn(schedulerA)
				.map(prod -> rxGroceries.doPurchase(prod, 1))
				.reduce(BigDecimal::add)
				.single();
		totalPrice.subscribe(x->System.out.println("Total price:"+x));
		poolA.shutdown();
	}



}
