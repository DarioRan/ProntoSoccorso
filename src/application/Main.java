package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlloaderPS=new FXMLLoader(getClass().getResource("PSFXML.fxml"));
			Pane root = (Pane)fxmlloaderPS.load();
			Scene scene = new Scene(root,730,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			PSController psc=fxmlloaderPS.getController();
			
			
			/////////////MEDICO1////////////////////////
			
			Stage Stage1=new Stage();
			FXMLLoader fxmlloaderM1C= new FXMLLoader(getClass().getResource("Medico1FXML.fxml"));
			Pane root1 = (Pane)fxmlloaderM1C.load();
			Scene scene1 = new Scene(root1,730,580);
			scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage1.setScene(scene1);
			Stage1.show();
			
			/////////////MEDICO2////////////////////////
			
			Stage Stage2=new Stage();
			FXMLLoader fxmlloaderM2C= new FXMLLoader(getClass().getResource("Medico2FXML.fxml"));
			Pane root2 = (Pane)fxmlloaderM2C.load();
			Scene scene2 = new Scene(root2,730,580);
			scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage2.setScene(scene2);
			Stage2.show();
			
			////////////////////////////////////////////////
			
			
			
			ThreadPS MainT=new ThreadPS(fxmlloaderM1C.getController(),fxmlloaderM2C.getController(),psc);
			MainT.start();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
		

	}
}
