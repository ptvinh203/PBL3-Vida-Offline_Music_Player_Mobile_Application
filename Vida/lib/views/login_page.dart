import 'package:Vida/models/login_request.dart';
import 'package:Vida/services/user_service.dart';
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
  final UserService service = UserService.instance;
  TextEditingController username = TextEditingController();
  TextEditingController password = TextEditingController();
  Route<Object?> _dialogLoginFailedBuilder(BuildContext context, String noti) {
    return DialogRoute<void>(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text('Notification'),
          content: Text(noti),
          actions: <Widget>[
            TextButton(
              style: TextButton.styleFrom(
                textStyle: Theme.of(context).textTheme.labelLarge,
              ),
              child: const Text('Cancel'),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    if (service.loggedInUser != null)
      return ProfilePage(
        logoutCallback: () {
          setState(() {});
        },
      );
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
                controller: username,
                image: CupertinoIcons.person,
                hintTxt: 'User name',
              ),
              textFild(
                controller: password,
                image: CupertinoIcons.lock,
                isObs: true,
                hintTxt: 'Password',
              ),
              SpaceVH(height: 10.0),
              SpaceVH(height: 100.0),
              Align(
                alignment: Alignment.bottomCenter,
                child: Column(
                  children: [
                    Mainbutton(
                      onTap: () {
                        LoginRequest userModel = LoginRequest(
                            username: username.text, password: password.text);
                        service.login(userModel).then((value) {
                          service.loggedInUser = value;
                          print(value.toJson());
                          setState(() {});
                          MNavigator.instance.navigate(3);
                        }).onError((error, stackTrace) {
                          print(error);
                          print(stackTrace);
                          Navigator.push(
                              context,
                              _dialogLoginFailedBuilder(
                                  context, "Wrong username or password"));
                        });
                      },
                      text: 'Sign in',
                      btnColor: purpButton,
                    ),
                    SpaceVH(height: 20.0),
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
                            text: 'Don\'t have an account? ',
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
