package com;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

public class Example13 {
	public static void main(String[] args) throws InterruptedException {

		Observable// .just(1l)
				.timer(1, TimeUnit.SECONDS).subscribe((Long zero) -> log(zero),
						(t) -> {
							System.out.println("error");
						}, () -> {
							System.out.println("done");
						});
		
			Thread.sleep(2000);
		

	}

	private static void log(Long zero) {
		System.out.println(zero);
	}
}
