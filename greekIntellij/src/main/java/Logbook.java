import processing.core.PApplet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static processing.core.PConstants.CORNERS;

public class Logbook {
    ArrayList<String> allHints;
    ArrayList<Hint> playerHints = new ArrayList<Hint>();
    ArrayList<String> hintLines = new ArrayList<String>();
    String hintUrl = "Files/Hints.txt";
    Main main;
    PApplet core;
    boolean logbookActive = false;
    String text ="";
    int page = 0;


    public Logbook(Main main){

        allHints = loadAllHints();
        setPlayerHints();
        this.main = main;
        core = main;
        //devEnableAll();
        //devPrintPlayerHints();
    }



    public ArrayList<String> loadAllHints(){
        ArrayList<String> hintList = new ArrayList<String>();
        Scanner scan = null;
        try {
            scan = new Scanner(new File(hintUrl));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scan != null;
        while (scan.hasNextLine()) {
            hintList.add(scan.nextLine());
        }
        scan.close();

        return hintList;
    }

    public void setPlayerHints(){
        for (String allHint : allHints) {
            Hint hint = new Hint(allHint,false);
            playerHints.add(hint);
        }
    }


    public void devEnableAll(){
        for(int i =0; i<25; i++){
            for(int j = 0; j<4; j++){
                //System.out.println("enabling:"+i+","+j);
                enableHint(i,j+1);
            }
        }
    }

    public void hintsToLines(){
        hintLines = new ArrayList<String>();
        for (Hint playerHint : playerHints) {
            if (playerHint.active) {
                ArrayList<String> lines = lineSplitter(playerHint.getContent());
                hintLines.addAll(lines);
            }

        }
    }

    ArrayList<String> lineSplitter(String sentence){
        String[] lineList = sentence.split(";");
        return new ArrayList<String>(Arrays.asList(lineList));
    }

    public void devPrintPlayerHints(){
        for(int i = 0; i<playerHints.size(); i++){
            System.out.println("#"+i+": "+playerHints.get(i).getContent());
        }
    }

    public void enableHint(int roomNumber, int interactionNumber){
        if(interactionNumber>0) {
            int i = (roomNumber * 5) + (interactionNumber);
            playerHints.get(i).setActive(true);
            //System.out.println(roomNumber + ";" + interactionNumber + " " + playerHints.get(i).getContent());
        }
    }

    public void getHints(int n){
        hintsToLines();
        text = "Dette er en logbog hvor alle de informationer du finder vil blive skrevet ned";
        text = text+ "Du kan bruge piletasterne til at navigere i logbogen \n \n";
        for (int i = 0; i <18;i++) {
            if(i<hintLines.size()){
                //System.out.println(hintLines.get(i+n));
                if (!hintLines.get(i+n).equals("x")) {
                    text = text + hintLines.get((i+n));
                    text = text+ "\n";
                    //System.out.println(hintLines.get(i+n)+"x");

                }




            }


        }





    }

    public void drawTextBox(){
        //TextBox
        core.rectMode(CORNERS);
        core.fill(0,0,255,120);
        core.strokeWeight(4);
        core.stroke(240,240,255,200);
        int space=3;
        core.rect(space,core.height-core.height,core.width-space,core.height-space,7);

        //Text
        core.textSize(core.width/42);
        core.textAlign(core.LEFT,core.TOP);
        core.fill(255,255,255,255);
        core.text(text,space*3,core.height-core.height+space,core.width-space*3,core.height-space);
    }


}
