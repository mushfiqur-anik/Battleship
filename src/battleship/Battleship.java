package battleship;
import java.util.Scanner;

/* This file contains all the necessary methods & attributes for the battleship game
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

public class Battleship {
	// Attributes
	private Player[][] p = new Player[8][8];
	private char[][] displayGrid = new char[8][8];
	Scanner keyboard = new Scanner(System.in);
	private String coord; // Get coordinates from Human
	private int hShip = 0; // Number of ships sunk
	private int cShip = 0;
	private Type type = null;
	private Owner owner = null;
	private int numOfTurns = 0; 
	private boolean isHuman = true;
	private int[] coordinates = new int[2]; // Coordinates 
	
	// Main game loop
	public void play() { 
		
		// Repeat until all Ships of one of the players have been sunk
		do {
			if(numOfTurns == 0) { numOfTurns = 1; } // If one player's turn is finished - set it up for next player
			
			outerloop:
		    // Launches rocket
			while(numOfTurns > 0) {
				
				if(isHuman) {
					coordinates = getCoords(owner.HUMAN, type.ROCKET);
				} else {
					System.out.println("Computer's turn to launch the rocket: ");
					coordinates = getCoords(owner.COMPUTER, type.ROCKET);
				} 
				
					
				// Launch rocket 
				// If Grenade is hit, the other player plays twice
				if(launchRocket() == 2) { 
					break outerloop;
				}	
			} 
			
			System.out.println("");
			isHuman = !isHuman; // Change player i.e From Human to Computer or vice versa
			
		} while(cShip < 6 && hShip < 6);
		
		winner(hShip, cShip); // Check and declare winner
		displayInitialGrid(); // Display initial position of Ships & Grenades for both players
	}
	
	// Launch rocket 
	// Returns the number of turn for the next player
	public int launchRocket() { 
		
			// If this coordinate has been called before then nothing happens
			if(p[coordinates[0]][coordinates[1]].isCalled()) { 
				// Do nothing
				System.out.println("This position has been called before, nothing happens!");
				displayGrid();
				numOfTurns--;
			} else {
					switch(p[coordinates[0]][coordinates[1]].getType()) { 
					
					case NOTHING:
						p[coordinates[0]][coordinates[1]].setiSCalled(true);
						System.out.println("Nothing was hit");
						displayGrid[coordinates[0]][coordinates[1]] = '*';
						displayGrid();
						numOfTurns--;
						return numOfTurns;
						
					case SHIP:
						if(p[coordinates[0]][coordinates[1]].getOwner() == owner.HUMAN) { 
							p[coordinates[0]][coordinates[1]].setiSCalled(true);
							System.out.println("Your ship has been hit!");
							displayGrid[coordinates[0]][coordinates[1]] = 's'; 
							displayGrid();
							hShip++;
						    
						} else { 
							p[coordinates[0]][coordinates[1]].setiSCalled(true);
							System.out.println("Computer's ship has been hit!");
							displayGrid[coordinates[0]][coordinates[1]] = 'S'; 
							cShip++;
							displayGrid();
						}
						numOfTurns--;
						return numOfTurns;
						
					case GRENADE:
						p[coordinates[0]][coordinates[1]].setiSCalled(true);
						if(p[coordinates[0]][coordinates[1]].getOwner() == owner.HUMAN) { 
							displayGrid[coordinates[0]][coordinates[1]] = 'g'; 
						} else { 
							displayGrid[coordinates[0]][coordinates[1]] = 'G'; 
						}
						System.out.println("Grenade has been hit, next turn will be missed!");
						displayGrid();
						numOfTurns = 2;
						return numOfTurns;
				}
			} 
			
			return -1;
	} 
	
	// Places Ships & Grenades
    public void placeShipsAndGren(Owner owner, Type type) { 
    	int limit = 0;
    	//int[] coordinates = new int[2];
    	
    	if(type == type.SHIP) { limit = 6;} 
    	else limit = 4; // For Grenade
    	
    	// Placing the Owner, Type of element (Ship or Grenade), and isCalled to false.
    	for(int i = 0; i < limit; i++) { 
    		coordinates = getCoords(owner, type);
    		p[coordinates[0]][coordinates[1]].setPlayer(owner, type, false);
    	}
	}
    
    // Gets coordinates for Ships/Grenades/Rocket
    public int[] getCoords(Owner owner, Type type) { 
    	int[] coordinates = new int[2];
    	
    	while(true) {
    		if(owner == owner.HUMAN) {
    			System.out.print("Enter the coordinates of your " + type + " (H): "); 
        		coord = keyboard.nextLine();
        		
        		if(coord.length() > 2) { continue; }
        		
        		coordinates[0] = charToInt(coord.charAt(0)) - 1;
        		coordinates[1] = Character.getNumericValue(coord.charAt(1)) - 1; 
    		
    		} else { 
    			coordinates[0] = (int)(Math.random() * 7) + 1;
        		coordinates[1] = (int)(Math.random() * 7) + 1;
        		
        		System.out.println("Entering the coordinates for " + type + " (C): ");
    		}
    		
    		if(!isValid(coordinates[0], coordinates[1])) { 
    			System.out.println("Sorry, coordinates outside the grid. Try again!");
	   			continue; 
    		}
    		
    		// Rockets can hit any position
    		if(type != type.ROCKET) { 
    			if (!isAvailable(coordinates[0], coordinates[1])) {
   	   			 System.out.println("Sorry, coordinates already used. Try again!");
   	   			 continue;
   	   		    }
    		}
    		
    		return coordinates;
    	}
    }
    
	// Declares winner
	public void winner(int human, int computer) { 
		// If human sinks computer's all 6 ships then Human is the winner
		if(computer == 6) { 
			System.out.println("Congratulations you have won the battle");
		} else {
			System.out.println("Sorry you have lost the battle, better luck next game!");
		} 
	}
	
	// Converts characters to integers.
	public int charToInt(char c) { 
		int converted;
		
		c = Character.toUpperCase(c);
		converted =  (c - 'A' + 1);
		return converted;
	}

	// Check if position is available
	public boolean isAvailable(int i, int j) { 
		return (p[i][j].getType() == Type.NOTHING);
	}
	
	// Check if position is valid
	public boolean isValid(int i, int j) { 
		return (0 <= i && i <= 7) && (0 <= j && j <= 7);
	}

	// Initializes the grids: Player as well the display grid
	public void initializeGrid() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++)  {
				p[i][j] = new Player();
				displayGrid[i][j] = '-';
			}
		}	
	}
	
	// Displays the grid after launching a rocket
	public void displayGrid() { 
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++)  {
				System.out.print(displayGrid[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	// Displays the positions of Ships & Grenades place by the Human & Computer.
	public void displayInitialGrid() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++)  {
				switch(p[i][j].getType()) {
				
				case NOTHING: 
					System.out.print('-' + " ");
					break;
					
				case SHIP:
					if(p[i][j].getOwner() == Owner.HUMAN) {
						System.out.print('s' + " ");
					} else { 
						System.out.print('S' + " ");
					}
					break;
					
				case GRENADE:
					if(p[i][j].getOwner() == Owner.HUMAN) {
						System.out.print('g' + " ");
					} else { 
						System.out.print('G' + " ");
					}
					break;
				}
			}
			
			System.out.println("");
		}
	}
}
