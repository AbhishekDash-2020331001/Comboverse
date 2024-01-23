package application;

import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;


public class gridDoubleGamePlayController implements Initializable{
	public int now=0;
	public static int winner=-1;
	public static String players[]=gridDoubleInstructionsController.players;
	public static Media sound;
	public static MediaPlayer mediaPlayer;
	public int jitse,row,col;
	@FXML
	private Button undo;
	@FXML
	private ImageView im;
	@FXML
	private Button fu;
	@FXML
	private Label move;
	@FXML
	private Button back;
	@FXML
	private Button next;
	@FXML
	private Button exit;
	public Integer rowIndex;
	public Integer colIndex;
	@FXML
    private Button b1;
    @FXML
    private Button b2;@FXML
    private Button b3;@FXML
    private Button b4;@FXML
    private Button b5;@FXML
    private Button b6;@FXML
    private Button b8;@FXML
    private Button b9;@FXML
    private Button b7;@FXML
    private Button b10;@FXML
    private Button b11;@FXML
    private Button b12;@FXML
    private Button b13;@FXML
    private Button b14;@FXML
    private Button b15;@FXML
    private Button b16;@FXML
    private Button b17;@FXML
    private Button b18;@FXML
    private Button b19;@FXML
    private Button b20;@FXML
    private Button b21;@FXML
    private Button b22;@FXML
    private Button b23;@FXML
    private Button b24;@FXML
    private Button b25;
    @FXML
    private Label cd;
    public Duration duration;
    public Timeline timeline;
    public KeyFrame keyFrame;
    @FXML
    private GridPane grid;
    @FXML
    private Circle ball;
    @FXML
    private Label battle;
    int tim=4;
    int rem=0;
    public void updateLabel() {
    	cd.setText(""+rem+"");
    }
    @FXML
    private void Undo(){
    	sound = new Media(getClass().getResource("button.mp3").toString());
        mediaPlayer = new MediaPlayer(sound);
        if(Main.s==1) mediaPlayer.play();
    	grid.getChildren().remove(ball); 
        GridPane.setConstraints(ball, col,row); 
        grid.getChildren().add(ball);
        timeline.stop();
        im.setVisible(false);
		cd.setVisible(false);
		undo.setVisible(false);
		jitse=1-jitse;
		if(winner==-1)move.setText(players[jitse]+"'s Turn...");
		rem=0;
    }
    @FXML
	private void Back(ActionEvent event) {
    	sound = new Media(getClass().getResource("button.mp3").toString());
        mediaPlayer = new MediaPlayer(sound);
        if(Main.s==1)mediaPlayer.play();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
			Stage primaryStage = (Stage)next.getScene().getWindow();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    @FXML
	private void exitToWindows(ActionEvent event) {
    	sound = new Media(getClass().getResource("button.mp3").toString());
        mediaPlayer = new MediaPlayer(sound);
        if(Main.s==1)mediaPlayer.play();
		try {
			Alert alert=new Alert(AlertType.CONFIRMATION);
			
			alert.setTitle("EXIT");
			alert.setHeaderText("Are you sure you want to exit?");
			alert.setContentText("Click OK to exit or Cancel to return.");
			if(alert.showAndWait().get()==ButtonType.OK) {
			Stage primaryStage = (Stage)next.getScene().getWindow();
			primaryStage.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
    @FXML
	private void Next(ActionEvent event) {
    	sound = new Media(getClass().getResource("button.mp3").toString());
        mediaPlayer = new MediaPlayer(sound);
        if(Main.s==1)mediaPlayer.play();
        if(winner==-1) {
    		Alert alert = new Alert(AlertType.ERROR, "Game is not finished yet!", ButtonType.OK);
            alert.showAndWait();
            return;
    	}
		try {
			Parent root = FXMLLoader.load(getClass().getResource("lastdouble.fxml"));
			Stage primaryStage = (Stage)next.getScene().getWindow();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    @FXML
    private void handleButtonClick(ActionEvent event) {
    	if(rem<=0) {
    	sound = new Media(getClass().getResource("button.mp3").toString());
        mediaPlayer = new MediaPlayer(sound);
        if(Main.s==1)mediaPlayer.play();
        if (event.getSource() instanceof Button) {
            Button clickedButton = (Button) event.getSource();
            rowIndex = GridPane.getRowIndex(clickedButton);
            colIndex = GridPane.getColumnIndex(clickedButton);
            Integer collIndex = GridPane.getColumnIndex(ball);
        	Integer rowwIndex = GridPane.getRowIndex(ball);
        	if(rowwIndex==null)row=0;
        	else row=rowwIndex.intValue();
        	if(collIndex==null)col=0;
        	else col=collIndex.intValue();
            if(rowIndex==null && colIndex==null) {
            	if(row!=0 && col!=0 || (row==0 && col==0)) {
            		Alert alert = new Alert(AlertType.ERROR, "Illegal Move!", ButtonType.OK);
                    alert.showAndWait();
                    return;
            	}
            	grid.getChildren().remove(ball);
                GridPane.setConstraints(ball, 0, 0);
                grid.getChildren().add(ball);
                im.setVisible(true);
        		cd.setVisible(true);
        		undo.setVisible(true);
                rem=tim;
                System.out.println("Age jitse "+jitse);
                jitse=1-jitse;
                System.out.println("pore jitse "+(1-jitse));
                if(winner==-1)move.setText(players[jitse]+" will get to move shortly...");
                updateLabel();
                duration=Duration.seconds(1);
                keyFrame=new KeyFrame(duration,eventt ->{
                	rem--;
                	updateLabel();
                	if(rem<=0) {
                		if(winner==-1)move.setText(players[jitse]+"'s turn!");
                        undo.setVisible(false);
                        im.setVisible(false);
                		cd.setVisible(false);
                	}
                });
                timeline=new Timeline(keyFrame);
                timeline.setCycleCount(tim);
                timeline.play();
            }
            else if(colIndex==null) {
            	if((row!=rowIndex.intValue() && col!=0) || (row==rowIndex.intValue() && col==0)|| row<rowIndex.intValue()||col>0) {
            		Alert alert = new Alert(AlertType.ERROR, "Illegal Move!", ButtonType.OK);
                    alert.showAndWait();
                    return;
            	}
            	grid.getChildren().remove(ball); 
                GridPane.setConstraints(ball, 0, rowIndex.intValue()); 
                grid.getChildren().add(ball);
                im.setVisible(true);
        		cd.setVisible(true);
        		undo.setVisible(true);
                rem=tim;
                System.out.println("Age jitse "+jitse);
                jitse=1-jitse;
                System.out.println("pore jitse "+(1-jitse));
                move.setText(players[jitse]+" will get to move shortly...");
                updateLabel();
                duration=Duration.seconds(1);
                keyFrame=new KeyFrame(duration,eventt ->{
                	rem--;
                	updateLabel();
                	if(rem<=0) {
                		undo.setVisible(false);
                		if(winner==-1)move.setText(players[jitse]+"'s turn!");
                		im.setVisible(false);
                		cd.setVisible(false);
                	}
                });
                timeline=new Timeline(keyFrame);
                timeline.setCycleCount(tim);
                timeline.play();

                
            }
            else if(rowIndex==null) {
            	if((row!=0 && colIndex.intValue()!=col) ||(row==0 && colIndex.intValue()==col)||row<0 || col>colIndex.intValue()) {
            		Alert alert = new Alert(AlertType.ERROR, "Illegal Move!", ButtonType.OK);
                    alert.showAndWait();
                    return;
            	}
            	grid.getChildren().remove(ball); 
                GridPane.setConstraints(ball, colIndex.intValue(), 0); 
                grid.getChildren().add(ball);
                if(colIndex.intValue()==4) {
                	move.setText(players[jitse]+" Won!");
                	winner=jitse;
                	sound = new Media(getClass().getResource("finish.mp3").toString());
                    mediaPlayer = new MediaPlayer(sound);
                    if(Main.s==1)mediaPlayer.play();
                	return;
                }
                im.setVisible(true);
        		cd.setVisible(true);
        		undo.setVisible(true);
                rem=tim;
                System.out.println("Age jitse "+jitse);
                jitse=1-jitse;
                System.out.println("pore jitse "+(1-jitse));
                if(winner==-1)move.setText(players[jitse]+" will get to move shortly...");
                updateLabel();
                duration=Duration.seconds(1);
                keyFrame=new KeyFrame(duration,eventt ->{
                	rem--;
                	updateLabel();
                	if(rem<=0) {
                		if(winner==-1)move.setText(players[jitse]+"'s turn!");
                		undo.setVisible(false);
                		im.setVisible(false);
                		cd.setVisible(false);
                	}
                });
                timeline=new Timeline(keyFrame);
                timeline.setCycleCount(tim);
                timeline.play();

            }
            
            else {
            	if((row!=rowIndex.intValue() && colIndex.intValue()!=col) ||(row==rowIndex.intValue() && colIndex.intValue()==col)||row<rowIndex.intValue()||col>colIndex.intValue()) {
            		Alert alert = new Alert(AlertType.ERROR, "Illegal Move!", ButtonType.OK);
                    alert.showAndWait();
                    return;
            	}
            	grid.getChildren().remove(ball); 
                GridPane.setConstraints(ball, colIndex.intValue(), rowIndex.intValue());
                grid.getChildren().add(ball); 
                im.setVisible(true);
        		cd.setVisible(true);
        		undo.setVisible(true);
                rem=tim;
                jitse=1-jitse;
                System.out.println("pore jitse "+(1-jitse));
                if(winner==-1)move.setText(players[jitse]+" will get to move shortly...");
                updateLabel();
                duration=Duration.seconds(1);
                keyFrame=new KeyFrame(duration,eventt ->{
                	rem--;
                	updateLabel();
                	if(rem<=0) {
                		if(winner==-1)move.setText(players[jitse]+"'s turn!");
                		undo.setVisible(false);
                		im.setVisible(false);
                		cd.setVisible(false);
                	}
                });
                timeline=new Timeline(keyFrame);
                timeline.setCycleCount(tim);
                timeline.play();

                
            }
        }}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		winner=-1;
		players=gridDoubleInstructionsController.players;
		im.setVisible(false);
		cd.setVisible(false);
		undo.setVisible(false);
		jitse=gridDoubleInstructionsController.jitse;
		now=jitse;
		TranslateTransition transition = new TranslateTransition(Duration.seconds(4), battle);
		transition.setFromX(-500); 
	    transition.setToX(500); 
        transition.setCycleCount(TranslateTransition.INDEFINITE); 
        transition.setAutoReverse(true); 
        transition.play(); 
        FadeTransition fade = new FadeTransition(Duration.seconds(1), move);
    	fade.setFromValue(1.0);
    	fade.setToValue(0.5);
    	fade.setAutoReverse(true);
    	fade.setCycleCount(Timeline.INDEFINITE);
    	fade.play();
    	move.setText("Game will start shortly...");
    	rem=tim;
    	duration=Duration.seconds(1);
        keyFrame=new KeyFrame(duration,eventt ->{
        	rem--;
        	if(rem<=0) {
                System.out.println("now jitse "+jitse);
                move.setText(players[jitse]+"'s turn");
        	}
        });
        timeline=new Timeline(keyFrame);
        timeline.setCycleCount(tim);
        timeline.play();
    	
	}
}
