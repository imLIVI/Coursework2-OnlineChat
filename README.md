# Coursework2-OnlineChat
## Description
You need to develop two applications for exchanging text messages over the network using a console (terminal) between two or more users.

The first application, the chat **server**, should be waiting for users to connect.

The second application is a chat **client**, connects to the chat server and delivers and receives new messages.

All messages should be written to ```file.log``` on both the server and the clients. The ```file.log``` must be updated every time it is started, as well as when a message 
is sent or received. The exit from the chat must be carried out by the exit command.

## Server requirements
- [x] Setting the port for connecting clients via the settings file (for example, ```settings.txt``` );
- [x] The ability to connect to the server at any time and join the chat;
- [x] Sending new messages to clients;
- [x] A record of all messages sent via the server with the user name and the time of sending.

## Customer requirements
- [x] Choosing a name to participate in the chat;
- [x] Read the application settings from the settings file - for example, the server port number;
- [x] Connecting to the server specified in the settings;
- [x] To exit the chat, you need to type the exit command - ```/exit```;
- [x] Each message of the participants should be recorded in a text file - a logging file. Each time the application is launched, the file must be updated.

## Implementation requirements
- [x] The server must be able to simultaneously wait for new users and process incoming messages from users;
- [x] ```Gradle/Maven``` package collector is used;
- [x] The code is posted on ```github```;
- [x] The code is covered by ```unit tests```.

<a href="https://github.com/netology-code/jd-homeworks/blob/master/diploma/networkchat.md">(RUS version of description)</a>

## The algorithm of the program
<p align="center">
  <img src="https://user-images.githubusercontent.com/63547457/214442277-62a662b7-de9c-4310-82ba-8c114044c940.png" alt="drawing" style="width:1000px;"/>
</p>

## Example of program launch:
1. Run SerberLoader.class 

<p align="center">
  <img src="https://user-images.githubusercontent.com/63547457/214437975-12d6bd77-eef4-42b8-96a4-c2014d052332.png" alt="drawing" style="height:500px;"/>
  <img src="https://user-images.githubusercontent.com/63547457/214438352-8e40babd-0388-4d11-a574-6724cb973bcc.png" alt="drawing" style="width:500px;"/> 
</p>
2. Run multiple ClientLoader.class 

<p align="center">
  <img src="https://user-images.githubusercontent.com/63547457/214438196-b137307d-2ae3-4c70-9f32-060e2976027d.png" alt="drawing" style="height:450px;"/>
  <img src="https://user-images.githubusercontent.com/63547457/214438453-11a9c4f0-2563-4daa-a94c-442d1ef47b68.png" alt="drawing" style="width:500px;"/>
</p>

3. Write your name and message via enter in Client

<p align="center">
  <img src="https://user-images.githubusercontent.com/63547457/214439006-4f171e8c-a5fb-4808-b470-72438e1908f1.png" alt="drawing" style="width:850px;"/>
</p>

4. For exit enter "/exit"

<p align="center">
  <img src="https://user-images.githubusercontent.com/63547457/214439162-f8842168-bda4-4c22-a319-86e0182c2978.png" alt="drawing" style="width:850px;"/>
</p>
