import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.io.File;
import java.util.ArrayList;

public class Main extends PApplet {
    Room r1;
    Player p1;
    Room currentRoom;
    ArrayList<Room> allRooms;
    PImage img;


    public static void main(String[] args){
        PApplet.main("Main");

    }

    public void settings(){
        size(1250,900);

    }

    public void setup(){
        r1 = new Room("1","g5.png",this);
        p1 = new Player(10,10,"Player1.png",this,this);


    }

    public void  draw(){
       r1.drawRoom();
       p1.drawPlayer();

    }

    public void keyPressed(){
        if(key =='a' || key =='A'){
            p1.moveLeft=true;
        }
        if(key =='d' || key =='D'){
            p1.moveRight=true;
        }
        if(key =='w' || key =='W'){
            p1.moveUp=true;
        }
        if(key =='s'||key =='S'){
            p1.moveDown=true;
        }
    }

    public void keyReleased(){
        if(key =='a' || key =='A'){
            p1.moveLeft=false;
        }
        if(key =='d' || key =='D'){
            p1.moveRight=false;
        }
        if(key =='w' || key =='W'){
            p1.moveUp=false;
        }
        if(key =='s'||key =='S'){
            p1.moveDown=false;
        }
    }

    public int getKeyCode(){
        if(keyPressed){
            return keyCode;
        }
        else return 0;
    }

    public void loadRooms(){
        File folder = new File("Images/Backgrounds");
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        for(int i = 0; i<listOfFiles.length; i++){
            allRooms.add(new Room("r"+i,listOfFiles[i].getName(),this));
        }

    }







}
