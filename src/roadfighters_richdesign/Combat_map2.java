package roadfighters_richdesign;

/*
 * 
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import static java.awt.SystemColor.menu;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javax.swing.UIManager;




/**
 * This is where all the magic happen! 90% of the code was written in this JFrame; this is where the characters fight!
 * 
 * Yang - Jan 04, 2020
 * Hey, I made some changes to the leftG, rightG, jumpG, and crouchG methods
 * You might still want to work on the jump formula cuz the version that I made is only going straight up and down 
 * "Garcia" now can move freely with "WASD" key controls. 
 * 
 * Tomio-Jan 04,2020
 * I modifed the methods some more, 
 * basically pressing a key needs to change his velocity, not move him directly,
 * because getting hit with an attack will change the velocity, and knock the player back
 * jump and moving all work smoothly now
 * I also added version history for the stuff i built before i sent it to you for the first time, and updated all the numbers
 *
 * Yang - Jan 07, 2020
 * I designed the main menu JFrame, I think it looks fantastic 
 * I also added a help JFrame that is not very helpful
 * I found two pictures of Garcia and Denis, however I am having trouble added these two images to the screen (top left and top right corner)
 * 
 * Richard - Jan 12, 2020
 * I created a new project and completely redesigned the JFrames and added a choose character method 
 * 
 * 
 * @author Tomio and Yang
 * 
 * version history
 * to help keep things straight, I used this version history notation, Yang used the one with the ver.
 * version 0.1 2020.12.23 - initilized variables for length and starting locations of all hitboxes/hurtboxes, using 0 for the moment
 * version 0.2 2020.12.23 - created methods to build hit and hurtboxes, no location or size currently selected, to be determined later
 * version 0.3 2020.12.26 - created basic methods to handle velocities
 * version 0.4 2020.12.27 - modified methods to allow for better movement, added accelerations and gravity
 * ver.0.5 2021.01.03 2:00 pm - set the starting position of the hurtboxes, having trouble setting up Garcia's x and y starting position, always appears on the top left corner
 * ver.0.6 2021.01.03 2:30 pm - solved problem above by disabling code from line 200 to 202
 * ver.0.7 2021.01.03 2:50 pm - changed the color of rectangle Garcia and Denis, also added a Rectangle called Bottom to represent the display board
 * Version 0.8 2021.01.03 2200 - Addressed bug in version 0.1, rewrote lines to relocate Garcia to use getX rather than getLayoutX
 * Version 0.9 2021.01.03 2210 - imported and modified method "dispatch key event" from stack overflow, added volatile booleans to show whether a specific key is being pressed
 * Version 1.0 2021.01.03 2230 - added methods to handle keys being pressed, including jump equation
 * Version 1.1 2021.01.03 2230 - fixed bug where code thought all keys were pressed at once
 * ver.1.2 2021.01.04 11:10 am - changed "Listener.schedule(heard, 0, 50)", from 20 millisecond to 50 millisecond. To fix the glitch 
 * ver.1.3 2021.01.04 11:20 am - made some changes to the moveG method, use conditional statements to keep object on the screen
 * ver.1.4 2021.01.04 11:30 am - made changes to the "leftG" and "rightG" method 
 * Version 1.5 2021.01.04 2000 - modified leftG and rightG using the recent changes and the original
 * Version 1.6 2021.01.04 2030 - modified JumpG to limit players jump height
 * Version 1.7 2021.01.04 2050 - recreated all movement code for Denis, using arrow keys
 * Version 1.8 2021.01.04 2110 - encapsulated the methods that change movement variables
 * Version 1.9 2021.01.04 2120 - added key bindings for attacks, might make those adjustable if time allows, would do it by making a string variable selected in the menu carry over
 * Version 2.0 2021.01.04 2130 - added methods to destroy hitboxes
 * Version 2.1 2021.01.05 1400 - added counters to control startup lag, duration and end lag of attacks
 * Version 2.2 2021.01.05 1430 - added methods to move the hitboxes, and keep them stationary relative to the character
 * ver. 2.3 2021.01.07 3:00 pm - added main menu, help JFrame, and hp - failed to add images of Garcia and Denis source:https://twitter.com/bcivs/status/881187568158683136
 * ver. 2.4 2021.01.10 5:00 pm - hit detection, when Garcia launches light attack on Denis, Denis' HP decreases; not functioning properly, Denis' HP goes down too much 
 * Version 2.5 2021.01.11 2000 - changed the attack movement and attack creation so that they always face the opponent
 * Version 2.6 2021.01.12 1900 - added interactions when attack hitboxes collide 
 * ver.2.7 2021.01.13 12:47 am - added two JFrames named DenisWin and GarciaWin, but still need to add code to make it work
 * maybe using if statements in the timer "heard"?
 * ver.2.8 2021.01.13 12:49 am - added "back to menu" button to the display board, still needs code to make if functional 
 * Version 2.9 2021.01.13 1430 - made first beta tester reports bug where victory can be achieved without hitting the opponent, assumed cause is old versions of Cmobat_Map2 remaining active after retuning to the menu
 * Version 3.0 2021.01.13 1440 - solution, disable Combat_Map2 as player exits
 * Version 3.1 2021.01.13 1520 - beta tester reported bug with crouching in mid air stalling momentum, solution rewrite crouch to account for velocity
 * Version 3.2 2021.01.14 1000 - adjusted crouching so the rectangle actually shrinks
 * ver. 3.3 2021.01.14 8:00 pm - made the combat map border less and can be disposed anywhere 
 * ver. 3.4 2021.01.14 8:00 pm - made the combat map border less and can be disposed anywhere 
 * ver. 3.5 2021.01.15 9:30 am - when clicked "back to menu", can't restart game again - problem fixed by Tomio 
 * Version 3.6 2021.01.15 1300 - removed the variable (width) from all the system.exit statements, replaced them with 0 
 * Version 3.7 2021.01.15 1300 - attempted to teach the A.I. how to get around opponents only crouching and using light attack 
 * ver. 3.8 2021.01.15 2:39 pm - defeat.java contributed by Richard, code added by Yang, defeat.java only shows up in Single Player mode. 
 * Version 3.9 2021.01.15 1500 - encapsulated the hit detection, encapsulated portions of the A.I. 
 * Version 4.0 2021.01.15 1730 – Uploaded the final version to the onedrive 
 */

