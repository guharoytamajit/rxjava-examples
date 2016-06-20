package com;

import rx.Observable;
import rx.Observer;

public class Example2 {
	private static String helloWorld() {
		return "Hello World";
		}
	public static void main(String[] args) {

		Observable<String> observable = Observable.just("struts", "spring",
				"hibernate",helloWorld());

		Observer<String> observer = new Observer<String>() {

			public void onCompleted() {
				System.out.println("Completed.");
			}

			public void onError(Throwable e) {
				System.out.println("Error occured.");
				e.printStackTrace();
			}

			public void onNext(String t) {
				System.out.println("Item received: " + t);

			}
		};
		observable.subscribe(observer);

	}
}
