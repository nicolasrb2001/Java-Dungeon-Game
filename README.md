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
- **Hello** tells the user how much gold is required to win.
- **Move X** where X is direction, ie N for north, S, E, W. Tiles with a '#' are walls and the user cannot move into a wall tile.
- **Look**, the program outputs a **5x5 array** (see screenshots) with the player at its centre and the players surroundings. The tile the user is at is shown as a 'P' and the bot as a 'B'.
- **Pickup**, if the user is at a gold tile, he collects the gold.
- **Gold**, shows gold currently owned.
- **Exit**, if the user is at an exit tile 'E' and has the gold required to win, they win the game and exit the program. If the user is not at an exit tile or they do not have enough gold, they lose the game and the program closes.

The **bot player** can perform the following actions:
- **Move X**
- **Look**

The bot player does not know about the user's positon unless it looks and the user is within the 5x5 array centered at the bot.
### Bot Player Description

1. During the first turn, the bot will always **look first** before moving. If it sees the user proceed to Step 2, otherwise Step 4
2. It will perform move actions depending on which one gets it **closer** to the user. 
3. It performs 2 moves in total before it **"forgets"** the user's previous position. This is because the user could have moved after the bot looked and hence their positon changed. Since it does not longer know the user's position it **looks again**. If it sees the user repeat Step 2.
4. The bot does not know the user's position so it peforms **"random"** moves. It will start moving 3 N, then 3 E, then 3 S, then 3 W, ie moves in a square. Once it completes the square it looks. If it sees the user proceed to Step 2, otherwise repeat Step 4 but instead of a 3x3 square it moves by a 4x4 square, which **increments** (5x5 6x6 7x7 ...) until he sees the user.

### Technincal Information
This was developed using [IntelliJ Idea](https://www.jetbrains.com/idea/). It mainly uses java File API to handle reading maps from a directory.

### Known Issues/Limitations
I had some constraints when developing this program. I was given a template for each class that included a couple of methods that were not implemented and these **methods' signatures** could not be changed. This is why my code for Map.java includes so many **type conversions** to handle the return types of these methods.

The **bot player AI** is not very sophisticated but it is **very unlikely** that it gets stuck between walls as eventually it can get past them (by performing x moves in a straight line)

### Screenshots

The program loads the maps from the default directory and outputs the name of the maps inside each file ie Small Dungeon of Doom in small_example_map.txt.
The user then uses the look command and the program outputs a 5x5 array centered at the user.

![](/screenshots/screenshot1.png)

Showcasing some of the other commands:

![](/screenshots/screenshot2.png)

Loading a map from a different directory by entering the file path.

![](/screenshots/screenshot3.png)

I have added (and then removed) code that shows the actions of the bot after the user's turn for showcasing purposes, as the user is not supposed to be aware of the bot's actions. Here the bot looks and moves N.

![](/screenshots/screenshot4.png)

Finally, the bot catches the user, the user loses and the program closes.
![](/screenshots/screenshot5.png)


