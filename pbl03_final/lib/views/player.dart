import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:just_audio/just_audio.dart';
import 'package:on_audio_query/on_audio_query.dart';
import 'package:pbl03_final/consts/colors.dart';
import 'package:pbl03_final/consts/text_style.dart';
import 'package:pbl03_final/controllers/player_controller.dart';

class Player extends StatelessWidget {
  final List<SongModel> songList;
  const Player({super.key, required this.songList});

  @override
  Widget build(BuildContext context) {
    var controller = Get.find<PlayerController>();
    return Scaffold(
      backgroundColor: flutterPurple,
      appBar: AppBar(),
      body: Padding(
        padding: const EdgeInsets.all(12.0),
        child: Column(
          children: [
            Obx(
              () => Expanded(
                child: Container(
                  clipBehavior: Clip.antiAliasWithSaveLayer,
                  decoration: const BoxDecoration(
                    shape: BoxShape.circle,
                    color: Colors.white,
                    boxShadow: [
                      BoxShadow(
                        color: Colors.black54,
                        blurRadius: 30,
                        offset: Offset(12, 20), // Shadow position
                      ),
                    ],
                  ),
                  height: 330,
                  width: 330,
                  alignment: Alignment.center,
                  child: QueryArtworkWidget(
                    id: songList[controller.playIndex.value].id,
                    type: ArtworkType.AUDIO,
                    artworkHeight: double.infinity,
                    artworkWidth: double.infinity,
                    nullArtworkWidget: const Icon(Icons.music_note,
                        size: 48, color: bgDarkColor),
                  ),
                ),
              ),
            ),
            const SizedBox(height: 20),
            Expanded(
                child: Container(
              alignment: Alignment.center,
              decoration: BoxDecoration(
                  color: Colors.white,
                  boxShadow: [
                    BoxShadow(
                      color: Colors.black54.withOpacity(0.6),
                      blurRadius: 30,
                      offset: Offset(14, 20), // Shadow position
                    ),
                  ],
                  borderRadius: BorderRadius.vertical(
                      top: Radius.circular(30.0),
                      bottom: Radius.circular(30.0))),
              child: Obx(
                () => Column(
                  children: [
                    const SizedBox(height: 30),
                    Text(songList[controller.playIndex.value].title,
                        textAlign: TextAlign.center,
                        overflow: TextOverflow.ellipsis,
                        maxLines: 2,
                        style: TextStyle(
                            fontSize: 25.0,
                            fontWeight: FontWeight.bold,
                            foreground: Paint()..shader = linearGradient)),
                    const SizedBox(height: 10),
                    Text(songList[controller.playIndex.value].artist.toString(),
                        textAlign: TextAlign.center,
                        overflow: TextOverflow.ellipsis,
                        maxLines: 2,
                        style: ourStyle(
                            size: 20,
                            fontWeight: FontWeight.normal,
                            color: bgDarkColor)),
                    const SizedBox(height: 20),
                    Obx(
                      () => Padding(
                        padding: const EdgeInsets.only(left: 20, right: 20),
                        child: Row(
                          children: [
                            Text(controller.position.value,
                                style: ourStyle(color: bgDarkColor)),
                            Expanded(
                                child: Slider(
                                    value: controller.value.value,
                                    inactiveColor: bgcolor,
                                    thumbColor: thumbColor,
                                    activeColor: sliderColor,
                                    min: const Duration(seconds: 0)
                                        .inSeconds
                                        .toDouble(),
                                    max: controller.max.value,
                                    onChanged: (newValue) {
                                      controller.changeDurationToSecond(
                                          newValue.toInt());
                                      newValue = newValue;
                                    })),
                            Text(controller.duration.value,
                                style: ourStyle(color: bgDarkColor))
                          ],
                        ),
                      ),
                    ),
                    const SizedBox(
                      height: 36,
                    ),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceAround,
                      children: [
                        IconButton(
                            onPressed: () {
                              controller.audioPlayer.shuffle();
                            },
                            icon: Icon(Icons.shuffle, size: 25, color: bar)),
                        IconButton(
                            onPressed: () {
                              controller.playSong(
                                  songList[(controller.playIndex.value - 1) %
                                          songList.length]
                                      .uri,
                                  (controller.playIndex.value - 1) %
                                      songList.length);
                            },
                            icon: Icon(Icons.skip_previous,
                                size: 40, color: bar)),
                        Obx(
                          () => CircleAvatar(
                            radius: 35,
                            backgroundColor: bar,
                            child: Transform.scale(
                              scale: 2.0,
                              child: IconButton(
                                  onPressed: () {
                                    if (controller.isPlaying.value) {
                                      controller.audioPlayer.pause();
                                      controller.isPlaying(false);
                                    } else {
                                      controller.audioPlayer.play();
                                      controller.isPlaying(true);
                                    }
                                  },
                                  icon: controller.isPlaying.value
                                      ? const Icon(
                                          Icons.pause,
                                          color: whiteColor,
                                        )
                                      : const Icon(
                                          Icons.play_arrow,
                                          color: whiteColor,
                                        )),
                            ),
                          ),
                        ),
                        IconButton(
                            onPressed: () {
                              controller.playSong(
                                  songList[(controller.playIndex.value + 1) %
                                          songList.length]
                                      .uri,
                                  (controller.playIndex.value + 1) %
                                      songList.length);
                            },
                            icon: Icon(
                              Icons.skip_next,
                              size: 40,
                              color: bar,
                            )),
                        IconButton(
                            onPressed: () {
                              controller.audioPlayer.setLoopMode(LoopMode.one);
                            },
                            icon: Icon(Icons.loop, size: 25, color: bar)),
                      ],
                    )
                  ],
                ),
              ),
            ))
          ],
        ),
      ),
    );
  }
}
