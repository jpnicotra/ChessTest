package com.jpn.chesstest.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OpenFileAction implements ActionListener {
    private final ChessTestGame chessTestGame;

    public OpenFileAction (ChessTestGame chessTestGame) {
        this.chessTestGame = chessTestGame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        final JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(false);
        fc.setCurrentDirectory(new File("."+File.separator+"data"));
        int retVal = fc.showOpenDialog(null);

        if (retVal==JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc.getSelectedFile();
            chessTestGame.readMovesFromFile(selectedFile.getAbsolutePath());
            Thread th = new Thread(chessTestGame);
            th.start();
        }
    }
}
