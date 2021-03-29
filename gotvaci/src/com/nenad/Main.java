package com.nenad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	// write your code here


        Sostojka mleko= new Sostojka("Mleko","ml");
        Sostojka brasno= new Sostojka("Brasno","gr");
        Sostojka kakao= new Sostojka("Kakao","gr");

        ArrayList<Sostojka> namirnici= new ArrayList<Sostojka>();
        Lock lockFrizider = new ReentrantLock();

        namirnici.add(mleko);
        namirnici.add(brasno);
        namirnici.add(kakao);

        Gotvac g1=new Gotvac("Marko",namirnici,lockFrizider);
        Gotvac g2=new Gotvac("Petko",namirnici,lockFrizider);

        Dostavuvac d=new Dostavuvac("Mirko",namirnici,lockFrizider);

        // mu kazuva na gotvacot da pocne so rabota


        g1.start();
        g2.start();



        d.start();

        // ako povikame run, blokira

        HashMap<String,Integer> recept1= new HashMap<String,Integer>();
        recept1.put("Brasno",400);
        recept1.put("Kakao",600);
        recept1.put("Mleko",300);

        HashMap<String,Integer> recept2= new HashMap<String,Integer>();
        recept2.put("Brasno",1400);
        recept2.put("Kakao",1600);
        recept2.put("Mleko",1300);

        HashMap<String,Integer> recept3= new HashMap<String,Integer>();
        recept3.put("Brasno",500);
        recept3.put("Kakao",700);
        recept3.put("Mleko",700);

        HashMap<String,Integer> recept4= new HashMap<String,Integer>();
        recept4.put("Brasno",2500);
        recept4.put("Kakao",5700);
        recept4.put("Mleko",3700);


        g1.zgotvi(recept1);
        g2.zgotvi(recept2);
        g1.zgotvi(recept3);
        g2.zgotvi(recept4);


        // sme gi dale site recepti za gotvenje

        g1.stopiraj();
        g2.stopiraj();


        g1.join(); // ceka da zavrsi g1
        g2.join();

        d.stopiraj();

        d.join();


        // ke treba da im dademe na gotvacite recepti

    }
}
