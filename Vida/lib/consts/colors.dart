import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';

const Color blueButton = Color(0xFFFF6D00);
const Color blueText = Colors.deepPurpleAccent;
const Color purpButton = Colors.deepPurpleAccent;

const Color grayText = Color(0xFF5D5F65);
const Color whiteText = Color(0xFFEDEEEF);

const Color white = Color(0xFFFFFFFF);
const Color black = Color(0xFF000000);

const Color blackBG = Color(0xFF181A20);
const Color blackTextFild = Color(0xFF262A34);

const List<Color> gradient = [
  Color.fromRGBO(24, 26, 32, 1),
  Color.fromRGBO(24, 26, 32, 0.9),
  Color.fromRGBO(24, 26, 32, 0.8),
  Color.fromRGBO(24, 26, 32, 0.7),
  Color.fromRGBO(24, 26, 32, 0.6),
  Color.fromRGBO(24, 26, 32, 0.5),
  Color.fromRGBO(24, 26, 32, 0.4),
  Color.fromRGBO(24, 26, 32, 0.0),
];
const bgcolor = Colors.white10;
const whiteColor = Colors.white;
final sliderColor = Color.fromARGB(255, 170, 196, 255);
final thumbColor = Color.fromARGB(255, 97, 85, 166);
final bar = Colors.black;
const buttonColor = Colors.blue;
const bgDarkColor = Colors.black;

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
