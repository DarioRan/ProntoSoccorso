package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class PSController implements Initializable{
	
	@FXML Label NPazientiLabel,DataOraLabel,MinorenniLabel,AssistitiLabel,TicketLabel;
	@FXML Pane PanePrincipale,PaneAggiungi,EliminaPane,InfoPane;
	@FXML ChoiceBox CBPriorita;
	@FXML TextField NomeText,SintomiText,EtaText,EliminaText;
	@FXML CheckBox TicketCheck;
	@FXML Label RossiLabel,GialliLabel,BianchiLabel;
	
	//MedicoController m1c;
	//GestionePS GPS;
	ThreadPS TPS;
	
	ObservableList<Character> Lista= FXCollections.observableArrayList('R','G','B');
	
	public void PassaTPS(ThreadPS tps ) 
	{
		//this.GPS=gps;
		TPS=tps;
	}
	
	public void InfoBt() throws IOException 
	{
		PanePrincipale.setVisible(false);
		InfoPane.setVisible(true);
		MinorenniLabel.setText(String.valueOf(TPS.NPazientiMinorenni()));
		AssistitiLabel.setText(String.valueOf(TPS.NAssistiti()));
		TicketLabel.setText(String.valueOf(TPS.NTicket()));
	}
	
	public void Indietro1Bt() 
	{
		InfoPane.setVisible(false);
		PanePrincipale.setVisible(true);
	}
	
	public void AggiungiPazientePane() 
	{
		PanePrincipale.setVisible(false);
		PaneAggiungi.setVisible(true);
	}
	
	public void AggiungiPazienteBt() throws IOException, InterruptedException 
	{
		boolean esito;
		char ticket='n';
		if(TicketCheck.isSelected()==true) 
		{
			ticket='s';
		}
		Paziente pz=new Paziente(NomeText.getText(),(char)CBPriorita.getValue(),SintomiText.getText(),null,ticket,Integer.valueOf(EtaText.getText()));
		esito=TPS.AggiungiPaziente(pz);
		if(esito) {
		PanePrincipale.setVisible(true);
		PaneAggiungi.setVisible(false);
		}
		else 
		{
			System.out.println("Già presente");
		}
		
	}
	
	public void EliminaPane() 
	{
		PanePrincipale.setVisible(false);
		EliminaPane.setVisible(true);
	}
	
	public void EliminaBt() 
	{
		boolean esito;
		esito=TPS.EliminaPaziente(EliminaText.getText());
		if(esito) 
		{
			System.out.println("Eliminato");
		}
		else 
		{
			System.out.println("Non Trovato");		
		}
		PanePrincipale.setVisible(true);
		EliminaPane.setVisible(false);
	}
	
	public void AggiornaNPazienti(int n[]) 
	{
		NPazientiLabel.setText(String.valueOf(n[0]+n[1]+n[2]));
		RossiLabel.setText(String.valueOf(n[0]));
		GialliLabel.setText(String.valueOf(n[1]));
		BianchiLabel.setText(String.valueOf(n[2]));
	}
	
	
	public void Reset() throws IOException 
	{
		TPS.ResetFile();
	}
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CBPriorita.setItems(Lista);
	}
	
	
	
}
