package com.jpn.chesstest.domain;
/**
 * Player of this game! This class also has some configuration about the starting position 
 * of each player, movement direction, etc
 * 
 * @see Side
 * @author jnicotra
 * @since 1.0
 */
public abstract class Player {

	/**
	 * Player name
	 * @return player name
	 */
	public abstract String getName();

	/**
	 * Player Side
	 * @see Side
	 * @return side
	 */
	public abstract Side getSide();

}
