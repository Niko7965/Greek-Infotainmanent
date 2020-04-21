import processing.core.PApplet;

public class TextBox {

    String text;

    int textNumber;

    PApplet core;
    Main main;

    public TextBox(PApplet core, Main main){
        this.core= core;

        this.main = main;

    }

    public void activateQuiz(int textNumber){

        this.textNumber = textNumber;
    }

    public void drawTextBox(){


    }


}
