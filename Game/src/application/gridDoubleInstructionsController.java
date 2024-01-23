package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class gridDoubleInstructionsController implements Initializable{
	public int chooser,spinner,winner=-1;
	public static String players[]= {doublePlayerInfoController.p0,doublePlayerInfoController.p1};
	public Timeline timeline;
	public String completeText;
	public static Media sound;
	public static MediaPlayer mediaPlayer;
	private int k=-1;
	public static int jitse=-1;
	private boolean t = false;
	int result,res;
	@FXML
	private Text text;
	@FXML
	private Label battle;
	@FXML
	private Button exittowindows,skip;
	@FXML
	private Button play;
	@FXML
	private Button back;
	@FXML
	private Label chooseHT;
	@FXML
	private Label tossWon;
    @FXML
    private ImageView coin;
    @FXML
    private Button spin;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label ht;
    @FXML
    private RadioButton heads;
    @FXML
    private RadioButton tails;
    @FXML
    private RadioButton first;
    @FXML
    private RadioButton second;
    @FXML
    private void Play(ActionEvent event) {
    	sound = new Media(getClass().getResource("button.mp3").toString());
        mediaPlayer = new MediaPlayer(sound);
        if(Main.s==1)mediaPlayer.play();
		try {
			if(winner==-1) {
				Alert alert = new Alert(AlertType.ERROR, "Please spin the coin...", ButtonType.OK);
			    alert.showAndWait();
	            return;
			}
			else if(first.isSelected()) {
				jitse=winner;
				Parent root = FXMLLoader.load(getClass().getResource("gridDoubleGamePlay.fxml"));
				Stage primaryStage = (Stage)exittowindows.getScene().getWindow();
				Scene scene = new Scene(root);
				
				primaryStage.setScene(scene);
				
			}
			else if(second.isSelected()) {
				jitse=1-winner;
				Parent root = FXMLLoader.load(getClass().getResource("gridDoubleGamePlay.fxml"));
				Stage primaryStage = (Stage)exittowindows.getScene().getWindow();
				Scene scene = new Scene(root);
				
				primaryStage.setScene(scene);
				
			}
			else{
				Alert alert = new Alert(AlertType.ERROR, "Please select whether you want to move first or second.", ButtonType.OK);
	           
	            alert.showAndWait();
	            return;

			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
    @FXML
    private void ExitToWindows(ActionEvent event) {
    	sound = new Media(getClass().getResource("button.mp3").toString());
        mediaPlayer = new MediaPlayer(sound);
        
        if(Main.s==1)mediaPlayer.play();
		try {
			Alert alert=new Alert(AlertType.CONFIRMATION);
			
			alert.setTitle("EXIT");
			alert.setHeaderText("Are you sure you want to exit?");
			alert.setContentText("Click OK to exit or Cancel to return.");
			
			if(alert.showAndWait().get()==ButtonType.OK) {
			Stage primaryStage = (Stage)back.getScene().getWindow();
			primaryStage.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
    @FXML
	private void Back(ActionEvent event) {
    	sound = new Media(getClass().getResource("button.mp3").toString());
        mediaPlayer = new MediaPlayer(sound);
        
        if(Main.s==1)mediaPlayer.play();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("doublePlayerInfo.fxml"));
			Stage primaryStage = (Stage)back.getScene().getWindow();
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    @FXML
    private void Skip(ActionEvent event) {
    	timeline.stop();
        text.setText(completeText);
        if(Main.s==1)mediaPlayer.stop();
        if(Main.s==1)Main.mediaPlayer.play();
		tossWon.setVisible(true);
		battle.setVisible(true);
		play.setVisible(true);
		back.setVisible(true);
		exittowindows.setVisible(true);
		chooseHT.setVisible(true);
		coin.setVisible(true);
		spin.setVisible(true);
		ht.setVisible(true);
		heads.setVisible(true);
		tails.setVisible(true);
		skip.setVisible(false);
		Random random=new Random();
		int rand=random.nextInt(2);
		if(rand==0) {
			chooser=0;spinner=1;
		}
		else {
			chooser=1;spinner=0;
		}
		battle.setText(players[chooser]+" will make call and "+players[spinner]+" will spin the coin...");
    }
    @FXML
    private void Spin(ActionEvent event) {
    	sound = new Media(getClass().getResource("button.mp3").toString());
        mediaPlayer = new MediaPlayer(sound);
        
        if(Main.s==1)mediaPlayer.play();
		try {
			if (t) {
	            Alert alert = new Alert(AlertType.ERROR, "Spin can only be called once!", ButtonType.OK);
	            
	            alert.showAndWait();
	            return;
	        }
			if(heads.isSelected())k=0;
			else if(tails.isSelected())k=1;
			else 
			{
				Alert alert = new Alert(AlertType.ERROR, "Please select your call.", ButtonType.OK);
				
	            alert.showAndWait();
	            return;
			}
			t=true;
			RotateTransition rotateTransition = new RotateTransition();
			rotateTransition.setNode(coin);
			rotateTransition.setDuration(Duration.millis(10));
	        rotateTransition.setByAngle(360);
	        rotateTransition.setCycleCount(100);
	        rotateTransition.setInterpolator(Interpolator.LINEAR);
	        rotateTransition.setAxis(Rotate.X_AXIS);
	        rotateTransition.setOnFinished(e -> {
	        	coin.setRotate(0);
	        	Random random = new Random();
	    	    result = random.nextInt(2);
	    	    if(result==1)ht.setText("TAILS");
	    	    else ht.setText("HEADS");
	    	    ht.setBackground(null);
	    	    ht.setVisible(true);
	    	    if(result==k) {
	    	    	tossWon.setText(players[chooser]+" won the toss and will move ");
	    	    	first.setVisible(true);
	    	    	second.setVisible(true);
	    	    	winner=chooser;
	    	    }
	    	    else {
	    	    	
	    	    	tossWon.setText(players[spinner]+" won the toss and will move ");
	    	    	first.setVisible(true);
	    	    	second.setVisible(true);
	    	    	winner=spinner;
	    	    }
	    	    battle.setText("Choose your move and click Play to enter the BATTLEGRID!");
	        });
	        rotateTransition.play();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
    
   
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		players[0]= doublePlayerInfoController.p0;
		players[1]=doublePlayerInfoController.p1;
		if(Main.s==1)Main.mediaPlayer.stop();
		sound = new Media(getClass().getResource("type.mp3").toString());
        mediaPlayer = new MediaPlayer(sound);
       
        if(Main.s==1)mediaPlayer.play();
		tossWon.setText("");
		battle.setText("Spin the coin...");
		first.setVisible(false);
		second.setVisible(false);
		tossWon.setVisible(false);
		battle.setVisible(false);
		play.setVisible(false);
		back.setVisible(false);
		exittowindows.setVisible(false);
		chooseHT.setVisible(false);
		coin.setVisible(false);
		spin.setVisible(false);
		ht.setVisible(false);
		heads.setVisible(false);
		tails.setVisible(false);
		FadeTransition fade = new FadeTransition(Duration.seconds(1), battle);
    	fade.setFromValue(1.0);
    	fade.setToValue(0.5);
    	fade.setAutoReverse(true);
    	fade.setCycleCount(Timeline.INDEFINITE);
    	fade.play();
    	completeText = "You are given a 5 X 5 grid.A ball is placed at the bottom left cell initially.In each turn a player can move the ball any number of cell rightwards or upwards.Then other player moves according to the rule.The one who can not move anymore loses.The gameplay starts with a coin toss.The winner of toss chooses whether to move first or second.HAVE FUN!";
    	StringBuilder displayedText = new StringBuilder();
    	timeline = new Timeline();
    	
    	for (int i = 0; i < completeText.length(); i++) {
    	    KeyFrame keyFrame = new KeyFrame(Duration.millis(50 * i), event -> {
    	        displayedText.append(completeText.charAt(displayedText.length()));
    	        text.setText(displayedText.toString());
    	    });
    	    timeline.getKeyFrames().add(keyFrame);
    	}

    	timeline.setOnFinished(event -> {
    		if(Main.s==1)mediaPlayer.stop();
    		if(Main.s==1)Main.mediaPlayer.play();
    		tossWon.setVisible(true);
    		battle.setVisible(true);
    		play.setVisible(true);
    		back.setVisible(true);
    		exittowindows.setVisible(true);
    		chooseHT.setVisible(true);
    		coin.setVisible(true);
    		spin.setVisible(true);
    		ht.setVisible(true);
    		heads.setVisible(true);
    		tails.setVisible(true);
    		skip.setVisible(false);
    		Random random=new Random();
    		int rand=random.nextInt(2);
    		if(rand==0) {
    			chooser=0;spinner=1;
    		}
    		else {
    			chooser=1;spinner=0;
    		}
    		battle.setText(players[chooser]+" will make call and "+players[spinner]+" will spin the coin...");
    	});

    	timeline.play();

	}
}

    
