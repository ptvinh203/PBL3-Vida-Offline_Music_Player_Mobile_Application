import 'package:flutter/material.dart';
import 'package:ionicons/ionicons.dart';

import '../consts/colors.dart';

// ignore: must_be_immutable
class LovedIcon extends Icon {
  bool isLoved;
  LovedIcon({super.key, required this.isLoved})
      : super(Ionicons.heart,
            color: isLoved ? purpButton : littleWhite, size: 25);
}
