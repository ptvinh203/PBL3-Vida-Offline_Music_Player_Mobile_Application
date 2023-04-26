import 'package:Vida/views/download_page.dart';
import 'package:Vida/views/login_page.dart';
import 'package:Vida/views/my_bottom_navigation_bar.dart';
import 'package:Vida/views/profile_page.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:Vida/views/offline_page.dart';
import 'package:Vida/views/player.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      debugShowCheckedModeBanner: false,
      home: MyBottomNavigationBar(),
      title: 'Vida',
      theme: ThemeData(
          appBarTheme:
              AppBarTheme(backgroundColor: Colors.transparent, elevation: 0)),
    );
  }
}
