import 'package:Vida/shared/dialog/dialog_helper.dart';
import 'package:Vida/views/login_screen/cubit/login_screen_cubit.dart';
import 'package:Vida/views/my_app_screen/cubit/my_app_cubit.dart';
import 'package:Vida/views/my_app_screen/my_app_screen.dart';
import 'package:Vida/views/profile_screen/cubit/profile_screen_cubit.dart';
import 'package:Vida/views/sign_up_screen/sign_up_screen.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../shared/consts/colors.dart';
import '../../shared/consts/space.dart';
import '../../shared/consts/text_style_log.dart';
import '../../shared/widget/main_button.dart';
import '../../shared/widget/text_fild.dart';

// ignore: must_be_immutable
class LoginScreen extends StatelessWidget {
  LoginScreen({super.key});
  TextEditingController username = TextEditingController();
  TextEditingController password = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) => LoginScreenCubit(),
      child: BlocListener<LoginScreenCubit, LoginScreenState>(
        listenWhen: (previous, current) =>
            previous.isLogin != current.isLogin && current.isLogin != null,
        listener: (context, state) {
          if (!state.isLogin!) {
            showMyDialog(context, content: "Wrong username or password")
                .then((value) {
              context
                  .read<LoginScreenCubit>()
                  .update((p0) => p0.copyWith(isLogin: null));
            });
            return;
          }
          context
              .read<ProfileScreenCubit>()
              .update((p0) => p0.copyWith(userModel: state.userModel));
          context
              .read<MyAppCubit>()
              .update((p0) => p0.copyWith(currentTab: BottomTab.DOWNLOAD));
        },
        child: BlocBuilder<LoginScreenCubit, LoginScreenState>(
          builder: (context, state) {
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
                                if (username.text.isNotEmpty &&
                                    password.text.isNotEmpty) {
                                  context
                                      .read<LoginScreenCubit>()
                                      .login(username.text, password.text);
                                } else {
                                  showMyDialog(context,
                                      content:
                                          "Username and password must be not blank!");
                                }
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
                                        builder: (context) => SignUpScreen()));
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
          },
        ),
      ),
    );
  }
}
