import processing.core.PApplet;
import processing.core.PImage;

import static processing.core.PConstants.CORNERS;

public class Player {
    PApplet core;

    int x;
    int y;
    PImage sprite;
    int spriteWidth = 25;

    public Player(int x,int y,String spritePath, PApplet core){
        this.x = x;
        this.y = y;
        this.core = core;
        setSprite(spritePath);
    }

    public void setSprite(String ImageURL){
        this.sprite = core.loadImage("Images/Sprites/"+ImageURL);
    }
    public void drawPlayer(){
        core.imageMode(CORNERS);
        core.image(sprite,x*(core.width/25),y*(core.height/20),x*(core.width/25)+(core.width/25),y*(core.height/20)+(core.height/9));
    }

}
