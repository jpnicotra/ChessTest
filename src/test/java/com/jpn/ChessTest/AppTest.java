package com.jpn.ChessTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jpn.chesstest.domain.BoardGame;
import com.jpn.chesstest.domain.Move;
import com.jpn.chesstest.domain.chess.ChessGame;
import com.jpn.chesstest.domain.chess.pieces.Piece;
import com.jpn.chesstest.exceptions.AnotherPieceInCellException;
import com.jpn.chesstest.exceptions.InvalidMovementException;
import com.jpn.chesstest.exceptions.KingInCheckException;
import com.jpn.chesstest.exceptions.MovingOpponentPieceException;
import com.jpn.chesstest.exceptions.NoPieceInCellException;
import com.whitehatgaming.UserInputFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AppTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);

	@Autowired
	private BoardGame game;

	@Before
	public void setUp() {
		Assert.assertNotNull(game);
	}

	@Test
	public void testCheckMateGame() {
		Piece pieceInCheck = null;
		game.newGame();
		Assert.assertEquals(2, game.getPlayers().size());
		Assert.assertEquals(16, game.getPlayers().get(0).getSide().getPieces().size());
		Assert.assertEquals(16, game.getPlayers().get(1).getSide().getPieces().size());
		
		try {
			UserInputFile input = new UserInputFile("data"+File.separator+"junit_checkmate.txt");
			int moves[]=input.nextMove();
			while (moves!=null && moves.length==4) {
				pieceInCheck = game.doMove(new Move(moves));
				moves=input.nextMove();
			}
		}
		catch (Exception e) {
			Assert.fail(e.toString());
		}
		
		Assert.assertNotNull(pieceInCheck);
		

	}

	@Test (expected = InvalidMovementException.class)
	public void testInvalidGame() throws FileNotFoundException, IOException, Exception {
		Piece pieceInCheck = null;
		game.newGame();
		Assert.assertEquals(2, game.getPlayers().size());
		Assert.assertEquals(16, game.getPlayers().get(0).getSide().getPieces().size());
		Assert.assertEquals(16, game.getPlayers().get(1).getSide().getPieces().size());
		
		UserInputFile input = new UserInputFile("data"+File.separator+"junit_sample-moves-invalid.txt");
		int moves[]=input.nextMove();
		while (moves!=null && moves.length==4) {
			pieceInCheck = game.doMove(new Move(moves));
			moves=input.nextMove();
		}
		Assert.assertNotNull(pieceInCheck);
	}

	@Test 
	public void testSamplesMovesGame() throws FileNotFoundException, IOException, Exception {
		Piece pieceInCheck = null;
		game.newGame();
		Assert.assertEquals(2, game.getPlayers().size());
		Assert.assertEquals(16, game.getPlayers().get(0).getSide().getPieces().size());
		Assert.assertEquals(16, game.getPlayers().get(1).getSide().getPieces().size());
		
		UserInputFile input = new UserInputFile("data"+File.separator+"junit_sample-moves.txt");
		int moves[]=input.nextMove();
		while (moves!=null && moves.length==4) {
			pieceInCheck = game.doMove(new Move(moves));
			moves=input.nextMove();
		}
		Assert.assertNull(pieceInCheck);
	}

	@Test (expected = InvalidMovementException.class)
	public void testInvalidMovesPawn() throws FileNotFoundException, IOException, Exception {
		Piece pieceInCheck = null;
		game.newGame();
		Assert.assertEquals(2, game.getPlayers().size());
		Assert.assertEquals(16, game.getPlayers().get(0).getSide().getPieces().size());
		Assert.assertEquals(16, game.getPlayers().get(1).getSide().getPieces().size());
		
		UserInputFile input = new UserInputFile("data"+File.separator+"junit_pawn_invalid.txt");
		int moves[]=input.nextMove();
		while (moves!=null && moves.length==4) {
			pieceInCheck = game.doMove(new Move(moves));
			moves=input.nextMove();
		}
		Assert.assertNull(pieceInCheck);
	}

	@Test (expected = InvalidMovementException.class)
	public void testInvalidMovesBishop() throws FileNotFoundException, IOException, Exception {
		Piece pieceInCheck = null;
		game.newGame();
		Assert.assertEquals(2, game.getPlayers().size());
		Assert.assertEquals(16, game.getPlayers().get(0).getSide().getPieces().size());
		Assert.assertEquals(16, game.getPlayers().get(1).getSide().getPieces().size());
		
		UserInputFile input = new UserInputFile("data"+File.separator+"junit_pawn_invalid.txt");
		int moves[]=input.nextMove();
		while (moves!=null && moves.length==4) {
			pieceInCheck = game.doMove(new Move(moves));
			moves=input.nextMove();
		}
		Assert.assertNull(pieceInCheck);
	}

	@Test (expected = InvalidMovementException.class)
	public void testInvalidMovesKing() throws FileNotFoundException, IOException, Exception {
		Piece pieceInCheck = null;
		game.newGame();
		Assert.assertEquals(2, game.getPlayers().size());
		Assert.assertEquals(16, game.getPlayers().get(0).getSide().getPieces().size());
		Assert.assertEquals(16, game.getPlayers().get(1).getSide().getPieces().size());
		
		UserInputFile input = new UserInputFile("data"+File.separator+"junit_king_invalid.txt");
		int moves[]=input.nextMove();
		while (moves!=null && moves.length==4) {
			pieceInCheck = game.doMove(new Move(moves));
			moves=input.nextMove();
			
		}
		Assert.assertNull(pieceInCheck);
	}

	@Test (expected = InvalidMovementException.class)
	public void testInvalidMovesQueen() throws FileNotFoundException, IOException, Exception {
		Piece pieceInCheck = null;
		game.newGame();
		Assert.assertEquals(2, game.getPlayers().size());
		Assert.assertEquals(16, game.getPlayers().get(0).getSide().getPieces().size());
		Assert.assertEquals(16, game.getPlayers().get(1).getSide().getPieces().size());
		
		UserInputFile input = new UserInputFile("data"+File.separator+"junit_queen_invalid.txt");
		int moves[]=input.nextMove();
		while (moves!=null && moves.length==4) {
			pieceInCheck = game.doMove(new Move(moves));
			moves=input.nextMove();
		}
		Assert.assertNull(pieceInCheck);
	}

	@Test (expected = InvalidMovementException.class)
	public void testInvalidMovesRook() throws FileNotFoundException, IOException, Exception {
		Piece pieceInCheck = null;
		game.newGame();
		Assert.assertEquals(2, game.getPlayers().size());
		Assert.assertEquals(16, game.getPlayers().get(0).getSide().getPieces().size());
		Assert.assertEquals(16, game.getPlayers().get(1).getSide().getPieces().size());
		
		UserInputFile input = new UserInputFile("data"+File.separator+"junit_rook_invalid.txt");
		int moves[]=input.nextMove();
		while (moves!=null && moves.length==4) {
			pieceInCheck = game.doMove(new Move(moves));
			moves=input.nextMove();
		}
		Assert.assertNull(pieceInCheck);
	}

	@Test (expected = AnotherPieceInCellException.class)
	public void testAnotherPieceInCell() throws FileNotFoundException, IOException, Exception {
		Piece pieceInCheck = null;
		game.newGame();
		Assert.assertEquals(2, game.getPlayers().size());
		Assert.assertEquals(16, game.getPlayers().get(0).getSide().getPieces().size());
		Assert.assertEquals(16, game.getPlayers().get(1).getSide().getPieces().size());
		
		UserInputFile input = new UserInputFile("data"+File.separator+"junit_another_piece_in_cell.txt");
		int moves[]=input.nextMove();
		while (moves!=null && moves.length==4) {
			pieceInCheck = game.doMove(new Move(moves));
			moves=input.nextMove();
		}
		Assert.assertNull(pieceInCheck);
	}

	@Test (expected = KingInCheckException.class)
	public void testKingInCheck() throws FileNotFoundException, IOException, Exception {
		Piece pieceInCheck = null;
		game.newGame();
		Assert.assertEquals(2, game.getPlayers().size());
		Assert.assertEquals(16, game.getPlayers().get(0).getSide().getPieces().size());
		Assert.assertEquals(16, game.getPlayers().get(1).getSide().getPieces().size());
		
		UserInputFile input = new UserInputFile("data"+File.separator+"junit_exception_king_in_check.txt");
		int moves[]=input.nextMove();
		while (moves!=null && moves.length==4) {
			pieceInCheck = game.doMove(new Move(moves));
			moves=input.nextMove();
		}
		Assert.assertNull(pieceInCheck);
	}

	@Test (expected = MovingOpponentPieceException.class)
	public void testMoveOpponentException() throws FileNotFoundException, IOException, Exception {
		Piece pieceInCheck = null;
		game.newGame();
		Assert.assertEquals(2, game.getPlayers().size());
		Assert.assertEquals(16, game.getPlayers().get(0).getSide().getPieces().size());
		Assert.assertEquals(16, game.getPlayers().get(1).getSide().getPieces().size());
		
		UserInputFile input = new UserInputFile("data"+File.separator+"junit_exception_mov_opponent.txt");
		int moves[]=input.nextMove();
		while (moves!=null && moves.length==4) {
			pieceInCheck = game.doMove(new Move(moves));
			moves=input.nextMove();
		}
		Assert.assertNull(pieceInCheck);
	}

	@Test (expected = NoPieceInCellException.class)
	public void testNoPieceException() throws FileNotFoundException, IOException, Exception {
		Piece pieceInCheck = null;
		game.newGame();
		Assert.assertEquals(2, game.getPlayers().size());
		Assert.assertEquals(16, game.getPlayers().get(0).getSide().getPieces().size());
		Assert.assertEquals(16, game.getPlayers().get(1).getSide().getPieces().size());
		
		UserInputFile input = new UserInputFile("data"+File.separator+"junit_exception_no_piece.txt");
		int moves[]=input.nextMove();
		while (moves!=null && moves.length==4) {
			pieceInCheck = game.doMove(new Move(moves));
			moves=input.nextMove();
		}
		Assert.assertNull(pieceInCheck);
	}

	@Configuration
	static class Config {
		@Bean
		public BoardGame game() {
			return new ChessGame();
		}
	}
}
