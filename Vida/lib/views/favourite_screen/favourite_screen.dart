import 'package:Vida/models/song_model_extended.dart';
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

class FavouriteScreen extends StatelessWidget {
  const FavouriteScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocSelector<OfflineScreenCubit, OfflineScreenState,
        List<SongModelExtended>>(
      selector: (state) => state.favouriteSongs,
      builder: (context, favouriteSongs) {
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
          body: Padding(
            padding: const EdgeInsets.all(10),
            child: ListView.builder(
                physics: BouncingScrollPhysics(
                    parent: AlwaysScrollableScrollPhysics()),
                itemCount: favouriteSongs.length,
                itemBuilder: (BuildContext context, int index) {
                  final song = favouriteSongs[index];
                  return Container(
                    margin: const EdgeInsets.only(bottom: 8),
                    child: ListTile(
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(13.0)),
                      tileColor: blackTextFild,
                      title: Text(song.songModel.title,
                          style: ourStyle(
                              fontWeight: FontWeight.bold, size: 15.0)),
                      subtitle: Text("${song.artistName}",
                          style: ourStyle(size: 15.0)),
                      leading: Container(
                        padding: const EdgeInsets.all(1.0),
                        decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(50),
                            color: purpButton),
                        child: QueryArtworkWidget(
                          artworkQuality: FilterQuality.high,
                          keepOldArtwork: true,
                          id: song.songModel.id,
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
                              context
                                  .read<OfflineScreenCubit>()
                                  .removeFavouriteSong(
                                      (item) => item.id == song.id);
                              context
                                  .read<OfflineScreenCubit>()
                                  .updateOfflineSongs(
                                      onUpdate: (item) {
                                        item.isLoved = false;
                                        return item;
                                      },
                                      condition: (item) => item.id == song.id);
                            },
                            child: LovedIcon(isLoved: song.isLoved),
                          )),
                      onTap: () {
                        Get.to(
                          () => PlayerScreen(
                              currentIndex: index,
                              songList: favouriteSongs
                                  .map<SongModel>((e) => e.songModel)
                                  .toList()),
                        );
                      },
                    ),
                  );
                }),
          ),
        );
      },
    );
  }
}
