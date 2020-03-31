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


    public Quiz(PApplet core, Main main){
        this.quizNumber=quizNumber;
        this.core= core;

    }


    public void drawQuiz(){
        core.rectMode(CORNERS);
        core.fill(0,0,255,120);
        core.strokeWeight(4);
        core.stroke(240,240,255,200);

        float qboxy = core.height-3;
        float qboxheight =core.height-core.height/4;
        float qboxwidth = core.width-2;
        core.rect(1,qboxy,qboxwidth,qboxheight,7);
        core.text("despacito",core.width/2,core.height/2);

        core.textSize(core.width/30);
        core.textAlign(core.LEFT,core.TOP);
        core.fill(255,255,255,255);
        core.text(quizGetter(0,1),qboxwidth/20,qboxy-(qboxheight/3)+5);
        //quizOption(1,answer);

    }

    public void quizOption() {

    }

    public String quizGetter(int question, int part){
        int rightanswer = Integer.parseInt(questionSplitter(questionString(question),0));
        String str = questionSplitter(questionString(question),part);
        return str;
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
