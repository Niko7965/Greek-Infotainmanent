import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import processing.core.PImage;
import processing.core.PApplet;


import static processing.core.PConstants.CORNERS;


//Class for the rooms
public class Room {
    //Processing core for using processing methods
    PApplet core;


    Logbook log;


    //Id of room
    int id;

    //List of spaces in the room
    ArrayList<Space> spaces = new ArrayList<Space>();

    //Background image of the room
    PImage background;
    PImage tileMap;

    //Url of interactions
    String interactionUrl;

    //Dimentions of room given in tiles
    int widthInTiles = 27;
    int heightInTiles = 20;

    int tileSize;

    //Constructor for room
    public Room(int id, String ImageURL,String tileMapURL, String interactionUrl, PApplet core, Main main){
        this.id = id;
        this.core = core;
        setTileMap(tileMapURL);
        setBackground(ImageURL);
        fillSpaces();
        this.interactionUrl = interactionUrl;
        this.log = main.l1;
        tileSize=2*background.width/27;
    }

    //Sets the background image as well as tilemap
    public void setBackground(String ImageURL){
        this.background = core.loadImage(ImageURL);
    }
    public void setTileMap(String tileMapURL){this.tileMap =core.loadImage(tileMapURL);}

    //Draws the room
    public void drawRoom(){

        core.imageMode(CORNERS);
        core.image(background, -tileSize, -tileSize,core.width+tileSize,core.height+tileSize);

    }


    //A function that fills the arraylist "Spaces" with spaces based on the color of pixels in a tilemap.
    //Red pixels are solid
    //Later on blue pixels will be interactable etc.

    public void fillSpaces(){
        int black = -16777216;
        int red = -65536;
        int white = -1;
        int blue = -16776961;
        int yellow = -256;
        int pink = -65281;

        for (int i=0; i<widthInTiles*heightInTiles; i++){
            int c =tileMap.get(xFromId(i),yFromId(i));

            if(c == black) {
                spaces.add(new Space(spaces.size(), true,false, 0,this));
            }

            else if(c == yellow){
                spaces.add(new Space(spaces.size(),true,false,1,this));
            }
            else if(c == pink){
                spaces.add(new Space(spaces.size(),false,true,1,this));
            }

            else {
                spaces.add(new Space(spaces.size(), false,false, 0,this));
            }
        }
        //evalSpaces();
    }

    public String getInteraction(int n){
        ArrayList<String> interactionList = new ArrayList<String>();
        Scanner scan = null;
        try {
            scan = new Scanner(new File(interactionUrl));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scan != null;
        while (scan.hasNextLine()) {
            interactionList.add(scan.nextLine());
        }
        scan.close();

        return interactionList.get(n);
    }

    ArrayList<String> interactionSplitter(String sentence){
        String[] lineList = sentence.split(";");

        return new ArrayList<String>(Arrays.asList(lineList));
    }


    public void evalSpaces(){
        for (int i=0; i<widthInTiles*heightInTiles; i++){
            System.out.println(spaces.get(i).getSolid());
        }
    }


    public int xFromId(int id){
        return id%widthInTiles;
    }

    public int yFromId(float id){
        return PApplet.floor(id/widthInTiles);
    }

    public int coordToId(int x, int y){
        return (y+1)*widthInTiles+(x+1);
    }

    public boolean spaceUpSolid(int x, int y){
        int tile = coordToId(x,y)-widthInTiles;
        return spaces.get(tile).getSolid();
    }

    public boolean spaceDownSolid(int x, int y){
        int tile = coordToId(x,y)+widthInTiles;
        return spaces.get(tile).getSolid();

    }

    public boolean spaceLeftSolid(int x, int y){
        int tile = coordToId(x,y)-1;
        return spaces.get(tile).getSolid();
    }

    public boolean spaceRightSolid(int x, int y){
        int tile = coordToId(x,y)+1;
        return spaces.get(tile).getSolid();
    }

    public boolean bossTerritory(int x, int y){
        int tile = coordToId(x,y);
        return spaces.get(tile).getBoss();
    }


    public int spaceInteraction(int x, int y, String direction){
        if(direction.equals("u")){
            int tile = coordToId(x,y)-widthInTiles;

            return spaces.get(tile).getInteraction();
        }
        if(direction.equals("d")){
            int tile = coordToId(x,y)+widthInTiles;
            return spaces.get(tile).getInteraction();
        }
        if(direction.equals("l")){
            int tile = coordToId(x,y)-1;
            return spaces.get(tile).getInteraction();
        }
        if(direction.equals("r")){
            int tile = coordToId(x,y)+1;
            return spaces.get(tile).getInteraction();
        }
        else{
            System.out.println("Space interaction direction error");
            int tile = coordToId(x,y)-widthInTiles;
            return spaces.get(tile).getInteraction();
        }




    }



}

