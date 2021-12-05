package StarbucksClicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Starbucks extends JDialog {
    double points = 0;
    double money = 0;
    double money2 = 0;
    int x2Usage = 3;
    int doubleUsage = 5;
    int x100Usage = 5;
    boolean hasDoubleClick = false;
    boolean hasX2money = false;
    boolean hasX100point = false;
    private JPanel contentPane;
    private JButton buttonQuit;
    private JButton starbucksButton;
    private JButton buyButton;
    private JButton buyButton2;
    private JButton buyButton10;
    private JLabel pointsLabel;
    private JLabel x2Reimagining;
    private JLabel moneyLabel;
    private JLabel doubleClickLabel;
    private JLabel ProfitOrLoss;
    private JLabel x100Remaining;
    private JLabel shopLebel;


    public Starbucks() {



        x2Reimagining.setText("Remaining " + x2Usage);
        doubleClickLabel.setText("Remaining " + doubleUsage);
        x100Remaining.setText("Remaining " + x100Usage);
        ProfitOrLoss.setText("-");

        pointsLabel.setText(" Points: " + (long)points);
        moneyLabel.setText("Money: $" + (long)money);
        setContentPane(contentPane);
        setModal(true);


        buttonQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        starbucksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onStarbucks();
            }
        });
        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onBuyX2Points();
            }
        });
        buyButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onBuyDoubleClick();
            }
        });
        buyButton10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onPointX100();
            }
        });
    }
    private void onPointX100(){
        if(money>=65){
            hasX100point = true;
            x100Usage--;
            money = money - 65;
            moneyLabel.setText("Money: $" + (long)money);
            x100Remaining.setText("Remaining " + x100Usage);

            buyButton10.setEnabled(false);
            buyButton10.isEnabled();
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "You don't have money",
                    "Starbucks Clicker",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void onBuyDoubleClick(){
        if(money>=50){
            hasDoubleClick = true;
            doubleUsage--;
            money = money - 50;
            moneyLabel.setText("Money: $" + (long)money);
            doubleClickLabel.setText("Remaining " + doubleUsage);
            buyButton2.setEnabled(false);
            buyButton2.isEnabled();
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "You don't have money",
                    "Starbucks Clicker",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void onBuyX2Points(){
        if (points>=50){
            x2Usage--;
            hasX2money = true;
            points = points - 50;

            pointsLabel.setText(" Points: " + (long)points);
            x2Reimagining.setText("Remaining " + x2Usage);

            buyButton.setEnabled(false);
            buyButton.isEnabled();

        }
        else{
            JOptionPane.showMessageDialog(null,
                    "You don't have point",
                    "Starbucks Clicker",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void onStarbucks() {
        if(hasDoubleClick){
            hasDoubleClick = false;
            buyButton2.setEnabled(true);
            buyButton2.isEnabled();

            if (doubleUsage > 0){
                int min = 1;
                int max = 10;
                long result = (long) (Math.random() * (max - min)) + min;
                long result2 = (long) (Math.random() * (max - min)) + min;
                money = (long)(result + points);
                money2 = (long)(result2 + points);
                if(money + money2 > result + points){
                    ProfitOrLoss.setText("↓");
                    ProfitOrLoss.setForeground(Color.red);
                }
                if(money + money2 < result + points){
                    ProfitOrLoss.setText("↑");
                    ProfitOrLoss.setForeground(Color.green);
                }
                points++;
                points++;
                pointsLabel.setText(" Points: " + (long)points);
                moneyLabel.setText("Money: $" + (long)(money + money2));
            }
            else if (doubleUsage == 0){
                hasDoubleClick = false;
                buyButton2.setEnabled(false);
                buyButton2.isEnabled();
                JOptionPane.showMessageDialog(null,
                        "You cant use double click anymore",
                        "Starbucks Clicker",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        }
        if(hasX2money){
            hasX2money = false;
            buyButton.setEnabled(true);
            buyButton.isEnabled();
            if(x2Usage > 0){
                points = points * 2;
                pointsLabel.setText(" Points: " + (long)points);
            }
            else if (x2Usage == 0){
                hasX2money = false;
                buyButton.setEnabled(false);
                buyButton.isEnabled();
                JOptionPane.showMessageDialog(null,
                        "You cant use x2 money anymore",
                        "Starbucks Clicker",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if(hasX100point){
            hasX100point = false;
            buyButton10.setEnabled(true);
            buyButton10.isEnabled();
            if (x100Usage > 0){
                money = money - 65;
                points = points * 10;
                moneyLabel.setText("Money: $" + (long)money);
                pointsLabel.setText(" Points: " + (long)points);
            }
            else if(x100Usage == 0){
                hasX100point = false;
                buyButton10.setEnabled(false);
                buyButton10.isEnabled();
                JOptionPane.showMessageDialog(null,
                        "You cant use x100 point anymore",
                        "Starbucks Clicker",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else{

            int min = 1;
            int max = 10;
            long result = (long) (Math.random() * (max - min)) + min;
            if(money > result + points){
                ProfitOrLoss.setText("↓");
                ProfitOrLoss.setForeground(Color.red);
            }
            if(money < result + points){
                ProfitOrLoss.setText("↑");
                ProfitOrLoss.setForeground(Color.green);
            }
            money = (long)(result + points);
            points++;
            pointsLabel.setText(" Points: " + (long)points);
            moneyLabel.setText("Money: $" + (long)money);

        }
    }
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    public static void main(String[] args) {
        Starbucks dialog = new Starbucks();
        dialog.pack();
        dialog.setName("Starbucks Clicker");
        dialog.setTitle("Starbucks Clicker");
        dialog.setResizable(false);
        dialog.setVisible(true);
        System.exit(0);
    }
}
/*

buy button 1 = x2 points
buy button 2 = each click x2
buy button 10 = point x100
buy button 9 = hire clicker
buy button 3 = hire 10 clicker
buy button 4 = hire master clicker
buy button 5 = hire mouse
buy button 6 = hire programmer
buy button 7 = hire gamer
buy button 8 = hire AHK
buy button 11 = hire everyone

 */
