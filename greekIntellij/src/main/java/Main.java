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

    //Quiz and player object
    Player p1;
    Quiz q1;

    //The room that is currently active
    Room currentRoom;

    //A list of all rooms
    ArrayList<Room> allRooms = new ArrayList<Room>();

    //A boolean that controls whether a quiz is active
    Boolean quizMode = false;

    //The main class, which instantiates the processing main.
    public static void main(String[] args){
        PApplet.main("Main");

    }

    //Processing settings. Currently sets the size of the screen
    public void settings(){
        int scale = 2;
        size(625*scale,450*scale);

    }

    //Setup is a processing function run on startup.
    //Loads the rooms, and creates a player and quiz object.
    public void setup(){
        frameRate(20);
        try {
            loadRooms();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Room one for test purposes
       // Room r1 = new Room("1","Images\\Backgrounds\\r5.png","Images\\Tilemaps\\r5.png",this);
        p1 = new Player(10,10,this,this);
        q1 = new Quiz(this,this);

        //Sets the first room in the list as the current room
        currentRoom=allRooms.get(0);

    }

    //Draw is run at every frame.
    //Draws the player as well as the room, and the quiz, if the quiz is active.
    public void  draw(){
       currentRoom.drawRoom();
       p1.drawPlayer();
       if(quizMode) {
           q1.drawQuiz();
       }
    }


    //PlayerMovement:
    //Player movement is handled in main, because processing's keylisteners are only accessible here.
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
        if(key =='q'||key =='Q'){

            triggerQuiz();
        }
    }

    public int getKeyCode(){
        if(keyPressed){
            return keyCode;
        }
        else return 0;
    }

    //Loads all the rooms, and puts them into the arraylist of rooms
    public void loadRooms() throws IOException {

        //Creates lists of background images and tilemaps.
        ArrayList<String> listOfBackgrounds = getImageFiles("Images/Backgrounds");
        ArrayList<String> listOfTilemaps = getImageFiles("Images/Tilemaps");

        //Makes sure that neither list is empty, before cycling through them
        assert listOfTilemaps != null;
        assert listOfBackgrounds != null;

        //Cycles through the lists, and creates new room objects for each pair of tilemaps and backgrounds
        for(int i = 0; i<listOfBackgrounds.size(); i++){
            //System.out.println(listOfBackgrounds.get(i));
            //System.out.println(listOfTilemaps.get(i));
            allRooms.add(new Room("r"+i, listOfBackgrounds.get(i),listOfTilemaps.get(i),this));
        }



    }

    //Returns an arraylist of strings, that are the relative url paths of png files the folder for a given string url
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

    //Toggles the quizmode
    public void triggerQuiz (){
        if(!quizMode){
            q1.activateQuiz(1);
            quizMode = true;
        }else{
            quizMode = false;
        }
    }

}
