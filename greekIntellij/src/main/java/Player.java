import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

import static processing.core.PConstants.CORNERS;

public class Player {
    PApplet core;

    int x;
    int y;
    PImage sprite;
    Main main;

    public Player(int x,int y,String spritePath, PApplet core, Main main){
        this.x = x;
        this.y = y;
        this.core = core;
        setSprite(spritePath);
        this.main = main;
    }



    public void setSprite(String ImageURL){
        this.sprite = core.loadImage("Images/Sprites/"+ImageURL);
    }
    public void drawPlayer(){
        core.imageMode(CORNERS);
        core.image(sprite,x*(core.width/25),y*(core.height/20),x*(core.width/25)+(core.width/25),y*(core.height/20)+(core.height/9));
        System.out.println(main.getKeyCode());


    }

}
