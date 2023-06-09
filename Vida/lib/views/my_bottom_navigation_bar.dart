import 'package:Vida/services/user_service.dart';
import 'package:Vida/views/download_page.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:ionicons/ionicons.dart';
import 'package:Vida/consts/colors.dart';
import 'package:Vida/views/login_page.dart';
import 'package:Vida/views/offline_page.dart';
import 'package:get/get.dart';
import 'favourite_song.dart';

class MNavigator {
  static MNavigator instance = MNavigator.__();

  var selectedIndex = 0.obs;
  UserService service = UserService.instance;
  final List<Widget> pages = [
    OfflinePage(),
    DownloadPage(),
    Favourite(),
    LoginPage(),
  ];
  //có 4 trang và nhiều widget khác

  MNavigator.__() {}

  navigate(int id) {
    selectedIndex.value = id;
  }

  reload() {
    selectedIndex.refresh();
  }
}

class MyBottomNavigationBar extends StatefulWidget {
  static final MyBottomNavigationBar instance = new MyBottomNavigationBar();
  const MyBottomNavigationBar({Key? key}) : super(key: key);

  @override
  State<MyBottomNavigationBar> createState() => _MyBottomNavigationBarState();
}

class _MyBottomNavigationBarState extends State<MyBottomNavigationBar> {
  // cái navigation dưới màn hình dùng để move giữa các trang

  void _onItemTapped(int index) {
    MNavigator.instance.navigate(index);
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        home: Obx(
          () => Scaffold(
            body: MNavigator.instance.pages[MNavigator.instance.selectedIndex
                .value], // phần dưới chỉ là setup các trang để move
            bottomNavigationBar: BottomNavigationBar(
              backgroundColor: blackBG,
              selectedItemColor: purpButton,
              unselectedItemColor: grayText,
              type: BottomNavigationBarType.fixed,
              showSelectedLabels: false,
              showUnselectedLabels: false,
              items: const [
                BottomNavigationBarItem(
                  icon: Icon(CupertinoIcons.music_house_fill),
                  label: "Offline",
                ),
                BottomNavigationBarItem(
                  icon: Icon(Ionicons.earth),
                  label: "Download",
                ),
                BottomNavigationBarItem(
                  icon: Icon(Ionicons.heart_half_sharp),
                  label: "Favourite",
                ),
                BottomNavigationBarItem(
                  icon: Icon(CupertinoIcons.person_crop_circle_fill),
                  label: "Profile",
                )
              ],
              currentIndex: MNavigator.instance.selectedIndex.value,
              onTap: _onItemTapped,
            ),
          ),
        ));
  }
}
