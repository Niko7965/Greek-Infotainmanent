
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


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

    //Player attempts
    int attempts = 0;

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

    //Heart sprites
    PImage heartLife;
    PImage heartDeath;
    PImage heart;

    //Question Mark
    PImage questionMark;

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

        questionMark = core.loadImage("Images/Sprites/questionMark.png");

    }


    //Tries interaction
    public void interact(){
        main.l1.enableHint(main.currentRoom.id,main.currentRoom.spaceInteraction((int) x, (int) y, direction));
        if(!main.bossMode) {
            if (x < 25 && !(x < 0) && y < 18 && !(y < 0)) {
                if (main.currentRoom.spaceInteraction((int) x, (int) y, direction) != 0) {
                    ArrayList<String> parts = main.currentRoom.interactionSplitter(main.currentRoom.getInteraction(main.currentRoom.spaceInteraction((int) x, (int) y, direction)));

                    if(interacting ==0){
                      try {
                          main.interact.play();
                      } catch (IOException e) {
                          e.printStackTrace();
                      } catch (LineUnavailableException e) {
                          e.printStackTrace();
                      } catch (UnsupportedAudioFileException e) {
                          e.printStackTrace();
                      }
                    }

                    if (interacting < parts.size()) {
                        t1.activateText(parts.get(interacting));
                        System.out.println(parts.get(interacting));
                        interacting++;
                    } else {
                        interacting = 0;
                    }

                }
            }
        }else{
            if(!main.quizMode) {
                interacting++;
                t1.sphinxMonologue(interacting);
                if (interacting == 3) {
                    interacting = 0;
                    attempts++;
                    main.q1.activateBoss();
                    main.quizMode = true;
                }
                if (interacting == 7) {
                    interacting = 0;
                    main.bossMode = false;
                    main.gameOver();
                }
                if (interacting == 11) {
                    interacting = 0;
                    main.bossMode = false;
                    main.gameOver();
                }

            }
        }

    }

    public void triggerBoss (){
        if(!main.bossMode){

            interacting = 1;
            t1.sphinxMonologue(interacting);
            main.bossMode = true;
        }
    }

    //Moves the player, and sets directional sprite based on keyboard input from main.
    //If the player hits the sides of the map, they get moved to another map
    public void movement() {
        if(!main.l1.logbookActive) {
            //System.out.println(x +","+y);

            //Right Boundary
            if (x > 24) {
                main.currentRoom = main.allRooms.get(main.currentRoom.id + 1);
                x = 0;
            }

            //Left Boundary
            if (x < 0) {
                main.currentRoom = main.allRooms.get(main.currentRoom.id - 1);
                x = main.currentRoom.widthInTiles - 3;
            }

            //Top Boundary
            if (y < 1) {
                main.currentRoom = main.allRooms.get(main.currentRoom.id - 5);
                y = main.currentRoom.heightInTiles - 3;
            }

            //Bottom Boundary
            if (y >= 18) {
                main.currentRoom = main.allRooms.get(main.currentRoom.id + 5);
                y = 1;
            }


            //The next four functions moves you in a given direction, if the space isn't solid
            //Up
            if (moveUp && !main.currentRoom.spaceUpSolid((int) x, (int) y)) {
                y--;
            }

            //Down
            if (moveDown && !main.currentRoom.spaceDownSolid((int) x, (int) y)) {
                y++;
            }

            //Left
            if (moveLeft && !main.currentRoom.spaceLeftSolid((int) x, (int) y)) {
                x--;
            }

            //Right
            if (moveRight && !main.currentRoom.spaceRightSolid((int) x, (int) y)) {
                x++;
            }


            //The next four functions set your sprite to face the direction the player is pressing.
            //Up
            if (moveUp) {
                sprite = spriteUp;
                direction = "u";
            }

            //Down
            if (moveDown) {
                sprite = spriteDown;
                direction = "d";
            }

            //Left
            if (moveLeft) {
                sprite = spriteLeft;
                direction = "l";
            }

            //Right
            if (moveRight) {
                sprite = spriteRight;
                direction = "r";
            }

            //Starts the bossfight if the player is standing in the right place.
            if (main.currentRoom.id == 2) {
                if (main.currentRoom.bossTerritory((int) x, (int) y)) {
                    triggerBoss();
                }
            }
        }
    }

    //A function that removes 1hp from the players healthbar
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
        if((!main.quizMode)&&(!main.bossMode)&&(interacting==0)) {
            movement();
        }

        //Draws the plauer
        core.imageMode(CORNERS);
        core.image(sprite,x*(core.width/25),(y+1)*(core.height/18),x*(core.width/25)+(core.width/25),((y+1)*(core.height/18))-(core.height/9));
        //System.out.println(x+" "+y);

        //Draws an exclamation point over the players head, if they're in a place where they can interact
        if(x<25 && !(x<0) && y<18 && !(y<0)){
            if (main.currentRoom.spaceInteraction((int) x, (int) y, direction) != 0) {
                core.image(questionMark,x*(core.width/25),((y+1)*(core.height/18)-core.height/8),x*(core.width/25)+(core.width/25),((y+1)*(core.height/18))-(core.height/5));
            }
            }

    }

    //Draws the HUD, currently used for HP-bar
    public void drawHUD(){

        if(interacting > 0){
            t1.drawTextBox();
        }
            core.imageMode(CORNER);
            core.tint(255, 190);
            if (health < 1) {
                heart = heartDeath;
            } else {
                heart = heartLife;
            }

            core.image(heart, core.width * 1 / 60, core.width / 50, core.width / 16, core.width / 16);
            if (health < 2) heart = heartDeath;
            core.image(heart, core.width * 5 / 60, core.width / 50, core.width / 16, core.width / 16);
            if (health < 3) heart = heartDeath;
            core.image(heart, core.width * 9 / 60, core.width / 50, core.width / 16, core.width / 16);
            core.tint(255);
    }


}
