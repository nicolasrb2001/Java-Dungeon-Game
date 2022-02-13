# Java-Dungeon-Game
This is a dungeon minigame in which the user can move around a map collecting gold in order to win.

## Motivation :bullettrain_front:
---
The main requisite for completing on of my coursework was implementing the minigame, however; I was eager to attempt the additional challenges.

### Features
- The user can choose to play the maps that they have created by entering their file path.
- There is a **bot player** that attempts to catch the user. If the user is caught they lose the game
- Maps created by the user can have any size and shape, they are stored in a MxN array where M is the max nº of rows and N the max nº of columns.
### Game Instructions
The user is prompted to choose one of the default maps or they can enter their own ones.
The user can perform the following commands:
- **Move X** where X is direction, ie N for north, S, E, W. 
- **Look**, the program outputs a **5x5 array** (see screenshots) with the player at its centre and the players surroundings. The tile the user is at is shown as a 'P' and the bot as a 'B'.
- **Pickup**, if the user is at a gold tile, he collects the gold.
- **Gold**, shows gold required to win.
- **Exit**, if the user is at an exit tile 'E' and has the gold required to win, they win the game and exit the program. If the user is not at an exit tile or they do not have enough gold, they lose the game and the program closes.

The **bot player** can perform the following actions:
- **Move X**
- **Look**

The bot player does not know about the user's positon unless it looks and the user is within the 5x5 array centered at the bot.
### Bot PLayer Description

1. During the first turn, the bot will always **look first** before moving. If it sees the user proceed to Step 2, otherwise Step 4
2. It will perform move actions depending on which one gets it **closer** to the user. 
3. It performs 2 moves in total before it **"forgets"** the user's previous position. This is because the user could have moved after the bot looked and hence their positon changed. Since it does not longer know the user's position it **looks again**. If it sees the user repeat Step 2.
4. The bot does not know the user's position so it peforms **"random"** moves. It will start moving 3 N, then 3 E, then 3 S, then 3 W, ie moves in a square. Once it completes the square it looks. If it sees the user proceed to Step 2, otherwise repeat Step 4 but instead of a 3x3 square it moves by a 4x4 square, which **increments** (5x5 6x6 7x7 ...) until he sees the user.

### Known Issues/Limitations
I had some constraints when developing this programm. I was given a template for each class that included a couple of methods that were not implemented and the methods' signatures could not be changed. This is why my code for Map.java includes so many type conversions to handle the return types of the methods.
