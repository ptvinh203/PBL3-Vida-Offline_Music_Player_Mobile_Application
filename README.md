# PBL3 - Vida - Offline Music Player Mobile Application
## Summary
Project Base Learning at DUT University about application programming that allows user listen to music from local storage, download music from server and make playlists according to your hobby. 
- What does this project has?
  * A local host server is used to storing data and business logic of application
  * A mobile application for user
  * A desktop application for admin that allows managing objects of application

- Technology and Framework were used in this project:
  * Server: Spring Boot
  * Mobile client-application: Flutter
  * Desktop client-application: Java Swing
## Prerequirements

Before you continue, ensure you meet the following requirments:
  - Java Development Kit (JDK) 19
  - Spring Boot version 3.1.0
## How to use?

### 1. Download database
- Download database files from link: [One-drive](https://dutudn-my.sharepoint.com/:f:/g/personal/102210048_sv1_dut_udn_vn/EmQp_wu4Ua1PmadPZM_39A4Bh9mNMa3S2DlJAKMeiDdvpQ?e=oDrYXA)
- Delete 'database.mv.db' file and 'database.trace.db' file in 'Server' folder
- Copy downloaded files and paste into 'Server' folder
### 2. Config IP in client application in order to connect to Server successfully
- Check server's ip: At the computer which runs server, open terminal:

```bash
C:\Users\DELL> ipconfig
```
`<Example Result>`
```bash
Windows IP Configuration


Ethernet adapter Ethernet:

   Media State . . . . . . . . . . . : Media disconnected
   Connection-specific DNS Suffix  . :

Wireless LAN adapter Local Area Connection* 1:

   Media State . . . . . . . . . . . : Media disconnected
   Connection-specific DNS Suffix  . :

Wireless LAN adapter Wi-Fi:

   Connection-specific DNS Suffix  . :
   Link-local IPv6 Address . . . . . : fe80::c749:f883:8e7b:27fe%15
   IPv4 Address. . . . . . . . . . . : 192.168.6.3
   Subnet Mask . . . . . . . . . . . : 255.255.255.0
   Default Gateway . . . . . . . . . : 192.168.6.1
```
**Then, copy IPv4 Address (In this Ex: 192.168.6.3)**

- Flutter App's Configuration: Paste to config.dart file (**dir**: Vida\lib\services\config.dart)
- Java Swing App;s Configuration: Paste to Config_URL.java file (**dir**: AdminPage\app\src\main\java\httprequest\config\Config_URL.java)
- Server Configuration: Paste to Config_IP.java file (**dir**: Server\src\main\java\com\pbl3\musicapplication\model\model\config\Config_IP.java)
### Now, You can run application!
## Demo Mobile Application GUI
<div>
<img src="https://github.com/thanhvinh73/PBL3/blob/main/document/MusicPlayer.jpeg" width="180" height="400" />
&emsp;&ensp;
<img src="https://github.com/thanhvinh73/PBL3/blob/main/document/HomePage.jpeg" width="180" height="400" />
&emsp;&ensp;
<img src="https://github.com/thanhvinh73/PBL3/blob/main/document/DownloadPage.jpeg" width="180" height="400" />
&emsp;&ensp;
<img src="https://github.com/thanhvinh73/PBL3/blob/main/document/FavouritePlaylistPage.jpeg" width="180" height="400" />
<div/>
<br>
<br>
<div>
<img src="https://github.com/thanhvinh73/PBL3/blob/main/document/LoginPage.jpeg" width="180" height="400" />
&emsp;&ensp;
<img src="https://github.com/thanhvinh73/PBL3/blob/main/document/Register.jpeg" width="180" height="400" />
&emsp;&ensp;
<img src="https://github.com/thanhvinh73/PBL3/blob/main/document/Profile.jpeg" width="180" height="400" />
<div/>

## Demo read api from server
<img src="https://github.com/thanhvinh73/PBL3/blob/main/document/DemoReadApi.png" />
