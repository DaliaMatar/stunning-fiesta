int touchPin = 3;                                                           
int buzzerPin = 11;
boolean buzzerState;
boolean touchState'
int offButtonPin;

void setup(){
  
  Serial.begin(9600);
  
  pinMode(buzzerPin, OUTPUT);
  pinMode(touchPin, INPUT);
  pinMode(offButtonPin, INPUT);
  
}

boolean alarmOn;

void loop(){
  
  if(Serial.available() > 0){
    alarmOn = Serial.read();
    
    if(alarmOn)
      toggleBuzzer();
      
    if(digitalRead(offButtonPin) == HIGH)
      turnAlarmOff();
  }
}

void toggleBuzzer(){
  
  tone(buzzerPin, 1000);
  delay(500);
  noTone(buzzerPin);
  delay(500);
  
}

void turnAlarmOff(){
  
  noTone(buzzerPin);
  alarmOn = false;
  
}
     
