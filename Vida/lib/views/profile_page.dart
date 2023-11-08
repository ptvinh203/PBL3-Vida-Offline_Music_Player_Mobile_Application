// ignore_for_file: must_be_immutable

import 'package:Vida/consts/colors.dart';
import 'package:Vida/services/user_service.dart';
import 'package:Vida/widget/main_button.dart';
import 'package:Vida/widget/text_show.dart';
import 'package:flutter/material.dart';

class ProfilePage extends StatefulWidget {
  Function? logoutCallback;
  ProfilePage({Key? key, this.logoutCallback}) : super(key: key);

  @override
  State<ProfilePage> createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  @override
  Widget build(BuildContext context) {
    // TextEditingController userName = TextEditingController();
    // TextEditingController userPass = TextEditingController();
    // TextEditingController userEmail = TextEditingController();
    // TextEditingController userPh = TextEditingController();
    UserService service = UserService.instance;
    return Scaffold(
      backgroundColor: blackBG,
      appBar: AppBar(
        title: Text('Profile',
            style: TextStyle(
                fontFamily: 'Comfortaa', fontSize: 30, color: flutterPurple)),
        leading: BackButton(
          color: purpButton,
        ),
        backgroundColor: blackBG,
        elevation: 0.0,
      ),
      body: SingleChildScrollView(
        child: Container(
          padding: const EdgeInsets.all(0.0),
          child: Column(
            children: [
              Stack(
                children: [
                  SizedBox(
                    width: 120,
                    height: 120,
                    child: ClipRRect(
                      borderRadius: BorderRadius.circular(100),
                      child: Image.network(
                        'https://pbs.twimg.com/media/FKNlhKZUcAEd7FY?format=jpg&name=4096x4096',
                        height: double.maxFinite,
                        width: 130,
                        fit: BoxFit.cover,
                      ),
                    ),
                  ),
                  Positioned(
                    bottom: 0,
                    right: 0,
                    child: Container(
                      width: 35,
                      height: 35,
                      decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(100),
                          color: purpButton),
                      child: Icon(Icons.camera, color: Colors.black, size: 20),
                    ),
                  ),
                ],
              ),
              SizedBox(
                height: 20,
              ),
              // -- Form Fields
              Form(
                child: Column(
                  children: [
                    textShow(
                        txt: "ID: ${service.loggedInUser!.userId.toString()}",
                        image: Icons.people),

                    const SizedBox(height: 5),
                    textShow(
                        txt: service.loggedInUser!.username.toString(),
                        image: Icons.email),
                    const SizedBox(height: 5),
                    textShow(
                        txt: service.loggedInUser!.fullName.toString(),
                        image: Icons.person),
                    const SizedBox(height: 5),
                    textShow(
                        txt: service.loggedInUser!.phoneNumber.toString(),
                        image: Icons.phone),
                    const SizedBox(height: 15),
                    Mainbutton(
                        onTap: () {
                          setState(() {
                            service.logout();
                            this.widget.logoutCallback!();
                          });
                        },
                        text: 'Log out',
                        btnColor: purpButton),
                    const SizedBox(height: 70),

                    // -- Form Submit Button

                    // -- Created Date and Delete Button
                    Text(
                      'Made by Huỳnh Hải Đăng',
                      style: TextStyle(color: littleWhite, fontSize: 11),
                    ),
                    Text(
                      'Phone: +840787614533',
                      style: TextStyle(color: littleWhite, fontSize: 8),
                    ),
                    Text(
                      'Email: hhdforwork@gmail.com',
                      style: TextStyle(color: littleWhite, fontSize: 8),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
