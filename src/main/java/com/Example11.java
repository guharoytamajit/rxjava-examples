package com;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;

public class Example11 {
	public static void main(String[] args) {

		ArrayList<String> list = new ArrayList<String>();
		list.add("struts");
		list.add("hibernate");
		list.add("spring");
		Observable<String> observable = Observable.from(list).take(2);//similarly use takeLast().

		Observer<String> observer = new Observer<String>() {

			public void onCompleted() {
				System.out.println("Completed.");
			}

			public void onError(Throwable e) {
				System.out.println("Error occured.");
				e.printStackTrace();
			}

			public void onNext(String t) {
			System.out.println("Item received: "+t);

			}
		};
		observable.subscribe(observer);

	}
}
