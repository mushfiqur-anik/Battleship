# Battleship Game
  
## Description of the problem
In this project a simplified version of [**Battleship**](https://en.wikipedia.org/wiki/Battleship_(game)) is built and player between Human (User) against Computer.

## Rules
1) Each player (Human & Computer) places 6 ships & 4 Grenades in each coordinate. Valid coordinates are character A-H or a-h followed by number 1 to 8.
	 - Each position can only hold one type of element (Ship or Grenade)
	 - The elements are hidden initially so ensure the players doesn't know each other's placements
2) The Human (User) launches rocket first in one of the valid coordinates.
	 - If rocket falls in a coordinate where there is nothing, '*' is displayed
	 - If rocket fall in a coordinate where there is a Ship -> if it human's ship then we display 's' or 'S' for computer's ship.
	 - If rocket fall in a coordinate where there is a Grenade -> if it's human's grenade we display 'g' and 'G' for computer's ship and the player loses next turn (The other player hits twice)
	 - If rocket falls in a coordinate which has been called before, nothing happens, and we display the previous coordinates.
3) The Computer launches rocket and the above rules are applied as well.
4) The game continues until all 6 ships are sunk (Rocket hits the coordinates where there is a ship) for one of the players.
5) The player who hit all 6 ships of the other player is declared the winner.
6) All the initial placements of Ships & Grenades for each player is displayed.

### Gameplay 
![Start](1.png?raw=true "Optional Title")
![Start](2.png?raw=true "Optional Title")
##### After several turns
![Start](3.png?raw=true "Optional Title")

## File List
- Player.java
- Battleship.java
- Driver.java

## Built with

* [**Java**](https://en.wikipedia.org/wiki/Java_(programming_language)) - The programming language used
* [**Eclipse**](https://en.wikipedia.org/wiki/Eclipse_(software)) - The IDE used 


## Author(s)

* [**Mushfiqur Anik**](https://github.com/mushfiqur-anik)

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
