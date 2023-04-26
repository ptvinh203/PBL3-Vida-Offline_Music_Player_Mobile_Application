import 'package:Vida/consts/colors.dart';
import 'package:Vida/widget/text_show.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class ProfilePage extends StatelessWidget {
  const ProfilePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    //final controller = Get.put(ProfileController());
    TextEditingController userName = TextEditingController();
    TextEditingController userPass = TextEditingController();
    TextEditingController userEmail = TextEditingController();
    TextEditingController userPh = TextEditingController();
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
              const SizedBox(
                height: 30,
              ),
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
              const SizedBox(height: 50),

              // -- Form Fields
              Form(
                child: Column(
                  children: [
                    textShow(
                        txt: 'Huỳnh Hải Đăng',
                        controller: userName,
                        image: Icons.people),

                    const SizedBox(height: 5),
                    textShow(
                        txt: 'hhdforwork@gmail.com',
                        controller: userEmail,
                        image: Icons.email),
                    const SizedBox(height: 5),
                    textShow(
                        txt: '+840787614533',
                        controller: userPh,
                        image: Icons.phone),

                    const SizedBox(height: 240),

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
