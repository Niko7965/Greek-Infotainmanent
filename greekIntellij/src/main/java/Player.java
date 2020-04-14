
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


//Class made for the player

public class Player {
    //Core used to access processing functions
    PApplet core;
    //Main used to access main funcitons and variables
    Main main;

    //Coordinates for the player
    float x;
    float y;

    //Boolean for movement directions
    boolean moveUp=false;
    boolean moveDown=false;
    boolean moveRight=false;
    boolean moveLeft=false;

    //Default sprite, as well for directional sprites
    PImage sprite;
    PImage spriteUp;
    PImage spriteDown;
    PImage spriteLeft;
    PImage spriteRight;



    //Contstructor for Player class
    public Player(float x,float y, PApplet core, Main main){
        this.x =  x;
        this.y =  y;
        this.core = core;

        this.main = main;

        //Loads sprites
        spriteUp = core.loadImage("Images/Sprites/PlayerUp.png");
        spriteDown= core.loadImage("Images/Sprites/PlayerDown.png");
        spriteLeft= core.loadImage("Images/Sprites/PlayerLeft.png");
        spriteRight= core.loadImage("Images/Sprites/PlayerRight.png");
        sprite =spriteDown;
    }


    //Moves the player, and sets directional sprite based on keyboard input from main.
    public void movement(){
        System.out.println(x+","+y+",r"+main.currentRoom.id);

        if(x > main.currentRoom.widthInTiles-2){
            main.currentRoom = main.allRooms.get(main.currentRoom.id+1);
            x=0;
        }

        if( x < 0){
            main.currentRoom = main.allRooms.get(main.currentRoom.id-1);
            x=main.currentRoom.widthInTiles-2;
        }


        if( y < 0){
            main.currentRoom = main.allRooms.get(main.currentRoom.id-5);
            y=main.currentRoom.heightInTiles-2;
        }


        if( y > main.currentRoom.heightInTiles-2){
            main.currentRoom = main.allRooms.get(main.currentRoom.id+5);
            y=0;
        }


        if(moveUp&&!main.currentRoom.SpaceUpSolid((int) x, (int) y)){
            sprite=spriteUp;
            y--;
        }
        if(moveDown&&!main.currentRoom.SpaceDownSolid((int) x, (int) y)){
            sprite=spriteDown;
            y++;
        }
        if(moveLeft && !main.currentRoom.SpaceLeftSolid((int) x, (int) y)){
            sprite =spriteLeft;
            x--;
        }
        if(moveRight && !main.currentRoom.SpaceRightSolid((int) x, (int) y)){
            sprite =spriteRight;
            x++;
        }

    }


    //Old function to load the sprite, might be removed later
    public void setSprite(String ImageURL){
        this.sprite = core.loadImage("Images/Sprites/"+ImageURL);
    }

    //Draws the player based on coordinates
    public void drawPlayer(){
        movement();
        core.imageMode(CORNERS);
        core.image(sprite,x*(core.width/25),(y+1)*(core.height/18),x*(core.width/25)+(core.width/25),((y+1)*(core.height/18))-(core.height/9));
        //System.out.println(x+" "+y);
    }

}
