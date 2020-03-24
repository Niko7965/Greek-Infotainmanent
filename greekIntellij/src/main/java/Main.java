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
       System.out.println(keyCode);
    }

    public int getKeyCode(){
        return keyCode;
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
