# Java-Dungeon-Game
This is a dungeon minigame in which the user can move around a map collecting gold in order to win.

## Motivation :bullettrain_front:
---
The main requisite for completing on of my coursework was implementing the minigame, however; I was eager to attempt the additional challenges.

### Features
- The user can choose to play the maps that they have created by entering their file path.
- There is a **bot player** that attempts to catch the user.
### Game Instructions
The user is prompted to choose one of the default maps or they can enter their own ones.
The user can perform the following commands:
- **Move X** where X is direction, ie N for north, S, E, W. 
- **Look**, the program outputs a **5x5 array** (see screenshots) with the player at its centre and the players surroundings
- **Pickup**, if the user is at a gold square, he collects the gold.
- **Gold**, shows gold required to win.
- **Exit**, if the user is at an exit square 'E' and has the gold required to win, they win the game and exit the program. If the user is not at an exit square or they do not have enough gold, they lose the game and the program closes.

### 
