/**
 * \file garden.ino
 * \brief This is the garden. It handles reading sensors and opening the water value when needed
 */

#include <Wire.h>

#include "moisture.h"
#include "dht.h"

//////////////////
// DIGITAL PINS //
//////////////////
#define TEMP_HUMID_PIN 2
#define WATER_FLOW_PIN 4
#define WATER_FLOW_POWER 7

/////////////////
// ANALOG PINS //
/////////////////
#define MOISTURE_PIN 0

// Define sensors
dht TempHumid;
moisture Moisture;

// Initialize sensor readings
double temperature = 0.0;
double humidity = 0.0;
double moisture = 0.0;

/**
 * \brief Initializes sensors, water flow relay, and i2c communication
 */
void setup() {
  Serial.begin(9600);

  // Initialize the Temperature and Humidity sensors
  Serial.println("Initializing...");
  Serial.print("Temperature and Humidity...");
  int chk = TempHumid.read11(TEMP_HUMID_PIN);
  if (chk == DHTLIB_OK)
    Serial.println("OK!");
  else
    Serial.println("ERROR!");

  // Initialize water flow signal and relay
  Serial.print("Water Flow Signal...");
  pinMode(WATER_FLOW_PIN, OUTPUT);
  Serial.println("OK!");

  Serial.print("Water Flow Relay...");
  pinMode(WATER_FLOW_POWER, OUTPUT);
  Serial.println("OK!");

  // Initialize i2c communication with WiFi Module
  Serial.print("WiFi Module...");
  Wire.begin(17);
  Wire.onRequest(sendReadings);
  Serial.println("OK!");
}

/**
 * \brief Gets sensor readings and turn on water flow based on predefined threshold
 */
void loop() {
  // Update sensors
  TempHumid.read11(TEMP_HUMID_PIN);
  Moisture.read(MOISTURE_PIN);
  
  readSensors(temperature, humidity, moisture);

  if (moisture <= 40)
    waterFlow(true);
  else
    waterFlow(false);
}

/**
 * \brief A toggle for the water flow. It is either on or off.
 */
void waterFlow(boolean flow) {
  if (flow == true)
    digitalWrite(WATER_FLOW_PIN, HIGH);
  else
    digitalWrite(WATER_FLOW_PIN, LOW);
}

/**
 * \brief Reads the current sensor readings and prints out the result
 */
void readSensors(double &_temperature, double &_humidity, double &_moisture) {
  _temperature = celsiusToFahrenheit(TempHumid.temperature);
  _humidity = TempHumid.humidity;
  _moisture = Moisture.moisture;

  printSensors(_temperature, _humidity, _moisture);
}

/**
 * \brief A function that is called when bytes are requested from the WiFi module through i2c
 */
void sendReadings(){
  i2cSend(temperature);
  i2cSend(humidity);
  i2cSend(moisture);
}

/**
 * \brief A helper function that sends sensor readings to the WiFi Module through i2c
 * 
 * i2c sends data a byte at a time and the data type cannot be double or float,
 * so bit and decimal manipulation is needed. The sensor readings are multipled by 100
 * and is sent as an int with a length of 2 bytes. The first write is the high byte
 * of the readings. The second write is the low byte of the readings. The sensor readings
 * are divided by 100.0 by the WiFi Module to get the original result.
 */
void i2cSend(double reading){
  int toSend = reading * 100;

  byte toSendHigh = highByte(toSend);
  byte toSendLow = lowByte(toSend);

  Wire.write(toSendHigh);
  Wire.write(toSendLow);
}

/**
 * \brief A helper function that converts celsius to fahrenheit
 * 
 * @return A the fahrenheit conversion of the temperature
 */
double celsiusToFahrenheit(double celsius){
  return celsius * 9 / 5 + 32;
}

/**
 * \brief Prints the temperature, humidity, and moisture sensor readings for debugging purposes
 */
void printSensors(double &_temperature, double &_humidity, double &_moisture){
  Serial.print("Temperature: ");
  Serial.print(_temperature);
  Serial.print(" | Humidity: ");
  Serial.print(_humidity);
  Serial.print(" | Moisture: ");
  Serial.println(_moisture);
}

