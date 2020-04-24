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

    public void sphinxMonologue(int step){
        if(step==1){text="Kun de viseste må træde ind i Athæneum.";}
        if(step==2){text="Hvis De vil komme forbi mig, skal du først vise dit værd.";}

        if(step==4){text="De er tydeligvis ikke værdig.";}
        if(step==5){text="Drag ud i verden og find ny viden.";}
        if(step==6){text="Uddyb dine horisonter og kom først tilbage, når De tror, at De nu er parat.";}

        if(step==8){text="Imponerende. Det lykkedes Dem at svare rigtigt på mine gåder.";}
        if(step==9){text="De er i sandhed værdig til at træde ind i Athenæum.";}

    }



    public void drawTextBox(){
        //Dark backdrop
        core.rectMode(CORNER);
        core.noStroke();
        core.fill(0,0,0,40);
        core.rect(0,0,core.width,core.height);

        //TextBox
        core.rectMode(CORNERS);
        core.fill(0,0,255,120);
        core.strokeWeight(4);
        core.stroke(240,240,255,200);
        space=3;
        core.rect(space,core.height-core.height/4,core.width-space*2,core.height-space,7);

        //Text
        core.textSize(core.width/42);
        core.textAlign(core.LEFT,core.TOP);
        core.fill(255,255,255,255);
        core.text(text,space*3,core.height-core.height/4+space,core.width-space*3,core.height-space);
    }


}
