import processing.core.PApplet;


//A simple class for each space in a room
public class Space {
    PApplet core;
    int id;

    //Room variables
    boolean solid;
    boolean interactable;


    public Space(int id, boolean solid, boolean interactable){
        this.id = id;

        this.solid=solid;
        this.interactable=interactable;

    }


    public boolean getSolid(){
        return solid;
    }




}
