import 'package:Vida/models/user_model.dart';
import 'package:Vida/shared/dialog/dialog_helper.dart';
import 'package:Vida/views/sign_up_screen/cubit/sign_up_screen_cubit.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../shared/consts/colors.dart';
import '../../shared/consts/space.dart';
import '../../shared/consts/text_style_log.dart';
import '../../shared/widget/main_button.dart';
import '../../shared/widget/text_fild.dart';

// ignore: must_be_immutable
class SignUpScreen extends StatelessWidget {
  SignUpScreen({super.key});
  TextEditingController usernameSignUp = TextEditingController();
  TextEditingController passwordSignUp = TextEditingController();
  TextEditingController fullNameSignUp = TextEditingController();
  TextEditingController phoneNumberSignUp = TextEditingController();
  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) => SignUpScreenCubit(),
      child: BlocListener<SignUpScreenCubit, SignUpScreenState>(
        listenWhen: (previous, current) =>
            previous.isSignUpSuccess != current.isSignUpSuccess &&
            current.isSignUpSuccess != null,
        listener: (context, state) {
          if (!state.isSignUpSuccess!) {
            showMyDialog(context,
                    content: "Register failed, account already exist")
                .then((value) {
              context
                  .read<SignUpScreenCubit>()
                  .update((p0) => p0.copyWith(isSignUpSuccess: null));
            });
            return;
          } else {
            showMyDialog(context, content: "Register successfully!")
                .then((value) {
              Navigator.pop(context);
            });
          }
        },
        child: LayoutBuilder(
          builder: (context, constraints) => Scaffold(
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
                        if (usernameSignUp.text.isNotEmpty &&
                            fullNameSignUp.text.isNotEmpty &&
                            phoneNumberSignUp.text.isNotEmpty &&
                            passwordSignUp.text.isNotEmpty) {
                          context.read<SignUpScreenCubit>().signUp(
                              UserModel(
                                  username: usernameSignUp.text,
                                  fullName: fullNameSignUp.text,
                                  phoneNumber: phoneNumberSignUp.text),
                              passwordSignUp.text);
                        } else {
                          showMyDialog(context,
                              content: "Please fill full information!");
                        }
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
          ),
        ),
      ),
    );
  }
}
