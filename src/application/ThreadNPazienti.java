package application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.text.DateFormatter;

import javafx.application.Platform;

public class ThreadNPazienti extends Thread {
	
	GestionePS PS;
	MedicoController m1c,m2c;
	PSController psc;
	
	public ThreadNPazienti(GestionePS PS,MedicoController m1c,MedicoController m2c, PSController psc) 
	{
		this.PS=PS;
		this.m2c=m2c;
		this.m1c=m1c;
		this.psc=psc;
	}
	
	
	
	@Override public void run() 
	{

	    	while(true) {
	    		try {
					sleep(500);
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		
	            
	            Platform.runLater(new Runnable() 
	            		{
	                 public void run() 
	                {
	                	LocalDateTime ora = LocalDateTime.now(); 
	                	String europeanDatePattern = "dd/MM/yyyy HH:mm";
	                	DateTimeFormatter Formatter = DateTimeFormatter.ofPattern(europeanDatePattern);
	
	                	psc.AggiornaNPazienti(PS.NumeroPazienti());
	                	
	                	m1c.DataOraLabel.setText(ora.format(Formatter));
	                	m2c.DataOraLabel.setText(ora.format(Formatter));
	                	
	                }
	            		});
	            
	    }
	}
	            
}
