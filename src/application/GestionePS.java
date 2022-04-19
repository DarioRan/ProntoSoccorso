package application;

import java.io.IOException;

public class GestionePS {

	file FilePS;
	NodoPrimario testa;
	
	public GestionePS() throws IOException 
	{
		FilePS=new file("FilePS.txt");
		testa=new NodoPrimario('R', new NodoPrimario('G',new NodoPrimario('B',null,null),null),null);
	}
	
	public boolean AggiungiPaziente(Paziente p) 
	{
		if(TrovaPaziente(p.getNome())==false)
		{
			switch(p.getPriorità()) 
			{
				case'R':
					if(testa.getTesta()==null) 
					{
						testa.setTesta(new NodoSecondario(p,null));
					}
					else 
					{
						testa.AggiungiCoda(new NodoSecondario(p,null));
					}
					break;
					
				case 'G':
					if(testa.getLink().getTesta()==null) 
					{
						testa.getLink().setTesta(new NodoSecondario(p,null));
					}
					else
					{
						testa.getLink().AggiungiCoda(new NodoSecondario(p,null));
					}
					break;
					
				case 'B':
					if(testa.getLink().getLink().getTesta()==null) 
					{
						testa.getLink().getLink().setTesta(new NodoSecondario(p,null));
					}
					else 
					{
						testa.getLink().getLink().AggiungiCoda(new NodoSecondario(p,null));
					}
					break;
			}
			return true;
		}
		else 
		{
			return false;
		}
		
	}
	
	
	public boolean EliminaPaziente(String N) 
	{
		boolean trovato=false;
		NodoPrimario n1temp=testa;
		NodoSecondario n2temp;
		Paziente temp;
		boolean temp1 = false,fine= false;
		
		
	
			while(n1temp!=null && trovato==false && fine==false) 
			{
				while(n1temp.getLink()!=null && n1temp.getTesta()==null) 
				{
					
					n1temp=n1temp.getLink();
					temp1=true;

				}
				if(n1temp.getLink()==null&&n1temp.getTesta()==null) 
				{
					fine=true;
				}
				if(fine==false) {
				//System.out.println(n1temp.info);
				n2temp=n1temp.getTesta();
				temp=(Paziente)n2temp.getInfo();
				if(temp.getNome().equalsIgnoreCase(N)) 
				{
					n1temp.setTesta(n2temp.getLink());
					trovato=true;
				}
				
				while(n2temp.getLink()!=null && trovato==false) 
				{
					temp=(Paziente)n2temp.getLink().getInfo();
					if(temp.getNome().equalsIgnoreCase(N)) 
					{
						n2temp.setLink(n2temp.getLink().getLink());
						trovato=true;
					}
					n2temp=n2temp.getLink();
				}
				n1temp=n1temp.getLink();
			}
				}
		
		if(trovato==true) 
		{
			return true;
		}
		return false;

	}
	
	
	/*public int[] NumeroPazienti() 
	{
		while()
	}*/
	
	public boolean TrovaPaziente(String N) 
	{
		boolean trovato=false;
		NodoPrimario n1temp=testa;
		NodoSecondario n2temp;
		Paziente temp;
		boolean temp1 = false,fine= false;
		
		
	
			while(n1temp!=null && trovato==false && fine==false) 
			{
				while(n1temp.getLink()!=null && n1temp.getTesta()==null) 
				{
					
					n1temp=n1temp.getLink();
					//temp1=true;

				}
				if(n1temp.getLink()==null&&n1temp.getTesta()==null) 
				{
					fine=true;
				}
				if(fine==false) {
				//System.out.println(n1temp.info);
				n2temp=n1temp.getTesta();
				temp=(Paziente)n2temp.getInfo();
				if(temp.getNome().equalsIgnoreCase(N)) 
				{
					trovato=true;
				}
				
				while(n2temp.getLink()!=null && trovato==false) 
				{
					temp=(Paziente)n2temp.getLink().getInfo();
					if(temp.getNome().equalsIgnoreCase(N)) 
					{
						trovato=true;
					}
					n2temp=n2temp.getLink();
				}
				n1temp=n1temp.getLink();
			}
				}
		
		if(trovato==true) 
		{
			return true;
		}
		return false;

	}
	
	public Paziente ProssimoPaziente() 
	{
		Paziente temp=null;
		if(testa.getTesta()!=null)
		{
			temp=(Paziente)testa.getTesta().getInfo();
			testa.EliminaTesta();
			
		}
		else 
		{
			temp=null;
			if(testa.getLink().getTesta()!=null) 
			{
				temp=(Paziente)testa.getLink().getTesta().getInfo();
				testa.getLink().EliminaTesta();
			}
			else 
			{
				if(testa.getLink().getLink().getTesta()!=null) {
				temp=(Paziente)testa.getLink().getLink().getTesta().getInfo();
				testa.getLink().getLink().EliminaTesta();
				}
			}
		}
		
		return temp;
	}
	
	public int[] NumeroPazienti() 
	{
		NodoPrimario n1temp=testa;
		NodoSecondario n2temp;
		int NPazienti[] = new int[3];
		NPazienti[0]=0;
		NPazienti[1]=0;
		NPazienti[2]=0;
		
		while(n1temp!=null) 
		{
			n2temp=n1temp.getTesta();
			while(n2temp!=null) 
			{
				switch(n1temp.getInfo()) 
				{
					case 'R':
						NPazienti[0]++;
						break;
					
					case 'G':
						NPazienti[1]++;
						break;
						
					case 'B':
						NPazienti[2]++;
						break;
				}
				n2temp=n2temp.getLink();
			}
			n1temp=n1temp.getLink();
		}
		
		return NPazienti;
	}
	
	public int NumeroPazientiAssistiti() throws IOException 
	{
		int NPazienti=0;
		String record;
		FilePS.apri("r");
		record=FilePS.leggi();
		while(record!=null) 
		{
			NPazienti++;
			record=FilePS.leggi();
		}
		FilePS.chiudi();
		return NPazienti;
		
	}
	
	public void RegistraPaziente(Paziente p) throws IOException 
	{
		
		FilePS.apri("a");
		FilePS.append(p.to_Record());
		FilePS.chiudi();
		
	}
	
	public int NPazientiMinorenni() throws IOException 
	{
		int NPazienti=0;
		String record;
		String[] recordSplitted;
		FilePS.apri("r");
		record=FilePS.leggi();
		while(record!=null) 
		{
			recordSplitted=record.split("/");
			if(Integer.valueOf(recordSplitted[recordSplitted.length-1])<18) 
			{
				NPazienti++;
			}
			record=FilePS.leggi();
		}
		FilePS.chiudi();
		return NPazienti;
}
	
	public int NPazientiTicket() throws IOException 
	{
		int NPazienti=0;
		String record;
		String[] recordSplitted;
		FilePS.apri("r");
		record=FilePS.leggi();
		while(record!=null) 
		{
			recordSplitted=record.split("/");
			if(recordSplitted[recordSplitted.length-2].equalsIgnoreCase("s")) 
			{
				NPazienti++;
			}
			record=FilePS.leggi();
		}
		FilePS.chiudi();
		return NPazienti;
	}
	
	public void ResetFile() throws IOException 
	{
		FilePS.Nuovo();
		
	}
	
public static void main(String[] args) throws IOException {
		


		
		
		
	}
}
