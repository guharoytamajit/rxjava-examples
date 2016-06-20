package com;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;

public class Example1 {
	public static void main(String[] args) {

		ArrayList<String> list = new ArrayList<String>();
		list.add("struts");
		list.add("spring");
		list.add("hibernate");
		Observable<String> observable = Observable.from(list);

		Observer<String> observer = new Observer<String>() {

			public void onCompleted() {
				System.out.println("Completed.");
			}

			public void onError(Throwable e) {
				System.out.println("Error occured.");
				e.printStackTrace();
			}

			public void onNext(String t) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			System.out.println("Item received: "+t);

			}
		};
		observable.subscribe(observer);

	}
}
