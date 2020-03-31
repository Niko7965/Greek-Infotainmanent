//import com.sun.org.apache.xpath.internal.objects.XString;
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


    public void activateQuiz(int quizNumber){
        this.quizNumber = quizNumber;

    }


    public void drawQuiz(){

        //QuizBox
        core.rectMode(CORNERS);
        core.fill(0,0,255,120);
        core.strokeWeight(4);
        core.stroke(240,240,255,200);
        float qboxy = core.height-3;
        float qboxheight =core.height-core.height/4;
        float qboxwidth = core.width-2;
        core.rect(1,qboxy,qboxwidth,qboxheight,7);

        //Question
        core.fill(255,255,255,255);
        core.textSize(core.width/25);
        core.textAlign(core.CENTER,core.CENTER);
        core.text(QuestionSplitter(QuestionString(0),0),core.width/2,core.height/3);

        //Option1
        quizOption(1,QuestionSplitter(QuestionString(0),1));

    }

    public void quizOption(int placement, String text) {
        float qboxy = core.height-3;
        float qboxheight =core.height-core.height/4;
        float qboxwidth = core.width-2;


            core.fill(0, 0, 255, 120);


        core.rectMode(CORNERS);
        core.strokeWeight(2);
        core.stroke(240,240,255,200);
        core.rect(4,4,40,20);



        core.textSize(core.width/30);
        core.textAlign(core.LEFT,core.TOP);
        core.fill(255,255,255,255);
        core.text(text,qboxwidth/20,qboxy-(qboxheight/3)+5);

    }

    public String QuestionString(int n){
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

    String QuestionSplitter(String sentence, int n){
        String[] answers = sentence.split("-");
        //System.out.println(answers[1]);
        return answers[n];
    }

}
