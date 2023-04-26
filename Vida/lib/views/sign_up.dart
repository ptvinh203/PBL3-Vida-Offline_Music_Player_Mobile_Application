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
  TextEditingController userName = TextEditingController();
  TextEditingController userPass = TextEditingController();
  TextEditingController userEmail = TextEditingController();
  TextEditingController userPh = TextEditingController();
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
              SpaceVH(height: 10.0),
              Text(
                'Create new account',
                style: headline1,
              ),
              SpaceVH(height: 10.0),
              Text(
                'Please fill in the form to continue',
                style: headline3,
              ),
              SpaceVH(height: 60.0),
              textFild(
                controller: userName,
                image: CupertinoIcons.person,
                keyBordType: TextInputType.name,
                hintTxt: 'Full Name',
              ),
              textFild(
                controller: userEmail,
                keyBordType: TextInputType.emailAddress,
                image: CupertinoIcons.person,
                hintTxt: 'Email',
              ),
              textFild(
                controller: userPh,
                image: CupertinoIcons.phone,
                keyBordType: TextInputType.phone,
                hintTxt: 'Số điện thoại',
              ),
              textFild(
                controller: userPass,
                isObs: true,
                image: CupertinoIcons.lock,
                hintTxt: 'Mật khẩu',
              ),
              SpaceVH(height: 80.0),
              Mainbutton(
                onTap: () {},
                text: 'Đăng ký',
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
                      text: 'Đã có tài khoản? ',
                      style: headline.copyWith(
                        fontSize: 14.0,
                      ),
                    ),
                    TextSpan(
                      text: ' Đăng nhập',
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