//IMPORTANT PLEASE READ FIRST
    //basic naming conventions are as follows, exceptions exist
    //characters and character hurtboxes are referred to as Garcia and Denis
    //variables involving attacks are named using three letters with the following rules:
    //the first letter will determine what kind of variable it is, I.E. a for the attack hitbox, s for starting lag ect.
    //the second will be either be l or h, to show whether it is a heavy or light attack, there is an exception for starting and ending lag, where the second letter is l and it stands for lag
    //the third letter will be a capitol G or a capitol D, to show which character they belong to, this is the only reason capitols are used,
    
    //handy trick for navigation, near top is the timertask, which calls most of the importnat functions, if you scroll to the top it can serve as a table of contents

@SuppressWarnings("Deprecation")
public class Combat_map2 extends JApplet {
    Timer Listener = new Timer();
    Pane root = new Pane();
    Random rnd = new Random();
    static JFrame frame;
   
     
    
    //public boxes
    Rectangle Garcia;//Garcia's hurtbox
    Rectangle Denis;//Denis' hurtbox
    Rectangle Bottom;//this is going to be the display board 
    Rectangle test;
    Rectangle alG;//attack light Garcia
    Rectangle alD;//attack light Denis
    Rectangle ahG;//attack heavy Garcia
    Rectangle ahD;//attack heavy Denis
        
    //hurtbox values
    int startx_Garcia = 50;//starting location x value for Garcia
    int starty_Garcia = 400;//starting y location for Garcia
    int startx_Denis = 1050;//starting x location for Denis
    int starty_Denis = 400;//starting y location for Denis
    int length_Garcia =100;//x value of Garcia's hurtbox
    int height_Garcia =200;//y value of Garcia's hurtbox
    int length_Denis =100;//x value of Denis' hurtbox
    int height_Denis =200;//y value of Denis' hurtbox
    
    //heavy attack values
    int length_ahG =100;
    int height_ahG =50;
    int length_ahD =100;
    int height_ahD =50;
    int slh=7;// starting lag heavy
    int elh=10;//ending lag heavy
    int dh =15;//duration heavy
    int hsh=8;//hit stun heavy
    int kbh=30;//knock back heavy
    
    //light attack values
    int length_alG =110;
    int height_alG =30;
    int length_alD =120;
    int height_alD =30;
    int sll =4;//starting lag light
    int ell =4;//ending lag light
    int dl =7;//duration light
    int hsl=4;//hit stun light
    int kbl = 20;//knock back light
    
    //gravity
    int GravityG = 2;//force of gravity for Garcia
    int GravityD = 2;//force of gravity for Denis
    
    //velocities
    int vxC=8;//constant velocity
    int vyC=64;//constant jump velocity
    int vxF=2;//rate at which "friction" reduces the players speed
    int vxG=0;//Garcia's current horizantal velocity
    int vyG=0;//Garcia's current vertical velocity
    int vxD=0;//Denis' current horizantal velocity
    int vyD=0;//Denis' current vertical velocity
    
    //accelerations
    int axG=0;//Garcia's current horizantal acceleration
    int ayG=0;//Garcia's current vertical acceleration
    int axD=0;//Denis' current horizantal acceleration
    int ayD=0;//Denis' current vertical acceleration
    
    //labels for the display board
    Label gLabel;
    Label gHP;
    Label dLabel;
    Label dHP;
    
    //to record the scores
    int gScore = 10;
    int dScore = 10;
    
    //important keys
    //booleans for whether or not a key is being pressed
    //voliatile because they are triggered by the KeyboardFocusManager, and accessed from the timer
    //it is possible this is unnecessary
    volatile boolean w =false;
    volatile boolean a =false;
    volatile boolean s =false;
    volatile boolean d =false;
    volatile boolean up =false;
    volatile boolean left =false;
    volatile boolean down =false;
    volatile boolean right =false;
    volatile boolean b =false;
    volatile boolean v =false;
    volatile boolean o =false;
    volatile boolean p =false;
    
    //count variables
    int jcount=0;
    
    //current ending lag for attacks
    int elG=0;
    int elD=0;
    
    //current starting lag for attacks
    int slG=0;
    int slD=0;
    int shG=0;
    int shD=0;
    
    //current duration of attacks
    int dlG=0;
    int dlD=0;
    int dhG=0;
    int dhD=0;
    
    //hit stun duration
    int hsD=0;
    int hsG=0;         
    
    //hit damage check
    int dd_alG;
    int dd_alD;
    int dd_ahD;
    int dd_ahG;
    
    //hit detection variables
    boolean ahGiDenis=false;
    boolean ahGiahD=false;
    boolean ahGialD=false;
    boolean alGiDenis=false;
    boolean alGiahD=false;
    boolean alGialD=false;
    boolean ahDiGarcia=false;
    boolean alDiGarcia=false;
    
    //AI variables  
    int choice=0;
    int ticks_moved=0;
    boolean has_choice=false;
    volatile boolean AID=false;
    volatile boolean AIG=false;
    
   
    
