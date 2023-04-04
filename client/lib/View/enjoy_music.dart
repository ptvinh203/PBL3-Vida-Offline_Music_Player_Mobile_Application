import 'package:flutter/material.dart';
import 'package:assets_audio_player/assets_audio_player.dart';
import 'package:client/Theme/theme.dart';
import 'package:flutter_swiper_null_safety/flutter_swiper_null_safety.dart';

import 'package:client/Widget/artistText.dart';

class EnjoyMusic extends StatefulWidget {
  const EnjoyMusic({super.key});

  @override
  State<EnjoyMusic> createState() => _EnjoyMusicState();
}

class _EnjoyMusicState extends State<EnjoyMusic> {
  final playlistVipPro = [
    Audio('./assets/Maroon-5-She-Will-Be-Loved.mp3',
        metas: Metas(
          title: 'She will be loved',
          artist: 'Maroon 5',
          album: 'wedding playlist',
          id: '1',
          image: MetasImage.asset('assets/she-will-be-loved.jpeg'),
          onImageLoadFail: MetasImage.asset('assets/undefinded.jpeg'),
        )),
    Audio('./assets/parado-no-bailao.mp3',
        metas: Metas(
          title: 'Parado no Bailao',
          artist: 'Neymar Jr',
          album: 'soccer playlist',
          id: '2',
          image: MetasImage.asset('assets/neymar.jpeg'),
          onImageLoadFail: MetasImage.asset('assets/undefinded.jpeg'),
        )),
    Audio('./assets/Dau-Nhat-La-Lang-Im.mp3',
        metas: Metas(
          title: 'Dau nhat la lang im',
          artist: 'Erik',
          album: 'sad playlist',
          id: '3',
          image: MetasImage.asset('assets/ErikDNLLI.jpeg'),
          onImageLoadFail: MetasImage.asset('assets/undefinded.jpeg'),
        ))
  ];
  final AssetsAudioPlayer assetsAudioPlayer = AssetsAudioPlayer();
  final SwiperController swiperController = SwiperController();
  final screenWidth = 0.0;
  final screenHeight = 0.0;
  static bool isLooping = false;

  static bool isShuffling = false;

  @override
  void initState() {
    super.initState();
    setupPage();
    swiperController;
  }

  /// Transforms string into a mm:ss format

  void setupPage() async {
    await assetsAudioPlayer.open(Playlist(audios: playlistVipPro),
        autoStart: true, loopMode: LoopMode.none);
  }

  void pauseMusic() async {
    await assetsAudioPlayer.pause();
  }

  void skipNext() async {
    await assetsAudioPlayer.next();
  }

  void skipPrevious() async {
    await assetsAudioPlayer.previous();
  }

  void loopSingleSong() {
    setState(() {
      if (isLooping == true) {
        isLooping = false;
        assetsAudioPlayer.toggleLoop();
      } else {
        isLooping = true;
        assetsAudioPlayer.toggleLoop();
      }
    });
  }

