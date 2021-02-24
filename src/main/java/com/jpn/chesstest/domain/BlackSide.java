package com.jpn.chesstest.domain;

/**
* Represents black pieces side
* 
* @see Side
* @author      Juan Pablo Nicotra
* @since       1.0
*/
public class BlackSide extends Side {

	/**
	* Indicates if this piece is from white side. In this case, it will always return false
	* 
	* @return Always <code>false</code>
	*/
	public boolean isWhite() {
		return false;
	}

	/**
	 * Returns a character string representation of this Side
	 * @return	String representation of the board with all pieces involved.
	 */
	@Override
	public String toString() {
		return "Black Side";
	}

}
