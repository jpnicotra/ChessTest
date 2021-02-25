package com.jpn.chesstest.domain.chess;

import com.jpn.chesstest.domain.Player;
import com.jpn.chesstest.domain.Side;

/**
 * Player of this game! This class also has some configuration about the starting position 
 * of each player, movement direction, etc
 * 
 * @see Side
 * @author jnicotra
 * @since 1.0
 */
public class ChessPlayer extends Player {
	public static final int UP_DIRECTION = -1;
	public static final int DOWN_DIRECTION = 1;

	private Side side;
	private String name;
	private int startRow;
	private int direction;

	/**
	 * Player name
	 * @return player name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set player name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Player Side
	 * @see Side
	 * @return side
	 */
	public Side getSide() {
		return side;
	}

	/**
	 * Set player side
	 * @see Side
	 * @param side
	 */
	public void setSide(Side side) {
		this.side = side;
	}

	/**
	 * Defines starting row in the chessboard
	 * @return chessboard row
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * Set starting tow
	 * @param startRow
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	/**
	 * Return if this player moves UP or DOWN
	 * @return direction
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * Set player direction
	 * @param direction UP or DOWN
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	
}
