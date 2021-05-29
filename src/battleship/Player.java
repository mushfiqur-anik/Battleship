package battleship;

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
