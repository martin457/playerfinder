# Player finder
This is a shadowplay-friendly player finder for SAMP 0.3.7 that you can use to find other players while playing & recording without it showing up on your recordings. This only works on players that are in the same interior as you. 

On the SAMP side, a Lua script is used to read information such as real-time player coordinates, name, skin, current vehicle, gun, etc. Sockets are used to forward this information to a Java application. The Java application listens for updates at localhost:5230 and displays the received data on a map. To display the map over SAMP, an autohotkey script is used, which must be run with admin privileges. This solution is p hacky but I'm not rewriting this. You, however, are more than welcome to.
![img](https://i.imgur.com/AOXesbX.jpg)

# Requirements
* [JDK 11](https://jdk.java.net/archive/)
* [AutoHotkey](https://www.autohotkey.com/) - if you wish to compile the AHK script yourself.
* [MoonLoader](https://gtaforums.com/topic/890987-moonloader/) - MoonLoader requires an ASI loader, if you don't already have CLEO, install it.
* [SAMPFUNCS](https://blast.hk/threads/17/page-138#post-279414)
* [LuaSocket](https://blast.hk/threads/16031/)
* [SAMP.Lua](https://github.com/THE-FYP/SAMP.Lua)

# Installation
1) Install [CLEO](https://cleo.li/) and [MoonLoader](https://gtaforums.com/topic/890987-moonloader/) if you haven't already.
2) Download and unzip [THIS ARCHIVE](https://www.upload.ee/files/11121066/playerfinder.rar.html). It contains SAMPFUNCS, SAMP.Lua, LuaSocket, playerfinder.lua, the java app to display a second map over your game, and a compiled AHK file to give the java app priority over SAMP.
3) Drag the contents of the archive's gta san andreas folder into your gta san andreas folder.
4) Run playerfinder.jar. An empty minimap should appear.
5) Run PrioritizeWindow.exe. It requires admin privileges to display the map over SAMP.
6) Run SAMP and you should see the minimap appear over your game.

If you would like to build the Java application manually, you will need to download the [skin & map images](https://www.upload.ee/files/11121136/gui.rar.html) and place all 313 of them into `src/main/resources` as follows: [img](https://i.imgur.com/TAF8HRe.png "Structure")

Running `gradlew jar` in the Java project root will then build the jar. Sources for everything else, including the lua script and the AHK script used to display the map over SAMP are present in this repo.

# Usage
* While in game with the Java application running, hold NUMPAD+ and type the ID of a player you would like to find. If they are connected and in the same interior as you, you should now begin to receive real-time location updates.
* When they are out of range, you will only receive basic information (coordinates, name).
* When they are close to you, you will also receive additional information such as their health/armor, current car/weapon and skin and more frequent location updates.
* To stop finding people, just press Numpad+ again.
* To close the Java application, make it active by clicking on it and then hit ESC.

# Credits
* [FYP](https://github.com/THE-FYP). All of this would have been way harder without the samp hacking tools he's graciously published over the past decade.
* [this jlink project i used as a starting point.](https://bitbucket.org/FlPe/javafx_jlink_example/src/master/)
