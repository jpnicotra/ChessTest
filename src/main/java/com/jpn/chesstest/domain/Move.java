package com.jpn.chesstest.domain;

/**
* Represents movement from one position to another
* 
* @see Position
* @author      Juan Pablo Nicotra
* @since       1.0
*/

public class Move {
	private Position positionFrom;
	private Position positionTo;

	/**
	 * Constructor indicating starting and ending positions
	 * @param fromCol
	 * @param fromRow
	 * @param toCol
	 * @param toRow
	 */
	public Move(int fromCol, int fromRow, int toCol, int toRow) {
		positionFrom = new Position (fromRow, fromCol);
		positionTo = new Position (toRow, toCol);
	}

	/**
	 * Constructor indicating starting and ending positions with int array parameter
	 * @param moves Array representing starting and ending positions. [0] from Col, [1] from Row, [2]to Col, [3] to Row
	 * @throws Exception if array it's null or length distinct of 4
	 */
	public Move(int moves[]) throws Exception {
		if (moves!=null && moves.length==4) {
			positionFrom = new Position (moves[1], moves[0]);
			positionTo = new Position (moves[3], moves[2]);
		}
		else {
			throw new Exception ("You must set an initial and a ending position with rows and cols for both of them");
		}
	}

	/**
	 * Start Position
	 * @return Start position
	 */
	public Position getPositionFrom() {
		return positionFrom;
	}

	/**
	 * Setting start position 
	 * @param positionFrom
	 */
	public void setPositionFrom(Position positionFrom) {
		this.positionFrom = positionFrom;
	}

	/**
	 * Ending position
	 * @return Ending position
	 */
	public Position getPositionTo() {
		return positionTo;
	}
	
	/**
	 * Set's ending position 
	 * @param positionTo
	 */
	public void setPositionTo(Position positionTo) {
		this.positionTo = positionTo;
	}

	/**
	 * Returns a character string representation of this class
	 * @return	String representation
	 */
	@Override
	public String toString() {
		return "From "+positionFrom+" to "+positionTo;
	}
	
}
