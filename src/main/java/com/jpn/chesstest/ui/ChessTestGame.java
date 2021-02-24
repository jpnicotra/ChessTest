package com.jpn.chesstest.ui;

import com.jpn.chesstest.domain.CellBoard;
import com.jpn.chesstest.domain.Game;
import com.jpn.chesstest.domain.Move;
import com.jpn.chesstest.domain.Position;
import com.jpn.chesstest.domain.pieces.Piece;
import com.jpn.chesstest.input.StringUserInput;
import com.whitehatgaming.UserInput;
import com.whitehatgaming.UserInputFile;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ChessTestGame implements Runnable {

	public static final Color DARK_CELL = new Color (220,130,130);
	public static final Color LIGHT_CELL = Color.WHITE;

	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private final JButton[][] chessBoardSquares = new JButton[8][8];
	private JPanel chessBoard;
	private static final String COLS = "ABCDEFGH";
	private Game game;
	private UserInput input;
	
	ChessTestGame() {
		initializeGui();
	}

	public final void initializeGui() {
		// set up the main GUI
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
		JToolBar tools = new JToolBar();
		tools.setFloatable(false);
		gui.add(tools, BorderLayout.PAGE_START);
		JButton chooseFile = new JButton("Open movements file");
		chooseFile.addActionListener(new OpenFileAction (this));
		tools.add(chooseFile);

		JButton quitButton = new JButton("Exit");
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				System.exit(0);
			}
		});
		tools.add(quitButton);

		tools.addSeparator();
		tools.add(new JLabel("First choose a movement file to start the game!"));

		gui.add(new JLabel("?"), BorderLayout.LINE_START);

		chessBoard = new JPanel(new GridLayout(0, 9));
		chessBoard.setBorder(new LineBorder(Color.BLACK));
		gui.add(chessBoard);

		// create the chess board squares
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int ii = 0; ii < chessBoardSquares.length; ii++) {
			for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
				JButton b = new JButton();
				b.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
				b.setHorizontalTextPosition(SwingConstants.CENTER);
				b.setMargin(buttonMargin);

				ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
				b.setIcon(icon);
				if ((jj % 2 == 1 && ii % 2 == 1)
				|| (jj % 2 == 0 && ii % 2 == 0)) {
					b.setBackground(ChessTestGame.LIGHT_CELL);
				} else {
					b.setBackground(ChessTestGame.DARK_CELL);
				}
				chessBoardSquares[jj][ii] = b;
			}
		}

		// fill the chess board
		chessBoard.add(new JLabel(""));
		// fill the top row
		for (int ii = 0; ii < 8; ii++) {
			chessBoard.add(new JLabel(COLS.substring(ii, ii + 1), SwingConstants.CENTER));
		}
		// fill the black non-pawn piece row
		for (int ii = 0; ii < 8; ii++) {
			for (int jj = 0; jj < 8; jj++) {
				if (jj == 0) {
					chessBoard.add(new JLabel("" + (8 - ii), SwingConstants.CENTER));
				}
				chessBoard.add(chessBoardSquares[jj][ii]);
			}
		}
	}

	public final void readMovesFromFile (String fileName) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class)) {
			input = new UserInputFile(fileName);

			game = context.getBean(Game.class);
			game.newGame();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public final void readMovesFromString (String moves) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class)) {
			input = new StringUserInput(moves);

			game = context.getBean(Game.class);
			game.newGame();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public final JComponent getGui() {
		return gui;
	}

	public static void main(String[] args) {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				ChessTestGame cb = new ChessTestGame();

				JFrame f = new JFrame("ChessChamp");
				f.add(cb.getGui());
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				f.setLocationByPlatform(true);

				// ensures the frame is the minimum size it needs to be
				// in order display the components within it
				f.pack();
				// ensures the minimum size is enforced.
				f.setMinimumSize(f.getSize());
				f.setVisible(true);

			}
		};
		SwingUtilities.invokeLater(r);
	}

	@Configuration
	static class Config {
		@Bean
		public Game game() {
			return new Game();
		}
	}

	private void redrawBoard (Piece pieceInCheck) {
		for (int col=0;col<8;col++) {
			for (int row=0;row<8;row++) {
				CellBoard cell = game.getBoard().getCell(new Position(col, row));
				Piece piece = cell.getPiece();
				JButton b = chessBoardSquares[row][col];
				if (piece!=null) {
					b.setText(piece.getCharCode());
					
					if (piece.equals(pieceInCheck))
						b.setForeground(Color.RED);
					else
						b.setForeground(Color.BLACK);
				}
				else { 
					b.setText(null);
				}
			}
		}
		if (pieceInCheck!=null) {
			JOptionPane.showMessageDialog(null, game.getCurrentPlayer().getSide()+" - CHECK!!!", "Check!", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	@Override
	public void run() {
		try {
			Piece pieceInCheck = null;
			redrawBoard(pieceInCheck);
			
			int[] moves = null;
			do {
				moves = input.nextMove();
				if (moves!=null) {
					Move move = new Move (moves);
					if (move!=null) {
						try {
							pieceInCheck = game.doMove(move);
							Thread.sleep(300);
							redrawBoard(pieceInCheck);
						}
						catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage(), "Ilegal movement", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
			while (moves!=null);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		System.out.println ("Thread end");
	}
}