package com;

import rx.Observable;
import rx.Observer;

public class Example5 {
	public static void main(String[] args) {
		// emitting nothing and never terminating
		Observable<Object> observable = Observable.never();

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
