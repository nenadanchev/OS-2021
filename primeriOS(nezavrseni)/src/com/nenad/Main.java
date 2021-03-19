package com.nenad;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here


		Naizmenicno naizmenicno= new Naizmenicno();

		naizmenicno.init(5);


		Nitka1 nitka1=new Nitka1(naizmenicno);
		Nitka2 nitka2=new Nitka2(naizmenicno);


		nitka1.start();
		nitka2.start();





		//File file1 = new File("/home/nenad/FINKI/OS/av1");
        //File novFolder = new File("/home/nenad/FINKI/OS/av1/probenFolder/novFolder");

        //File file2 = new File("/home/nenad/FINKI/OS/av1/probenFolder/novFolder");

        //file2.mkdir();

        //System.out.println(file2.exists());


		// da go izbriseme novFolder
		// rekurzivno brisenje


        //recursiveList(file1);

        //recursiveDelete(novFolder);

		//recursiveList(file1);


        /*
        if (file1.exists()) {
			System.out.println(fileProperties(file1));

			if(file1.isDirectory()){

				String [] filesList = file1.list();
				for(String fileIn : filesList){
					System.out.println(fileIn);
				}
			}
		} else {
        	System.out.println(" File does not exist!");
		}

		*/

        //dataStoreStreams("/home/nenad/FINKI/OS/av1/proben.txt",
		//		"/home/nenad/FINKI/OS/av1/proben.txt");
    	//randomAccess();

    }

    public static String fileProperties(File file){
    	String ret="";

    	ret+= file.getName() + " "  + file.getAbsolutePath() + " folder:" + file.isDirectory() + " " +
				file.length() + " r:" + file.canRead() + " w:" + file.canWrite() + " x:" + file.canExecute();
    	return ret;

	}

	public static void recursiveList(File file){

    	if(!file.exists()) return;

    	System.out.println(fileProperties(file));

    	if(file.isDirectory()){

    		File [] filesInside= file.listFiles();

    		for(File fileIn: filesInside){

    			recursiveList(fileIn);
			}
		}
	}

	public static void recursiveDelete(File file){

		if(!file.exists()) return;



		if(file.isDirectory()){

			File [] filesInside= file.listFiles();

			for(File fileIn: filesInside){


				// ako e fajl, brisi go tuka7
				if(!fileIn.isDirectory()){
					if (fileIn.delete()) {
						System.out.println("Deleted: "+fileProperties(fileIn));
					}
				}
				recursiveDelete(fileIn);
				//System.out.println(fileProperties(file));

			}
			// folderite brisi gi tuka
			//System.out.println(fileProperties(file));
			if (file.delete()) {
				System.out.println("Deleted: "+fileProperties(file));
			}
		}
	}

	public static void dataStoreStreams (String inFile, String outFile){

		DataOutputStream out;
		DataInputStream in;
		try {
			out = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream(outFile)));


			//out.writeFloat((float)(3.14));
			out.writeDouble(5.14);
			out.writeDouble(7.14);
			out.writeUTF("\n");
			out.writeUTF("hagsfiyvaisf\n");



			out.close();

			in = new DataInputStream(new BufferedInputStream(new FileInputStream(inFile)));

			System.out.println(in.readUTF());
			//System.out.println(in.readDouble());
			//System.out.println(in.readDouble());

			in.close();


		} catch (Exception ex){
			System.err.println("Some exception "+ ex.getStackTrace());
		}


	}

	static String filePath = "rtest.dat";

	static void display() throws IOException {
		RandomAccessFile rf = null;
		try{
			rf = new RandomAccessFile(filePath, "r");
			for(int i = 0; i < 7; i++)
			{
				System.out.println("Value " + i + ": " + rf.readDouble());
			}
			System.out.println(rf.readUTF());
		} finally { if(rf!=null) rf.close(); }
	}

	public static void randomAccess() throws IOException{
		RandomAccessFile rf = null;
		try{
			rf = new RandomAccessFile(filePath, "rw");
			for(int i = 0; i < 7; i++) {
				rf.writeDouble(i * 1.414);
			}
			rf.writeUTF("The end of the file");
		} finally { if(rf!=null) rf.close(); }
		display();
		try{
			rf = new RandomAccessFile(filePath, "rw");
			rf.seek(5*8);
			rf.readDouble();
			rf.writeDouble(47.0001);
		} finally { if(rf!=null) rf.close(); }
		display();
	}


}
