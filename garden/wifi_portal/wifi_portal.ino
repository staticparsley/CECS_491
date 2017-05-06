/**
 * \file wifi_portal.ino
 * \brief This is the ESP8266 WiFi module for the garden. It handles communication with the server
 */

#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <ArduinoJson.h>

#include <Wire.h>
#include "credentials.h"

#define WIFI_DELAY 500 // time to retry connection in ms
#define UPDATE_RATE 1000 // frequency of sending POST request in ms
#define ARDUINO_CHANNEL 17 // i2c channel that Arduino UNO is on

// Clients
WiFiClient client;

// Variables for sensor readings
float temperature;
float humidity;
float moisture;

/**
 * \brief Initializes i2c communication with the garden and WiFi connection to the server
 */
void setup() {
  // Start i2c communication as master
  Wire.begin();

  // Initialize LED indicators, the logic is inverted on the ESP8266.
  // HIGH is off, LOW is on.
  pinMode(D0, OUTPUT); // This is the built-in red LED
  digitalWrite(D0, HIGH);

  pinMode(D4, OUTPUT); // This is the built-in blue LED
  digitalWrite(D4, HIGH);

  // Start serial communication for debug messages
  Serial.begin(115200);
  Serial.println();

  Serial.print("Initializing WiFi connection");

  // Initialize WiFi settings
  WiFi.mode(WIFI_STA);
  WiFi.begin(WIFI_SSID, WIFI_PASS);

  while (WiFi.status() != WL_CONNECTED){
    delay(500);
    Serial.print(".");
  }

  Serial.println("OK!");

  Serial.print("IP Address: ");
  Serial.println(WiFi.localIP());

  digitalWrite(D0, LOW);
}

/**
 * \brief Handles the POST request
 */
void loop() {
  // TODO: Check to see if WiFi is still connected. If not, retry to connect

  digitalWrite(D4, LOW);
  postReadings();
  digitalWrite(D4, HIGH);
  delay(UPDATE_RATE);
  
}

/**
 * \brief Gets the readings from the garden, prepares POST request, and sends POST request
 */
void postReadings() {
  // Get the readings from the garden
  String readings = getReadings();
  HTTPClient http;

  // Attempt to connect to the server. If the connection is unsuccessful, return and try again
  if (!client.connect(SERVER, PORT)){
    Serial.println("Connection failed!");
    return;
  }
  
  Serial.println("Connection success!");
  Serial.print("Sending POST request...");

  // Build POST request and send
  http.begin(String(SERVER), PORT, String(SERVER_URI));
  http.addHeader("Content-Type", "application/json");
  http.POST(readings);
  http.end();

  Serial.println("OK!");
}

/**
 * \brief A helper function that gets the sensor readings and places the data in JSON format
 * 
 * @return A string of the JSON Object
 */
String getReadings() {
  // Request sensor readings from Aruduino UNO
  Wire.flush();
  Wire.requestFrom(ARDUINO_CHANNEL, 6);

  // Get sensor readings from Arduino UNO. The sensor readings are sent in this order:
  // temperature, humidity, and moisture.
  float temperature = i2cRead();
  float humidity = i2cRead();
  float moisture = i2cRead();

  // Build JSON Object
  StaticJsonBuffer<200> jsonBuffer;
  JsonObject& sensorReadings = jsonBuffer.createObject();

  // Insert sensor readings to JSON object
  sensorReadings["temperature"] = temperature;
  sensorReadings["humidity"] = humidity;
  sensorReadings["moisture"] = moisture;

  // Prepare JSON Object for POST
  String jsonSensor;
  sensorReadings.printTo(jsonSensor);

  // Return JSON output
  return jsonSensor;
}

/**
 * \brief A helper function that gets the sensor readings from the garden via i2c
 * 
 * i2c sends data a byte at a time and the data type cannot be double or float,
 * so bit and decimal manipulation is needed. It is sent as an int with a length
 * of 2 bytes. The first read is the high byte of the sensor readings. It is
 * shifted 8 bits to the left, and ORed with the next reading. The reading is
 * then reconstructed by dividing 100.0.  The sensor readings are multiplied by
 * 100 from the garden side.
 * 
 * @return The float of the sensor reading.
 */
float i2cRead(){
  int toRead = Wire.read();
  toRead = toRead << 8;
  toRead = toRead | Wire.read();

  return toRead / 100.0;
}

