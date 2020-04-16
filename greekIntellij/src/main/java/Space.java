import processing.core.PApplet;


//A simple class for each space in a room
public class Space {
    PApplet core;
    int id;

    //Room variables
    boolean solid;
    int interaction;


    public Space(int id, boolean solid, int interaction){
        this.id = id;

        this.solid=solid;
        this.interaction = interaction;

    }


    public boolean getSolid(){
        return solid;
    }
    public int getInteraction(){return interaction;}





}
