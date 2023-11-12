import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:Vida/shared/consts/colors.dart';
import 'package:Vida/shared/consts/text_style_log.dart';

Widget textFild({
  required String hintTxt,
  required TextEditingController controller,
  bool isObs = false,
  TextInputType? keyBordType,
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
        Container(
          width: 270.0,
          child: TextField(
            controller: controller,
            textAlignVertical: TextAlignVertical.center,
            obscureText: isObs,
            keyboardType: keyBordType,
            decoration: InputDecoration(
              border: InputBorder.none,
              hintText: hintTxt,
              hintStyle: hintStyle,
            ),
            style: headline2,
          ),
        ),
        CupertinoButton(
          padding: EdgeInsets.zero,
          minSize: 20,
          child: Icon(
            image,
            size: 20,
            color: grayText,
          ),
          onPressed: () {},
        ),
      ],
    ),
  );
}