    private static final int JFXPANEL_WIDTH_INT = 1200;
    private static final int JFXPANEL_HEIGHT_INT = 750;
    private static JFXPanel fxContainer;

    /**
     * @param args the command line arguments
     */
    
    //making the code start
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                }
                
                frame = new JFrame("Combat Map");
                frame.setUndecorated(true);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                JApplet applet = new Combat_map2();
                applet.init();
                
                frame.setContentPane(applet.getContentPane());
                
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                
                applet.start();
            }
        });
    }

   
    
    @Override
    public void init() {
        
        fxContainer = new JFXPanel();
        fxContainer.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT));
        
        add(fxContainer, BorderLayout.CENTER);
        // create JavaFX scene
        Platform.runLater(new Runnable() {
            
            // when it starts 
            @Override
            public void run() { 
                try { 
                    createScene();
                } catch (IOException ex) {
                    Logger.getLogger(Combat_map2.class.getName()).log(Level.SEVERE, null, ex);
                }
            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher(){//listens for keypresses
            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                //sets up booleans tied to keys, so the program knows when one is being pressed
                    switch (ke.getID()) {
                    case KeyEvent.KEY_PRESSED:
                        switch(ke.getKeyCode()){
                            
                            //Garcia's keys
                            case KeyEvent.VK_W: 
                            if(!AIG)w = true;
                            break;
                            case KeyEvent.VK_S: 
                            if(!AIG)s = true;
                            break;
                            case KeyEvent.VK_D: 
                            if(!AIG)d = true;
                            break;
                            case KeyEvent.VK_A: 
                            if(!AIG)a = true;
                            break;
                            case KeyEvent.VK_V: 
                            if(!AIG)v = true;
                            break;
                            case KeyEvent.VK_B: 
                            if(!AIG)b = true;
                            break;
                            
                            
                            
                            //Denis' keys
                            case KeyEvent.VK_UP: 
                            if(!AID)up = true;
                            break;
                            case KeyEvent.VK_DOWN: 
                            if(!AID)down = true;
                            break;
                            case KeyEvent.VK_RIGHT: 
                            if(!AID)right = true;
                            break;
                            case KeyEvent.VK_LEFT: 
                            if(!AID)left = true;
                            break;
                            case KeyEvent.VK_O: 
                            if(!AID)o = true;
                            break;
                            case KeyEvent.VK_P: 
                            if(!AID)p = true;
                            break;
                        }
                        break;

                        
                        
                        
                        
                    case KeyEvent.KEY_RELEASED:
                        switch(ke.getKeyCode()){
                        
                            //Garcia's keys
                            case KeyEvent.VK_W: 
                            if(!AIG)w = false;
                            break;
                            case KeyEvent.VK_S: 
                            if(!AIG)s = false;
                            break;
                            case KeyEvent.VK_D: 
                            if(!AIG)d = false;
                            break;
                            case KeyEvent.VK_A: 
                            if(!AIG)a = false;
                            break;
                            case KeyEvent.VK_V: 
                            if(!AIG)v = false;
                            break;
                            case KeyEvent.VK_B: 
                            if(!AIG)b = false;
                            break;
                            
                            
                            
                            //Denis' keys
                            case KeyEvent.VK_UP: 
                            if(!AID)up = false;
                            break;
                            case KeyEvent.VK_DOWN: 
                            if(!AID)down = false;
                            break;
                            case KeyEvent.VK_RIGHT: 
                            if(!AID)right = false;
                            break;
                            case KeyEvent.VK_LEFT: 
                            if(!AID)left = false;
                            break;
                            case KeyEvent.VK_O: 
                            if(!AID)o = false;
                            break;
                            case KeyEvent.VK_P: 
                            if(!AID)p = false;
                            break;
                        }
                        break;
                    }
                    return false;
            }
        }); 
                
                Listener.schedule(heard, 0, 50);//in order: tasks to be executed, delay before first execution, milliseconds between each execution

            }
        });
    }
    
    
    
    //Building hurtboxes
    
    private void createScene() throws FileNotFoundException, IOException {
        try 

            { // import image 
                Image myPic = new Image(new FileInputStream("combatBG.jpg"));              
                ImageView gPic = new ImageView(myPic);                
                gPic.relocate(0, 0);               
                gPic.setFitHeight(620);
                gPic.setFitWidth(1200);
                root.getChildren().addAll(gPic);

            }
        catch (FileNotFoundException ex) {
                System.out.println(ex.toString());
            }

        //root.setStyle("-fx-background-color: #a2d5c6;");
        Button mm = new Button("Back to Menu");//Button that allows the user to return to the main menu
        mm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
          // fxContainer.setVisible(false);
            Listener.cancel();
            frame.setVisible(false);//disposes of the old combat frame, give a null pointer exception, but still closes
            Menu.main(null);//starts the main menu again
            
            
            }});
        Button quit = new Button("Quit");//Button to allow the user to exit the program
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                System.exit(WIDTH);
        
            }});
        mm.setFont(new Font("Cambria", 24));
        mm.setBackground(Background.EMPTY);
        mm.setTextFill(Color.CORAL);
        mm.relocate(500, 695);
        quit.setFont(new Font("Cambria", 24));
        quit.setBackground(Background.EMPTY);
        quit.relocate(555, 653);
        quit.setTextFill(Color.RED);
        Bottom = new Rectangle(0,600,1200,150); // bottom display bar       
        Bottom.setFill(Color.BLACK); // change the color of the display bar
        Garcia = new Rectangle(startx_Garcia,starty_Garcia,length_Garcia,height_Garcia);//creates hurtbox for character Garcia change starting location and dimensions
        Garcia.setFill(Color.TOMATO); // change the color of Garcia
        Denis = new Rectangle(startx_Denis,starty_Denis,length_Denis,height_Denis);//creates hurtbox for charact Denis change starting location and dimensions
        Denis.setFill(Color.PINK); // change the color of Denis 
        gLabel = new Label("Garcia's HP: ");
        gLabel.setFont(new Font("Cambria", 32));
        gLabel.setTextFill(Color.WHITE);
        gLabel.relocate(80, 640);
        gHP = new Label("10");
        gHP.setFont(new Font("Cambria", 32));
        gHP.setTextFill(Color.YELLOW);
        gHP.relocate(250, 640);
        dLabel = new Label("Denis's HP: ");
        dLabel.setTextFill(Color.WHITE);
        dLabel.setFont(new Font("Cambria", 32));
        dLabel.relocate(925, 640);
        dHP = new Label("10");
        dHP.setFont(new Font("Cambria", 32));
        dHP.setTextFill(Color.YELLOW);
        dHP.relocate(1085, 640);
        root.getChildren().addAll(Denis,Garcia,Bottom,gLabel,gHP,dLabel,dHP,mm,quit);
       
        //reads txt file to dtermine which charcter the player has chosen
        String fileName = "Character.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int character=Integer.valueOf(br.readLine());
        if (character==1){AID=true;}
        if (character==2){AIG=true;}
        
        try 

            { 
                // import images for Garcia and Denis
                Image myPic = new Image(new FileInputStream("Garcia.jpg")); 
                Image dP = new Image(new FileInputStream("denis.jpg")); 
                ImageView gPic = new ImageView(myPic); 
                ImageView dPic = new ImageView(dP); 
                gPic.relocate(20, 620);
                dPic.relocate(1130, 620);
                gPic.setFitHeight(80);
                gPic.setFitWidth(50);
                root.getChildren().addAll(gPic,dPic);

            } catch (FileNotFoundException ex) { 

                System.out.println(ex.toString()); 

            }
        
        fxContainer.setScene(new Scene(root));
        
    }
    
    //Listener that performs actions every "tick" of the game
         
      TimerTask heard = new TimerTask(){

        public void run(){
          
          countdown(); //ticks down all the counting variables i.e. attack duration, start up lag, ending lag
          
          if(hsG==0){Gkeys();}//checks if Garcia is in hitstun, if they're not it reads the players key inputs
          if(hsD==0){Dkeys();}//checks if Denis is in hitstun, if they're not it reads the players key inputs
          
          if (AID){//checks to see if an AI is playing, then calls its logic
          if(!has_choice){choose_rndD();}
          AI_D();
          }
          
          if (AIG){//checks to see is an AI is playing, then calls its logic
          if(!has_choice){choose_rndG();}
          AI_G();
          }
         
          buildattacks();//checks if the startup lag of an attack has ended, if so builds the attacks hitbox
          destroyattacks();//checks if the duration of an attack has ended, if so it removes the attacks hitbox
          
          movementG();//changes movement variables for Garcia, controls gravity, acceleration, and velocity cap
          movementD();//changes movement variables for Denis, controls gravity, acceleration, and velocity cap
          moveG();//uses velocity to move Garcia, ensures he doesn't leave the screen
          moveD();//uses velocity to move Denis, ensures he doesn't leave the screen
          
          //checks to see if the attack exists, then moves it. Movement is relative to the character locations, not velocity
          if(root.getChildren().contains(ahD)){move_ahD();}
          if(root.getChildren().contains(ahG)){move_ahG();}
          if(root.getChildren().contains(alD)){move_alD();}
          if(root.getChildren().contains(alG)){move_alG();}
             
          hitdetect();//checks for intersections between hitboxes and hurtboxes, also handles knockback from the hit
          reset();//resets boolean variables to be changed again next tick
          endcheck();//checks for victories depending on who the player is
          }
        };
    
    
      //resets booleans to false, to be checked again
      public void reset(){
          ahGiDenis=false;
          ahGiahD=false;
          ahGialD=false;
          alGiDenis=false;
          alGiahD=false;
          alGialD=false;
          ahDiGarcia=false;
          alDiGarcia=false;
      }
      
      
      //encapsulates method calls for creating attacks
      public void buildattacks(){
          if (shD==1){create_ahD();}
          if (shG==1){create_ahG();}
          if (slD==1){create_alD();}
          if (slG==1){create_alG();}
      }
      
      
      //encapsulates method call for destroying attacks
      public void destroyattacks(){
          if (dhD==1){destroy_ahD();}
          if (dhG==1){destroy_ahG();}
          if (dlD==1){destroy_alD();}
          if (dlG==1){destroy_alG();}
      }
           
             
      //encapsulates checking if a player has won
      public void endcheck(){
          if (AIG){// Garcia is the bot
              if(dScore<=0)defeat();
              if(gScore<=0)victoryD();
              System.out.println("Garcia was bot");
          }
          if (AID){// Denis is the bot
              if(gScore<=0)defeat();
              if(dScore<=0)victoryG();
              System.out.println("Denis was bot");
          }
          if (!AID&&!AIG){//if both are real players
              if(dScore<=0)victoryG();
              if(gScore<=0)victoryD();
      }}
     
      //encapsulates the victory of Denis
      public void victoryD(){
          DenisWin.main(null);
          Listener.cancel();
          frame.dispose();
      }
      
       //encapsulates the victory of Garcia
       public void victoryG(){
          GarciaWin.main(null);
          Listener.cancel();
          frame.dispose();
       }
       
       //encapsulates the defeat
       public void defeat(){
          defeat.main(null);
          Listener.cancel();
          frame.dispose();
       }
    
  

    //Building attack hitboxes and starting duration
    //locations are based on characters current location, and sizes are controlled by esily adjustable variables at the start of the class
    //direction (side of charcter the attack is created on) is based on the location of both characters relative to each other
    private void create_ahG(){
        dhG=dh;
        dd_ahG=0;
        if(Garcia.getX()<=Denis.getX()){
        ahG = new Rectangle(Garcia.getX()+length_Garcia,Garcia.getY()+Garcia.getHeight()-height_ahG,length_ahG,height_ahG);}
        else if(Garcia.getX()>Denis.getX()){
        ahG = new Rectangle(Garcia.getX()-length_ahG,Garcia.getY()+Garcia.getHeight()-height_ahG,length_ahG,height_ahG);
        }
        Platform.runLater(new Runnable(){
             @Override
             public void run() {
                 root.getChildren().add(ahG);
             }
         }); 
        }
    
    private void create_ahD(){
         dhD=dh;
         dd_ahD=0;
         if (Denis.getX()>=Garcia.getX()){
         ahD = new Rectangle(Denis.getX()-length_ahD,Denis.getY()+Denis.getHeight()-height_ahD,length_ahD,height_ahD);}
         if (Denis.getX()<Garcia.getX()){
         ahD = new Rectangle(Denis.getX()+length_Denis,Denis.getY()+Denis.getHeight()-height_ahD,length_ahD,height_ahD);
         }
          Platform.runLater(new Runnable(){
             @Override
             public void run() {
                 root.getChildren().add(ahD);
            }
        }); 
    }
    
    private void create_alG(){
        dlG=dl;
        dd_alG=0;
        if(Garcia.getX()<=Denis.getX()){
         alG = new Rectangle(Garcia.getX()+length_Garcia,Garcia.getY(),length_alG,height_alG);
        }
        else if(Garcia.getX()>Denis.getX()){
        alG = new Rectangle(Garcia.getX()-length_alG,Garcia.getY(),length_alG,height_alG);
        }
          Platform.runLater(new Runnable(){
             @Override
             public void run() {
                 root.getChildren().add(alG);
            }
        }); 
   }
    
    private void create_alD(){
        dlD=dl;
        dd_alD=0;
        if (Denis.getX()>=Garcia.getX()){
         alD = new Rectangle(Denis.getX()-length_alD,Denis.getY(),length_alD,height_alD);}
        else if (Denis.getX()<Garcia.getX()){
         alD = new Rectangle(Denis.getX()+length_Denis,Denis.getY(),length_alD,height_alD);
        }
         Platform.runLater(new Runnable(){
             @Override
             public void run() {
                 root.getChildren().add(alD);
             }
        }); 
   }
    
    
    
    //Methods for destroying hitboxes and starting end lag
    
    private void destroy_ahG(){
          elG=elh;
          Platform.runLater(new Runnable(){
             @Override
             public void run() {
                 root.getChildren().remove(ahG);
             }
         });
        }
    
    private void destroy_ahD(){
          elD=elh;
          Platform.runLater(new Runnable(){
             @Override
             public void run() {
                 root.getChildren().remove(ahD);
             }
         });
        }
    
    private void destroy_alG(){
          elG=ell;
          Platform.runLater(new Runnable(){
             @Override
             public void run() {
                 root.getChildren().remove(alG);
             }
         });
        }
    
    private void destroy_alD(){
         elD=ell;
         Platform.runLater(new Runnable(){
             @Override
             public void run() {
                 root.getChildren().remove(alD);
             }
         });
        }
    
    
    
    
   
  //Methods for handling movement variables
  //Controlled by accerlation and velocity
  //both variables are capped at 8 of either one
    
    
    //handles changing movement variables for Denis  
    public void movementD(){
    ayD=ayD+GravityD;
    vxD = vxD + axD;
    vyD = vyD + ayD;
        
    if (vxD>0)vxD=Math.abs(vxD)-2;
    if (vxD<0)vxD=-1*(Math.abs(vxD)-2);
    
    if(hsD==0){
    if(vxD>8)vxD=8;
    if(vxD<-8)vxD=-8;}
    
    if(vyD>8)vyD=8;
    if(vyD<-8)vyD=-8;
    
    if(ayD>(vyC/2))ayD=vyC/2;
    if(ayD<-(vyC/2))ayD=-vyC/2;
    }
    
   //handles changing movement variables for Garcia
   public void movementG(){
    ayG=ayG+GravityG;
    vxG = vxG + axG;
    vyG = vyG + ayG;
        
    if (vxG>0)vxG=Math.abs(vxG)-1;
    if (vxG<0)vxG=-1*(Math.abs(vxG)-1);
    
    if(hsG==0){
    if(vxG>8)vxG=8;
    if(vxG<-8)vxG=-8;}
       
    if(vyG>8)vyG=8;
    if(vyG<-8)vyG=-8;
    
    if(ayG>(vyC/2))ayG=vyC/2;
    if(ayG<-(vyC/2))ayG=-vyC/2;
   }
     
   
   //Methods for handling charcter movement, and keeping characters inside the window
   //movement based on variables controlled in the method above
   public void moveG(){
     
   Garcia.setX(Garcia.getX()+vxG);
   Garcia.setY(Garcia.getY()+vyG);
   
   if (Garcia.getX() <= 0){ // stops "Garcia" moving left when it hits the left side of the border 
       Garcia.setX(0);
   }
   if (Garcia.getX() >= 1100){ // stops "Garcia" moving right when it hits the right side of the border 
       Garcia.setX(1100);
   }
   if (Garcia.getY() <= 0){ // stops "Garcia" moving up when it hits the top of the border 
       Garcia.setY(0);
   }
    if (Garcia.getY() >= (600-Garcia.getHeight())){ // stops "Garcia" moving down when it hits the bottomwww of the border 
       Garcia.setY(600-Garcia.getHeight());
   }
   }
    
        
   public void moveD(){
        
   Denis.setX(Denis.getX()+vxD);
   Denis.setY(Denis.getY()+vyD);
   
   if (Denis.getX() <= 0){ // stops "Garcia" moving left when it hits the left side of the border 
       Denis.setX(0);
   }
   if (Denis.getX() >= 1100){ // stops "Garcia" moving right when it hits the right side of the border 
       Denis.setX(1100);
   }
   if (Denis.getY() <= 0){ // stops "Garcia" moving up when it hits the top of the border 
       Denis.setY(0);
   }
   if (Denis.getY() >= (600-Denis.getHeight())){ // stops "Garcia" moving down when it hits the bottom of the border 
       Denis.setY(600-Denis.getHeight());
   }
   }   
           
    
     //Methods for moving hitboxes 
     //moves hitboxes based on charcter location
     //updates diretion the attacks are facing
     public void move_ahG(){
         ahG.setY(Garcia.getY()+Garcia.getHeight()-height_ahG);
     if (Garcia.getX()<=Denis.getX()){
         ahG.setX(Garcia.getX()+length_Garcia);}
         
        else if(Garcia.getX()>Denis.getX()){
        ahG.setX(Garcia.getX()-length_ahG);}
     }
     
     
     public void move_ahD(){
         ahD.setY(Denis.getY()+Denis.getHeight()-height_ahD);
         if (Denis.getX()>=Garcia.getX()){
         ahD.setX(Denis.getX()-length_ahD);}
                 
         else if (Denis.getX()<Garcia.getX()){
         ahD.setX(Denis.getX()+length_Denis);}

     }
     
     
     public void move_alG(){
         alG.setY(Garcia.getY());
     
        if (Garcia.getX()<=Denis.getX()){
         alG.setX(Garcia.getX()+length_Garcia);}
         
         else if (Garcia.getX()>Denis.getX()){
         alG.setX(Garcia.getX()-length_alG);
        }
     }
     
     
     public void move_alD(){
         alD.setY(Denis.getY());
         
     if (Denis.getX()>=Garcia.getX()){
         alD.setX(Denis.getX()-length_alD);}
         
          else if(Denis.getX()<Garcia.getX()){
        alD.setX(Denis.getX()+length_Garcia);}
     }
     
     
  
    //Methods for when a key is used for movement
        
     
     //Methiods for Garcia
   
      private void jumpG(){if(Garcia.getY()==400)ayG=ayG-vyC;}
      
      private void crouchG(){if(Garcia.getY()>=400){Garcia.setHeight(height_Garcia/2);Garcia.setY(400+(height_Garcia/2));}}
      
      private void uncrouchG(){Garcia.setHeight(height_Garcia);}
      
      private void leftG(){vxG=vxG-vxC;}
      
      private void rightG(){vxG=vxG+vxC;}
      
      
      //Methods for Denis
      
      private void jumpD(){if(Denis.getY()==400)ayD=ayD-vyC;}
      
      private void crouchD(){if(Denis.getY()>=400){Denis.setHeight(height_Denis/2);Denis.setY(400+(height_Denis/2));}}
      
      private void uncrouchD(){Denis.setHeight(height_Denis);}
      
      private void leftD(){vxD=vxD-vxC;}
      
      private void rightD(){vxD=vxD+vxC;}
      
