# SpaceShooter

## What is this project

<p align="center">
 <img src="https://github.com/demarbre1u/SpaceShooter/blob/master/img/screen_space_shooter.png" alt="SpaceShooter screen"/>
</p>

This project is a simple Space Shooter game made using Light-Weight Java Game Library (LWJGL).

## How to play

The game controls :
 - W, A, S, D : move spaceship on screen
 - Spacebar : fire projectiles
 - R : when on game over screen, restarts the game

## Features

The currently implemented features :
- Infinite scrolling
- Score system
- Obstacles and enemies
- Hit points
- Power-ups
- More types of enemies

Features to come (some day, hopefully) :
- Scoreboard
- Enemies that fire missiles
- More types of obstacles
- Bosses
- Sound
- Pause menu
- Title menu

## How to deploy the project

Using [JarSplice](http://ninjacave.com/jarsplice) :
- Export a non-runnable ```.jar``` file
- Add the required ```.jar``` lib (LWJGL, etc)
- Add natives for the targeted OS (Windows, Linux)
- Create the FatJar
- Done :)
