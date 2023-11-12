import 'dart:io';

import 'package:Vida/shared/dialog/dialog_helper.dart';
import 'package:Vida/shared/widget/loved_icon.dart';
import 'package:Vida/views/offline_screen/cubit/offline_screen_cubit.dart';
import 'package:Vida/views/player_screen/player_screen.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:get/get.dart';
import 'package:ionicons/ionicons.dart';
import 'package:on_audio_query/on_audio_query.dart';

import '../../shared/consts/colors.dart';
import '../../shared/consts/text_style.dart';

class OfflineScreen extends StatelessWidget {
  const OfflineScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<OfflineScreenCubit, OfflineScreenState>(
      builder: (context, state) {
        return Scaffold(
          backgroundColor: blackBG,
          appBar: AppBar(
            elevation: 0,
            toolbarHeight: 100,
            backgroundColor: blackBG,
            foregroundColor: littleWhite,
            title: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const SizedBox(height: 5),
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
              ],
            ),
          ),
          body: RefreshIndicator(
            onRefresh: context.read<OfflineScreenCubit>().getOfflineSongs,
            backgroundColor: purpButton,
            color: white,
            strokeWidth: 4,
            displacement: 50,
            child: Builder(builder: (context) {
              if (state.listOfflineSongs == null) {
                return const Center(
                    child: CircularProgressIndicator(
                        backgroundColor: Colors.deepPurpleAccent));
              } else if (state.listOfflineSongs!.isEmpty) {
                return Center(
                    child: Text("No song found in local storage",
                        style: ourStyle()));
              } else {
                return Padding(
                  padding: const EdgeInsets.all(10),
                  child: ListView.builder(
                      physics: BouncingScrollPhysics(
                          parent: AlwaysScrollableScrollPhysics()),
                      itemCount: state.listOfflineSongs!.length,
                      itemBuilder: (BuildContext context, int index) {
                        return Container(
                            margin: const EdgeInsets.only(bottom: 8),
                            child: ListTile(
                              shape: RoundedRectangleBorder(
                                  borderRadius: BorderRadius.circular(13.0)),
                              tileColor: blackTextFild,
                              title: Text(
                                  state
                                      .listOfflineSongs![index].songModel.title,
                                  style: ourStyle(
                                    color: white,
                                    fontWeight: FontWeight.bold,
                                    size: 15.0,
                                  )),
                              subtitle: Text(
                                  state.listOfflineSongs![index].artistName,
                                  style:
                                      ourStyle(size: 15.0, color: littleWhite)),
                              leading: Container(
                                padding: const EdgeInsets.all(1.0),
                                decoration: BoxDecoration(
                                    borderRadius: BorderRadius.circular(50),
                                    color: purpButton),
                                child: QueryArtworkWidget(
                                  artworkQuality: FilterQuality.high,
                                  quality: 100,
                                  keepOldArtwork: true,
                                  id: state
                                      .listOfflineSongs![index].songModel.id,
                                  type: ArtworkType.AUDIO,
                                  nullArtworkWidget: const Icon(
                                    Icons.music_note,
                                    color: whiteColor,
                                    size: 32,
                                  ),
                                ),
                              ),
                              trailing: SizedBox(
                                width: 86,
                                child: GestureDetector(
                                  onTap: () {
                                    (state.listOfflineSongs![index].isLoved)
                                        ? context
                                            .read<OfflineScreenCubit>()
                                            .removeFavouriteSong(
                                              (item) =>
                                                  item.id ==
                                                  state.listOfflineSongs![index]
                                                      .id,
                                            )
                                        : context
                                            .read<OfflineScreenCubit>()
                                            .addFavouriteSong(
                                                state.listOfflineSongs![index]);
                                    context
                                        .read<OfflineScreenCubit>()
                                        .updateOfflineSongs(
                                            onUpdate: (item) {
                                              item.isLoved = !item.isLoved;
                                              return item;
                                            },
                                            condition: (item) =>
                                                item.id ==
                                                state.listOfflineSongs![index]
                                                    .id);
                                  },
                                  child: LovedIcon(
                                      isLoved: state
                                          .listOfflineSongs![index].isLoved),
                                ),
                              ),
                              onLongPress: () async {
                                File file = File(state
                                    .listOfflineSongs![index].songModel.data);
                                showConfirmDialog(
                                  context,
                                  title: "Delete song",
                                  content:
                                      "Are you sure you want to delete this song?",
                                  onAccept: () {
                                    context
                                        .read<OfflineScreenCubit>()
                                        .deleteFile(file,
                                            state.listOfflineSongs![index]);
                                  },
                                );
                              },
                              onTap: () async {
                                Get.to(
                                  () => PlayerScreen(
                                      currentIndex: index,
                                      songList: state.listOfflineSongs!
                                          .map<SongModel>((e) => e.songModel)
                                          .toList()),
                                );
                              },
                            ));
                      }),
                );
              }
            }),
          ),
        );
      },
    );
  }
}
