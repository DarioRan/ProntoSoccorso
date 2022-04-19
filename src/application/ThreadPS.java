package application;

import java.io.IOException;

import javafx.application.Platform;

public class ThreadPS extends Thread {
	GestionePS PS;
	ThreadNPazienti t1;
	MedicoController m1c,m2c;
	PSController psc;
	//int T1wait=0,T2wait=0;
	Paziente p;
	
	ThreadMedico TM1;
	ThreadMedico TM2;
	
	
	
	
	
	public ThreadPS (MedicoController m1c,MedicoController m2c , PSController psc ) 
	{
		try {
//L'istanza del GestionePS è "centralizzata" perciò ogni controller farà riferimento alla stessa istanza GestionePS
			PS=new GestionePS();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.m1c=m1c;
		this.m2c=m2c;
		this.psc=psc;
		this.PS=PS;

// Ad ogni controller viene passato il Thread Padre che gestisce in modo "synchronized" i metodi del GestionePS
//Ogni controller utilizzerà i metodi del Thread Padre (this) per lavorare sul GestionePS
		try {
			m1c.PassaTPS(this);
			m2c.PassaTPS(this);
			psc.PassaTPS(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void run() 
	{
		setName("ThreadPS");
		t1=new ThreadNPazienti(PS,m1c,m2c,psc);
		t1.start();
		
		//TM1=new ThreadMedico(m1c,this);
		//TM2=new ThreadMedico(m2c,this);
	
		//TM1.start();
		//TM2.start();
		
		//Paziente prossimo;
		p=PS.ProssimoPaziente();
		
		while(true) 
		{
			 try {
				sleep(100);
				ProssimoPaziente();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	public synchronized void ProssimoPaziente() throws InterruptedException 
	{
		
		while(m1c.occupato==1 && m2c.occupato==1) 
		{
			System.out.println("In attesa");
			wait();
			
			
			//System.out.println(p.getNome()+"1");
		}
		while(p==null) 
		{
			//System.out.println("Pazinete nullo");
			if(PS.NumeroPazienti()[0]+PS.NumeroPazienti()[1]+PS.NumeroPazienti()[2]==0 ) {
				//System.out.println("Attesa di inserire paziente");
			wait();
			
			}
			System.out.println("Prox Pazinete nullo");
			p=PS.ProssimoPaziente();
			//System.out.println(p.getNome()+"2");
		}
		
		
		//System.out.println(p.getNome());
		if(m1c.occupato==0 && p!=null) 
		{
			m1c.temp=p;
			Platform.runLater(new Runnable() 
    		{
		         public void run() 
		        {	try {
					m1c.Assisti();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					m1c.occupato=1;
		        }});
			System.out.println("Prossimo paziente 1");
			p=null;
		}
		if(m2c.occupato==0 && p!=null) 
		{
			m2c.temp=p;
			Platform.runLater(new Runnable() 
    		{
				
		         public void run() 
		        {	try {
					m2c.Assisti();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					m2c.occupato=1;
		        }});
			System.out.println("Prossimo paziente 2");

			p=null;
		}
		
	}
	
	
	public synchronized boolean AggiungiPaziente(Paziente p) throws IOException, InterruptedException 
	{
		boolean esito;
		esito=PS.AggiungiPaziente(p);
		notifyAll();
		return esito;
		
	}
	
	public synchronized void RegistraPaziente(Paziente p) throws IOException, InterruptedException 
	{
		
		PS.RegistraPaziente(p);
	}
	
	public synchronized void Libera() 
	{
		notify();
	}
	
	public synchronized boolean EliminaPaziente(String n) 
	{
		return PS.EliminaPaziente(n);
	}
	
	public synchronized int [] NumeroPazienti() 
	{
		return PS.NumeroPazienti();
	}
	
	public synchronized int NPazientiMinorenni() throws IOException 
	{
		return PS.NPazientiMinorenni();
	}
	
	public synchronized int NAssistiti() throws IOException 
	{
		return PS.NumeroPazientiAssistiti();
	}
	
	public synchronized int NTicket() throws IOException 
	{
		return PS.NPazientiTicket();
	}
	
	public synchronized void ResetFile() throws IOException 
	{
		PS.ResetFile();
	}
	
}
