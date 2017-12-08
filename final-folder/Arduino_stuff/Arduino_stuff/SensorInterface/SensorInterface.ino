/*
 * Dalia Matar, 101003740
 * SYSC3010 - Digi-smart clock
 */

//Class used to make use of buttons and buzzers on an Arduino for the digi-smart clock.


//initialize all variable pins
int touchPin = 11;
int buzzerPin = 9;
int buttonPin = 3;

char LCD_ON = 'L';
char LCD_OFF = 'N';

int alarmON, pressed = 0;

//Set up code, used to initialize all pin-ports and their respective role,
// setup will run once.
void setup() {
 
 Serial.begin(9600); // set up Serial library at 9600 bps
 
 pinMode(buzzerPin, OUTPUT);
 pinMode(touchPin, INPUT);
 pinMode (buttonPin, OUTPUT);
 
 //boolean alarmOn = Serial.read();
 
}

void loop() {
  
  if (Serial.available() > 0){
    //if the server sends a '1'
    alarmON = Serial.read();
    if (alarmON == '1'){ //if false skip to second if statement.

      //while the button isnt being pressed
      while (digitalRead(buttonPin) == LOW){
        toggleBuzzer();
      }
      //leaves loop when button is pressed and calls this function.
      turnAlarmOFF();
    }
  } 
  //check status of the Touch Sensor

  if(digitalRead(touchPin) == HIGH && pressed % 2 == 0) {
    pressed++;
    sendData(LCD_ON);
    delay(1000);
  }
  
  if(digitalRead(touchPin) == HIGH && pressed % 2 == 1) {
    pressed++;
    sendData(LCD_OFF);
    delay(1000);
  }
}



/* Method used to sound the active Buzzer, uses built-in libraries, 
 * such as Tone and noTone and delays to make an alarm-clock noise. 
 */
void toggleBuzzer(){
  tone(buzzerPin, 1000);
  delay(500);
  noTone(buzzerPin);
  delay(500);
  
}

/* Method used to turn off the active buzzer off, this method 
 * will be called when a button is pressed.
 */
void turnAlarmOFF(){
  noTone(buzzerPin);
  alarmON = 0;
}

//method used to send information to the main database.
void sendData(char s){
  Serial.println(s);    
}



