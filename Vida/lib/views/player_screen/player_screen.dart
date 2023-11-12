import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:just_audio/just_audio.dart';
import 'package:on_audio_query/on_audio_query.dart';
import 'package:Vida/shared/consts/colors.dart';
import 'package:Vida/shared/consts/text_style.dart';
import 'package:Vida/controllers/player_controller.dart';

class PlayerScreen extends StatefulWidget {
  final List<SongModel> songList;
  final int currentIndex;
  const PlayerScreen(
      {super.key, required this.songList, this.currentIndex = 0});

  @override
  State<PlayerScreen> createState() => _PlayerScreenState();
}

class _PlayerScreenState extends State<PlayerScreen> {
  final controller = Get.find<PlayerController>();

  @override
  void initState() {
    controller.playSong(
        widget.songList[widget.currentIndex].uri, widget.currentIndex);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: flutterPurple,
      appBar: AppBar(
        backgroundColor: flutterPurple,
        elevation: 0,
        leading: GestureDetector(
          onTap: () {
            controller.audioPlayer.pause();
            Get.back();
          },
          child: const Icon(
            Icons.chevron_left,
            color: white,
            size: 35,
          ),
        ),
      ),
      body: Padding(
        padding: const EdgeInsets.only(left: 12, right: 12, bottom: 12),
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
                        color: Colors.black87,
                        blurRadius: 30,
                        offset: Offset(14, 18), // Shadow position
                      ),
                    ],
                  ),
                  height: 330,
                  width: 330,
                  alignment: Alignment.center,
                  child: QueryArtworkWidget(
                    id: widget.songList[controller.playIndex.value].id,
                    type: ArtworkType.AUDIO,
                    artworkQuality: FilterQuality.high,
                    artworkHeight: 350,
                    artworkWidth: 350,
                    quality: 100,
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
                      color: Colors.black87.withOpacity(0.6),
                      blurRadius: 35,
                      offset: Offset(20, 20), // Shadow position
                    ),
                  ],
                  borderRadius: BorderRadius.vertical(
                      top: Radius.circular(30.0),
                      bottom: Radius.circular(30.0))),
              child: Obx(
                () => Column(
                  children: [
                    const SizedBox(height: 40),
                    Text(widget.songList[controller.playIndex.value].title,
                        textAlign: TextAlign.center,
                        overflow: TextOverflow.ellipsis,
                        maxLines: 2,
                        style: TextStyle(
                            fontSize: 25.0,
                            fontWeight: FontWeight.bold,
                            //foreground: Paint()..shader = linearGradient
                            color: Colors.black)),
                    const SizedBox(height: 10),
                    const SizedBox(height: 40),
                    Obx(
                      () => Padding(
                        padding: const EdgeInsets.only(left: 20, right: 20),
                        child: Row(
                          children: [
                            Text(controller.position.value,
                                style: ourStyle(color: Colors.black)),
                            Expanded(
                                child: Slider(
                                    value: controller.value.value,
                                    inactiveColor: littleBlack,
                                    thumbColor: black,
                                    activeColor:
                                        Colors.black87.withOpacity(0.65),
                                    min: const Duration(seconds: 0)
                                        .inSeconds
                                        .toDouble(),
                                    max: controller.max.value,
                                    onChanged: (newValue) {
                                      controller.changeDurationToSecond(
                                          newValue.toInt());
                                    })),
                            Text(controller.duration.value,
                                style: ourStyle(color: Colors.black))
                          ],
                        ),
                      ),
                    ),
                    const SizedBox(
                      height: 65,
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
                                  widget
                                      .songList[
                                          (controller.playIndex.value - 1) %
                                              widget.songList.length]
                                      .uri,
                                  (controller.playIndex.value - 1) %
                                      widget.songList.length);
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
                                  widget
                                      .songList[
                                          (controller.playIndex.value + 1) %
                                              widget.songList.length]
                                      .uri,
                                  (controller.playIndex.value + 1) %
                                      widget.songList.length);
                            },
                            icon: Icon(
                              Icons.skip_next,
                              size: 40,
                              color: bar,
                            )),
                        IconButton(
                            onPressed: () {
                              if (controller.audioPlayer.loopMode ==
                                  LoopMode.off)
                                controller.audioPlayer
                                    .setLoopMode(LoopMode.one);
                              else
                                controller.audioPlayer
                                    .setLoopMode(LoopMode.off);
                            },
                            icon: Icon(
                                controller.audioPlayer.loopMode == LoopMode.off
                                    ? Icons.loop
                                    : Icons.access_alarm,
                                size: 25,
                                color: bar)),
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
