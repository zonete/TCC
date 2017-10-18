const int A=1;
const int B=2;
const int C=3;
const int D=4;
const int E=5;
const int F=6;
const int G=7;
const int H=8;
const int I=9;
const int J=10;
const int L=11;
const int M=12;
const int N=13;

int Led;
int Status;      // a variable to read incoming serial data into

void setup() {
  // initialize serial communication:
  Serial.begin(9600);
  // initialize the LED pin as an output:
  pinMode(A, OUTPUT);
  pinMode(B, OUTPUT);
  pinMode(C, OUTPUT);
  pinMode(D, OUTPUT);
  pinMode(E, OUTPUT);
  pinMode(F, OUTPUT);
  pinMode(G, OUTPUT);
  pinMode(H, OUTPUT);
  pinMode(I, OUTPUT);
  pinMode(J, OUTPUT);
  pinMode(L, OUTPUT);
  pinMode(M, OUTPUT);
  pinMode(N, OUTPUT);
  
}

void loop() {
  // see if there's incoming serial data:
  if (Serial.available() >= 2) {
    // read the oldest byte in the serial buffer:
    Led = Serial.read();
    Status = Serial.read();
    // if it's a capital H (ASCII 72), turn on the LED:
    if (Status == 'H') {
        switch (Led) {
          case 'A':
            digitalWrite(A, HIGH);      
            break;
          case 'B':
            digitalWrite(B, HIGH);      
            break;
          case 'C':
            digitalWrite(C, HIGH);      
            break;
          case 'D':
            digitalWrite(D, HIGH);      
            break;
          case 'E':
            digitalWrite(E, HIGH);      
            break;
          case 'F':
            digitalWrite(F, HIGH);      
            break;
          case 'G':
            digitalWrite(G, HIGH);      
            break;
          case 'H':
            digitalWrite(H, HIGH);      
            break;
          case 'I':
            digitalWrite(I, HIGH);      
          case 'J':
            digitalWrite(J, HIGH);      
          case 'L':
            digitalWrite(L, HIGH);      
          case 'M':
            digitalWrite(M, HIGH);      
          case 'N':
            digitalWrite(N, HIGH);      
      }      
    } 
    // if it's an L (ASCII 76) turn off the LED:
    if (Status == 'L') {
     switch (Led) {
          case 'A':
            digitalWrite(A, LOW);      
            break;
          case 'B':
            digitalWrite(B, LOW);      
            break;
          case 'C':
            digitalWrite(C, LOW);      
            break;
          case 'D':
            digitalWrite(D, LOW);      
            break;
          case 'E':
            digitalWrite(E, LOW);      
            break;
          case 'F':
            digitalWrite(F, LOW);      
            break;
          case 'G':
            digitalWrite(G, LOW);      
            break;
          case 'H':
            digitalWrite(H, LOW);      
            break;
          case 'I':
            digitalWrite(I, LOW);      
          case 'J':
            digitalWrite(J, LOW);      
          case 'L':
            digitalWrite(L, LOW);      
          case 'M':
            digitalWrite(M, LOW);      
          case 'N':
            digitalWrite(N, LOW);      
      }
      
      // digitalWrite(ledPin, LOW);
    }
  }
}
