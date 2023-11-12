import 'package:Vida/models/user_model.dart';
import 'package:Vida/views/download_screen/download_screen.dart';
import 'package:Vida/views/favourite_screen/favourite_screen.dart';
import 'package:Vida/views/login_screen/login_screen.dart';
import 'package:Vida/views/offline_screen/offline_screen.dart';
import 'package:Vida/views/profile_screen/cubit/profile_screen_cubit.dart';
import 'package:Vida/views/profile_screen/profile_screen.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:ionicons/ionicons.dart';

import '../../shared/consts/colors.dart';
import 'cubit/my_app_cubit.dart';

enum BottomTab { OFFLINE, DOWNLOAD, FAVOURITE, PROFILE }

extension BottomTabExt on BottomTab {
  String get lable => {
        BottomTab.DOWNLOAD: "Download",
        BottomTab.OFFLINE: "Offline",
        BottomTab.FAVOURITE: "Favourite",
        BottomTab.PROFILE: "Profile",
      }[this]!;
  IconData get icon => {
        BottomTab.DOWNLOAD: Ionicons.earth,
        BottomTab.OFFLINE: CupertinoIcons.music_house_fill,
        BottomTab.FAVOURITE: Ionicons.heart_half_sharp,
        BottomTab.PROFILE: CupertinoIcons.person_crop_circle_fill,
      }[this]!;
}

class MyApp extends StatelessWidget {
  MyApp({super.key});

  final PageController _pageController = PageController();

  @override
  Widget build(BuildContext context) {
    return BlocListener<MyAppCubit, MyAppState>(
      listenWhen: (previous, current) =>
          previous.currentTab != current.currentTab,
      listener: (context, state) {
        _pageController.animateToPage(state.currentTab.index,
            duration: const Duration(milliseconds: 200), curve: Curves.linear);
      },
      child: BlocBuilder<MyAppCubit, MyAppState>(
        builder: (context, state) {
          return Scaffold(
            bottomNavigationBar: BottomNavigationBar(
              backgroundColor: blackBG,
              selectedItemColor: purpButton,
              unselectedItemColor: grayText,
              type: BottomNavigationBarType.fixed,
              showSelectedLabels: false,
              showUnselectedLabels: false,
              currentIndex: state.currentTab.index,
              items: BottomTab.values.map((e) {
                return BottomNavigationBarItem(
                    icon: Icon(e.icon), label: e.lable);
              }).toList(),
              onTap: (value) => context.read<MyAppCubit>().update(
                  (p0) => p0.copyWith(currentTab: BottomTab.values[value])),
            ),
            body: BlocSelector<ProfileScreenCubit, ProfileScreenState,
                UserModel?>(
              selector: (state) => state.userModel,
              builder: (context, userModel) {
                return PageView(
                  controller: _pageController,
                  physics: const NeverScrollableScrollPhysics(),
                  children: [
                    OfflineScreen(),
                    DownloadScreen(),
                    FavouriteScreen(),
                    (userModel == null) ? LoginScreen() : ProfileScreen(),
                  ],
                );
              },
            ),
          );
        },
      ),
    );
  }
}
