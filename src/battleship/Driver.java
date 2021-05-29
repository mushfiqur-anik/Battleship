package battleship;

public class Driver {

	public static void main(String[] args) {
		
		Battleship b = new Battleship();
		
		b.initializeGrid();
		
		b.placeShipsAndGren(Owner.HUMAN, Type.SHIP);
		b.placeShipsAndGren(Owner.HUMAN, Type.GRENADE);
		b.placeShipsAndGren(Owner.COMPUTER, Type.SHIP);
		b.placeShipsAndGren(Owner.COMPUTER, Type.GRENADE);
		
		b.play();

	}

}
