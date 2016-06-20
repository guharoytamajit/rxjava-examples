package com;

import rx.Observable;
import rx.Observer;

public class Example4 {
	public static void main(String[] args) {
//emitting nothing but terminating normally
		Observable<Object> observable =Observable.empty();

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
