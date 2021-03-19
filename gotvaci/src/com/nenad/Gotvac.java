package com.nenad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class Gotvac extends Thread {
	// klasa koja implemetira thread- gotvac koj gotvi po daden recept

	Semaphore semRecepti;


	String ime;
	ArrayList<Sostojka> frizider; /// ovae friziderot
	ArrayList<HashMap<String,Integer>> recepti; //lista od recepti
	// koi treba da gi gotvime

	public Gotvac(String ime, ArrayList<Sostojka> sostojki) {
		this.ime = ime;
		this.frizider=sostojki;
		recepti= new ArrayList<HashMap<String,Integer>>();
		semRecepti= new Semaphore(1);
	}


// se povikuva od thread main, menuva lokalni polinja
	public void zgotvi(HashMap<String,Integer> recept) throws InterruptedException {
		// ni treba da zememe lista od namirnici so nivna kolicina


		//dodaj go na lista na recpti koi treba da gi zgotvime

		System.out.println(ime+ " pristigna nov recept");
		semRecepti.acquire();
		recepti.add(recept);
		semRecepti.release();

		System.out.println(ime+ " zapisa nov recept");

	}

	@Override
	public void run() { // raboti od ovoj thread
		//super.run();
		//ovde ke gotvi daden recept
		System.out.println(ime + " startuva...");

		while (true){ // beskonecen ciklus, go drzi gotvacot na strek
			///da proveruva dali stignale novi recepti

			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				semRecepti.acquire();

			if(!recepti.isEmpty()){  //ako ima recept
				HashMap<String,Integer> recept=recepti.get(0);

				System.out.println(ime+ "ima recept");

				//gotvi, zemi gi site sostojki
				Set<String> iminja= recept.keySet();
				for (String sostojka: iminja){

					System.out.println(ime+ "gotvam recept");

					// ja barame nasata sostojka vo friziderot
					for(Sostojka s:frizider){
						if(s.ime.equals(sostojka)){

							//ova ke ni ja vrati potrebnata kolicina
							int kol=recept.get(sostojka);
							//consumer

							System.out.println(ime+" bara " + kol + sostojka);
							s.potrosi(kol);
							//ova ke bara sinhronizacija
							System.out.println(ime+" potrosi " + kol + sostojka);

							recepti.remove(0); // ja trga prvata sostojka od receptot
						}
					}
					// izvesti deka si zgotvil
					zgotveno();

				}
			}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semRecepti.release();
			}

		}

	}

	private void zgotveno(){
		System.out.println("Gotvac "+ime+" zgotvi eden recept");

	}
}
