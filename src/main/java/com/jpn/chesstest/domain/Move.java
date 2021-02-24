package com.jpn.chesstest.domain;

public class Move {
	private Position positionFrom;
	private Position positionTo;

	public Move(int fromCol, int fromRow, int toCol, int toRow) {
		positionFrom = new Position (fromRow, fromCol);
		positionTo = new Position (toRow, toCol);
	}

	public Move(int moves[]) throws Exception {
		if (moves!=null && moves.length==4) {
			positionFrom = new Position (moves[1], moves[0]);
			positionTo = new Position (moves[3], moves[2]);
		}
		else {
			throw new Exception ("You must set an initial and a ending position with rows and cols for both of them");
		}
	}

	
	public Position getPositionFrom() {
		return positionFrom;
	}

	public void setPositionFrom(Position positionFrom) {
		this.positionFrom = positionFrom;
	}

	public Position getPositionTo() {
		return positionTo;
	}

	public void setPositionTo(Position positionTo) {
		this.positionTo = positionTo;
	}

	@Override
	public String toString() {
		return "From "+positionFrom+" to "+positionTo;
	}
	
}
