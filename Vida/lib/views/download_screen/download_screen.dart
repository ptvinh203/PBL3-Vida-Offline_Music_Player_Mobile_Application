import 'package:Vida/models/user_model.dart';
import 'package:Vida/shared/dialog/dialog_helper.dart';
import 'package:Vida/views/download_screen/components/empty_song_download_list.dart';
import 'package:Vida/views/download_screen/components/song_download_item.dart';
import 'package:Vida/views/download_screen/cubit/download_screen_cubit.dart';
import 'package:Vida/views/my_app_screen/cubit/my_app_cubit.dart';
import 'package:Vida/views/my_app_screen/my_app_screen.dart';
import 'package:Vida/views/profile_screen/cubit/profile_screen_cubit.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:get/get_rx/src/rx_workers/utils/debouncer.dart';
import 'package:ionicons/ionicons.dart';

import '../../shared/consts/colors.dart';

class DownloadScreen extends StatelessWidget {
  DownloadScreen({super.key});
  final Debouncer _debouncer =
      Debouncer(delay: const Duration(milliseconds: 300));

  @override
  Widget build(BuildContext context) {
    return MultiBlocListener(
      listeners: [
        BlocListener<DownloadScreenCubit, DownloadScreenState>(
          listenWhen: (previous, current) =>
              previous.errorMessage != current.errorMessage &&
              current.errorMessage != null,
          listener: (context, state) {
            showMyDialog(context, title: "Error", content: state.errorMessage)
                .then((value) {
              context
                  .read<DownloadScreenCubit>()
                  .update((p0) => p0.copyWith(errorMessage: null));
            });
          },
        ),
        BlocListener<DownloadScreenCubit, DownloadScreenState>(
          listenWhen: (previous, current) =>
              previous.fileExisted != current.fileExisted &&
              current.fileExisted != null,
          listener: (context, state) {
            if (state.fileExisted!) {
              showMyDialog(context, content: "This song already exists!")
                  .then((value) {
                context
                    .read<DownloadScreenCubit>()
                    .update((p0) => p0.copyWith(fileExisted: null));
              });
            }
          },
        ),
        BlocListener<DownloadScreenCubit, DownloadScreenState>(
          listenWhen: (previous, current) =>
              previous.downloadSuccess != current.downloadSuccess &&
              current.downloadSuccess != null,
          listener: (context, state) {
            if (state.downloadSuccess!) {
              showMyDialog(context,
                      title: "Download Successfully",
                      content: "The song is downloaded successfully!")
                  .then((value) {
                context
                    .read<DownloadScreenCubit>()
                    .update((p0) => p0.copyWith(downloadSuccess: null));
              });
            }
          },
        ),
        BlocListener<DownloadScreenCubit, DownloadScreenState>(
          listenWhen: (previous, current) =>
              previous.isDownloading != current.isDownloading &&
              current.isDownloading != null,
          listener: (context, state) {
            if (state.isDownloading!) {
              context
                  .read<DownloadScreenCubit>()
                  .update((p0) => p0.copyWith(downloadingContext: context));
              showMyDialog(
                context,
                title: "Download Successfully",
                content: "The song is downloaded successfully!",
              );
            } else if (state.downloadingContext != null) {
              Navigator.pop(state.downloadingContext!);
              context
                  .read<DownloadScreenCubit>()
                  .update((p0) => p0.copyWith(downloadingContext: null));
            }
          },
        ),
      ],
      child: BlocBuilder<DownloadScreenCubit, DownloadScreenState>(
        builder: (context, state) {
          return Scaffold(
            appBar: AppBar(
              elevation: 0,
              toolbarHeight: 200,
              backgroundColor: blackBG,
              foregroundColor: littleWhite,
              title: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const SizedBox(height: 5),
                  Column(
                    children: [
                      Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Icon(Ionicons.headset, color: purpButton, size: 60),
                          SizedBox(
                            width: 5,
                          ),
                          Padding(
                            padding: const EdgeInsets.only(top: 12),
                            child: Text(
                              "Vida",
                              style: TextStyle(
                                  color: flutterPurple,
                                  fontSize: 42,
                                  fontWeight: FontWeight.bold,
                                  fontFamily: 'Comfortaa'),
                            ),
                          ),
                        ],
                      ),
                      SizedBox(height: 46),
                      Container(
                        height: 55.0,
                        padding: EdgeInsets.all(1),
                        decoration: BoxDecoration(
                          color: purpButton,
                          borderRadius: BorderRadius.circular(20.0),
                        ),
                        width: 350.0,
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Container(
                              decoration: BoxDecoration(
                                color: blackTextFild,
                                borderRadius: BorderRadius.circular(20.0),
                              ),
                              height: 55.0,
                              width: 348.0,
                              child: TextField(
                                textAlignVertical: TextAlignVertical.center,
                                decoration: InputDecoration(
                                  contentPadding: EdgeInsets.only(top: 5),
                                  prefixIcon: Icon(Icons.search),
                                  prefixIconColor: purpButton,
                                  border: InputBorder.none,
                                  hintText: "Search song",
                                  hintStyle: TextStyle(
                                      fontSize: 16, color: littleWhite),
                                ),
                                onChanged: (value) {
                                  _debouncer.call(() {
                                    context
                                        .read<DownloadScreenCubit>()
                                        .searchSong(value);
                                  });
                                },
                                style: TextStyle(fontSize: 16, color: white),
                                // onChanged: searchSong,
                              ),
                            ),
                          ],
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ),
            backgroundColor: blackBG,
            body: BlocSelector<ProfileScreenCubit, ProfileScreenState,
                UserModel?>(
              selector: (state) => state.userModel,
              builder: (context, userModel) {
                return RefreshIndicator(
                  onRefresh:
                      context.read<DownloadScreenCubit>().getSongDownloads,
                  backgroundColor: purpButton,
                  color: white,
                  strokeWidth: 4,
                  displacement: 50,
                  child: SafeArea(
                    child: ListView(
                        physics: BouncingScrollPhysics(
                            parent: AlwaysScrollableScrollPhysics()),
                        padding: const EdgeInsets.all(14),
                        children: [
                          if (state.connectionChecked == null)
                            const Center(
                                child: CircularProgressIndicator(
                                    backgroundColor: Colors.deepPurpleAccent))
                          else if (state.searchSongs.isEmpty)
                            EmptySongDownloadList()
                          else
                            ...(state.searchSongs
                                .map((e) => SongDownloadItem(
                                      songModelDownload: e,
                                      onTap: () {
                                        userModel == null
                                            ? showLoginDialog(
                                                context,
                                                content:
                                                    "You have to log in to use this feature!",
                                                onLogin: () {
                                                  context
                                                      .read<MyAppCubit>()
                                                      .update((p0) =>
                                                          p0.copyWith(
                                                              currentTab:
                                                                  BottomTab
                                                                      .PROFILE));
                                                },
                                              )
                                            : context
                                                .read<DownloadScreenCubit>()
                                                .download(e);
                                      },
                                    ))
                                .toList())
                        ]),
                  ),
                );
              },
            ),
          );
        },
      ),
    );
  }
}
