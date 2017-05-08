import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;


class Lingkaran extends Thread
{
	Circle circle = new Circle();
	float x,y;
	float velocityX, velocityY;
	float radius;

	int wait;
	private Thread t;
    private String threadName;
   
	public Lingkaran(Group group, String name, float x, float y)
	{
		setRadius(30);//radius lingkaran
		setPosition(x, y);//posisi lingkaran
		setVelocityX(10);//kecepatan lingkaran
		setVelocityY(2);
		circle.setFill(Color.BLUE);//Warna lingkaran
		group.getChildren().add(circle);//Memasukan ke dalam group

		this.wait = 0;//statu thread
		/*0 = Wait/Runnable/
		  1 = Run
		  2 = Dead*/
		threadName = name;
      	System.out.println("Creating " +  threadName );
	}

	//Get Set Method
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

	public void setWait(int w)
	{
		this.wait = w;
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

	public int getWait()
	{
		return wait;
	}

	//thread method
	public void run()
	{
		 System.out.println("Thread " +  threadName +" Run");
	      try 
	      {
	      	while(getWait() != 2)//jika get wait sama dengan 2 thread akan Dead
	      	{
	      		if(getWait() == 1)
	         	{
	      			//jika get wait sama dengan 0 thread akan Run
	            	this.setPosition(this.getX() + this.getVelocityX(), this.getY());
	         	}
	         	else
	         	{
	         		//jika get wait sama dengan 0 thread akan Wait
	         		waitFor();
	         	}
	            Thread.sleep(400);//jeda
	      	}
	      }catch (InterruptedException e) 
	      {
	         System.out.println("Thread " +  threadName + " Dead");
	      }
	      System.out.println("Thread " +  threadName + " Dead");//jika thread Dead
	}

	public synchronized void waitFor() throws InterruptedException 
	{
		//jika thread mengalami Wait
        System.out.println("Thread " +  threadName +" Wait");
        while (getWait() == 0) 
        {
            Thread.sleep(400);
        }
        System.out.println("Thread " +  threadName +" Run");
    }

    //jika program melakukan start maka program juga akan melakukan run() (thread pada java)
	public void start () 
	{
      System.out.println("Starting " +  threadName );
      if (t == null) 
      {
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
		/*tampilan stage lingkaran dan deklarasi thread*/
		Group root = new Group();
		Scene scene = new Scene(root, 400, 300);
		stage.setTitle("Thread Life Cycle");

		Label l1 = new Label("1.");
		l1.setLayoutX(10);
		l1.setLayoutY(20);
		root.getChildren().add(l1);
		Lingkaran lingkaran1 = new Lingkaran(root, "l1", 20, 20);

		Label l2 = new Label("2.");
		l2.setLayoutX(10);
		l2.setLayoutY(100);
		root.getChildren().add(l2);
		Lingkaran lingkaran2 = new Lingkaran(root, "l2", 20, 100);

		/*Button pada lingkaran 1*/
		Label b1 = new Label("1.");
		b1.setLayoutX(10);
		b1.setLayoutY(190);
		root.getChildren().add(b1);

		Button btnStart = new Button("Start");
		btnStart.setPrefWidth(70);
		btnStart.setPrefHeight(20);
		btnStart.setLayoutX(20);
		btnStart.setLayoutY(200);
		root.getChildren().add(btnStart);

		Button btnWait = new Button("Waiting");
		btnWait.setPrefWidth(70);
		btnWait.setPrefHeight(20);
		btnWait.setLayoutX(120);
		btnWait.setLayoutY(200);
		root.getChildren().add(btnWait);


		Button btnRunning = new Button("Running");
		btnRunning.setPrefWidth(70);
		btnRunning.setPrefHeight(20);
		btnRunning.setLayoutX(220);
		btnRunning.setLayoutY(200);
		root.getChildren().add(btnRunning);

		Button btnDead = new Button("Dead");
		btnDead.setPrefWidth(70);
		btnDead.setPrefHeight(20);
		btnDead.setLayoutX(320);
		btnDead.setLayoutY(200);
		root.getChildren().add(btnDead);

		btnStart.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e)
			{
				lingkaran1.start();
			}
		});

		btnWait.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e)
			{
				lingkaran1.setWait(0);
			}
		});

		btnRunning.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e)
			{
				lingkaran1.setWait(1);
			}
		});

		btnDead.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e)
			{
				lingkaran1.setWait(2);
			}
		});



		/*Button pada lingkaran 2*/
		Label b2 = new Label("2.");
		b2.setLayoutX(10);
		b2.setLayoutY(240);
		root.getChildren().add(b2);

		Button btnStart2 = new Button("Start");
		btnStart2.setPrefWidth(70);
		btnStart2.setPrefHeight(20);
		btnStart2.setLayoutX(20);
		btnStart2.setLayoutY(250);
		root.getChildren().add(btnStart2);

		Button btnWait2 = new Button("Waiting");
		btnWait2.setPrefWidth(70);
		btnWait2.setPrefHeight(20);
		btnWait2.setLayoutX(120);
		btnWait2.setLayoutY(250);
		root.getChildren().add(btnWait2);


		Button btnRunning2 = new Button("Running");
		btnRunning2.setPrefWidth(70);
		btnRunning2.setPrefHeight(20);
		btnRunning2.setLayoutX(220);
		btnRunning2.setLayoutY(250);
		root.getChildren().add(btnRunning2);

		Button btnDead2 = new Button("Dead");
		btnDead2.setPrefWidth(70);
		btnDead2.setPrefHeight(20);
		btnDead2.setLayoutX(320);
		btnDead2.setLayoutY(250);
		root.getChildren().add(btnDead2);

		btnStart2.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e)
			{
				lingkaran2.start();
			}
		});

		btnWait2.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e)
			{
				lingkaran2.setWait(0);
			}
		});

		btnRunning2.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e)
			{
				lingkaran2.setWait(1);
			}
		});

		btnDead2.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e)
			{
				lingkaran2.setWait(2);
			}
		});


		stage.setScene(scene);
		stage.show();

	}
}