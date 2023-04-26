import 'package:Vida/views/download_page.dart';
import 'package:flutter/material.dart';
import 'package:ionicons/ionicons.dart';
import 'package:Vida/consts/colors.dart';
import 'package:Vida/views/login_page.dart';
import 'package:Vida/views/offline_page.dart';

import 'favourite_song.dart';

class MyBottomNavigationBar extends StatefulWidget {
  static final MyBottomNavigationBar instance = new MyBottomNavigationBar();
  const MyBottomNavigationBar({Key? key}) : super(key: key);

  @override
  State<MyBottomNavigationBar> createState() => _MyBottomNavigationBarState();
}

class _MyBottomNavigationBarState extends State<MyBottomNavigationBar> {
  int _selectedIndex = 0;
  final List<Widget> pages = [
    OfflinePage(),
    DownloadPage(),
    Favourite(),
    LoginPage(),
  ];

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        
        body: pages[_selectedIndex],
        bottomNavigationBar: BottomNavigationBar(
          
          backgroundColor: blackBG,
          selectedItemColor: purpButton,
          unselectedItemColor: grayText,
          type: BottomNavigationBarType.fixed,
          showSelectedLabels: false,
          showUnselectedLabels: false,
          items: const [
            BottomNavigationBarItem(
              icon: Icon(Ionicons.home_outline),
              label: "Online",
            ),
            BottomNavigationBarItem(
              icon: Icon(Ionicons.cloud_done_outline),
              label: "Offline",
            ),
            BottomNavigationBarItem(
              icon: Icon(Ionicons.heart_outline),
              label: "Loved",
            ),
            BottomNavigationBarItem(
              icon: Icon(Ionicons.person_outline),
              label: "Profile",
            )
          ],
          currentIndex: _selectedIndex,
          onTap: _onItemTapped,
        ),
      ),
    );
  }
}
