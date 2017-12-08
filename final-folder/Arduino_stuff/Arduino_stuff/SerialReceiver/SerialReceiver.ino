const int ledPin = 3;

void setup(){
  pinMode(ledPin, OUTPUT);
  Serial.begin(9600);
}

void loop(){
  if(Serial.available()){
    light(Serial.read()-'0');
  }
  delay(500);
}

void light(int num){
  for (int i = 0; i < num; i++){
    digitalWrite(ledPin, HIGH);
    delay(100);
    digitalWrite(ledPin, LOW);
    delay(100);
  }
}
