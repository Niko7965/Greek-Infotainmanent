
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.Table;
import processing.event.KeyEvent;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static processing.core.PConstants.*;

public class Quiz {
    PApplet core;
    Main main;
    float space;
    float maxX;
    float maxY;
    float minX;
    float minY;
    float correctPlacement;
    int quizNumber = 1;
    int resultsTimer;
    int level;
    int maxLevel;
    boolean results;
    boolean correct;
    boolean success;
    boolean optionHover;
    PImage sphinx;

    public Quiz(PApplet core, Main main) {
        this.core = core;

        this.main = main;
        sphinx = core.loadImage("Images/Sprites/sphinx.jpg");
    }

    public void activateBoss() {
        maxLevel = core.round(core.random(10,questionNumbers()));
        level = maxLevel-10;
        activateQuiz(level, core.random(0, 4));
    }

    public void activateQuiz(int quizNumber, float correctPlacement) {
        this.correctPlacement = correctPlacement;
        this.quizNumber = quizNumber;
        results = false;
        resultsTimer = 50;
    }



    public void drawSphinx() {
        //Sphinx billede
        if (main.bossMode) {
            core.image(sphinx, 0, 0, core.width, core.height);
        }
    }

    public void drawQuiz() {

        //Dark backdrop
        core.rectMode(CORNER);
        core.noStroke();
        core.fill(0, 0, 0, 40);
        core.rect(0, 0, core.width, core.height);

        //QuizBox
        core.rectMode(CORNERS);
        core.fill(0, 0, 255, 120);
        core.strokeWeight(4);
        core.stroke(240, 240, 255, 200);
        space = 3;
        core.rect(space, core.height - core.height / 4, core.width - space * 2, core.height - space, 7);

        //Question
        if (!results) {
            core.fill(255, 255, 255, 255);
            core.textSize(core.width / 25);
            core.textAlign(core.CENTER, core.CENTER);
            core.text(questionSplitter(questionString(quizNumber), 1), 0, core.height - core.height / 5, core.width, core.height / 2);
        } else {
            core.textSize(core.width / 10);
            core.textAlign(core.CENTER, core.CENTER);
            if (correct) {
                core.fill(20, 255, 20, 255);
                core.text("RIGTIGT", core.width / 2, core.height / 2);
            } else {
                core.fill(255, 20, 20, 255);
                if (main.p1.health < 1) {
                    core.text("GAME OVER", core.width / 2, core.height / 2);
                } else {
                    core.text("FORKERT", core.width / 2, core.height / 2);
                }
            }
        }

        //Option
        optionHover = false;
        shuffleOptions();

        //Check answer
        if (results) {
            if (resultsTimer > 0) {
                resultsTimer--;
            } else {
                if (main.bossMode == true) {
                    if (main.p1.health == 0) {
                        main.quizMode = false;
                        main.p1.interacting = 4;
                        main.p1.t1.sphinxMonologue(4);
                    } else {

                        if (level < maxLevel) {
                            level++;
                            activateQuiz(level, core.random(0, 4));
                        } else {
                            main.p1.interacting = 8;
                            main.p1.t1.sphinxMonologue(8);
                            main.quizMode = false;
                        }
                    }
                } else {
                    main.quizMode = false;
                    if (main.p1.health < 1) {
                        main.gameOver();
                    }
                }
            }
        }
    }


    public void shuffleOptions() {
        if (correctPlacement > 3) {
            quizOption(1, questionSplitter(questionString(quizNumber), 2), true);
            quizOption(2, questionSplitter(questionString(quizNumber), 3), false);
            quizOption(3, questionSplitter(questionString(quizNumber), 4), false);
            quizOption(4, questionSplitter(questionString(quizNumber), 5), false);
        } else {
            if (correctPlacement > 2) {
                quizOption(2, questionSplitter(questionString(quizNumber), 2), true);
                quizOption(1, questionSplitter(questionString(quizNumber), 3), false);
                quizOption(4, questionSplitter(questionString(quizNumber), 4), false);
                quizOption(3, questionSplitter(questionString(quizNumber), 5), false);
            } else {
                if (correctPlacement > 1) {
                    quizOption(3, questionSplitter(questionString(quizNumber), 2), true);
                    quizOption(4, questionSplitter(questionString(quizNumber), 3), false);
                    quizOption(2, questionSplitter(questionString(quizNumber), 4), false);
                    quizOption(1, questionSplitter(questionString(quizNumber), 5), false);
                } else {
                    quizOption(4, questionSplitter(questionString(quizNumber), 2), true);
                    quizOption(3, questionSplitter(questionString(quizNumber), 3), false);
                    quizOption(1, questionSplitter(questionString(quizNumber), 4), false);
                    quizOption(2, questionSplitter(questionString(quizNumber), 5), false);
                }
            }
        }
    }

    public void quizOption(int placement, String text, boolean correct) {

        if (placement == 1 || placement == 2) {
            maxX = space * 3;
            minX = core.width / 2 - space;
        }

        if (placement == 3 || placement == 4) {
            maxX = core.width / 2 + space;
            minX = core.width - space * 4;
        }
        if (placement == 1 || placement == 3) {
            maxY = core.height - core.height / 4 + space * 2;
            minY = core.height - core.height / 8 - space;
        }

        if (placement == 2 || placement == 4) {
            maxY = core.height - core.height / 8 + space;
            minY = core.height - space * 3;
        }

        core.fill(0, 0, 255, 120);

        if (!results) {
            if ((core.mouseX < minX) && (core.mouseX > maxX) && (core.mouseY < minY) && (core.mouseY > maxY)) {
                core.fill(120, 120, 255, 240);
                this.correct = correct;
                optionHover = true;
            }
        } else {
            if (correct) {
                core.fill(20, 220, 20, 240);
            }
        }

        core.rectMode(CORNERS);
        core.strokeWeight(2);
        core.stroke(240, 240, 255, 200);
        core.rect(maxX, maxY, minX, minY);

        core.textSize(core.width / 42);
        core.textAlign(core.LEFT, core.TOP);
        core.fill(255, 255, 255, 255);
        core.text(text, maxX + space, maxY + space, minX - space, minY - space + 10);

    }

    public String questionString(int n) {
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

    int questionNumbers(){
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

        return questionlist.size();
}

    String questionSplitter(String sentence, int n){
        String[] answers = sentence.split(";");
        return answers[n];
    }

}
