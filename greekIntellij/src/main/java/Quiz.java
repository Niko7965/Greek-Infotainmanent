
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.Table;
import processing.event.KeyEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static processing.core.PConstants.CORNERS;
import static processing.core.PConstants.UP;

public class Quiz {
    PApplet core;
    int quizNumber=1;
    String question;
    String answer;
    Table questions;
    float space;
    float maxX;
    float maxY;
    float minX;
    float minY;
    boolean correct;
    boolean optionHover;

    public Quiz(PApplet core, Main main){
        this.quizNumber=quizNumber;
        this.core= core;

    }


    public void activateQuiz(int quizNumber){
        this.quizNumber = quizNumber;

    }


    public void drawQuiz(){

        //QuizBox
        core.rectMode(CORNERS);
        core.fill(0,0,255,120);
        core.strokeWeight(4);
        core.stroke(240,240,255,200);
        space=3;
        core.rect(space,core.height-core.height/4,core.width-space*2,core.height-space,7);

        //Question
        core.fill(255,255,255,255);
        core.textSize(core.width/25);
        core.textAlign(core.CENTER,core.CENTER);
        core.text(questionSplitter(questionString(0),1),core.width/2,core.height/3);

        //Option1
        optionHover=false;
        //shuffleOptions(core.random(0,4));
        quizOption(1,questionSplitter(questionString(0),2),true);
        quizOption(2,questionSplitter(questionString(0),3),false);
        quizOption(3,questionSplitter(questionString(0),4),false);
        quizOption(4,questionSplitter(questionString(0),5),false);
    }

    public void shuffleOptions(float correctPlacement){

    }

    public void quizOption(int placement, String text,boolean correct) {

        if(placement==1||placement==2){
            maxX = space * 3;
            minX = core.width / 2 - space;
        }

        if(placement==3||placement==4){
            maxX = core.width / 2 + space;
            minX = core.width-space * 4;
        }

        if(placement==1||placement==3){
            maxY = core.height - core.height / 4+space*2;
            minY = core.height - core.height / 8 - space;
        }

        if(placement==2||placement==4){
            maxY = core.height - core.height / 8 + space;
            minY = core.height- space*3;
        }

        core.fill(0, 0, 255, 120);

        if((core.mouseX<minX)&&(core.mouseX>maxX)&&(core.mouseY<minY)&&(core.mouseY>maxY)) {
            core.fill(120, 120, 255, 240);
                this.correct=correct;
                optionHover=true;
        }

        core.rectMode(CORNERS);
        core.strokeWeight(2);
        core.stroke(240,240,255,200);
        core.rect(maxX,maxY,minX,minY);

        core.textSize(core.width/42);
        core.textAlign(core.LEFT,core.TOP);
        core.fill(255,255,255,255);
        core.text(text,maxX+space,maxY+space,minX-space,minY-space);

    }




    public String questionString(int n){
        ArrayList<String> questionlist = new ArrayList<String>();
        Scanner scan = null;
        try {
            scan = new Scanner(new File("files/Questions.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNextLine()) {
            questionlist.add(scan.nextLine());
        }
        scan.close();

        //System.out.println(characterlist.get(5));
        return questionlist.get(n);
    }

    String questionSplitter(String sentence, int n){
        String[] answers = sentence.split(";");
        return answers[n];
    }

}
