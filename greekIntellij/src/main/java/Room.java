import java.util.ArrayList;
import processing.core.PImage;
import processing.core.PApplet;

import static processing.core.PConstants.CORNERS;

public class Room {
    //Processing core for using processing methods
    PApplet core;

    //Id of room
    String id;

    //List of spaces in the room
    ArrayList<Space> spaces;

    //Background image of the room
    PImage background;

    public Room(String id, String ImageURL, PApplet core){
        this.id = id;
        this.core = core;
        setBackground(ImageURL);
    }

    public void setBackground(String ImageURL){
        this.background = core.loadImage("Images/Backgrounds/"+ImageURL);
    }

    public void drawRoom(){
        core.imageMode(CORNERS);
        core.image(background,0,0,core.width,core.height);
    }

}

