// Maze Game: Using GUI interfaces and controls to build a simple maze game, by Christian Mitchell
import java.lang.*;
import javafx.scene.shape.*;
import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.Scene.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.canvas.*;
import javafx.event.*;
import javafx.animation.*;
import java.io.*;


public class MazeRun extends Application
{
    //MazeRun member elements
    ArrayList<ArrayList<Integer>> mapData = new ArrayList<ArrayList<Integer>>();// 2D Arraylist to store data from file
    
    public int x; //Variables for x and y starting position
    
    public int y;
     
    FlowPane root;
    
    GraphicsContext gc;
    
    MapCanvas Map;//canvas object to draw the squares for the Maze Map
    
    //Creating Canvas class to draw the map for the maze
    public class MapCanvas extends Canvas
    {
      GraphicsContext gc= getGraphicsContext2D();
   
   
      //Sets canvas to the size of the window, which is 525 by 525
      public MapCanvas()
      {
        setWidth(525);
        setHeight(525);
      }
      
     
      //void method that initializes user starting position(zero of first row)
      public void BeginHere()
      {
         gc=getGraphicsContext2D();
         //For loop itereates through data and looks for the first zero of the first row
         for(int k=0;k<21;k++)
         {
            if(mapData.get(0).get(k)==0)
            {
               y=k*25;
               x=0;
            }
         }
         
       //Draw the starting square
       gc.setFill(new Color(2,.5,2,1));
       gc.setRect(x,y,25,25);
      }
       
       //void method to draw and paint the map of the maze
       public void draw()
       {
       
         //initialize a scanner object for reading the data for the Map
         Scanner scan=new Scanner(new File("mazedata.txt"));
         
         //Creating inner dimension of 2D Arraylist
         for(int i=0;i<21;i++)
         {
            ArrayList<Integer> sublist=new ArrayList<Integer>();
            mapData.add(sublist);
         }
         
         //Read the data from the mazetxt file into the arraylist using nested for loop
        do
        { 
         for(int j=0;j<21;j++)
         {
            for(int k=0;k<21;k++)
            {
               mapData.get(j).add(scan.nextInt());
            }
            
          }
        }while(scan.hasNextInt());
          
          //Now that the arraylist contains the maze map data, use it to draw black squares for ones and white squares for zero(25x25)
          for(int j=0;j<21;j++)
          {
            for(int k=0;j<21;j++)
            {
               if(mapData.get(j).get(k)==0)
               {
                  gc.setFill(Color.WHITE);
                  gc.fillRect(j*25,k*25,25,25);
               }
               
               if(mapData.get(j).get(k)==1)
               {
                  gc.setFill(Color.BLACK);
                  gc.fillRect(j*25,k*25,25,25);
               }
             }
           }
       }
   }
   
   
   //Setting the scene and adding proper containers to make the game visisble
   public void Start(Stage temple)
   {
     //Creating a new canvas object to display the maze, and adding it to a flowpane which is used as the root container
      MapCanvas Map=new MapCanvas();
      Map.BeginHere();
      Map.setOnKeyPressed(new KeyListener());
      Map.draw();
      root=new FlowPane();
      root.getChildren().add(Map);
      
     //Creating scene and adding it to the stage
      Scene scene = new Scene(root,525,525); 
      temple.setScene(scene); 
      temple.setTitle("MazeRunner");
      temple.show();
      Map.requestFocus();
      
   }
   
   
   //KeyListener Class implements from KeyEvent eventhandler to execute certain functions when a key is pressed
   public class KeyListener implements EventHandler<KeyEvent>
   {
      
      //Handle Method to execute the keyevent functions
      public void handle(KeyEvent ke)
      {
         
         gc.clearRect(0,0,525,525);
         Map.draw();
         Map.BeginHere();
         gc=Map.getGraphicsContext2D;
         
         //Listening for keys pressed inluding down, up, left, and right arrow keys, KeyCode for the keypad arrows was gotten from Oracle, link to source below
         if(ke.getCode().equals(KeyCode.KP_DOWN))
         {
            gc.clearRect(x,y,25,25);
            y=y+25;
            gc.fillRect(x,y,25,25);
         
            //If potential position is black, disables user square
            if(mapData.get(y/y+25).get(x/25)==1)
            {
               gc.clearRect(x,y,25,25);
            }
         }
         
         else if(ke.getCode().equals(KeyCode.KP_UP))
         {
            gc.clearRect(x,y,25,25);
            y=y-25;
            gc.fillRect(x,y,25,25);
            
            if(mapData.get(y/y-25).get(x/25)==1)
            {
               gc.clearRect(x,y,25,25);
            }
         }
         
         else if(ke.getCode().equals(KeyCode.KP_LEFT))
         {
            
            gc.clearRect(x,y,25,25);
            x=x-25;
            gc.fillRect(x,y,25,25);
            
            if(mapData.get(y/25).get(x/x-25)==1) //divide by 25 to get the the square's position in the arraylist since the square is drawn in the canvas by multiplying its position in the arraylist by 25
            {
               gc.clearRect(x,y,25,25);
            }
         }
         
          else if(ke.getCode().equals(KeyCode.KP_RIGHT))
         {
            
           gc. clearRect(x,y,25,25);
            x=x+25;
            gc.fillRect(x,y,25,25);
            
            if(mapData.get(y/25).get(x/x+25)==1)
            {
               gc.clearRect(x,y,25,25);
            }
         }
         
         //If the square is in the position of the only zero in the bottom row then the user is alerted the maze has been solved
         if(mapData.get(20).get(x)==0)
         {
            System.out.println("You win!");
         }
   }
 }
   
   
   //Launch Application
   public static void main(String []args) 
   {
      launch(args);
   }           
   
   
   }
  


/* Link to source for the KeyCode symbols of the keypad arrows: https://docs.oracle.com/javafx/2/api/javafx/scene/input/KeyCode.html*/
         
            
   
      
      

      
      
      

   



                  
         
         
         
         
         
         
         
         
         
     


   
    
    
    
    
    
    
  

   