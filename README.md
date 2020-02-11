# Player finder
This is a shadowplay-friendly player finder for SAMP 0.3.7 that you can use to find other players while playing & recording without it showing up on your recordings. This only works on players that are in the same interior as you. On the SAMP side, a Lua script is used to read information such as real-time player coordinates, name, skin, current vehicle, gun, etc. Sockets are used to forward this information to a Java application. The Java application listens for updates at localhost:5230 and displays the received data on a map. To display the map over SAMP, an autohotkey script is used, which must be run with admin privileges. This solution is p hacky but I'm not rewriting this. You, however, are more than welcome to.
![img](https://i.imgur.com/AOXesbX.jpg)

# Requirements
* JDK 11
* [AutoHotkey](https://www.autohotkey.com/) (if you wish to compile the AHK script yourself)
* [MoonLoader](https://gtaforums.com/topic/890987-moonloader/) - requires an ASI loader, if you don't already have CLEO, install it
* [SAMPFUNCS](http://ugbase.eu/index.php?threads/sampfuncs-5-3-0-3-7.14796/) - place inside your GTA SA folder
* [LuaSocket](https://blast.hk/threads/16031/) - extract the attached archive and drag the contents of LuaSocket into `moonloader/lib`

# Installation
1) Download the [archive](https://www.upload.ee/files/11111883/playerfinder.rar.html) containing a jar file, a lua script and a compiled AHK script
2) Place playerfinder.lua inside your moonloader folder
3) Extract the remaining two files and run them both. PrioritizeWindow.exe requires admin privileges to display the map over SAMP.
4) Run SAMP and you should see a second minimap over your game

If you would like to build the Java application manually, you will need to find your own skin & map images and place all 313 of them into `src/main/resources` as follows: [img](https://i.imgur.com/TAF8HRe.png "Structure")

Everything else is provided in this repository. Running `gradlew jar` in the Java project root will build the jar.

# Usage
* While in game with the Java application running, hold NUMPAD+ and type the ID of a player you would like to find. If they are connected and in the same interior as you, you should now begin to receive real-time location updates.
* When they are out of range, you will only receive basic information (coordinates, name)
* When they are close to you, you will also receive additional information such as their health/armor, current car/weapon and skin and more frequent location updates
* To stop finding people, just press Numpad+ again.
* To close the Java application, make it active by clicking on it and then hit ESC.

# Credits
* [FYP](https://github.com/THE-FYP). All of this would have been way harder without all the samp hacking tools he's graciously published over the past decade.
* [this jlink project i used as a starting point](https://bitbucket.org/FlPe/javafx_jlink_example/src/master/)
