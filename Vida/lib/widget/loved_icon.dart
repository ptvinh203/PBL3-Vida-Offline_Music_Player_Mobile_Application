// ignore_for_file: must_be_immutable

import 'package:flutter/material.dart';
import 'package:get/get_rx/src/rx_types/rx_types.dart';
import 'package:ionicons/ionicons.dart';

import '../consts/colors.dart';

class LovedIcon extends Icon {
  RxBool isLoved;
  LovedIcon({super.key, required this.isLoved})
      : super(Ionicons.heart,
            color: isLoved.value ? purpButton : littleWhite, size: 25);
}
