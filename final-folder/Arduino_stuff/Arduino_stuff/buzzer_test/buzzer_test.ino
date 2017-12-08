//Mini class to test the hardware set up of a button and an active buzzer

int buzzerPin = 9; //buzzer pin
int buttonPin = 3; //button pin

//set up the GPIO pins.
void setup(){
  
  pinMode(buzzerPin, OUTPUT);
  pinMode(buttonPin, INPUT);
  
}

void loop(){
  //if the button is pressed
  if(digitalRead(buttonPin) == HIGH){
    //sound the active buzzer
    tone(buzzerPin, 1000);
  } //no button pressed, no tone from buzzer.
  else{
    noTone(buzzerPin);
  }
  
}
