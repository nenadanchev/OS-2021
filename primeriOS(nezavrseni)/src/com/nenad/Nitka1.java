package com.nenad;


public class Nitka1 extends Thread {

	Naizmenicno n;

	public Nitka1(Naizmenicno n) {
		this.n=n;

	}

	public void run(){

		for(int i=1;i<100;i++){

			n.f1();
		}
	}
}
