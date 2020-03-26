import com.sun.org.apache.xpath.internal.objects.XString;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

import static processing.core.PConstants.CORNERS;
import static processing.core.PConstants.UP;

public class Quiz {
    PApplet core;

    int quizNumber=1;

    String question;

    String answer;


    public Quiz(int quizNumber, PApplet core, Main main){

        question= "Hvilken digter boede på øen Lesbos";

        answer="Sappho";

        this.quizNumber=quizNumber;

        this.core= core;

    }


    public void drawQuiz(){
        core.rectMode(CORNERS);
        core.fill(0,0,255,120);
        core.strokeWeight(4);
        core.stroke(240,240,255,200);
        core.rect(1,core.height-3,core.width-2,core.height-core.height/4,7);

        quizOption(1,answer);

    }

    public void quizOption(int placement, String text) {


    }


}
