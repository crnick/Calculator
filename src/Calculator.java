import java.awt.*;
import java.awt.event.*; //graphical library and for handling events
import java.util.Arrays; // listing out buttons
import javax.swing.*; //JFrame
import javax.swing.border.LineBorder;



public class Calculator {

    int windowWidth = 600;
    int windowHeight = 650;

    //part of java.awt package
    Color customLightGray= new Color(212,212,210);
    Color customDarkGray= new Color(80,80,80);
    Color customBlack= new Color(28,28,28);
    Color customOrange= new Color(255,149,0);


    String []buttonValues  = {
            "AC","+/-","%","÷",
            "7","8","9","*",
            "4","5","6","-",
            "1","2","3","+",
            "0",".","√","="
    };

    String[] rightSymbols = {"+","*","-","=","÷"};
    String[] topSymbols = {"AC","+/-","%"};

    JFrame jframe = new JFrame("Calculator"); // main calculator window
    //put text inside label and then place label inside panel and then place it inside window
    JLabel displayLabel = new JLabel();

    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    String A = "0";
    String operator = null;
    String B = null;

    //create window using Jframe
    Calculator(){
         jframe.setVisible(true); // window is visible
         jframe.setSize(windowWidth,windowHeight);
         jframe.setLocationRelativeTo(null); // centering the window.
         jframe.setResizable(false);
         jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         jframe.setLayout(new BorderLayout()); // can place components in a particular position

        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setFont(new Font("Arial",Font.PLAIN,80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);


        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        jframe.add(displayPanel,BorderLayout.NORTH);


        buttonsPanel.setLayout(new GridLayout(5,4));
        buttonsPanel.setBackground(customBlack);
        jframe.add(buttonsPanel);



        for(int i =0;i<buttonValues.length;i++){
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial",Font.PLAIN,30));
            button.setText(buttonValue);
            button.setFocusable(false); //gets rid of the rectangle around text.
            button.setBorder(new LineBorder(customBlack));


            //Arrays have 3 categories of buttons
            if(Arrays.asList(topSymbols).contains(buttonValues)){
                button.setBackground(customLightGray);
                button.setForeground(customBlack);

            }else if (Arrays.asList(rightSymbols).contains(buttonValues)){
                button.setBackground(customOrange);
                button.setForeground(Color.WHITE);
            }else{
                //digit or number
                button.setBackground(customDarkGray);
                button.setForeground(Color.WHITE);
            }
            buttonsPanel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource(); //from where the click comes from, source would be jbutton
                    //identify which button was clicked
                    String buttonValue = button.getText();

                    if(Arrays.asList(rightSymbols).contains(buttonValue)){
                            if(buttonValue == "="){
                                    if(A !=null){
                                        B = displayLabel.getText();
                                        double numA = Double.parseDouble(A);

                                        double numB = Double.parseDouble(B);
                                        if (operator == "+") {
                                            displayLabel.setText(removeZeroDecimal(numA+numB));
                                        }
                                        else if (operator == "-") {
                                            displayLabel.setText(removeZeroDecimal(numA-numB));
                                        }
                                        else if (operator == "×") {
                                            displayLabel.setText(removeZeroDecimal(numA*numB));
                                        }
                                        else if (operator == "÷") {
                                            displayLabel.setText(removeZeroDecimal(numA/numB));
                                        }
                                        clearAll();
                                    }
                            }else if("+-/*".contains(buttonValue)){
                                    if(operator == null){
                                        A = displayLabel.getText();
                                        displayLabel.setText("0");
                                        B = "0";
                                    }
                                    operator = buttonValue;
                            }
                    }else if (Arrays.asList(topSymbols).contains(buttonValue)){
                             if(buttonValue == "AC"){
                                    clearAll();
                                    displayLabel.setText("0");
                             }else if (buttonValue == "+/-"){
                                    double numDisplay = Double.parseDouble(displayLabel.getText());
                                    numDisplay*=-1;
                                    displayLabel.setText(removeZeroDecimal(numDisplay));
                             }else if (buttonValue == ""){
                                 double numDisplay = Double.parseDouble(displayLabel.getText());
                                 numDisplay /= 100;
                                 displayLabel.setText(removeZeroDecimal(numDisplay));
                             }
                    }else{
                        if(buttonValue == "."){
                            //either you have decimal place once or don't have it
                            if(!displayLabel.getText().contains(buttonValue)){
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }else if ("0123456789".contains(buttonValue)){
                            if(displayLabel.getText()=="0"){
                                displayLabel.setText(buttonValue);
                            }else{
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }
                    }
                }
            });
        }


    }
    void clearAll(){
        A = "0";
        operator = null;
        B = null;
    }

    String removeZeroDecimal(double numDisplay){
        if(numDisplay % 1 == 0){
            return Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
    }

}
