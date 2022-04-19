package application;

import javafx.application.Platform;

public class ThreadMedico extends Thread {
	
	ThreadPS TPS;
	MedicoController mc;
	Paziente temp;
	int waiting=0;
	
	public ThreadMedico(MedicoController mc, ThreadPS TPS) 
	{
		this.mc=mc;
		this.TPS=TPS;
	}
	
/*	public synchronized void run() 
	{
		try {
			sleep(1000);
	    	System.out.println("Attendo Paziente");
			temp=TPS.ProssimoPaziente();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true && mc.occupato!=1) 
		{
			
			waiting=0;
			
			mc.AggiornaNPazienti(TPS.NumeroPazienti()[0]+TPS.NumeroPazienti()[1]+TPS.NumeroPazienti()[2]);
			
			mc.temp=temp;
			Platform.runLater(new Runnable() 
			{
				
			     public void run() 
			    {	try {
			    	System.out.println("Servendo");
					mc.Assisti();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    }});
			System.out.println("Attesa");
			
			try {
				sleep(1000);
		    	System.out.println("Attendo Paziente");
				temp=TPS.ProssimoPaziente();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}*/

}
