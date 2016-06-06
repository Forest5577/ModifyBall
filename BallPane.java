//U10416023

//import API
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

//create a class extends Pane
public class BallPane extends Pane {

  //the radius for the ball
	private double radius = 15;
	//record the path for balls on XY
	private double [][]xy = {{radius,radius*6},{radius*9,radius*15},{radius*20,radius*30}};
	//a array for the xy move
	private double[][] dxy = {{1,1},{1,1},{1,1}};
	//create ten balls
	private Circle[] circle = {new Circle(xy[0][0], xy[0][1], radius),new Circle(xy[1][0], xy[1][1], radius*1.5),
			new Circle(xy[2][0], xy[2][1], radius*2)};
  private Timeline animation;

  //Constructor
  public BallPane() {
    //add three balls in the pane
		circle[0].setFill(Color.GREEN);
		circle[1].setFill(Color.BLUE);
		circle[2].setFill(Color.RED);
		for(int i = 0; i<3;i++){
			this.getChildren().addAll(circle[i]);
    }

    // Create an animation for moving the ball
    animation = new Timeline(
      new KeyFrame(Duration.millis(20), e -> moveBall()));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play(); // Start animation
  }

  //Create a method playing
  public void play() {
    animation.play();
  }

  //Create a method to pausing
  public void pause() {
    animation.pause();
  }

  //Create a method to increaseSpeed
  public void increaseSpeed() {
    animation.setRate(animation.getRate() + 0.1);
  }

  //Create a method to decreaseSpeed
  public void decreaseSpeed() {
    animation.setRate(
      animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
  }

  //Create a method to rateProperty
  public DoubleProperty rateProperty() {
    return animation.rateProperty();
  }

  //Create a method to move ball
  protected void moveBall() {
    // Check boundaries
    for(int i = 0;i<3;i++){
      if (xy[i][0] < radius || xy[i][0] > getWidth() - radius) {
        dxy[i][0] *= -1; // Change ball move direction
      }
      if (xy[i][1] < radius || xy[i][1] > getHeight() - radius) {
        dxy[i][1] *= -1; // Change ball move direction
      }

      // Adjust ball position
      xy[i][0] += dxy[i][0];
      xy[i][1] += dxy[i][1];
      circle[i].setCenterX(xy[i][0]);
      circle[i].setCenterY(xy[i][1]);
    }
  }
}
