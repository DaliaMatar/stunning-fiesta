# stunning-fiesta
A fiesta of fun! :)

Here we have the final product for the SmartClock.

Not all files are necessary to run the SmartClock, but were in some way used in development.
All java files were created using BlueJay IDE, but were edited on Eclipse for final touch ups.
The only potential problem could be the package, but otherwise, everything should work.

1) Download the folder "final-project"

2) On the Arduino, run final-folder/Arduino_stuff/buzzer_test/buzzer_test.ino
   This should start the buzzer every time you push the button. If this does not
   work, double check Arduino wiring before continuing.

3) Run final-folder/Arduino_stuff/Arduino_Clock/Arduino_Clock.ino
   This runs the main Arduino program used throughout the program

4) Run final-folder/Gui/DigitalClock.java and final-folder/server/shell.java
   This runs the Gui and server. Once the server receives data from the Android
   app, the Gui will appear. 

5) To start the Android app, unzip the SYSC3010.zip file and open it in 
   Android Studio stunning-fiesta/final-folder/AndroidApp/src/main/java/e/hoda/digi_smartclock/MainActivity.java
   contains the main activity java file.

6) Once every program is running, as soon as you enter the country and city, the Gui will launch. 
   When you send an alarm time, wait for the alarm to trigger. When sending the alarm time, if the time is 12:00PM, 
   the alarm time should be 00:00 PM.
