package battleship;
import java.util.Scanner;

public class Battleship {
	// Attributes
	private Player[][] p = new Player[8][8];
	private char[][] displayGrid = new char[8][8];
	Scanner keyboard = new Scanner(System.in);

	private String coord;
	private int hShip = 0;
	private int cShip = 0;
	private int numOfTurns = 0;
	private boolean isHuman = true;
	private int[] coordinates = new int[2];
	
	private Type type = null;
	private Owner owner = null;
	
	// Methods
	public void play() { 
		
		do {
			if(numOfTurns == 0) { numOfTurns = 1; }
			
			outerloop:
			while(numOfTurns > 0) {
				
				if(isHuman) {
					coordinates = getCoords(owner.HUMAN, type.ROCKET);
				} else {
					System.out.println("Computer's turn to launch the rocket: ");
					coordinates = getCoords(owner.COMPUTER, type.ROCKET);
				} 
				
				// If is called before then nothing.
				if(p[coordinates[0]][coordinates[1]].isCalled()) { 
					// Do nothing
					displayGrid();
					numOfTurns--;
				} else { 
				
					if(launchRocket() == 2) { 
						break outerloop;
					}	
				}
			} 
			
			
			System.out.println("");
			isHuman = !isHuman;
			
		} while(cShip < 4 && hShip < 4);
		
	
		
		winner(hShip, cShip);
		displayInitialGrid();
	}
	
	// Launch rocket 
	public int launchRocket() { 
			switch(p[coordinates[0]][coordinates[1]].getType()) { 
			case NOTHING:
				p[coordinates[0]][coordinates[1]].setiSCalled(true);
				System.out.println("Nothing hit");
				displayGrid[coordinates[0]][coordinates[1]] = '*';
				displayGrid();
				numOfTurns--;
				return numOfTurns;
			case SHIP:
				if(p[coordinates[0]][coordinates[1]].getOwner() == owner.HUMAN) { 
					p[coordinates[0]][coordinates[1]].setiSCalled(true);
					displayGrid[coordinates[0]][coordinates[1]] = 's'; 
					System.out.println("Your ship has been hit!");
					hShip++;
				    displayGrid();
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
			default:
				return -1;
		}
	} 
	
	// Place ships and grenades
    public void placeShipsAndGren(Owner owner, Type type) { 
    	int limit = 0;
    	int[] coordinates = new int[2];
    	
    	if(type == type.SHIP) { limit = 4;} 
    	else limit = 6; 
    	
    	for(int i = 0; i < limit; i++) { 
    		coordinates = getCoords(owner, type);
    		p[coordinates[0]][coordinates[1]].setPlayer(owner, type, false);
    	}
	}
    
    // Get Coordinates
    public int[] getCoords(Owner owner, Type type) { 
    	int[] coordinates = new int[2];
    	
    	while(true) {
    		if(owner == owner.HUMAN) {
    			System.out.print("Enter the coordinates of your " + type + " (H): "); 
        		coord = keyboard.nextLine();
        		
        		if(coord.length() > 2) { continue;}
        		
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
    
	// Declare winner
	public void winner(int human, int computer) { 
		if(computer == 4) { 
			System.out.println("Congratulations you have won the battle");
		} else {
			System.out.println("Sorry you have lost the battle, better luck next game!");
		} 
	}
	
	// Convert character to integer.
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

	// Initialize the display grid
	public void initializeGrid() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++)  {
				p[i][j] = new Player();
				displayGrid[i][j] = '-';
			}
		}	
	}
	
	// Display all the characters in array
	public void displayGrid() { 
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++)  {
				System.out.print(displayGrid[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	// Display the Ship & Grenade position of each player
	public void displayInitialGrid() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++)  {
				switch(p[i][j].getType()) {
				
				case NOTHING: 
					System.out.print('-' + " ");
					break;
					
				case SHIP:
					if(p[i][j].getOwner() == Owner.HUMAN) {
						System.out.print('S' + " ");
					} else { 
						System.out.print('s' + " ");
					}
					break;
					
				case GRENADE:
					if(p[i][j].getOwner() == Owner.HUMAN) {
						System.out.print('G' + " ");
					} else { 
						System.out.print('g' + " ");
					}
					break;
				}
				
			}
			
			System.out.println("");
			
		}
	}
}
