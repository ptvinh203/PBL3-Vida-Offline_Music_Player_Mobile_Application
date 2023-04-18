import 'package:flutter/material.dart';

const bgcolor = Colors.white10;
const whiteColor = Colors.white;
final sliderColor = Color.fromARGB(255, 170, 196, 255);
final thumbColor = Color.fromARGB(255, 97, 85, 166);
final bar = Colors.black;
const buttonColor = Colors.blue;
const bgDarkColor = Colors.black;
const gradientpurp = LinearGradient(
  begin: Alignment.topRight,
  end: Alignment.bottomLeft,
  colors: <Color>[Color(0xffDA44bb), Color(0xff8921aa)],
);
final Shader linearGradient = const LinearGradient(
  colors: <Color>[
    Color(0xffDA44bb),
    Color(0xff8921aa),
    Color(0xffDA44bb),
    Color(0xff8921aa),
    Color(0xffDA44bb),
  ],
).createShader(const Rect.fromLTWH(0.0, 0.0, 200.0, 70.0));

final flutterPurple = Color.fromARGB(255, 178, 164, 255);
final homeBGColor = Color.fromARGB(255, 97, 85, 166);
