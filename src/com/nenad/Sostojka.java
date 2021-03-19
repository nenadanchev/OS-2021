package com.nenad;

public class Sostojka {
	//klasa vo koja ke cuvame objekti za sostojki

	String ime;
	String mernaEdinica;
	int kolicina;

	public Sostojka(String ime, String mernaEdinica) {
		this.ime = ime;
		this.mernaEdinica = mernaEdinica;
		this.kolicina=0;
	}

	public int proveriKolicina(){
		return kolicina;
	}

	public void kupi(int kol){
		kolicina+=kol;
	}

	public void potrosi(int kol){
		kolicina-=kol;
	}


	public String getIme() {
		return ime;
	}

	public String getMernaEdinica() {
		return mernaEdinica;
	}
}
