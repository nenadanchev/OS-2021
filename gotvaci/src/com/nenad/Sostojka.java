package com.nenad;

public class Sostojka {
	//klasa vo koja ke cuvame objekti za sostojki

	private String ime;
	private String mernaEdinica;
	private int kolicina;
	private boolean nedostiga;





	public Sostojka(String ime, String mernaEdinica) {
		this.ime = ime;
		this.mernaEdinica = mernaEdinica;
		this.kolicina=0;
		this.nedostiga=true;
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

	public void setNedostiga(boolean n){
		this.nedostiga=n;
	}

	public boolean getNedostiga(){
		return this.nedostiga;
	}
}
