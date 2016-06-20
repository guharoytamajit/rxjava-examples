package com;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

public class Example7 {
	public static void main(String[] args) {
		final PublishSubject<Boolean> subject = PublishSubject.create();
		subject.subscribe(new Observer<Boolean>() {
			@Override
			public void onCompleted() {
			}
			@Override
			public void onError(Throwable e) {
			}
			@Override
			public void onNext(Boolean t) {
				System.out.println("Observable completed!");
			}
		});

		Observable.create(new Observable.OnSubscribe<Integer>() {
			@Override
			public void call(Subscriber<? super Integer> subscriber) {
				for (int i = 0; i < 5; i++) {
					subscriber.onNext(i);
				}
				System.out.println("compleating..");
				subscriber.onCompleted();
			}
		}).doOnCompleted(new Action0() {
			@Override
			public void call() {
				subject.onNext(true);
			}
		}).subscribe();
	}

}
