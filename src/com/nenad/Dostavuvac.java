package com.nenad;

import java.util.ArrayList;
import java.util.Random;


// producer thread
public class Dostavuvac extends Thread {
	// dostavuvac koj kupuva i polni namirnici

	String ime;
	ArrayList<Sostojka> namirnici;

	public Dostavuvac(String ime, ArrayList<Sostojka> namirnici) {
		this.ime = ime;
		this.namirnici = namirnici;
	}

	@Override
	public void run() {
		super.run();

		// celo vreme ke proveruva dali nedostiga dadena namirnica

		while (true){

			// proveruva vo friziderot, site namirnici
			for(Sostojka s:namirnici){

				// ako fali nekoja (pomalku od 1000 edinici)
				if (s.proveriKolicina()<1000){
					Random r=new Random();

					int kol = r.nextInt(10)*1000;

					// kupiva random kolicina (pomegu 1 000 i 10 000)
					s.kupi(kol);
					System.out.println(ime+" kupi "+ kol + " "
							+ s.getMernaEdinica() + " " + s.getIme()  );
				}
			}


		}
	}
}
