package com;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;

public class Example9 {
	public static void main(String[] args) {

		Observable<Integer> observable = Observable.range(10,3);

		Observer<Integer> observer = new Observer<Integer>() {

			public void onCompleted() {
				System.out.println("Completed.");
			}

			public void onError(Throwable e) {
				System.out.println("Error occured.");
				e.printStackTrace();
			}

			public void onNext(Integer t) {
			System.out.println("Item received: "+t);

			}
		};
		observable.subscribe(observer);

	}
}
