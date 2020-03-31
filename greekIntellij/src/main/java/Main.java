import processing.core.PApplet;
import processing.core.PImage;
import processing.data.Table;
import processing.event.KeyEvent;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;


public class Main extends PApplet {
    Room r1;
    Player p1;
    Quiz q1;
    Room currentRoom;
    ArrayList<Room> allRooms = new ArrayList<Room>();
    PImage img;



    public static void main(String[] args){
        PApplet.main("Main");

    }

    public void settings(){
        size(625,450);



    }

    public void setup(){
        //frameRate(20);
        D
        try {
            loadRooms();
        } catch (IOException e) {
            e.printStackTrace();
        }


       // r1 = new Room("1","Images\\Backgrounds\\r5.png","Images\\Tilemaps\\r5.png",this);
        p1 = new Player(10,10,"Player1.png",this,this);
        q1 = new Quiz(this,this);
        currentRoom=allRooms.get(0);

    }

    public void  draw(){
       currentRoom.drawRoom();
       p1.drawPlayer();
       q1.drawQuiz();
    }


    //PlayerMovement:
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

    public void loadRooms() throws IOException {

        ArrayList<String> listOfBackgrounds = getImageFiles("Images/Backgrounds");
        ArrayList<String> listOfTilemaps = getImageFiles("Images/Tilemaps");

        assert listOfTilemaps != null;
        assert listOfBackgrounds != null;

        for (int i =0; i<listOfBackgrounds.size(); i++) {
            System.out.println(listOfBackgrounds.get(i));
            System.out.println(listOfTilemaps.get(i));
        }


        for(int i = 0; i<listOfBackgrounds.size(); i++){
            System.out.println(listOfBackgrounds.get(i));
            System.out.println(listOfTilemaps.get(i));
            allRooms.add(new Room("r"+i, listOfBackgrounds.get(i),listOfTilemaps.get(i),this));
        }
        for (Room allRoom : allRooms) {
            System.out.println(allRoom);
        }


    }

    public ArrayList<String> getImageFiles(String url) throws IOException {
        File folder = new File(url);
        String[] extensions = new String[]{"png"};
        List<File> files = (List<File>) FileUtils.listFiles(folder,extensions,true);
        ArrayList<String> imagePaths = new ArrayList<String>();
        for(File file : files){
            imagePaths.add(file.getPath());
        }
        return imagePaths;

    }
}
