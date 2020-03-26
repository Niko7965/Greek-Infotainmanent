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
    ArrayList<Space> spaces = new ArrayList<Space>();

    //Background image of the room
    PImage background;
    PImage tileMap;

    int widthInTiles = 27;
    int heightInTiles = 20;


    public Room(String id, String ImageURL,String tileMapURL, PApplet core){
        this.id = id;
        this.core = core;
        setTileMap(tileMapURL);
        setBackground(ImageURL);
        fillSpaces();
    }

    public void setBackground(String ImageURL){
        this.background = core.loadImage("Images/Backgrounds/"+ImageURL);
    }
    public void setTileMap(String tileMapURL){this.tileMap =core.loadImage("Images/TileMaps/"+tileMapURL);}

    public void drawRoom(){
        core.imageMode(CORNERS);
        core.image(background,0,0,core.width,core.height);

    }



    public void fillSpaces(){
        int black = -16777216;
        int red = -65536;
        int white = -1;
        int blue = -16776961;


        for (int i=0; i<widthInTiles*heightInTiles; i++){
            int c =tileMap.get(xFromId(i),yFromId(i));


            System.out.println(c);

            if(c == black) {
                spaces.add(new Space(spaces.size(), true, false));
            }
            else {
                spaces.add(new Space(spaces.size(), false, false));

            }
        }
        evalSpaces();
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
        return core.floor(id/widthInTiles);
    }

    public int coordToId(int x, int y){
        return (y+1)*widthInTiles+(x+1);
    }

    public boolean SpaceUpSolid(int x, int y){
        int tile = coordToId(x,y)-widthInTiles;
        return spaces.get(tile).getSolid();
    }

    public boolean SpaceDownSolid(int x, int y){
        int tile = coordToId(x,y)+widthInTiles;
        return spaces.get(tile).getSolid();

    }

    public boolean SpaceLeftSolid(int x, int y){
        int tile = coordToId(x,y)-1;
        return spaces.get(tile).getSolid();
    }

    public boolean SpaceRightSolid(int x, int y){
        int tile = coordToId(x,y)+1;
        return spaces.get(tile).getSolid();
    }



}

