package battleship;

public class Driver {

	public static void main(String[] args) {
		Battleship b = new Battleship();
		
		b.initializeGrid(); // Initialize the Player Grid
		
		// Place Ships & Grenades
		b.placeShipsAndGren(Owner.HUMAN, Type.SHIP);
		System.out.println("");
		b.placeShipsAndGren(Owner.HUMAN, Type.GRENADE);
		System.out.println("");
		b.placeShipsAndGren(Owner.COMPUTER, Type.SHIP);
		System.out.println("");
		b.placeShipsAndGren(Owner.COMPUTER, Type.GRENADE);
		System.out.println("");
		
		b.play(); // Start the game
	}
}
