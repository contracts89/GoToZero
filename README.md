Programming Language

JAVA

UI library

JAVA FX

Game Name: GoToZero

Goal of the Game: 

	The goal of the game is through the mathematical operations addition and subtraction to reach a score of 0.
	
Objects: 

•	Background 

•	Background menu – background image for menu

•	Background game – background image for game

1.1.	Menu

The sample.Main menu gives the player the following options:

Start -  starts the game

Help – describes the goal of the game

HighScore – shows high-scores(TO DO)

Quit – exits the game

1.2.	Falling objects

Falling object number – falling numbers with random value

Falling object sign – falling mathematic signs corresponding to the mathematical operations of addition, subtraction, multiplication and
division

Falling object symbol – falling symbols (like $, %, #) which have an unpredictable outcome for the score(TO DO)

1.3.	sample.Main Player

Object controlled by the player, representing a female ninja who runs side to side and collects the falling objects 

1.4.	Static Objects

Score – object, which shows current score

Timer – object, which shows time elapsed since the game has begun(TO DO)

1.5.	Other Objects

Pause – the player can pause the game at any time with the button Pause(TO DO)

Exit – the player can exit the game at any time with the button Exit(TO DO)

GamePlay:
When the player starts the game, a game menu is shows with options for Single Player, Multiplayer(TO DO), High Scores, Settings(TO DO) and Quit
1.6.	Èçáîð íà áóòîí Start
	- starts a new instance of the game with the background game image
	- a random three digit number is shown on the screen, which is the score the player has to reach
	- starts the game timer
	- a random number of falling objects begin their fall from the top of the screen
	- each object has its own individual “fall” lane
	- the player(aka the ninja) is spawned at the bottom of the screen
	
1.7.	End Of The Game
	- the game ends when the current score reaches 0
	- the timer is stopped and the time elapsed is written on the screen(TO DO)
	- the player’s score is compared to the High Scores(TO DO)
	- the player can enter a username, if his score is within the top 5(TO DO)
	- through a back button, the player can return to the sample.Main Menu(TO DO)

1.8.	Help
	- shows the game goal and typical gameplay

1.9.	Highscore
	- shows the top 5 scores the their corresponding user names

1.10.	Quit
	 - Exits the game

1.11.	Pause 
	- the player can pause the game at any point, but the falling objects become hidden while the game is paused.
