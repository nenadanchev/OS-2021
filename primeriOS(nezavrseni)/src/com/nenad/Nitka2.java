package com.nenad;


public class Nitka2 extends Thread {

	Naizmenicno n;

	public Nitka2(Naizmenicno n) {

		this.n=n;
	}


	@Override
	public void run() {
		for(int i=1;i<100;i++){

			n.f2();
		}
	}
}
