
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

import static processing.core.PConstants.CORNERS;
import static processing.core.PConstants.UP;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Player {

    PApplet core;

    float x;
    float y;
    boolean moveUp=false;
    boolean moveDown=false;
    boolean moveRight=false;
    boolean moveLeft=false;
    PImage sprite;
    Main main;

    public Player(float x,float y,String spritePath, PApplet core, Main main){
        this.x =  x;
        this.y =  y;
        this.core = core;
        setSprite(spritePath);
        this.main = main;

    }

    public void movement(){
        if(moveUp&&!main.r1.SpaceUpSolid((int) x, (int) y)){
            y--;
        }
        if(moveDown&&!main.r1.SpaceDownSolid((int) x, (int) y)){
            y++;
        }
        if(moveLeft && !main.r1.SpaceLeftSolid((int) x, (int) y)){
            x--;
        }
        if(moveRight && !main.r1.SpaceRightSolid((int) x, (int) y)){
            x++;
        }

    }

    public void setSprite(String ImageURL){
        this.sprite = core.loadImage("Images/Sprites/"+ImageURL);
    }
    public void drawPlayer(){
        movement();
        core.imageMode(CORNERS);
        core.image(sprite,x*(core.width/25),(y+1)*(core.height/18),x*(core.width/25)+(core.width/25),((y+1)*(core.height/18))-(core.height/9));
        //System.out.println(x+" "+y);
    }

}
