import processing.core.PApplet;

import static processing.core.PConstants.CORNER;
import static processing.core.PConstants.CORNERS;

public class TextBox {

    int space = 3;

    String text;

    PApplet core;
    Main main;

    public TextBox(PApplet core, Main main){
        this.core= core;

        this.main = main;

    }

    public void activateText(String text){

        this.text=text;
    }

    public void drawTextBox(){
        //Dark backdrop
        core.rectMode(CORNER);
        core.noStroke();
        core.fill(0,0,0,40);
        core.rect(0,0,core.width,core.height);

        //QuizBox
        core.rectMode(CORNERS);
        core.fill(0,0,255,120);
        core.strokeWeight(4);
        core.stroke(240,240,255,200);
        space=3;
        core.rect(space,space,core.width-space*2,core.height-space,7);

        //Text
        core.textSize(core.width/42);
        core.textAlign(core.LEFT,core.TOP);
        core.fill(255,255,255,255);
        core.text(text,space * 3,space*3,core.width-space*3,core.height-space*3);


    }


}
