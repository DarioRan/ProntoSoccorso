package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MedicoController {
	
	ThreadPS TPS;
	Paziente temp;
	@FXML Label DataOraLabel;
	@FXML Pane PanePrincipale,PaneSecondario;
	@FXML Label NomeLabel,SintomiLabel,EtaLabel,PrioritaLabel;
	@FXML TextArea PrestazioneText;
	@FXML Button ProssimoBt;
	int occupato=0;
	
	public void PassaTPS(ThreadPS tps) throws IOException 
	{
		this.TPS=tps;
	}
	
	public void Assisti() throws InterruptedException 
	{
		occupato=1;
		PanePrincipale.setVisible(false);
		PaneSecondario.setVisible(true);
		NomeLabel.setText(temp.getNome());
		SintomiLabel.setText(temp.getSintomi());
		EtaLabel.setText(String.valueOf(temp.getEtà()));
		switch(temp.getPriorità()) 
		{
			case'R':
				PrioritaLabel.setText("CODCIE ROSSO");
				PrioritaLabel.setStyle("-fx-background-color:red");
				break;
			
			case'G':
				PrioritaLabel.setText("CODCIE GIALLO");
				PrioritaLabel.setStyle("-fx-background-color:yellow");
				break;
			
			case'B':
				PrioritaLabel.setText("CODCIE BIANCO");
				PrioritaLabel.setStyle("-fx-background-color:white");
				break;
				
				
		}
		PrestazioneText.setText("");

		//MODIFICA LABEL//
	}
	

	public void Registra() throws IOException, InterruptedException 
	{
		
		temp.setPrestazione(PrestazioneText.getText());
		TPS.RegistraPaziente(temp);
		occupato=0;
		TPS.Libera();
		PaneSecondario.setVisible(false);
		PanePrincipale.setVisible(true);
		
	}
	
		
	
	
	
}
