package com.nenad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Gotvac extends Thread {
	// klasa koja implemetira thread- gotvac koj gotvi po daden recept

	Semaphore semRecepti;
	Lock friziderLock;
	private boolean raboti;
	private boolean imaRecepti;


	String ime;
	ArrayList<Sostojka> frizider; /// ovae friziderot
	ArrayList<HashMap<String,Integer>> recepti; //lista od recepti
	// koi treba da gi gotvime

	public Gotvac(String ime, ArrayList<Sostojka> sostojki, Lock friziderLock) {
		this.ime = ime;
		this.frizider=sostojki;
		recepti= new ArrayList<HashMap<String,Integer>>();
		semRecepti= new Semaphore(1);
		this.friziderLock=friziderLock;
		raboti=true;
		imaRecepti=true;

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

		while (raboti || imaRecepti){ // beskonecen ciklus, go drzi gotvacot na strek
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

				System.out.println(ime+ " ima recept");

				//gotvi, zemi gi site sostojki
				Set<String> iminja= recept.keySet();


				System.out.println(ime+ " gotvam recept");
				for (String sostojka: iminja){


//tuka bi zaklucile, ako vo friziderot vo runtimemozat da se pojavat
					/// novi tipovi na sostojki

					// ja barame nasata sostojka vo friziderot
					for(Sostojka s:frizider){
						if(s.getIme().equals(sostojka)){


							//tuka zaklucuvame, ako ne mozat da se pojavat novi sostojki
							friziderLock.lock();
							//ova ke ni ja vrati potrebnata kolicina
							int kol=recept.get(sostojka);
							//consumer

							System.out.println(ime+" bara " + kol + sostojka);

							// sakame da implementirame cekanje, podobro e while namesto if
							while(s.proveriKolicina()<kol){

								s.setNedostiga(true);
								friziderLock.unlock();
								System.out.println(ime+ " ceka da se kupi "+s.getIme());
								Thread.sleep(1000);

								friziderLock.lock();
							}

							s.setNedostiga(false);
							s.potrosi(kol);
							friziderLock.unlock();

							//ova ke bara sinhronizacija
							System.out.println(ime+" potrosi " + kol + sostojka);

						}
					}

				}

				// izvesti deka si zgotvil
				zgotveno();
			} else {
				imaRecepti=false;
			}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semRecepti.release();
			}

		}
		System.out.println(ime+ " zavrsiv");


	}

	private void zgotveno(){
		recepti.remove(0); // go trga prviot recept i izvestuva deka e zgotven
		System.out.println("Gotvac "+ime+" zgotvi eden recept");

	}

	public void stopiraj(){
		raboti=false;
	}
}
