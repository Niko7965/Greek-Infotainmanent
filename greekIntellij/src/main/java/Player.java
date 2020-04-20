
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

    int interacting = 0;

    String direction = "d";

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


    //Tries interaction
    public void interact(){
        if (main.currentRoom.spaceInteraction((int) x, (int) y, direction) != 0){
            ArrayList<String> parts = main.currentRoom.interactionSplitter(main.currentRoom.getInteraction(main.currentRoom.spaceInteraction((int) x, (int) y, direction)));
            if(interacting<parts.size()){
                System.out.println(parts.get(interacting));
                interacting++;
            }
            else {
                interacting = 0;
            }

        }



    }
    //Moves the player, and sets directional sprite based on keyboard input from main.
    public void movement(){
        //System.out.println(x +","+y);
        if(interacting == 0) {
            if (x > main.currentRoom.widthInTiles - 2) {
                main.currentRoom = main.allRooms.get(main.currentRoom.id + 1);
                x = 0;
            }

            if (x < 0) {
                main.currentRoom = main.allRooms.get(main.currentRoom.id - 1);
                x = main.currentRoom.widthInTiles - 2;
            }


            if (y < 0) {
                main.currentRoom = main.allRooms.get(main.currentRoom.id - 5);
                y = main.currentRoom.heightInTiles - 2;
            }


            if (y >= main.currentRoom.heightInTiles - 2) {
                System.out.println(main.currentRoom.id);
                main.currentRoom = main.allRooms.get(main.currentRoom.id + 5);
                y = 0;
                System.out.println(main.currentRoom.id);
            }


            if (moveUp && !main.currentRoom.spaceUpSolid((int) x, (int) y)) {
                y--;
            }

            if (moveDown && !main.currentRoom.spaceDownSolid((int) x, (int) y)) {
                y++;
            }
            if (moveLeft && !main.currentRoom.spaceLeftSolid((int) x, (int) y)) {
                x--;
            }
            if (moveRight && !main.currentRoom.spaceRightSolid((int) x, (int) y)) {
                x++;
            }

            if (moveUp) {
                sprite = spriteUp;
                direction = "u";
            }

            if (moveDown) {
                sprite = spriteDown;
                direction = "d";
            }

            if (moveLeft) {
                sprite = spriteLeft;
                direction = "l";
            }

            if (moveRight) {
                sprite = spriteRight;
                direction = "r";
            }
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
