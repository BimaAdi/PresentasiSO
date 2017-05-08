import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


class Lingkaran extends Thread
{
	Circle circle = new Circle();
	float x,y;
	float velocityX, velocityY;
	float radius;

	private Thread t;
    private String threadName;
   
	public Lingkaran(Group group, String name)
	{
		setRadius(10);
		setPosition(0, 0);
		setVelocityX(2);
		setVelocityY(2);
		circle.setFill(Color.BLUE);
		group.getChildren().add(circle);

		threadName = name;
      	System.out.println("Creating " +  threadName );
	}

	public void setRadius(float radius)
	{
		this.radius = radius;
		circle.setRadius(radius);
	}

	public void setPosition(float x, float y)
	{
		this.x = x;
		this.y = y;
		circle.setLayoutX(x + radius);
		circle.setLayoutY(y + radius);
	}

	public void setVelocityX(float velocity)
	{
		this.velocityX = velocity;
	}

	public void setVelocityY(float velocity)
	{
		this.velocityY = velocity;
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	public float getVelocityX()
	{
		return velocityX;
	}

	public float getVelocityY()
	{
		return velocityY;
	}

	public void run()
	{
		 System.out.println("Running " +  threadName );
	      try 
	      {
	         for(int i = 4; i < 5; i--) 
	         {
	            /*System.out.println("Thread: " + threadName + ", " + i);*/
	            // Let the thread sleep for a while.
	            this.setPosition(this.getX() + this.getVelocityX(), this.getY() + this.getVelocityY());
	            Thread.sleep(1000);
	         }
	      }catch (InterruptedException e) 
	      {
	         System.out.println("Thread " +  threadName + " interrupted.");
	      }
	      System.out.println("Thread " +  threadName + " exiting.");
	}

	public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
}

public class So extends Application
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		Group root = new Group();
		Scene scene = new Scene(root, 400, 300);
		Lingkaran lingkaran1 = new Lingkaran(root, "l1");
		lingkaran1.start();

		stage.setScene(scene);
		stage.show();

	}
}