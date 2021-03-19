package com.nenad;

import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

/*
Да се напише програмски код за наизменично извршување на две функции
f1() и f2(). Нивното повикување да се имплементира со помош на нитки, ok
 така
што првата нитка непрекинато ќе ја повикува функцијата f1(),
додека пак
втората нитка непрекинато ќе ја повикува функцијата f2(). ok
Покрај тоа што
нитките треба да обезбедат наизменично повикување на функциите, //todo naizmenicno
 треба да
се внимава и на тоа дека функцијата f1() ќе се повикува повеќе пати од
функцијата f2() и притоа тој број да не е поголем од N.
//todo da proverime sinhronizaija poradi povicite od f2 kon f1
 */

public class Naizmenicno {

	public static int NUM_RUNS = 1000;

	int f1count;
	int f2count;
	int maxDifference = 0;

	/**
	 * Metod koj treba da gi inicijalizira vrednostite na semaforite i
	 * ostanatite promenlivi za sinhronizacija.
	 */
	Semaphore f1;
	Semaphore f2;
	Object obj;
	Object obj2;

	public void init(int count) {
//da se implementira
		maxDifference = count;
		f1 = new Semaphore(maxDifference);
		f2 = new Semaphore(1);
		obj = new Object();

	}

	public void f1(){


		try {
			f1.acquire();
			System.out.println(" Vo f1 ");

		} catch (InterruptedException e) {
			e.printStackTrace();

			System.err.println(" Semaphore is locked!");
		}

		f1.release();


	}

	public void f2(){


		try {
			f2.acquire();
			for (int i=0;i<maxDifference;i++){
				f1();
			}


			System.out.println(" Vo f2 ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// ova n da dozvoli n+1 povici kaj f1. Inaku ke blokira

		// ke ja povikuva poveke pati f1, no toj broj da ne e pogolem od N

		f2.release();

	}
}