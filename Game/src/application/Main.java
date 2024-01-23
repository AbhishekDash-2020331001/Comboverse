package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main extends Application {
	public static Media sound;
	public static MediaPlayer mediaPlayer;
	public static int s=1;
	@Override
	public void start(Stage primaryStage) {
		try {
			
		    sound = new Media(getClass().getResource("bgm.mp3").toString());
	        mediaPlayer = new MediaPlayer(sound);
	        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	        if(Main.s==1)mediaPlayer.play();
	        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/log.jpg")));
			Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
