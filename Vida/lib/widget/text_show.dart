import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:Vida/consts/colors.dart';
import 'package:Vida/consts/text_style_log.dart';

Widget textShow({
  required String txt,
 
  required IconData image,
}) {
  return Container(
    height: 70.0,
    padding: EdgeInsets.symmetric(horizontal: 30.0),
    margin: EdgeInsets.symmetric(
      horizontal: 20.0,
      vertical: 10.0,
    ),
    decoration: BoxDecoration(
      color: blackTextFild,
      borderRadius: BorderRadius.circular(20.0),
    ),
    child: Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Icon(
          image,
          size: 20,
          color: purpButton,
        ),
        Container(
          width: 245,
          child: Text(
            txt,
            style: TextStyle(
                color: littleWhite, fontWeight: FontWeight.bold, fontSize: 16),
          ),
        ),
      ],
    ),
  );
}
