import 'package:Vida/views/download_screen/cubit/download_screen_cubit.dart';
import 'package:Vida/views/my_app_screen/my_app_screen.dart';
import 'package:Vida/views/offline_screen/cubit/offline_screen_cubit.dart';
import 'package:Vida/views/profile_screen/cubit/profile_screen_cubit.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:get/get.dart';
import 'package:permission_handler/permission_handler.dart';

import 'views/my_app_screen/cubit/my_app_cubit.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Permission.storage.request();
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});
  @override
  Widget build(BuildContext context) {
    return MultiBlocProvider(
      providers: [
        BlocProvider(create: (context) => MyAppCubit()),
        BlocProvider(create: (context) => ProfileScreenCubit()),
        BlocProvider(
            create: (context) => OfflineScreenCubit()..getOfflineSongs()),
        BlocProvider(
            create: (context) => DownloadScreenCubit()..getSongDownloads()),
      ],
      child: GetMaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'Vida',
        home: MyApp(),
      ),
    );
  }
}
