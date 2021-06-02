package battleship;

/* This file contains all the necessary methods & attributes for the player object
 *  Rules: 
 *  Each player (Human & Computer) places 6 ships & 4 Grenades in each coordinate. Valid coordinates are character A-H or a-h followed by number 1 to 8.
	Each position can only hold one type of element (Ship or Grenade)
	The elements are hidden initially so ensure the players doesn't know each other's placements
	The Human (User) launches rocket first in one of the valid coordinates.
	If rocket falls in a coordinate where there is nothing, '*' is displayed
	If rocket fall in a coordinate where there is a Ship -> if it human's ship then we display 's' or 'S' for computer's ship.
	If rocket fall in a coordinate where there is a Grenade -> if it's human's grenade we display 'g' and 'G' for computer's ship and the player loses next turn (The other player hits twice)
	If rocket falls in a coordinate which has been called before, nothing happens, and we display the previous coordinates.
	The Computer launches rocket and the above rules are applied as well.
	The game continues until all 6 ships are sunk (Rocket hits the coordinates where there is a ship) for one of the players.
	The player who hit all 6 ships of the other player is declared the winner.
	All the initial placements of Ships & Grenades for each player is displayed.
 * Author: Mushfiqur Anik
 * */

// The type of element
enum Type { 
	SHIP, GRENADE, NOTHING, ROCKET;
}

enum Owner {
	HUMAN, COMPUTER;
}

public class Player {
	// Type
	private Type type;
	private Owner owner;
	private boolean isCalled;
	
	// Default constructor
	public Player() { 
		type = type.NOTHING;
		isCalled = false;
	}
	
	// Parameter constructor
	public Player(Owner owner, Type type, boolean isCalled) {
		this.owner = owner;
		this.type = type;
		this.isCalled = isCalled;
	}
	
	// Getters 
	public Owner getOwner() {
		return owner;
	}
	
	public Type getType() { 
		return type;
	}
	
	// If this position has been called before
	public boolean isCalled() { 
		return isCalled;
	}
	
	// Setters
	public void setOwner(Owner owner) { 
		this.owner = owner;
	}
	
	public void setType(Type type) { 
		this.type = type;
	}
	
	public void setiSCalled(boolean isCalled) {
		this.isCalled = isCalled;
	}
	
	public void setPlayer(Owner owner, Type type, boolean isCalled) { 
		this.owner = owner;
		this.type = type;
		this.isCalled = isCalled;
	}
}
