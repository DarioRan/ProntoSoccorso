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
		setPriorità(p);
		setSintomi(s);
		setPrestazione(prest);
		setEtà(eta);
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
	public void setPriorità(char P) {
		Priorita=P;
	}
	public void setSintomi(String S) {
		Sintomi=S;
	}
	public void setPrestazione(String P) {
		Prestazione=P;
	}
	public void setEtà(int E) {
		Eta=E;
	}

	public String getNome() {
		return Nome;
	}
	public char getPriorità() {
		return Priorita;
	}
	public String getSintomi() {
		return Sintomi;
	}
	public String getPrestazione() {
		return Prestazione;
	}
	public int getEtà() {
		return Eta;
	}
	public char getTicket() 
	{
		return Ticket;
	}
	

	public String to_Record() 
	{
		String record;
		record=getNome()+"/"+getPriorità()+"/"+getSintomi()+"/"+getPrestazione()+"/"+getTicket()+"/"+String.valueOf(getEtà());
		return record;
		
	}
}
