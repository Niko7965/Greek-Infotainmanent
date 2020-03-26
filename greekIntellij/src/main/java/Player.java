import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

import static processing.core.PConstants.CORNERS;
import static processing.core.PConstants.UP;


public class Player {
    //Proceesing core
    PApplet core;

    float x;
    float y;
    boolean moveUp=false;
    boolean moveDown=false;
    boolean moveRight=false;
    boolean moveLeft=false;
    PImage sprite;
    Main main;

    public Player(int x,int y,String spritePath, PApplet core, Main main){
        this.x = x;
        this.y = y;
        this.core = core;
        setSprite(spritePath);
        this.main = main;

    }

    public void movement(){
        if(moveUp){
            y--;
        }
        if(moveDown){
            y++;
        }
        if(moveLeft){
            x--;
        }
        if(moveRight){
            x++;
        }
    }

    public void setSprite(String ImageURL){
        this.sprite = core.loadImage("Images/Sprites/"+ImageURL);
    }
    public void drawPlayer(){
        movement();
        core.imageMode(CORNERS);
        core.image(sprite,x*(core.width/25),y*(core.height/18),x*(core.width/25)+(core.width/25),y*(core.height/18)+(core.height/9));
        //System.out.println(x+" "+y);
    }

}
