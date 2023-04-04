import 'package:client/View/enjoy_music.dart';
import 'package:client/View/homepage.dart';
import 'package:flutter/material.dart';
import 'View/listenpage.dart';

void main() {
  runApp(MaterialApp(home: EnjoyMusic()));
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Center(
          child: Text('Hello World!'),
        ),
      ),
    );
  }
}
