package com;

import rx.Observer;
import rx.subjects.PublishSubject;

public class Example6 {
	public static void main(String[] args) {
		// Subject = Observable + Observer
		PublishSubject<String> publishSubject = PublishSubject.create();

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
		publishSubject.subscribe(observer);
		
		publishSubject.onNext("Hello World");

	}

}
