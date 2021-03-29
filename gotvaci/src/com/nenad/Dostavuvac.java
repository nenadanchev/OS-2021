package com.nenad;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;


// producer thread
public class Dostavuvac extends Thread {
	// dostavuvac koj kupuva i polni namirnici

	private String ime;
	private ArrayList<Sostojka> namirnici;
	private Lock lockFrizider;
	private boolean raboti;

	public Dostavuvac(String ime, ArrayList<Sostojka> namirnici, Lock lockFrizider) {
		this.ime = ime;
		this.namirnici = namirnici;
		this.lockFrizider=lockFrizider;
		this.raboti=true;
	}

	@Override
	public void run() {
		super.run();

		// celo vreme ke proveruva dali nedostiga dadena namirnica

		while (raboti){

			//ako e definirano deka vo runtime moze da se pojavat novi namirnici
			// lockot ke odi tuka
			// proveruva vo friziderot, site namirnici
			for(Sostojka s:namirnici){

				// ako ne mozat da se pojavat celosno novi objekti od namirnici
				// lockot ke odi tuka

				lockFrizider.lock();
				// ako fali nekoja (pomalku od 1000 edinici)
				if (s.proveriKolicina()<1000 || s.getNedostiga()){
					Random r=new Random();

					int kol = r.nextInt(10)*1000;

					// kupiva random kolicina (pomegu 1 000 i 10 000)
					s.kupi(kol);
					System.out.println(ime+" kupi "+ kol + " "
							+ s.getMernaEdinica() + " " + s.getIme()  );
				}
				lockFrizider.unlock();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println(ime+ " zavrsiv");
	}

	public void stopiraj(){
		raboti=false;
	}
}
