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

    public void drawTextBox(){
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
        core.rect(space,space,core.width-space*2,core.height-space,7);

        //Text
        core.textSize(core.width/42);
        core.textAlign(core.LEFT,core.TOP);
        core.fill(255,255,255,255);
        core.text("I've come to make an announcement. Shadow the Hedgehog's a bitch ass motherfucker. He pissed on my fucking wife. That's right, he took his hedgehog-fucking quilly dick out and he pissed on my fucking wife and he said it was 'this big' and I said 'that's disgusting'.\n" +
                "\n" +
                "So I'mma make a call-out post on my twitter.com. Shadow the Hedgehog, you got a small dick. It's the size of this walnut except way smaller, and guess what? Here's what my dong looks like!\n" +
                "\n" +
                "That's right, baby. All points, no quills, no pillows. Look at that, it looks like two balls and a bong! He fucked my wife so I'm gonna fuck the Earth. That's right, this is what you get, MY SUPER LASER PISS!\n" +
                "\n" +
                "Except I'm not gonna piss on the Earth, I'm gonna go higher. I'm pissing on THE MOOOOOON! How do you like that, Obama? I PISSED ON THE MOON, YOU IDIOT!\n" +
                "\n" +
                "You have 23 hours before the piss drrrrrropllllets hit the fucking Earth. Now get outta my fucking sight before I piss on you, too.",space * 3,space*3,core.width-space*3,core.height-space*3);


    }


}