  @override
  void dispose() {
    assetsAudioPlayer.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final screenWidth = MediaQuery.of(context).size.width;
    final screenHeight = MediaQuery.of(context).size.height;

    String transformString(int seconds) {
      String minuteString =
          '${(seconds / 60).floor() < 10 ? 0 : ''}${(seconds / 60).floor()}';
      String secondString = '${seconds % 60 < 10 ? 0 : ''}${seconds % 60}';
      return '$minuteString:$secondString'; // Returns a string with the format mm:ss
    }

    Widget swiper(RealtimePlayingInfos realtimePlayingInfos) {
      return Container(
        width: screenWidth,
        height: screenHeight * 0.35,
        child: Swiper(
          controller: swiperController,
          itemCount: playlistVipPro.length,
          itemBuilder: (context, index) {
            return ClipRRect(
              borderRadius: BorderRadius.circular(30.0),
              child: Image.asset(
                playlistVipPro[index].metas.image?.path ?? 'undefined.jpeg',
                fit: BoxFit.cover,
              ),
            );
          },
          onIndexChanged: (newIndex) async {
            await assetsAudioPlayer.playlistPlayAtIndex(newIndex);
          },
          viewportFraction: 0.75,
          scale: 0.8,
        ),
      );
    }

    Widget slider(RealtimePlayingInfos realtimePlayingInfos) {
      return SliderTheme(
          data: SliderThemeData(
            thumbShape: RoundSliderThumbShape(enabledThumbRadius: 0.5),
          ),
          child: Slider.adaptive(
              value: realtimePlayingInfos.currentPosition.inSeconds.toDouble(),
              max: realtimePlayingInfos.duration.inSeconds.toDouble() + 1.0,
              min: 0,
              thumbColor: Colors.white,
              activeColor: Colors.black,
              inactiveColor: Colors.grey[800],
              onChanged: (double value) {
                setState(() {
                  assetsAudioPlayer.seek(Duration(seconds: value.toInt()));
                });
                //if (!isLooping &&
                //    realtimePlayingInfos.currentPosition >=
                //        realtimePlayingInfos.duration - Duration(seconds: 1)) {
                //  setState(() {
                //    skipNext();
                //  });
                //}
              }));
    }

    Widget titleBar(RealtimePlayingInfos realtimePlayingInfos) {
      return Padding(
        padding: const EdgeInsets.only(left: 30.0, right: 30.0),
        child: FittedBox(
          fit: BoxFit.fitHeight,
          child: Text(
            realtimePlayingInfos.current?.audio?.audio?.metas?.title ??
                'undefined',
            style: TextStyle(
                fontSize: 35, color: Colors.black, fontWeight: FontWeight.bold),
          ),
        ),
      );
    }

//Slider(
//      min: 0,
//      max: duration.inSeconds.toDouble(),
//      value: position.inSeconds.toDouble(),
//      activeColor: Color.fromRGBO(19, 41, 128, 1),
//      thumbColor: Colors.deepPurpleAccent,
//      onChanged: (value) async {
//        position = Duration(seconds: value.toInt());
//        await assetsAudioPlayer.seek(Duration(seconds: value.toInt()));
//      },
//     );
    String formatAlbum(String album) {
      String albumA = '';
      for (int i = 0; i < album.length; i++) {
        albumA += album[i];
        albumA += " ";
      }
      return albumA.toUpperCase();
    }

    Widget albumFormated(RealtimePlayingInfos realtimePlayingInfos) {
      return Padding(
        padding: const EdgeInsets.only(left: 40.0, right: 40.0),
        child: FittedBox(
            fit: BoxFit.fitHeight,
            child: Text(
                formatAlbum(
                    realtimePlayingInfos.current?.audio?.audio?.metas?.album ??
                        'undefined'),
                style: TextStyle(fontSize: 30))),
      );
    }

    Widget playTimeFomated(RealtimePlayingInfos realtimePlayingInfos) {
      return Padding(
        padding: const EdgeInsets.symmetric(horizontal: 15),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(
              transformString(realtimePlayingInfos.currentPosition.inSeconds),
              style: TextStyle(
                  color: Colors.black,
                  fontWeight: FontWeight.bold,
                  fontSize: 17),
            ),
            Text(
              transformString(realtimePlayingInfos.duration.inSeconds),
              style: TextStyle(
                  color: Colors.black,
                  fontWeight: FontWeight.bold,
                  fontSize: 17),
            )
          ],
        ),
      );
    }

    Widget playBar(RealtimePlayingInfos realtimePlayingInfos) {
      return Padding(
        padding: const EdgeInsets.only(left: 15.0, right: 15.0),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Row(
              children: [
                IconButton(
                  icon: Icon(Icons.shuffle_rounded),
                  onPressed: () => {},
                  iconSize: screenHeight * 0.05,
                  color: Colors.black,
                ),
                IconButton(
                    icon: Icon(Icons.skip_previous_rounded),
                    onPressed: () => swiperController.previous(),
                    iconSize: screenHeight * 0.08,
                    color: Colors.black),
                Container(
                  decoration: BoxDecoration(
                      color: Colors.black, shape: BoxShape.circle),
                  child: IconButton(
                    icon: Icon(realtimePlayingInfos.isPlaying
                        ? Icons.pause_rounded
                        : Icons.play_arrow_rounded),
                    onPressed: () async {
                      await assetsAudioPlayer.playOrPause();
                    },
                    iconSize: screenHeight * 0.06,
                    color: Colors.white,
                    splashColor: Colors.transparent,
                    highlightColor: Colors.transparent,
                  ),
                ),
                IconButton(
                  icon: Icon(Icons.skip_next_rounded),
                  onPressed: () => swiperController.next(),
                  iconSize: screenHeight * 0.08,
                  color: Colors.black,
                ),
                IconButton(
                  icon: Icon(Icons.loop_rounded),
                  onPressed: () => loopSingleSong(),
                  iconSize: screenHeight * 0.05,
                  color: Colors.black,
                )
              ],
            ),
          ],
        ),
      );
    }

    return MaterialApp(
      debugShowCheckedModeBanner: true,
      home: Scaffold(
        backgroundColor: Colors.white,
        body: assetsAudioPlayer.builderRealtimePlayingInfos(
            builder: (context, RealtimePlayingInfos? infos) {
          if (infos == null) {
            return Scaffold(body: Column());
          } else
            return (Scaffold(
              body: SafeArea(
                child: Container(
                  padding: EdgeInsets.all(0.0),
                  child: Center(
                    child: Column(children: [
                      SizedBox(height: screenHeight * 0.02),
                      albumFormated(infos),
                      SizedBox(height: screenHeight * 0.04),
                      swiper(infos),
                      SizedBox(height: screenHeight * 0.04),
                      titleBar(infos),
                      SizedBox(height: screenHeight * 0.02),
                      artistText(infos),
                      SizedBox(height: screenHeight * 0.02),
                      slider(infos),
                      SizedBox(height: screenHeight * 0.01),
                      playTimeFomated(infos),
                      SizedBox(height: screenHeight * 0.03),
                      playBar(infos)
                    ]),
                  ),
                ),
              ),
            ));
        }),
      ),
    );
  }
}
