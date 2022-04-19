package application;

public class NodoSecondario {
	
	Object info;
	NodoSecondario link;

	
	public NodoSecondario(Object p,NodoSecondario n ) 
	{
		setInfo(p);
		setLink(n);
	}
	
	public void setInfo(Object p){
		info=p;
	}
	public void setLink(NodoSecondario L) {
		link=L;
	}
	
	public Object getInfo() 
	{
		return info;
	}
	public NodoSecondario getLink() 
	{
		return link;
	}
}
