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

import com.jpn.chesstest.domain.Game;
import com.jpn.chesstest.domain.Move;
import com.jpn.chesstest.domain.pieces.Piece;
import com.jpn.chesstest.exceptions.InvalidMovementException;
import com.whitehatgaming.UserInputFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AppTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);

	@Autowired
	private Game game;

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
			UserInputFile input = new UserInputFile("data"+File.separator+"checkmate.txt");
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
		
		UserInputFile input = new UserInputFile("data"+File.separator+"sample-moves-invalid.txt");
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
		
		UserInputFile input = new UserInputFile("data"+File.separator+"sample-moves.txt");
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
		public Game game() {
			return new Game();
		}
	}
}
