import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:audioplayers/audioplayers.dart';

class listenpage extends StatefulWidget {
  const listenpage({super.key});

  @override
  State<listenpage> createState() => _listenpageState();
}

class _listenpageState extends State<listenpage> {
  final audioPlayer = AudioPlayer();
  static bool isPlaying = false;
  final playlist_string = 'W E D D I N G   P L A Y L I S T';
  final songname_string = 'She will be loved';
  final image_path = 'assets/she-will-be-loved.jpeg';
  final artistname_string = 'Maroon 5';
  final player = AudioCache(prefix: './assets/');
  final song_string = 'Maroon-5-She-Will-Be-Loved.mp3';
  static Duration duration = Duration.zero;
  static Duration position = Duration.zero;
  static Duration seek = Duration.zero;
  static bool isLoop = false;
  static bool isShuffle = false;
  // static String musicPath = './assets/Maroon-5-She-Will-Be-Loved.mp3';

  @override
  void initState() {
    super.initState();

    setAudio();

    // TODO: implement initState
    //xem state// pause, play, stop
    audioPlayer.onPlayerStateChanged.listen((state) {
      setState(() {
        isPlaying = state == PlayerState.playing;
      });
    });

    audioPlayer.onPositionChanged.listen((newPosition) {
      setState(() {
        position = newPosition;
      });
    });

    audioPlayer.onDurationChanged.listen((newDuration) {
      setState(() {
        duration = newDuration;
      });
    });
  }

  Future setAudio() async {
    final Mpath = await player.load(song_string);
    audioPlayer.setSourceDeviceFile(Mpath.path);
  }

  @override
  void dispose() {
    audioPlayer.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final shadow = BoxShadow(
        offset: Offset(14, 25),
        // color: Color.fromARGB(255, 80, 40, 255).withOpacity(0.45),
        color: Colors.black.withOpacity(0.8),
        blurRadius: 40);

    final sliderProgress = Slider(
      min: 0,
      max: duration.inSeconds.toDouble(),
      value: position.inSeconds.toDouble(),
      activeColor: Color.fromRGBO(19, 41, 128, 1),
      thumbColor: Colors.deepPurpleAccent,
      onChanged: (value) async {
        position = Duration(seconds: value.toInt());
        await audioPlayer.seek(Duration(seconds: value.toInt()));
      },
    );

    String formatTime(Duration duration) {
      String twoDigits(int n) => n.toString().padLeft(2, '0');
      final hours = twoDigits(duration.inHours.remainder(60));
      final minutes = twoDigits(duration.inMinutes.remainder(60));
      final seconds = twoDigits(duration.inSeconds.remainder(60));

      return [
        if (duration.inHours > 0) hours,
        minutes,
        seconds,
      ].join(':');
    }

    final loopButton = Container(
        child: CircleAvatar(
            radius: 20,
            backgroundColor:
                isShuffle ? Colors.deepPurpleAccent.shade700 : Colors.white,
            foregroundColor:
                isShuffle ? Colors.white : Colors.deepPurpleAccent.shade700,
            child: DecoratedBox(
                decoration: BoxDecoration(boxShadow: [
                  BoxShadow(
                      spreadRadius: 0,
                      blurRadius: 20,
                      offset: Offset(5, 15),
                      color: Colors.black.withOpacity(0.2))
                ]),
                child: IconButton(
                  icon: Icon(isLoop ? Icons.loop_outlined : Icons.loop_sharp),
                  iconSize: 20,
                  onPressed: () async {},
                ))));

    final shuffleButton = Container(
        child: CircleAvatar(
            radius: 20,
            backgroundColor:
                isShuffle ? Colors.deepPurpleAccent.shade700 : Colors.white,
            foregroundColor:
                isShuffle ? Colors.white : Colors.deepPurpleAccent.shade700,
            child: DecoratedBox(
                decoration: BoxDecoration(boxShadow: [
                  BoxShadow(
                      spreadRadius: 0,
                      blurRadius: 20,
                      offset: Offset(5, 15),
                      color: Colors.black.withOpacity(0.2))
                ]),
                child: IconButton(
                  icon: Icon(isShuffle ? Icons.shuffle : Icons.shuffle_rounded),
                  iconSize: 20,
                  onPressed: () async {},
                ))));

    final timePlay = Padding(
      padding: const EdgeInsets.symmetric(horizontal: 15),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [Text(formatTime(position)), Text(formatTime(duration))],
      ),
    );

    final playButton = Container(
        child: CircleAvatar(
            radius: 30,
            backgroundColor: Colors.deepPurpleAccent.shade700,
            foregroundColor: Colors.white,
            child: DecoratedBox(
                decoration: BoxDecoration(boxShadow: [
                  BoxShadow(
                      spreadRadius: 0,
                      blurRadius: 20,
                      offset: Offset(5, 15),
                      color: Colors.grey.withOpacity(0.4))
                ]),
                child: IconButton(
                  icon: Icon(isPlaying ? Icons.pause : Icons.play_arrow),
                  iconSize: 40,
                  onPressed: () async {
                    if (isPlaying) {
                      await audioPlayer.pause();
                    } else {
                      // await audioPlayer.play(DeviceFileSource(musicPath));
                      await audioPlayer.resume();
                    }
                  },
                ))));

