package application;
	

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Pong extends Application {
	
	//variable
	
	private static final int width = 800;
	private static final int height = 600;
	private static final int PLAYER_HEIGHT = 100;
	private static final int PLAYER_WIDTH = 15;
	

	private static final double BALL_1= 15;
	

	
	private int ball1YSpeed = 1; 
	private int ball1XSpeed = 1;

	
	
	private double playerOneYPos = height /2  ;
	private double playerTwoYPos = height /2 ;


	private double ball1XPos = width /2;
	private double ball1YPos = width /2;
	
	
	private int scoreP1 = 0;
	private int scoreP2 = 0;
	private boolean gameStarted;
	private int playerOneXPos = 0;
	private double playerTwoXPos = width - PLAYER_WIDTH;
	
	
	
	
	
	public void start(Stage stage) throws Exception {
	stage.setTitle(" P O N G ");
	Canvas canvas = new Canvas(width, height);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	Timeline t1 = new Timeline(new KeyFrame(Duration.millis(10), e ->run(gc)));
	t1.setCycleCount(Timeline.INDEFINITE);
	
	// mouse control
	
	canvas.setOnMouseMoved(e -> playerOneYPos = e.getY());
	canvas.setOnMouseClicked(e -> gameStarted = true);
	stage.setScene(new Scene (new StackPane(canvas)));
	stage.show();
	t1.play();
		
	
	}
	

	
	
	
	private void run(GraphicsContext gc) {
	//set background color
		gc.setFill(Color.BLACK); 
		gc.fillRect(0, 0, width, height); 
		
		
		
		//set text color
		gc.setFill(Color.ORANGERED);
		gc.setFont(Font.font(25));
		
		
		
		if (gameStarted) {
			//set ball movement
			ball1XPos+= ball1XSpeed;
			ball1YPos += ball1YSpeed;
			
			
			//computer 
			
			if (ball1XPos <width - width /4) {
				playerTwoYPos = ball1YPos - PLAYER_HEIGHT / 2 ;
				
			}else {
				playerTwoYPos = ball1YPos > playerTwoYPos + PLAYER_HEIGHT /2 ?playerTwoYPos += 1: playerTwoYPos -1;
			} 
			
			 
			
			//draw ball
			gc.fillOval(ball1XPos, ball1YPos, BALL_1, BALL_1 );
			
			
		
			
		}else {
			//set the start text
			gc.setStroke(Color.WHITE);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.strokeText(" on CLICK \n move your mouse to control ", width / 2, height / 2);
			
			
			//reset the ball start position
			ball1XPos = width / 2;
			ball1YPos = height / 2;
		
			
			
			//reset speed & direction
			ball1XSpeed = new Random().nextInt(2) == 0 ? 1: -1;
			
			ball1YSpeed = new Random().nextInt(2) == 0 ? 1: -1;
			
	
			
			
			
		}
		// ball stays in canvas 
			if (ball1YPos > height || ball1YPos < 0 ) ball1YSpeed *=-1;
			//computer gets a point
			
			if (ball1XPos < playerOneXPos - PLAYER_WIDTH ) {
				scoreP2++;
				gameStarted = false;
			}
				
		
			
		
			// Player1 get a point
			if (ball1XPos > playerTwoXPos + PLAYER_WIDTH ) {
				scoreP1++;
				gameStarted = false;
				
			}
			
		
			
			//increase the ball speed
			if ( ((ball1XPos + BALL_1 > playerTwoXPos) && ball1YPos >= playerTwoYPos && ball1YPos <= playerTwoYPos + PLAYER_HEIGHT) ||
				(( ball1XPos < playerOneXPos + PLAYER_WIDTH) && ball1YPos >= playerOneYPos && ball1YPos <= playerOneYPos + PLAYER_HEIGHT)) {
					ball1YSpeed += 1 * Math.signum(ball1YSpeed) ;
					ball1XSpeed += 1 * Math.signum(ball1XSpeed) ;
					ball1XSpeed *=-1;
					ball1YSpeed *=-1;
					
				} 
		
			
			
			// draw score
				
			gc.fillText(scoreP1 + "\t\t\t\t\t\t\t\t"  + scoreP2, width /2, 100) ;   
								// draw player 1 & 2            
			gc.fillRect(playerTwoXPos, playerTwoYPos, PLAYER_WIDTH, PLAYER_HEIGHT);
			gc.fillRect(playerOneXPos, playerOneYPos, PLAYER_WIDTH, PLAYER_HEIGHT);
			
			
	
	}
	
}			
		
	
