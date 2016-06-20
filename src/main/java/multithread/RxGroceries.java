package multithread;

import java.math.BigDecimal;

import rx.Observable;

public class RxGroceries {
	Observable<BigDecimal> purchase(String productName, int quantity) {
		return Observable.create(subscriber -> {
			subscriber.onNext(doPurchase(productName, quantity));
			subscriber.onCompleted();
		});
	}

	BigDecimal doPurchase(String productName, int quantity) {
		log("Purchasing " + quantity + " " + productName);
		sleep(1000);// real logic here
		log("Done " + quantity + " " + productName);
		BigDecimal priceForProduct = new BigDecimal(50);
		return priceForProduct;
	}

	static void log(Object label) {

		System.out.println(Thread.currentThread().getName() + "\t| " + label);
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
