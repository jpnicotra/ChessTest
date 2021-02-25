package com.jpn.chesstest.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jpn.chesstest.domain.BoardGame;
import com.jpn.chesstest.domain.CellBoard;
import com.jpn.chesstest.domain.Move;
import com.jpn.chesstest.domain.Position;
import com.jpn.chesstest.domain.chess.ChessGame;
import com.jpn.chesstest.domain.chess.pieces.Piece;
import com.jpn.chesstest.input.StringUserInput;
import com.whitehatgaming.UserInput;
import com.whitehatgaming.UserInputFile;

/**
 * Main class that shows a GUI of the chessboard and shows movements and allows
 * users to select movement file through UI.
 * 
 * @author jnicotra
 * @since 1.0
 */
public class ChessTestGame implements Runnable {

	public static Long delay = 1000l;
	public static final int cellSize = 75;
	public static final Color DARK_CELL = new Color(220, 130, 130);
	public static final Color LIGHT_CELL = Color.WHITE;

	private final JPanel mainPanel = new JPanel(new BorderLayout(3, 3));
	private final JLabel movLabel = new JLabel (".");
	private final JButton[][] chessBoardSquares = new JButton[8][8];
	private static final String COLS = "ABCDEFGH";
	private BoardGame game;
	private UserInput input;

	public ChessTestGame() {
		buildBoard();
	}

	/**
	 * Create main user interface with Toolbar and Chessboard User can click on
	 * "Open movements file" button to upload a movement file and start game
	 * 
	 * @see OpenFileAction
	 */
	public final void buildBoard() {
		mainPanel.setBorder(new EmptyBorder(0, 0, 20, 20));
		JToolBar tools = new JToolBar();
		tools.setFloatable(false);
		JButton chooseFile = new JButton("Open movements file");
		chooseFile.addActionListener(new OpenFileAction(this));
		tools.add(chooseFile);

		JButton quitButton = new JButton("Exit");
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				System.exit(0);
			}
		});
		tools.add(quitButton);

		tools.addSeparator(new Dimension(50, 10));
		tools.add(new JLabel("First choose a movement file to start the game!"));
		tools.addSeparator(new Dimension(50, 10));
		tools.add(new JLabel("Movement delay: "));
		JFormattedTextField jDelay = new JFormattedTextField(new DecimalFormat("0"));
		jDelay.setValue(delay);
		jDelay.setColumns(2);
		jDelay.setSize(70, 20);
		jDelay.addPropertyChangeListener("value", new PropertyChangeListener () {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getNewValue()==null || ((Long)evt.getNewValue()).longValue() <=0) {
					jDelay.setValue(evt.getOldValue());
				}
				else {
					ChessTestGame.delay = (Long)evt.getNewValue();
				}
			}
			
		});
		tools.add(jDelay);
		mainPanel.add(tools, BorderLayout.PAGE_START);

		JPanel chessBoard = new JPanel(new GridLayout(0, 9));
		mainPanel.add(chessBoard);

		movLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
		movLabel.setHorizontalAlignment(SwingConstants.CENTER);
		movLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		movLabel.setSize(400, 100);

		mainPanel.add(movLabel, BorderLayout.SOUTH);

		// Draw chessboard
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int ii = 0; ii < chessBoardSquares.length; ii++) {
			for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
				JButton b = new JButton();
				b.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 60));
				b.setHorizontalTextPosition(SwingConstants.CENTER);
				b.setMargin(buttonMargin);

				ImageIcon icon = new ImageIcon(new BufferedImage(cellSize, cellSize, BufferedImage.TYPE_INT_ARGB));
				b.setIcon(icon);
				if ((jj % 2 == 1 && ii % 2 == 1) || (jj % 2 == 0 && ii % 2 == 0)) {
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

	/**
	 * Get's Game bean and start UserInputFile with chosen file
	 * 
	 * @param fileName Chosen movement file
	 */
	public final void readMovesFromFile(String fileName) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class)) {
			input = new UserInputFile(fileName);

			game = context.getBean(BoardGame.class);
			game.newGame();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get's Game bean and start UserInputFile with String movements variable
	 * 
	 * @param moves String movements
	 */
	public final void readMovesFromString(String moves) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class)) {
			input = new StringUserInput(moves);

			game = context.getBean(BoardGame.class);
			game.newGame();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final JComponent getMainPanel() {
		return mainPanel;
	}

	public static void main(String[] args) {
		ChessTestGame cb = new ChessTestGame();

		JFrame f = new JFrame("ChessChamp");
		f.add(cb.getMainPanel());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLocationByPlatform(true);

		// Resize according to the created components
		f.pack();
		// Set this as the minimum size
		f.setMinimumSize(f.getSize());
		f.setVisible(true);
	}

	@Configuration
	static class Config {
		@Bean
		public BoardGame game() {
			return new ChessGame();
		}
	}

	/**
	 * This method draw each piece in the board and also display if one of the king's are in check status
	 * @param pieceInCheck Piece in check or null
	 */
	private void redrawBoard(Piece pieceInCheck) {
		for (int col = 0; col < 8; col++) {
			for (int row = 0; row < 8; row++) {
				CellBoard cell = game.getBoard().getCell(new Position(col, row));
				Piece piece = cell.getPiece();
				JButton b = chessBoardSquares[row][col];
				if (piece != null) {
					b.setText(piece.getCharCode());

					if (piece.equals(pieceInCheck))
						b.setForeground(Color.RED);
					else
						b.setForeground(Color.BLACK);
				} else {
					b.setText(null);
				}
			}
		}
		if (pieceInCheck != null) {
			JOptionPane.showMessageDialog(null, game.getCurrentPlayer().getSide() + " - CHECK!!!", "Check!",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Automatic movement thread. Every x milliseconds asks for next movement.
	 */
	@Override
	public void run() {
		try {
			Piece pieceInCheck = null;
			redrawBoard(pieceInCheck);
			
			int[] moves = null;
			do {
				moves = input.nextMove();
				if (moves != null) {
					Move move = new Move(moves);
					if (move != null) {
						try {
							movLabel.setText(game.getCurrentPlayer().getSide()+" - "+move.toString());
							pieceInCheck = game.doMove(move);
							Thread.sleep(ChessTestGame.delay);
							redrawBoard(pieceInCheck);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage(), "Ilegal movement",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			} while (moves != null);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}