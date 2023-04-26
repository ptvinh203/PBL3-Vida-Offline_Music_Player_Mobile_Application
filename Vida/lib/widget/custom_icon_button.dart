// ignore_for_file: public_member_api_docs, sort_constructors_first
import 'package:flutter/material.dart';

class CustomIconButton extends StatelessWidget {
  const CustomIconButton({
    Key? key,
    required this.icon,
  }) : super(key: key);
  final Widget icon;
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(15.0),
      child: IconButton(
        onPressed: () {},
        constraints: const BoxConstraints.tightFor(width: 40),
        color: Colors.black54,
        icon: icon,
        splashRadius: 22,
      ),
    );
  }
}
