import java.awt.*;
import java.awt.event.*; //graphical library and for handling events
import java.util.Arrays; // listing out buttons
import javax.swing.*;
import javax.swing.border.LineBorder.*; // modifying buttons on calculator


public class Calculator {

    int windowWidth = 600;
    int windowHeight = 650;

    JFrame jframe = new JFrame("Calculator");

    //create window using Jframe
    Calculator(){
    jframe.setVisible(true); // window is visible
     jframe.setSize(windowWidth,windowHeight);
     jframe.setLocationRelativeTo(null); // centering the window.
     jframe.setResizable(false);
     jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     jframe.setLayout(new BorderLayout()); // can place components in a particular position
    }
}
