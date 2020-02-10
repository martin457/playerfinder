# Requirements
* JDK 11
* [AutoHotkey](https://www.autohotkey.com/) (if you wish to compile the AHK script yourself)
* [MoonLoader](https://gtaforums.com/topic/890987-moonloader/) - requires an ASI loader, if you don't already have CLEO, install it
* [SAMPFUNCS](http://ugbase.eu/index.php?threads/sampfuncs-5-3-0-3-7.14796/) - place inside your GTA SA folder
* [LuaSocket](https://blast.hk/threads/16031/) - extract the attached archive and drag the contents of LuaSocket to moonloader/lib

# Installation
1) Download the [archive](https://www.upload.ee/files/11111883/playerfinder.rar.html) containing a jar file, a lua script and a compiled AHK script
2) Place playerfinder.lua inside your moonloader folder
3) Extract the remaining two files and run them both. PrioritizeWindow.exe requires admin privileges to display the map over SAMP.
4) Run SAMP and you should see a second minimap over your game

To build the Java application manually, you will need to find your own skin & map images and place all 313 of them into src/main/resources as follows: ![img](https://i.imgur.com/TAF8HRe.png "Structure")

Everything else is provided in this repository. Running `gradlew jar` in the Java project root will build the jar.
