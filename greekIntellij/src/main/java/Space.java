import processing.core.PApplet;

public class Space {
    PApplet core;
    int id;

    //Obstacle obs;
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
