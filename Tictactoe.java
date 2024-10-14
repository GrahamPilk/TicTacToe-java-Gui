package tictactoe;

import javax.swing.*;
import java.awt.GridLayout;

public class Tictactoe extends JPanel{

    char playersign = 'x';
    JButton[] jButtons = new JButton[9];

    int p1Wins;
    int p2Wins;

    public Tictactoe() {

        GridLayout ticTacToeGridLayout = new GridLayout(3,3);
        setLayout(ticTacToeGridLayout);

        initializeBoard();

    }

    public void initializeBoard(){

        for(int i = 0; i <= 8; i++){

            jButtons[i] = new JButton();

            jButtons[i].setText("");

            jButtons[i].addActionListener(e -> {
                JButton clickedButton = (JButton) e.getSource();
                clickedButton.setText(String.valueOf(playersign));
                clickedButton.setEnabled(false);

                if(playersign == 'x'){
                    playersign = 'o';
                } else {
                    playersign = 'x';
                }

                showWinner();
            });
            add(jButtons[i]);
        }

    }

    public void showWinner(){

        if(checkForWinner()){
            if(playersign == 'x'){
                playersign = '0';
            } else {
                playersign = 'x';
            }
            JOptionPane jOptionPane = new JOptionPane();
            int dialog = JOptionPane.showConfirmDialog(jOptionPane, "Game Over. " + "The Winner is " + playersign + " ", "Result", JOptionPane.DEFAULT_OPTION);
            if(dialog == JOptionPane.OK_OPTION){
                System.exit(0);
            }
        } else if (checkIfMatchDraw()) {
            JOptionPane jOptionPane = new JOptionPane();
            int dialog = JOptionPane.showConfirmDialog(jOptionPane, "Game Draw", "Result", JOptionPane.DEFAULT_OPTION);
            if(dialog == JOptionPane.OK_OPTION){
                System.exit(0);
            }
        }

        

    }

    public boolean checkIfMatchDraw(){

        boolean gridsFull = true;
        for(int i = 0; i < 9; i++){
            if(jButtons[i].getText().equals("")){
                gridsFull = false;
            }
        }
        return gridsFull;
    }

    public boolean checkForWinner() {
        return checkAllRows() || checkAllColumns() || checkTheDiagonals();
    }

    public boolean checkAllRows(){
        int i = 0; 
        for(int j = 0; j < 3; j++){
            if((jButtons[i].getText().equals(jButtons[i+1].getText())) && jButtons[i].getText().equals(jButtons[i+2].getText()) && !jButtons[i].getText().equals("")){
                return true;
            }
            i = i+3;
        }
        return false;
    }

    public boolean checkAllColumns() {
        int i = 0;
        for (int j = 0; j < 3; j++) {
          if (jButtons[i].getText().equals(jButtons[i + 3].getText()) && jButtons[i].getText().equals(jButtons[i + 6].getText()) && !jButtons[i].getText().equals("")) {
            return true;
          }
          i++;
        }
        return false;
      }

      public boolean checkTheDiagonals() {
        if (jButtons[0].getText().equals(jButtons[4].getText()) && jButtons[0].getText().equals(jButtons[8].getText()) && !jButtons[0].getText().equals(""))
          return true;
        else
          return jButtons[2].getText().equals(jButtons[4].getText()) && jButtons[2].getText().equals(jButtons[6].getText()) && !jButtons[2].getText().equals("");
      }

    public static void main (String[] args){

        JFrame jFrame = new JFrame("Tic Tac Toe Game");

        jFrame.getContentPane().add(new Tictactoe());
        jFrame.setBounds(500, 500, 600, 550);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);

    }

}