// Vincente Sequeira
//6/15/2023
// The Minesweeper program aims to provide an interactive game 
// where players can enjoy the classic puzzle experience of uncovering tiles on a grid while avoiding hidden mines.
package minesweeper;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.MouseEvent;

public class MinesweeperGUI extends javax.swing.JFrame {

    private JButton[][] buttons;
    private boolean[][] mines;
    private boolean[][] revealed;
    private boolean[][] flagged;
    private int rows;
    private int columns;
    private int minesCount;
    private int cellsToReveal;

    public MinesweeperGUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton68 = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        infoButton = new javax.swing.JButton();
        endButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        xaxisInput = new javax.swing.JTextField();
        xaxisLabel = new javax.swing.JLabel();
        yaxisInput = new javax.swing.JTextField();
        yaxisLabel = new javax.swing.JLabel();

        jButton68.setText("jButton68");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        infoButton.setText("What?");
        infoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoButtonActionPerformed(evt);
            }
        });

        endButton.setText("Exit");
        endButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endButtonActionPerformed(evt);
            }
        });

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        xaxisLabel.setText("X Axis");

        yaxisLabel.setText("Y Axis");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(resetButton)
                    .addComponent(infoButton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(startButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(endButton, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(xaxisInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(xaxisLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(yaxisInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yaxisLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(331, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(infoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resetButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xaxisLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xaxisInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(yaxisLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(yaxisInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void endButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endButtonActionPerformed

        System.exit(0);
    }//GEN-LAST:event_endButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        resetGame();// Resets the game
        xaxisInput.setText(""); // Clears x axis input text field
        yaxisInput.setText(""); // Clears y axis input text field
    }//GEN-LAST:event_resetButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        startGame();
    }//GEN-LAST:event_startButtonActionPerformed
    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {
        JButton button = (JButton) evt.getSource();
        int row = Integer.parseInt(button.getClientProperty("row").toString());
        int column = Integer.parseInt(button.getClientProperty("column").toString());

        if (SwingUtilities.isRightMouseButton(evt)) {
            flagCell(row, column);
        } else {
            revealCell(row, column);
        }
    }
    private void buttonMouseClicked(java.awt.event.MouseEvent evt) {                                     
    if (evt.getButton() == MouseEvent.BUTTON3) {
        JButton button = (JButton) evt.getSource();
        int row = Integer.parseInt(button.getClientProperty("row").toString());
        int column = Integer.parseInt(button.getClientProperty("column").toString());
        flagCell(row, column);
    }
}         
    private void resetGame() {
    if (buttons != null) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                buttons[row][column].setText("");
                buttons[row][column].setEnabled(true);
                buttons[row][column].setBackground(null);
            }
        }
    }
    mines = null;
    revealed = null;
    flagged = null;
}
private void startGame() {
    resetGame();
    try {
        rows = Integer.parseInt(xaxisInput.getText());
        columns = Integer.parseInt(yaxisInput.getText());
        minesCount = (rows * columns) / 8;

        buttons = new JButton[rows][columns];
        mines = new boolean[rows][columns];
        revealed = new boolean[rows][columns];
        flagged = new boolean[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                JButton button = new JButton();
                button.putClientProperty("row", row);
                button.putClientProperty("column", column);
                button.setPreferredSize(new Dimension(30, 30));
                button.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        buttonActionPerformed(evt);
                    }
                });
                button.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        buttonMouseClicked(evt);
                    }
                });
                buttons[row][column] = button;
                gridPanel.add(button);
            }
        }

        mineCountLabel.setText("Mines: " + minesCount);

        int count = 0;
        while (count < minesCount) {
            int randomRow = (int) (Math.random() * rows);
            int randomColumn = (int) (Math.random() * columns);
            if (!mines[randomRow][randomColumn]) {
                mines[randomRow][randomColumn] = true;
                count++;
            }
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Please enter valid dimensions.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
private void flagCell(int row, int column) {
    if (revealed[row][column]) {
        return;
    }

    flagged[row][column] = !flagged[row][column];

    if (flagged[row][column]) {
        buttons[row][column].setText("F");
    } else {
        buttons[row][column].setText("");
    }
}

private void revealCell(int row, int column) {
    if (revealed[row][column]) {
        return;
    }

    revealed[row][column] = true;
    buttons[row][column].setEnabled(false);
    buttons[row][column].setBackground(Color.GRAY);

    if (mines[row][column]) {
        buttons[row][column].setText("X");
        revealMines();
        JOptionPane.showMessageDialog(this, "Game over!", "Game Over", JOptionPane.ERROR_MESSAGE);
        resetGame();
    } else {
        int count = countAdjacentMines(row, column);
        if (count > 0) {
            buttons[row][column].setText(Integer.toString(count));
        } else {
            revealAdjacentCells(row, column);
        }

        if (checkGameWon()) {
            revealMines();
            JOptionPane.showMessageDialog(this, "Congratulations! You won the game!", "Game Won", JOptionPane.INFORMATION_MESSAGE);
            resetGame();
        }
    }
}


private void revealAdjacentCells(int row, int column) {
    for (int r = row - 1; r <= row + 1; r++) {
        for (int c = column - 1; c <= column + 1; c++) {
            if (isValidCell(r, c) && !revealed[r][c]) {
                revealCell(r, c);
            }
        }
    }
}

private boolean isValidCell(int row, int column) {
    return row >= 0 && row < rows && column >= 0 && column < columns;
}

private boolean checkGameWon() {
    for (int row = 0; row < rows; row++) {
        for (int column = 0; column < columns; column++) {
            if (!mines[row][column] && !revealed[row][column]) {
                return false;
            }
        }
    }
    return true;
} 

private int countAdjacentMines(int row, int column) {
    int count = 0;
    for (int r = row - 1; r <= row + 1; r++) {
        for (int c = column - 1; c <= column + 1; c++) {
            if (isValidCell(r, c) && mines[r][c]) {
                count++;
            }
        }
    }
    return count;
}

private void revealMines() {
    for (int row = 0; row < rows; row++) {
        for (int column = 0; column < columns; column++) {
            if (mines[row][column]) {
                buttons[row][column].setEnabled(false);
                buttons[row][column].setBackground(Color.RED);
                buttons[row][column].setText("X");
            }
        }
    }
}

    private void infoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoButtonActionPerformed
        JOptionPane.showMessageDialog(this, "Welcome to Minesweeper!\n\n"
                + "Left-click to reveal a cell.\n"
                + "Right-click to toggle a flag on a suspected mine.\n"
                + "The goal is to reveal all non-mine cells without triggering a mine.\n"
                + "Good luck and have fun!");
    }//GEN-LAST:event_infoButtonActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MinesweeperGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MinesweeperGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MinesweeperGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MinesweeperGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MinesweeperGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton endButton;
    private javax.swing.JButton infoButton;
    private javax.swing.JButton jButton68;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton startButton;
    private javax.swing.JTextField xaxisInput;
    private javax.swing.JLabel xaxisLabel;
    private javax.swing.JTextField yaxisInput;
    private javax.swing.JLabel yaxisLabel;
    // End of variables declaration//GEN-END:variables
}
