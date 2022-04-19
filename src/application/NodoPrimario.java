package application;

public class NodoPrimario {
	
	char info;
	NodoPrimario link;
	NodoSecondario testa;
	
	public NodoPrimario() 
	{
		info='n';
		link=null;
		testa=null;
	}
	
	public NodoPrimario(char i,NodoPrimario l1,NodoSecondario t) 
	{
		info=i;
		link=l1;
		testa=t;
	}
	
	
	
	public void setInfo(char p){
		info=p;
	}
	public void setLink(NodoPrimario L) {
		link=L;
	}
	public void setTesta(NodoSecondario t) 
	{
		testa=t;
	}
	
	public char getInfo() 
	{
		return info;
	}
	
	public NodoSecondario getTesta() 
	{
		return testa;
	}
	
	public NodoPrimario getLink() 
	{
		return link;
	}
	
	public void AggiungiCoda(NodoSecondario N)
	{
		NodoSecondario testaTemp;
		if(testa!=null) 
		{
			testaTemp=testa;
			while(testaTemp.getLink()!=null) 
			{
				testaTemp=testaTemp.getLink();
			}
			
			testaTemp.setLink(N);
		}
		else 
		{
			testa=N;
		}
	}
	
	public void EliminaTesta() 
	{
		if(testa!=null) 
		{
		testa=testa.getLink();
		}
	}
	
	
public static void main(String[] args) {
		
		//NodoPrimario n=new NodoPrimario('R',null,new NodoSecondario(new Paziente("Ranieri",'G',"Mal Di testa","bho",18),null));
		NodoPrimario n=new NodoPrimario('R',null,null);
		n.AggiungiCoda(new NodoSecondario(new Paziente("Ranieri",'G',"Mal Di testa","bho",'s',18),null));
		System.out.println(((Paziente)n.getTesta().getInfo()).getPrestazione());
	}
	
	

}
