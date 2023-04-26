import 'package:Vida/views/offline_page.dart';
import 'package:Vida/views/my_bottom_navigation_bar.dart';
import 'package:Vida/views/profile_page.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:Vida/consts/colors.dart';
import 'package:Vida/consts/space.dart';
import 'package:Vida/consts/text_style_log.dart';
import 'package:Vida/views/sign_up.dart';
import 'package:Vida/widget/main_button.dart';
import 'package:Vida/widget/text_fild.dart';
import 'package:get/get.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  TextEditingController phoneNum = TextEditingController();
  TextEditingController userPass = TextEditingController();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: blackBG,
      body: Padding(
        padding: EdgeInsets.only(top: 50.0),
        child: SingleChildScrollView(
          child: Column(
            children: [
              SpaceVH(height: 50.0),
              Text(
                'Welcome Back!',
                style: headline1,
              ),
              SpaceVH(height: 10.0),
              Text(
                'Please sign in to your account',
                style: headline3,
              ),
              SpaceVH(height: 60.0),
              textFild(
                controller: phoneNum,
                image: CupertinoIcons.person,
                hintTxt: 'User name',
              ),
              textFild(
                controller: userPass,
                image: CupertinoIcons.lock,
                isObs: true,
                hintTxt: 'Password',
              ),
              SpaceVH(height: 10.0),
              Align(
                alignment: Alignment.centerRight,
                child: Padding(
                  padding: EdgeInsets.only(right: 20.0),
                  child: TextButton(
                    onPressed: () {},
                    child: Text(
                      'Forgot Password?',
                      style: headline3,
                    ),
                  ),
                ),
              ),
              SpaceVH(height: 100.0),
              Align(
                alignment: Alignment.bottomCenter,
                child: Column(
                  children: [
                    Mainbutton(
                      onTap: () {
                        Get.to(() => ProfilePage());
                      },
                      text: 'Sign in',
                      btnColor: purpButton,
                    ),
                    SpaceVH(height: 20.0),
                    Mainbutton(
                      onTap: () {},
                      text: 'Sign in with google',
                      image: 'google.png',
                      btnColor: white,
                      txtColor: blackBG,
                    ),
                    SpaceVH(height: 20.0),
                    TextButton(
                      onPressed: () {
                        Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: (builder) => SignUpPage()));
                      },
                      child: RichText(
                        text: TextSpan(children: [
                          TextSpan(
                            text: 'Don\' have an account? ',
                            style: headline.copyWith(
                              fontSize: 14.0,
                            ),
                          ),
                          TextSpan(
                            text: ' Sign Up',
                            style: headlineDot.copyWith(
                              fontSize: 14.0,
                            ),
                          ),
                        ]),
                      ),
                    )
                  ],
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
