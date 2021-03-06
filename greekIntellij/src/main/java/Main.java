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
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Main extends PApplet {

    //Quiz player, and logbook objects.
    Player p1;
    Quiz q1;
    Logbook l1;

    //SFX
    SoundController correct;
    SoundController wrong;
    SoundController interact;


    //The room that is currently active
    Room currentRoom;

    //A list of all rooms
    ArrayList<Room> allRooms = new ArrayList<Room>();

    //Booleans that controls whether a quiz is active
    public Boolean quizMode = false;
    public Boolean bossMode = false;

    public long startTime;

    //The main class, which instantiates the processing main.
    public static void main(String[] args){
        PApplet.main("Main");
    }

    //Processing settings. Currently sets the size of the screen
    public void settings(){
        int scale = 1;
        size(625*scale,450*scale);

    }

    //Setup is a processing function run on startup.
    //Loads the rooms, and creates a player and quiz object.
    public void setup(){
        frameRate(20);
        l1 = new Logbook(this);

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
        currentRoom=allRooms.get(7);
        try {
            correct = new SoundController("files/Sfx/Correct.wav");
            wrong = new SoundController("files/Sfx/wrong2.wav");
            interact = new SoundController("files/Sfx/Interaction.wav");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        startTime = System.nanoTime();


    }

    //Draw is run at every frame.
    //Draws the player as well as the room, and the quiz, if the quiz is active.
    public void  draw(){
       currentRoom.drawRoom();
       p1.drawPlayer();


       if(bossMode){q1.drawSphinx();}
       if(quizMode) {
           q1.drawQuiz();
       }

        if(l1.logbookActive){
            l1.drawTextBox();
        }else{
            p1.drawHUD();
        }
    }


    //PlayerMovement:
    //Player movement is handled in main, because processing's keylisteners are only accessible here.
    public void keyPressed(){
        if (key == CODED) {
            if (keyCode == UP) {
                p1.moveUp =true;
            }
            if (keyCode == DOWN) {
                p1.moveDown =true;
            }
            if (keyCode == RIGHT) {
                p1.moveRight =true;
            }
            if (keyCode == LEFT) {
                p1.moveLeft =true;
            }

        }

        if(key == ' '){
            p1.interact();

        }

        if(key == 'l'||key =='L'){
            if(bossMode==false && p1.interacting==0) {
                l1.logbookActive = !l1.logbookActive;
                l1.page = 0;
                l1.getHints(l1.page);
                System.out.println(getTimeMin());
            }
        }
    }

    public void keyReleased(){
        if (key == CODED) {
            if (keyCode == UP) {
                p1.moveUp =false;
            }
            if (keyCode == DOWN) {
                p1.moveDown =false;
            }
            if (keyCode == RIGHT) {
                if(l1.logbookActive&&l1.page<l1.hintLines.size()-18){
                    l1.page++;
                    l1.getHints(l1.page);
                }
                p1.moveRight =false;
            }
            if (keyCode == LEFT) {
                if(l1.logbookActive&&l1.page>0){
                    l1.page--;
                    l1.getHints(l1.page);
                }
                p1.moveLeft =false;
            }

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
        ArrayList<String> listOfInteractions = getInteractionFiles("files/Interactions");

        //Makes sure that neither list is empty, before cycling through them
        assert listOfTilemaps != null;
        assert listOfBackgrounds != null;

        //Cycles through the lists, and creates new room objects for each pair of tilemaps and backgrounds
        for(int i = 0; i<listOfBackgrounds.size(); i++){
            //System.out.println(listOfBackgrounds.get(i));
            //System.out.println(listOfTilemaps.get(i));
            //System.out.println(listOfInteractions.get(i));
            allRooms.add(new Room(i, listOfBackgrounds.get(i),listOfTilemaps.get(i),listOfInteractions.get(i),this, this));
        }

    }

    //Returns an arraylist of strings, that are the relative url paths of png files the folder for a given string url
    public ArrayList<String> getImageFiles(String url) {
        File folder = new File(url);
        String[] extensions = new String[]{"png"};
        List<File> files = (List<File>) FileUtils.listFiles(folder,extensions,true);
        ArrayList<String> imagePaths = new ArrayList<String>();
        for(File file : files){
            imagePaths.add(file.getPath());
        }
        return imagePaths;

    }


    public ArrayList<String> getInteractionFiles(String url) {
        File folder = new File(url);
        String[] extensions = new String[]{"txt"};
        List<File> files = (List<File>) FileUtils.listFiles(folder,extensions,true);
        ArrayList<String> paths = new ArrayList<String>();
        for(File file : files){
            paths.add(file.getPath());
        }
        return paths;

    }



    //Toggles the quizmode
    public void triggerQuiz (){
        if(!quizMode){
            q1.activateQuiz(round(random(0,14)),random(0,4));
            quizMode = true;
        }
    }





    public void mouseClicked() {
        if (q1.optionHover) {
            if (q1.correct) {
                try {
                    correct.play();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
                q1.success=true;
                q1.results=true;

            }
            if (!q1.correct) {
                try {
                    wrong.play();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
                q1.success=false;
                q1.results=true;
                p1.damage();
            }
        }
    }


    public void gameOver(){
        p1.health=3;
        p1.x=10;
        p1.y=10;
        currentRoom=allRooms.get(7);
    }

    public long getTimeMin(){
        long endTime = System.nanoTime();
        return (((endTime - startTime)/(60000))/1000000);
    }

}


