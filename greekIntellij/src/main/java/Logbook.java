import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Logbook {
    ArrayList<String> allHints;
    ArrayList<Hint> playerHints = new ArrayList<Hint>();
    String hintUrl = "Files/Hints.txt";


    public Logbook(){
        playerHints.add(new Hint("Dette er en logbog, hvor alle hints du finder, vil blive skrevet ned",true));
        allHints = loadAllHints();
        setPlayerHints();
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



    public void enableHint(int roomNumber, int interactionNumber){
        playerHints.get((roomNumber*5)+interactionNumber).setActive(true);

    }

    public void getHints(){
        for (int i = 0; i <playerHints.size() ; i++) {
            if(playerHints.get(i).active) {
                System.out.println(playerHints.get(i).getContent());
            }

        }
    }


}
