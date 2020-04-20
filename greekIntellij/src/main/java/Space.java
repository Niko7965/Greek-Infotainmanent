import processing.core.PApplet;


//A simple class for each space in a room
public class Space {
    PApplet core;
    int id;

    //Room variables
    boolean solid;
    int interaction;
    Room r;

    public Space(int id, boolean solid, int interaction, Room room){
        this.id = id;

        this.solid=solid;
        this.interaction = interaction;
        this.r = room;

    }


    public boolean getSolid(){
        return solid;
    }


    public int getInteraction(){
        r.log.enableHint(r.id,interaction);
        return interaction;}





}
