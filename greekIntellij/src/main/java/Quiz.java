
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.Table;
import processing.event.KeyEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static processing.core.PConstants.*;

public class Quiz {
    PApplet core;
    int quizNumber=1;
    Main main;
    float space;
    float maxX;
    float maxY;
    float minX;
    float minY;
    int resultsTimer;
    boolean results;
    boolean correct;
    boolean success;
    boolean optionHover;
    float correctPlacement;

    public Quiz(PApplet core, Main main){
        this.core= core;

        this.main = main;

    }

    public void activateQuiz(int quizNumber,float correctPlacement){
        this.correctPlacement=correctPlacement;
        this.quizNumber = quizNumber;
        results=false;
        resultsTimer=50;
    }


    public void drawQuiz(){

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
        core.rect(space,core.height-core.height/4,core.width-space*2,core.height-space,7);

        //Question
        if(results==false) {
            core.fill(255, 255, 255, 255);
            core.textSize(core.width / 25);
            core.textAlign(core.CENTER, core.CENTER);
            core.text(questionSplitter(questionString(quizNumber), 1), core.width / 4, core.height / 6, core.width - core.width / 4, core.height - core.height / 4);
        }else{
            core.textSize(core.width / 10);
            core.textAlign(core.CENTER, core.CENTER);
            if(correct==true){
                core.fill(20, 255, 20, 255);
                core.text("RIGTIGT!", core.width / 2, core.height / 2);
            }else {
                core.fill(255, 20, 20, 255);
                core.text("FORKERT!", core.width / 2, core.height /2);
            }
        }

        //Option1
        optionHover=false;
        shuffleOptions();

        if(results==true) {
            if (resultsTimer > 0) {
                resultsTimer--;
            } else {
                main.quizMode = false;
            }

        }
    }


    public void shuffleOptions(){
        if(correctPlacement>3){
            quizOption(1,questionSplitter(questionString(quizNumber),2),true);
            quizOption(2,questionSplitter(questionString(quizNumber),3),false);
            quizOption(3,questionSplitter(questionString(quizNumber),4),false);
            quizOption(4,questionSplitter(questionString(quizNumber),5),false);
        }else{
            if(correctPlacement>2){
                quizOption(2,questionSplitter(questionString(quizNumber),2),true);
                quizOption(1,questionSplitter(questionString(quizNumber),3),false);
                quizOption(4,questionSplitter(questionString(quizNumber),4),false);
                quizOption(3,questionSplitter(questionString(quizNumber),5),false);
            }else{
                if(correctPlacement>1){
                    quizOption(3,questionSplitter(questionString(quizNumber),2),true);
                    quizOption(4,questionSplitter(questionString(quizNumber),3),false);
                    quizOption(2,questionSplitter(questionString(quizNumber),4),false);
                    quizOption(1,questionSplitter(questionString(quizNumber),5),false);
                }else{
                    quizOption(4,questionSplitter(questionString(quizNumber),2),true);
                    quizOption(3,questionSplitter(questionString(quizNumber),3),false);
                    quizOption(1,questionSplitter(questionString(quizNumber),4),false);
                    quizOption(2,questionSplitter(questionString(quizNumber),5),false);
                }
            }
        }
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

        if(!results) {
            if ((core.mouseX < minX) && (core.mouseX > maxX) && (core.mouseY < minY) && (core.mouseY > maxY)) {
                core.fill(120, 120, 255, 240);
                this.correct = correct;
                optionHover = true;
            }
        }else{
            if(correct){
                core.fill(20, 220, 20, 240);
            }
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
