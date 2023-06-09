import 'package:Vida/models/login_request.dart';
import 'package:Vida/models/register_request.dart';
import 'package:Vida/models/user_model.dart';
import 'package:Vida/services/user_service.dart';
import 'package:Vida/views/my_bottom_navigation_bar.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'package:Vida/consts/colors.dart';
import 'package:Vida/consts/space.dart';
import 'package:Vida/consts/text_style_log.dart';

import 'package:Vida/widget/main_button.dart';
import 'package:Vida/widget/text_fild.dart';

class SignUpPage extends StatefulWidget {
  const SignUpPage({Key? key}) : super(key: key);

  @override
  _SignUpPageState createState() => _SignUpPageState();
}

class _SignUpPageState extends State<SignUpPage> {
  TextEditingController usernameSignUp = TextEditingController();
  TextEditingController passwordSignUp = TextEditingController();
  TextEditingController fullNameSignUp = TextEditingController();
  TextEditingController phoneNumberSignUp = TextEditingController();
  final UserService service = UserService.instance;

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
              child: Text('Cancel', style: TextStyle(color: purpButton)),
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
    return Scaffold(
      appBar: AppBar(
        leading: BackButton(
          color: purpButton,
        ),
        backgroundColor: blackBG,
        elevation: 0.0,
      ),
      backgroundColor: blackBG,
      body: Padding(
        padding: EdgeInsets.all(0.0),
        child: SingleChildScrollView(
          child: Column(
            children: [
              Text(
                'Create new account',
                style: headline1,
              ),
              SpaceVH(height: 10.0),
              Text(
                'Please fill in the form to continue',
                style: headline3,
              ),
              SpaceVH(height: 40.0),
              textFild(
                controller: usernameSignUp,
                image: CupertinoIcons.person,
                keyBordType: TextInputType.name,
                hintTxt: 'Username',
              ),
              textFild(
                controller: passwordSignUp,
                isObs: true,
                image: CupertinoIcons.lock,
                hintTxt: 'Password',
              ),
              textFild(
                controller: fullNameSignUp,
                keyBordType: TextInputType.name,
                image: CupertinoIcons.person,
                hintTxt: 'Full name',
              ),
              textFild(
                controller: phoneNumberSignUp,
                keyBordType: TextInputType.phone,
                image: CupertinoIcons.phone,
                hintTxt: 'Phone Number',
              ),
              SpaceVH(height: 60.0),
              Mainbutton(
                onTap: () {
                  RegisterRequest userModel = RegisterRequest(
                      username: usernameSignUp.text,
                      password: passwordSignUp.text,
                      fullName: fullNameSignUp.text,
                      phoneNumber: phoneNumberSignUp.text);
                  service.register(userModel).then((value) {
                    Navigator.pop(context);
                  }).onError((error, stackTrace) {
                    print(error);
                    print(stackTrace);
                    Navigator.push(
                        context,
                        _dialogLoginFailedBuilder(
                            context, "Register failed, account already exist"));
                  });
                },
                text: 'Register',
                btnColor: purpButton,
              ),
              SpaceVH(height: 20.0),
              TextButton(
                onPressed: () {
                  Navigator.pop(context);
                },
                child: RichText(
                  text: TextSpan(children: [
                    TextSpan(
                      text: 'Already have an account?',
                      style: headline.copyWith(
                        fontSize: 14.0,
                      ),
                    ),
                    TextSpan(
                      text: ' Sign in',
                      style: headlineDot.copyWith(
                        fontSize: 14.0,
                      ),
                    ),
                  ]),
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
