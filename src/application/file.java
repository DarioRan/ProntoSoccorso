package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class file implements Serializable
{
	private String FilePath;
	private String Apertura;
	private BufferedWriter bw;
	private BufferedReader br;
	private FileWriter fw;
	private FileReader fr;
	private FileInputStream fis;
	private FileOutputStream fos;
	private ObjectOutputStream ow;
	private ObjectInputStream or;
	private File file1;
	
	public file(String File) throws IOException 
	{
		this.FilePath=File;
		file1 = new File(FilePath);
		if(file1.exists()==false) 
		{
			file1.createNewFile();
			System.out.println("File Creato");
		}
	}
	
	public void apri(String apertura) throws IOException 
	{
		 
		 this.Apertura=apertura;
		switch (Apertura) 
		{
			case "a":
				 fw=new FileWriter(file1,true);
				 bw= new BufferedWriter(fw);
				break;
				
			case "w":
				 fw=new FileWriter(file1);
				 bw= new BufferedWriter(fw);
				break;
			
			case "r":
				 fr=new FileReader(file1);
				 br= new BufferedReader(fr);
				break;
				
			case "or":
				fis=new FileInputStream(file1);
				or=new ObjectInputStream(fis);
				break;
			
			case "ow":
				fos=new FileOutputStream(file1);
				ow=new ObjectOutputStream(fos);
				break;	
		}
	
	}
	
	
	
	public String leggi() throws IOException 
	{
		return br.readLine();
	}
	
	public void scrivi(String Stringa) throws IOException 
	{
		bw.write(Stringa+"\n");
	}
	
	public void append(String Stringa) throws IOException
	{
		bw.write(Stringa+"\n");
	}
	
	
	public String[] cercaRecord(String Stringa) throws IOException
	{
		fr=new FileReader(file1);
		br= new BufferedReader(fr);
		String[] s=new String[10];
		String riga=br.readLine();
		//System.out.println(Stringa.length());
		int i=0;
		try 
		{	while(true) 
			{
				//riga=br.readLine();
				if(riga.substring(0, Stringa.length()).equalsIgnoreCase(Stringa))
						{
							s[i]=riga;
							i++;
						}
				riga=br.readLine();
			}
		}
		catch(Exception e) 
		{
			
		}
		
		return s;
	}
	
	public String restituisciField(String Token,String separatore,int campo) throws IOException 
	{
		fr=new FileReader(file1);
		br= new BufferedReader(fr);
		String[] array=new String[10];
		String record = null;
		String[] recordDiviso;
		array=cercaRecord(Token);
		record=array[0];
		for(int i=1;i<10 && record!=null;i++) 
		{
			recordDiviso=record.split(separatore);
			if(recordDiviso[0].equalsIgnoreCase(Token)) 
			{
				return recordDiviso[campo];
			}
		}
		return null;
		
	}
	
	public void eliminaRecord(String Stringa) throws IOException
	{
		FileReader fr1=new FileReader(file1);
		BufferedReader br1=new BufferedReader(fr1);
		String record=br1.readLine();
		
		File append=new File("append.txt");
		append.createNewFile();
		FileWriter fw1=new FileWriter(append);
		BufferedWriter bw1=new BufferedWriter(fw1);
		
		try 
		{
			while(record!=null) 
			{
				bw1.write(record+"\n");
				record=br1.readLine();
			}
			
		}
		catch(Exception e) 
		{
		}
		
		bw1.close();
		br1.close();
		
		fr1=new FileReader(append);
		br1=new BufferedReader(fr1);
		
		fw1=new FileWriter(file1);
		bw1=new BufferedWriter(fw1);
		
		record=br1.readLine();
		try 
		{
			while(record!=null) 
			{
				if(record.substring(0, Stringa.length()).equalsIgnoreCase(Stringa)==false) 
				{
				bw1.write(record+"\n");
				}
				record=br1.readLine();
			}
		}
		catch(Exception e) 
		{
		}
		
		bw1.close();
		br1.close();
		append.delete();
	}
	
	public File copiaFile(File file) throws IOException 
	{
		
		FileReader fr1=new FileReader(file);
		BufferedReader br1=new BufferedReader(fr1);
		String record=br1.readLine();
		
		File append=new File("copia.txt");
		append.createNewFile();
		FileWriter fw1=new FileWriter(append);
		BufferedWriter bw1=new BufferedWriter(fw1);
		
		try 
		{
			while(record!=null) 
			{
				bw1.write(record+"\n");
				record=br1.readLine();
			}
			
		}
		catch(Exception e) 
		{
			
		}
		bw1.close();
		br1.close();
		return append;
		
	}
	
	
	public void cambiaField(String ID,String Token,int campo, String field) throws IOException 
	{
		String[] fileArray= new String[100];
		String riga,rigaModificata[];
		fr=new FileReader(file1);
		br= new BufferedReader(fr);
		fileArray[0]=br.readLine();
		int i=1,j=0;
		try 
		{	while(true) 
			{
				//riga=br.readLine();
				fileArray[i]=br.readLine();
				i++;
			}
		}
		catch(Exception e) 
		{
			
		}
		i=0;
		j=0;
		riga=fileArray[j];
		try 
		{	while(true) 
			{
			
				if(riga.substring(0, ID.length()).equalsIgnoreCase(ID))
						{
							rigaModificata=riga.split(Token);
							riga="";
							rigaModificata[campo]=field;
							for(i=0;i<rigaModificata.length;i++) 
							{
								riga=riga+rigaModificata[i]+Token;	
							}
							riga=riga.substring(0, riga.length()-1);
							fileArray[j]=riga;
								
						}
				j++;
				riga=fileArray[j];
			}
		}
		catch(Exception e) 
		{
			
		}
		
		sovrascriviFile(fileArray);
		br.close();
		
		
	}
	
	
	public Object leggiObj() throws IOException, ClassNotFoundException 
	{
		return or.readObject();
	}
	
	public void scriviObj(Object obj) throws IOException 
	{
		ow.writeObject(obj);
	}
	
	/*
	public void appendObj(Object obj)throws IOException
	{
		File append=new File("append.txt");
		append.createNewFile();
		FileOutputStream fos2;
		ObjectOutputStream ow2;
		
		File file1 = new File(FilePath);
		fis=new FileInputStream(file1);
		or=new ObjectInputStream(fis);
		
		fos2=new FileOutputStream(append);
		ow2=new ObjectOutputStream(fos2);
		
		
		try 
		{
			while(true) 
			{
				ow2.writeObject(or.readObject());
			}
			
		}
		catch(Exception e) 
		{
			
		}
		//ow2.writeObject(obj);
		ow2.close();
		or.close();
		FileInputStream fis2=new FileInputStream(append);
		ObjectInputStream or2=new ObjectInputStream(fis2);
		fos=new FileOutputStream(file1);
		ow=new ObjectOutputStream(fos);
		try 
		{
			while(true) 
			{
				ow.writeObject(or2.readObject());
			}
			
		}
		catch(Exception e) 
		{
			
		}
		ow.writeObject(obj);
		ow.close();
		or2.close();
		//append.delete();
		
		
	}*/
	
	public void sovrascriviFile(String[] array) throws IOException 
	{
		fw=new FileWriter(file1);
		bw= new BufferedWriter(fw);
		
		for(int i=0;i<array.length;i++) 
		{
			bw.write(array[i]+"\n");
		}
		bw.close();
		
	}
	
	public void Nuovo() throws IOException 
	{
		file1.delete();
		file1.createNewFile();
	}
	
	public void chiudi() throws IOException 
	{
		switch (Apertura) 
		{
			case "a":
				 bw.close();
				break;
				
			case "w":
				 bw.close();
				break;
			
			case "r":
				br.close();
				break;
				
			case "or":
				or.close();
				break;
			
			case "ow":
				ow.close();
				break;

				
				
		}
		
	}
	
	
	/*public static void prova() throws IOException 
	{
		rettangolo r=new rettangolo(50,45);
		file f=new file("file.bin","ow");
		f.apri();
		f.scriviObj(r);
		f.chiudi();
	}/*
	
	/*public static void prova2() throws IOException 
	{
		rettangolo r=new rettangolo(55,40);
		file f=new file("file.bin","");
		//f.apri();
		f.appendObj(r);
	}*/
	
	/*public static void prova1() throws IOException, ClassNotFoundException 
	{
		rettangolo r;
		file f=new file("file.bin","or");
		f.apri();
		r= (rettangolo) f.leggiObj();
		System.out.println(r.getAltezza());
		r= (rettangolo) f.leggiObj();
		System.out.println(r.getAltezza());
		
	}*/
	
	public static void prova3() throws IOException 
	{
		
		String[][] array={{"ciao","ciao1"},{"prova","prova1"}};
		//array= {{"ciao","ciao1"},{"prova","prova1"}};
		file f= new file("prova.txt");
		f.apri("w");
		f.scrivi("ciao");
		f.chiudi();
		f.apri("a");
		f.append("ciaooo");
		f.chiudi();
		
	}
	
	public static void prova4() throws IOException 
	{
		String[] array=new String[10];
		file f= new file("prova.txt");
		int i=0;
		f.apri("w");
		f.scrivi("ciao-1232");
		f.scrivi("ciao-12");
		f.chiudi();
		f.apri("r");
		array=f.cercaRecord("ciao");
		f.chiudi();
		
		while(array[i]!=null) {
			System.out.println(array[i]);
			i++;
		}
		
		
	}
	public static void prova5() throws IOException 
	{
		file f= new file("prova.txt");
		f.apri("w");
		f.scrivi("ciao-1234");
		f.scrivi("ca-1232");
		f.scrivi("ciao-12");
		//f.scrivi("");
		f.chiudi();
		f.eliminaRecord("ca");
		f.chiudi();
		
	}
	
	public static void prova6() throws IOException 
	{
		file f= new file("prova.txt");
		f.apri("w");
		f.scrivi("ciao/1234");
		f.scrivi("ca/1232");
		f.scrivi("ciao/12");
		//f.scrivi("");
		f.chiudi();
		f.apri("r");
		System.out.println(f.restituisciField("ciao","/",1));
		f.chiudi();
		
	}
	
	public static void prova7() throws IOException 
	{
		file f= new file("prova.txt");
		f.apri("w");
		f.scrivi("ciao/1234/prova");
		f.scrivi("ca/1232/prova");
		f.scrivi("ciao/12/prova");
		//f.scrivi("");
		f.chiudi();
		//f.apri("r");
		//System.out.println(f.restituisciField("ciao","/",1));
		//f.chiudi();
		f.cambiaField("ciao", "/", 2, "funziona");
		
	}
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		prova5();
		
	
	}
	
}
