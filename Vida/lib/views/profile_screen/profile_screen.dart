import 'package:Vida/views/profile_screen/cubit/profile_screen_cubit.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../shared/consts/colors.dart';
import '../../shared/widget/main_button.dart';
import '../../shared/widget/text_show.dart';

class ProfileScreen extends StatelessWidget {
  const ProfileScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<ProfileScreenCubit, ProfileScreenState>(
      builder: (context, state) {
        return Scaffold(
          backgroundColor: blackBG,
          appBar: AppBar(
            title: Text('Profile',
                style: TextStyle(
                    fontFamily: 'Comfortaa',
                    fontSize: 30,
                    color: flutterPurple)),
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
                          child:
                              Icon(Icons.camera, color: Colors.black, size: 20),
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
                            txt: "ID: ${state.userModel!.userId.toString()}",
                            image: Icons.people),
                        const SizedBox(height: 5),
                        textShow(
                            txt: state.userModel!.username.toString(),
                            image: Icons.email),
                        const SizedBox(height: 5),
                        textShow(
                            txt: state.userModel!.fullName.toString(),
                            image: Icons.person),
                        const SizedBox(height: 5),
                        textShow(
                            txt: state.userModel!.phoneNumber.toString(),
                            image: Icons.phone),
                        const SizedBox(height: 15),
                        Mainbutton(
                            onTap: () {
                              context
                                  .read<ProfileScreenCubit>()
                                  .update((p0) => p0.copyWith(userModel: null));
                            },
                            text: 'Log out',
                            btnColor: purpButton),
                        const SizedBox(height: 70),
                        Text(
                          'Made by Huỳnh Hải Đăng & Phạm Thành Vinh',
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
      },
    );
  }
}
