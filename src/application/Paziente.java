package application;

public class Paziente {
	
	String Nome;
	char Priorita;
	String Sintomi,Prestazione;
	char Ticket;
	int Eta;
	
	public Paziente(String n,char p,String s, String prest, char t, int eta) 
	{
		setNome(n);
		setPriorit�(p);
		setSintomi(s);
		setPrestazione(prest);
		setEt�(eta);
		Ticket=t;
		
	}

	/*public Paziente(String record) 
	{
		String[] SplittedRecord;
		SplittedRecord
	}*/
	
	public void setNome(String N) {
		Nome=N;
	}
	public void setPriorit�(char P) {
		Priorita=P;
	}
	public void setSintomi(String S) {
		Sintomi=S;
	}
	public void setPrestazione(String P) {
		Prestazione=P;
	}
	public void setEt�(int E) {
		Eta=E;
	}

	public String getNome() {
		return Nome;
	}
	public char getPriorit�() {
		return Priorita;
	}
	public String getSintomi() {
		return Sintomi;
	}
	public String getPrestazione() {
		return Prestazione;
	}
	public int getEt�() {
		return Eta;
	}
	public char getTicket() 
	{
		return Ticket;
	}
	

	public String to_Record() 
	{
		String record;
		record=getNome()+"/"+getPriorit�()+"/"+getSintomi()+"/"+getPrestazione()+"/"+getTicket()+"/"+String.valueOf(getEt�());
		return record;
		
	}
}
