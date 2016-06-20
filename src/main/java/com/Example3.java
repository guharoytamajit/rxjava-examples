package com;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Subscriber;

public class Example3 {
	public static void main(String[] args) {

		Observable<Object> observable = Observable
				.create(new OnSubscribe<Object>() {

					public void call(Subscriber<? super Object> subscriber) {
						for (Integer i = 0; i < 10; i++) {
							subscriber.onNext(i);
						}
						subscriber.onCompleted();
					}
				});

		Observer<Object> observer = new Observer<Object>() {

			public void onCompleted() {
				System.out.println("Completed.");
			}

			public void onError(Throwable e) {
				System.out.println("Error occured.");
				e.printStackTrace();
			}

			public void onNext(Object t) {
				System.out.println("Item received: " + t);

			}
		};
		observable.subscribe(observer);

	}
	

}