    final nextButton = Container(
        child: CircleAvatar(
            radius: 25,
            backgroundColor: Colors.deepPurpleAccent.shade700,
            foregroundColor: Colors.white,
            child: DecoratedBox(
                decoration: BoxDecoration(boxShadow: [
                  BoxShadow(
                      spreadRadius: 0,
                      blurRadius: 20,
                      offset: Offset(5, 15),
                      color: Colors.grey.withOpacity(0.4))
                ]),
                child: IconButton(
                  icon: Icon(Icons.skip_next),
                  iconSize: 30,
                  onPressed: () async {},
                ))));

    final previousButton = Container(
        child: CircleAvatar(
            radius: 25,
            backgroundColor: Colors.deepPurpleAccent.shade700,
            foregroundColor: Colors.white,
            child: DecoratedBox(
                decoration: BoxDecoration(boxShadow: [
                  BoxShadow(
                      spreadRadius: 0,
                      blurRadius: 20,
                      offset: Offset(5, 15),
                      color: Colors.grey.withOpacity(0.4))
                ]),
                child: IconButton(
                  icon: Icon(Icons.skip_previous),
                  iconSize: 30,
                  onPressed: () async {},
                ))));

    final BackToHomePageButton = BackButton();

    final playListName = Center(
      child: Text(
        playlist_string,
        style: TextStyle(
            fontSize: 10,
            color: Color.fromRGBO(62, 84, 172, 1),
            fontWeight: FontWeight.w400),
        textScaleFactor: 2,
      ),
    );

    const sizeBox = SizedBox(
      height: 10.0,
      width: 10.0,
    );

    final borderedImageOfPlaylist = Center(
      child: Container(
          padding: EdgeInsets.all(10.0),
          child: DecoratedBox(
              decoration: BoxDecoration(boxShadow: [
                BoxShadow(
                    spreadRadius: -3,
                    blurRadius: 30,
                    offset: Offset(0, 20),
                    color: Colors.black.withOpacity(0.2))
              ]),
              child: ClipRRect(
                  borderRadius: BorderRadius.circular(15.0),
                  child: Image(
                    height: 180.0,
                    image: AssetImage(image_path),
                    fit: BoxFit.fill,
                  )))),
    );

    final nameOfSong = Center(
      child: Text(
        songname_string,
        style: TextStyle(
            fontSize: 15, color: Colors.black, fontWeight: FontWeight.bold),
        textScaleFactor: 2,
      ),
    );

    final nameOfArtist = Center(
      child: Text(
        artistname_string,
        style: TextStyle(
            fontSize: 10,
            color: Color.fromRGBO(62, 84, 172, 1),
            fontWeight: FontWeight.w200),
        textScaleFactor: 2,
      ),
    );

    final borderedPlayZone = Container(
        height: 550.0,
        padding: EdgeInsets.all(1.0),
        child: DecoratedBox(
            child: Center(
              child: ListView(
                padding: EdgeInsets.all(36.0),
                children: <Widget>[
                  playListName,
                  sizeBox,
                  borderedImageOfPlaylist,
                  sizeBox,
                  nameOfSong,
                  nameOfArtist,
                  sliderProgress,
                  timePlay,
                  sizeBox,
                  Center(
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceAround,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        shuffleButton,
                        previousButton,
                        playButton,
                        nextButton,
                        loopButton
                      ],
                    ),
                  )
                ],
              ),
            ),
            decoration: BoxDecoration(
              boxShadow: [shadow],
              color: Color.fromRGBO(238, 238, 238, 5),
              borderRadius: BorderRadius.circular(25.0),
            )));

    return MaterialApp(
        home: Scaffold(
            // backgroundColor: Color.fromRGBO(68, 54, 173, 0.98),

            // backgroundColor: Color.fromARGB(248, 42, 42, 131).withOpacity(0.9),
            body: Container(
      width: double.infinity,
      decoration: BoxDecoration(
          gradient: LinearGradient(
              begin: Alignment.topLeft,
              end: Alignment.topRight,
              colors: [
            Color.fromARGB(255, 213, 160, 253),
            Color.fromARGB(255, 140, 166, 219)
            //hh
            // Color.fromARGB(255, 132, 94, 194),
            // Color.fromARGB(255, 214, 93, 177),
            // Color.fromARGB(255, 255, 111, 145),
            // Color.fromARGB(255, 255, 150, 113),
            // Color.fromARGB(255, 255, 199, 195),
            // Color.fromARGB(255, 249, 248, 113)
            //hh
          ])),
      child: Center(
        child: ListView(
          shrinkWrap: true,
          padding: EdgeInsets.all(30.0),
          children: <Widget>[borderedPlayZone],
        ),
      ),
    )));
  }
}
