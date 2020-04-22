
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static processing.core.PConstants.*;


//Class made for the player

public class Player {
    //Core used to access processing functions
    PApplet core;
    //Main used to access main funcitons and variables
    Main main;

    //Coordinates for the player
    float x;
    float y;

    //Player HP
    int health=3;

    //Boolean for movement directions
    boolean moveUp=false;
    boolean moveDown=false;
    boolean moveRight=false;
    boolean moveLeft=false;

    boolean textMode = false;

    int interacting = 0;

    String direction = "d";

    //Default sprite, as well for directional sprites
    PImage sprite;
    PImage spriteUp;
    PImage spriteDown;
    PImage spriteLeft;
    PImage spriteRight;

    //Heart sprites
    PImage heartLife;
    PImage heartDeath;
    PImage heart;

    TextBox t1;

    //Contstructor for Player class
    public Player(float x,float y, PApplet core, Main main){
        this.x =  x;
        this.y =  y;
        this.core = core;
        this.main = main;
        t1 = new TextBox(core,main);


        //Loads sprites
        spriteUp = core.loadImage("Images/Sprites/PlayerUp.png");
        spriteDown= core.loadImage("Images/Sprites/PlayerDown.png");
        spriteLeft= core.loadImage("Images/Sprites/PlayerLeft.png");
        spriteRight= core.loadImage("Images/Sprites/PlayerRight.png");
        sprite =spriteDown;

        //Loads hearts
        heartLife = core.loadImage("Images/Sprites/heart1.png");
        heartDeath = core.loadImage("Images/Sprites/heart2.png");
    }


    //Tries interaction
    public void interact(){
        if (main.currentRoom.spaceInteraction((int) x, (int) y, direction) != 0){
            ArrayList<String> parts = main.currentRoom.interactionSplitter(main.currentRoom.getInteraction(main.currentRoom.spaceInteraction((int) x, (int) y, direction)));
            if(interacting<parts.size()){
                textBox(parts.get(interacting));
                System.out.println(parts.get(interacting));
                interacting++;
            } else {
                interacting = 0;
                textMode = false;
            }

        }

    }

    public void textBox(String text) {
        if (!textMode) {
            t1.activateText(text);
            textMode = true;
        }else{
            textMode = false;
        }
    }

    //Moves the player, and sets directional sprite based on keyboard input from main.
    public void movement(){
        //System.out.println(x +","+y);

            if (x > 24) {
                main.currentRoom = main.allRooms.get(main.currentRoom.id + 1);
                x = 0;
            }

            if (x < 0) {
                main.currentRoom = main.allRooms.get(main.currentRoom.id - 1);
                x = main.currentRoom.widthInTiles - 3;
            }


            if (y < 1) {

                main.currentRoom = main.allRooms.get(main.currentRoom.id - 5);
                y = main.currentRoom.heightInTiles - 3;

            }

            if (y >= 18) {

                main.currentRoom = main.allRooms.get(main.currentRoom.id + 5);
                y = 1;

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

    public void damage(){
        health--;
    }



    //Old function to load the sprite, might be removed later
    public void setSprite(String ImageURL){
        this.sprite = core.loadImage("Images/Sprites/"+ImageURL);
    }

    //Draws the player based on coordinates
    public void drawPlayer(){
        //Flytter spilleren
        if((!main.quizMode)&&(!textMode)) {
            movement();
        }

        //Tegner spilleren
        core.imageMode(CORNERS);
        core.image(sprite,x*(core.width/25),(y+1)*(core.height/18),x*(core.width/25)+(core.width/25),((y+1)*(core.height/18))-(core.height/9));
        //System.out.println(x+" "+y);

    }

    public void drawLife(){
        //Tegner spillerens liv

            core.imageMode(CORNER);
            core.tint(255, 190);
            if (health < 1) {
                heart = heartDeath;
            } else {
                heart = heartLife;
            }
            ;
            core.image(heart, core.width * 1 / 60, core.width / 50, core.width / 16, core.width / 16);
            if (health < 2) heart = heartDeath;
            core.image(heart, core.width * 5 / 60, core.width / 50, core.width / 16, core.width / 16);
            if (health < 3) heart = heartDeath;
            core.image(heart, core.width * 9 / 60, core.width / 50, core.width / 16, core.width / 16);
            core.tint(255);
       
        if(interacting > 0){
            t1.drawTextBox();
        }

    }


}