//countdown ticks in the timer
      public void countdown(){
       
                
       //counts hit stun
          if (hsD>0)hsD--;
          if (hsG>0)hsG--;
          
      //counts down starting lag of attacks
          if (shD>0)shD--;
          if (shG>0)shG--;
          if (slD>0)slD--;
          if (slG>0)slG--;
          
          //counts down end lag of attacks
          if (elD>0)elD--;
          if (elG>0)elG--;
          
          //counts down duration of attacks
          if (dlG>0)dlG--;
          if (dlD>0)dlD--;
          if (dhG>0)dhG--;
          if (dhD>0)dhD--;
      }
      
      
      
      
      
//Garcia's key calls
      public void Gkeys(){
          if (w) {jumpG();}
          if (s) {crouchG();}
          if (!s) {uncrouchG();}
          if (a) {leftG();}
          if (d) {rightG();}
          //starts starting lag
          if (v&&!root.getChildren().contains(ahG)&&!root.getChildren().contains(alG)&&shG==0&&slG==0&&elG==0) {shG=slh;}
          if (b&&!root.getChildren().contains(alG)&&!root.getChildren().contains(ahG)&&shG==0&&slG==0&&elG==0) {slG=sll;}
      }

//Denis' key calls
      public void Dkeys(){
          if (up) {jumpD();}
          if (down) {crouchD();}
          if (!down) {uncrouchD();}
          if (left) {leftD();}
          if (right) {rightD();};
          //starts starting lag 
          if (o&&!root.getChildren().contains(ahD)&&!root.getChildren().contains(alD)&&shD==0&&slD==0&&elD==0) {shD=slh;}
          if (p&&!root.getChildren().contains(alD)&&!root.getChildren().contains(ahD)&&shD==0&&slD==0&&elD==0) {slD=sll;}
      }

      
      
      
      
      //Hit detecion
      //notation is  attack1 i attack2 (the i stands for intersects)
      //returns a boolean to show whether or not the rectangles intersect, and handles most of the interations directly
      public void hitdetect(){
      //Garcia's heavy attack    
      if (root.getChildren().contains(ahG)){ 
      ahGi();
      }
      //Denis' heavy attack
      if (root.getChildren().contains(ahD)&&ahD.getBoundsInLocal().intersects(Garcia.getBoundsInLocal())){
      ahDiGarcia();
      }
      //Garcia's light attack
      if (root.getChildren().contains(alG)){ 
      alGi();
      }
      //Denis' light attack
      if (root.getChildren().contains(alD)){
      if (alD.getBoundsInLocal().intersects(Garcia.getBoundsInLocal())){
      alDiGarcia();
      }}
      }
      
      
      //hit detection for Garcia's heavy attack
      public void ahGi(){
      //ahGiDenis
      if (ahG.getBoundsInLocal().intersects(Denis.getBoundsInLocal())){ahGiDenis=true;
      ahGiDenis();}
      //ahGiahD
      if (root.getChildren().contains(ahD)){
      if (ahG.getBoundsInLocal().intersects(ahD.getBoundsInLocal())){ahGiahD=true;
      ahGiahD();}}
      //ahGialD
      if (root.getChildren().contains(alD)){
      if (ahG.getBoundsInLocal().intersects(alD.getBoundsInLocal())){ahGialD=true;
      destroy_alD();}}
      }
           
      //hit detection for Garcia's light attack
      public void alGi(){
      //alGiDenis    
      if (alG.getBoundsInLocal().intersects(Denis.getBoundsInLocal())){alGiDenis=true;
      alGiDenis();}
      //alGiahD
      if (root.getChildren().contains(ahD)&&alG.getBoundsInLocal().intersects(ahD.getBoundsInLocal())){
      alGiahD=true;
      destroy_alG();}
      //alGialD
      if (root.getChildren().contains(alD)&&alG.getBoundsInLocal().intersects(alD.getBoundsInLocal())){
      alGialD=true;
      alGialD();}
      }
        
      
      //interatction between the light attacks
      public void alGialD(){
      vxD=(int)((Denis.getX()-Garcia.getX())*kbl/Math.abs(Denis.getX()-Garcia.getX()));
      vxG=(int)((Denis.getX()-Garcia.getX())*kbl/Math.abs(Denis.getX()-Garcia.getX()));
      }
      
      //interactions between the heavy attacks
      public void ahGiahD(){
      vxD=(int)((Denis.getX()-Garcia.getX())*kbh/Math.abs(Denis.getX()-Garcia.getX()));
      vxG=(int)((Garcia.getX()-Denis.getX())*kbh/Math.abs(Garcia.getX()-Denis.getX()));
      }
      
      
      //interaction when Garcia's heavy attack hits Denis
      public void ahGiDenis(){
      axD=0;
      vxD=(int)((Denis.getX()-Garcia.getX())*kbh/Math.abs(Denis.getX()-Garcia.getX()));
      hsD=hsh; 
      if (dd_ahG==0){
      Platform.runLater(new Runnable(){
      @Override
      public void run() {
      dd_ahG=1;
      dScore -=2; // heavy attack makes Denis' HP go down 2
      dHP.setText(Integer.toString(dScore)); // dHP shows Denis' crrent HP status
      }});}
      }
            
      
      //interaction when Garcia's light attack hits Denis
      public void alGiDenis(){axD=0;
      vxD=(int)((Denis.getX()-Garcia.getX())*kbl/Math.abs(Denis.getX()-Garcia.getX()));
      hsD=hsl;
      if (dd_alG==0){
      Platform.runLater(new Runnable(){
      @Override
      public void run() {
      dd_alG=1;//ensures attacks only deal damage once
      dScore -=1; // heavy attack makes Denis' HP go down 2
      dHP.setText(Integer.toString(dScore)); // dHP shows Denis' crrent HP status
      }});}
      }
      
      
     //interaction when Denis' heavy attack hits Garcia
      public void ahDiGarcia(){
      axG=0;
      vxG=(int)((Garcia.getX()-Denis.getX())*kbh/Math.abs(Garcia.getX()-Denis.getX()));
      hsG=hsh;
      if (dd_ahD==0){
      Platform.runLater(new Runnable(){
      @Override
      public void run() {
      dd_ahD=1;//ensures attacks only deal damage once
      gScore -=2; // heavy attack makes Denis' HP go down 2
      gHP.setText(Integer.toString(gScore)); // dHP shows Denis' crrent HP status
      }});}
      }
      
      
      //interaction when Denis' light attack hits Garcia
      public void alDiGarcia(){
      axG=0;
      vxG=(int)((Garcia.getX()-Denis.getX())*kbl/Math.abs(Garcia.getX()-Denis.getX()));
      hsG=hsl;
      if (dd_alD==0){
      Platform.runLater(new Runnable(){
      @Override
      public void run() {
      dd_alD=1;//ensures attacks only deal damage once
      gScore -=1; // heavy attack makes Denis' HP go down 2
      gHP.setText(Integer.toString(gScore)); // dHP shows Denis' crrent HP status
      }});}}
   
      
            
    
      //A.I. Section
      //chooses the action of the A.I., using rigged rng
    public void choose_rndD(){
      
        if (Denis.getX()>900||Denis.getX()<250){
         choice=rnd.nextInt(4)+1;
        }
        else if (Denis.getY()<Garcia.getY()){
        choice=rnd.nextInt(6)+1;
        if (choice==1||choice==3){choice=rnd.nextInt(6)+1;}
        if (choice==1||choice==3){choice=rnd.nextInt(6)+1;}
        }
      
        else if(Garcia.getHeight()!=height_Garcia){
        choice=rnd.nextInt(4)+3; 
        
        }      
        has_choice=true;
    }
     
    
    //Controls actions of A.I. Massive switch statement controls all their options
    public void AI_D(){
        keysaiD();//resets keys
                 
      switch(choice){
          case 1://walk up and heavy attack and dodge a heavy attack by jumping
          approachhD();//move Garcia into range to attack
          if (Math.abs(Garcia.getX()-Denis.getX())<length_ahG*3){o=true;}
          if (shG>0){up=true;}
          if(elD==2){has_choice=false;}
          break;
          
          case 2://walk up and light attack
          approachlD();//move Garcia into range to attack
          if (Math.abs(Garcia.getX()-Denis.getX())<length_alD*3){p=true;}
          if(elD==2){has_choice=false;}
          break;
          
          case 3://jump and heavy attack
          up=true;
          approachhD();//move Garcia into range to heavy attack
          if (Math.abs(Garcia.getX()-Denis.getX())<(length_ahG*3)){o=true;}
          if(elD==2){has_choice=false;}
          break;
          
          case 4://duck and light attack
          down=true;
          approachlD();//move Garcia into range to light attack
          if (Math.abs(Garcia.getX()-Denis.getX())<(length_alG*3)){p=true;}
          if(elD==2){has_choice=false;}
          break;
          
          case 5://make space
          ticks_moved=ticks_moved+1;
          if (Garcia.getX()<Denis.getX()){right=true;}
          else if (Garcia.getX()>Denis.getX()){left=true;}
          if (ticks_moved>=10){has_choice=false;ticks_moved=0;}
          break;
          
          case 6://make space
          ticks_moved=ticks_moved+1;
          if (Garcia.getX()<Denis.getX()){right=true;}
          else if (Garcia.getX()>Denis.getX()){left=true;}
          if (ticks_moved>=5){has_choice=false;ticks_moved=0;}
          break;
          }}
      
    
    
      //chooses the action of the A.I., using rigged rng
       public void choose_rndG(){
      
      if (Garcia.getX()>900||Garcia.getX()<250){
         choice=rnd.nextInt(4)+1;
      }
      else if (Denis.getY()>Garcia.getY()){
      choice=rnd.nextInt(6)+1;
      if (choice==1||choice==3){choice=rnd.nextInt(6)+1;}
      if (choice==1||choice==3){choice=rnd.nextInt(6)+1;}
      }
      
      else if(Denis.getHeight()!=height_Denis){
      choice=rnd.nextInt(4)+3; 
      if (choice==5||choice==6){choice=rnd.nextInt(4)+3;}
      }
     
      has_choice=true;
      
      }
      
      
      
      public void AI_G(){
        keysaiG();
          
      switch(choice){
          case 1://walk up and heavy attack and dodge a heavy attack by jumping
          approachhG();//move Garcia into heavy attack range
          if (Math.abs(Garcia.getX()-Denis.getX())<length_ahG*3){v=true;}
          if (Denis.getHeight()==height_Denis&&slD>0){s=true;}
          if (shD>0){w=true;}
          if(elG==2){has_choice=false;}
          break;
          
          case 2://walk up and light attack
          approachlG();//move Garcia into light attack range 
          if (Math.abs(Garcia.getX()-Denis.getX())<length_alG*3){b=true;}
          if(elG==2){has_choice=false;}
          break;
          
          case 3://jump and heavy attack
          w=true;
          approachhG();//move Garcia into heavy attack range 
          if (Math.abs(Garcia.getX()-Denis.getX())<(length_ahD*3)){v=true;}
          if(elG==2){has_choice=false;}
          break;
          
          case 4://duck and light attack
          s=true;
          approachlG();//move Garcia into light attack range
          if (Math.abs(Garcia.getX()-Denis.getX())<(length_alD*3)){b=true;}
          if(elG==2){has_choice=false;}
          break;
          
          case 5://make space
          ticks_moved=ticks_moved+1;
          if (Garcia.getX()>Denis.getX()){d=true;}
          else if (Garcia.getX()<Denis.getX()){a=true;}
          if (ticks_moved>=10){has_choice=false;ticks_moved=0;}
          break;
          
          case 6://make space
          ticks_moved=ticks_moved+1;
          if (Garcia.getX()>Denis.getX()){d=true;}
          else if (Garcia.getX()<Denis.getX()){a=true;}
          if (ticks_moved>=5){has_choice=false;ticks_moved=0;}
          break;
          }}

      
public void approachhD(){
        if (Math.abs(Garcia.getX()-Denis.getX())>=length_ahD){
        if (Garcia.getX()>Denis.getX()){right=true;}
        else if (Garcia.getX()<Denis.getX()){left=true;}
}}

public void approachlD(){
        if (Math.abs(Garcia.getX()-Denis.getX())>=length_alD){
        if (Garcia.getX()>Denis.getX()){right=true;}
        else if (Garcia.getX()<Denis.getX()){left=true;}
}}

public void keysaiD(){
        up =false;
        left =false;
        down =false;
        right =false;
        o =false;
        p =false;
}

public void keysaiG() {
        w =false;
        a =false;
        s =false;
        d =false;
        b =false;
        v =false;
}

public void approachhG(){
        if (Math.abs(Garcia.getX()-Denis.getX())>=length_ahG){
        if (Garcia.getX()<Denis.getX()){d=true;}
        else if (Garcia.getX()>Denis.getX()){a=true;}
}}

public void approachlG(){
        if (Math.abs(Garcia.getX()-Denis.getX())>=length_alG){
        if (Garcia.getX()<Denis.getX()){d=true;}
        else if (Garcia.getX()>Denis.getX()){a=true;}
}}
}  