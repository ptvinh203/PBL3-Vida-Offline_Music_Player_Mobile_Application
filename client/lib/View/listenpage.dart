//import 'dart:ui';
//import '../Model/song.dart';
//import 'package:flutter/material.dart';
//import 'package:assets_audio_player/assets_audio_player.dart';

//class listenpage extends StatefulWidget {
//  const listenpage({super.key});

//  @override
//  State<listenpage> createState() => listenpageState();
//}

//class listenpageState extends State<listenpage> {
//  //final audioPlayer = AudioPlayer();
//  //List<Song> songList = [
//  //  Song('1', '31/03/2023', 'Neymar', 'S O C C E R   P L A Y L I S T',
//  //      'Parado no bailao', 'parado-no-bailao.mp3', 'neymar.jpeg'),
//  //  Song('2', '31/03/2023', 'Erik', 'S A D   P L A Y L I S T',
//  //      'Dau nhat la lang im', 'Dau-Nhat-La-Lang-Im.mp3', 'ErikDNLLI.jpeg'),
//  //  Song('3', '31/03/2023', 'Hoang Thuy Linh', 'H A P P Y   P L A Y L I S T',
//  //      'See Tinh', 'See-Tinh-Hoang-Thuy-Linh.mp3', 'see-tinh.jpeg'),
//  //  Song(
//  //      '4',
//  //      '31/03/2023',
//  //      'Maroon 5',
//  //      'W E D D I N G   P L A Y L I S T',
//  //      'She will be loved',
//  //      'Maroon-5-She-Will-Be-Loved.mp3',
//  //      'she-will-be-loved.jpeg')
//  //];

//  static bool isPlaying = false;

//  //final playlist_string = 'W E D D I N G   P L A Y L I S T';
//  //final songname_string = 'She will be loved';
//  //final image_path = Image.asset('she-will-be-loved.jpeg');
//  //final artistname_string = 'Maroon 5';
//  //final song_path = 'Maroon-5-She-Will-Be-Loved.mp3';
//  //final player = AudioCache(prefix: './assets/');

//  static Duration duration = Duration.zero;
//  static Duration position = Duration.zero;
//  static Duration seek = Duration.zero;
//  static bool isLoop = false;
//  static bool isShuffle = false;
//  final AssetsAudioPlayer assetsAudioPlayer = AssetsAudioPlayer();
//  // static String musicPath = './assets/Maroon-5-She-Will-Be-Loved.mp3';

//  @override
//  void initState() {
//    super.initState();
//    setupPlaylist();
//  }

//  void setupPlaylist() async {
//    await assetsAudioPlayer.open(
//        Playlist(audios: [
//          Audio.file('./assets/Maroon-5-She-Will-Be-Loved.mp3',
//              metas: Metas(
//                title: 'She will be loved',
//                artist: 'Maroon 5',
//                album: 'W E D D I N G   P L A Y L I S T',
//                id: '1',
//                image: MetasImage(
//                    path: 'assets/she-will-be-loved.jpeg',
//                    type: ImageType.asset),
//                onImageLoadFail: MetasImage(
//                    path: 'assets/undefinded.jpeg', type: ImageType.asset),
//              )),
//          Audio.file('./assets/parado-no-bailao.mp3',
//              metas: Metas(
//                title: 'Parado no Bailao',
//                artist: 'Neymar Jr',
//                album: 'S O C C E R   P L A Y L I S T',
//                id: '2',
//                image: MetasImage(
//                    path: 'assets/neymar.jpeg', type: ImageType.asset),
//                onImageLoadFail: MetasImage(
//                    path: 'assets/undefinded.jpeg', type: ImageType.asset),
//              )),
//          Audio.file('./assets/Dau-Nhat-La-Lang-Im.mp3',
//              metas: Metas(
//                title: 'Dau nhat la lang im',
//                artist: 'Erik',
//                album: 'S A D   P L A Y L I S T',
//                id: '3',
//                image: MetasImage(
//                    path: 'assets/ErikDNLLI.jpeg', type: ImageType.asset),
//                onImageLoadFail: MetasImage(
//                    path: 'assets/undefinded.jpeg', type: ImageType.asset),
//              ))
//        ]),
//        showNotification: true,
//        autoStart: false);
//  }

//  void playMusic() async {
//    await assetsAudioPlayer.play();
//  }

//  void pauseMusic() async {
//    await assetsAudioPlayer.pause();
//  }

//  void skipNext() async {
//    await assetsAudioPlayer.next();
//  }

//  void skipPrevious() async {
//    await assetsAudioPlayer.previous();
//  }

//  @override
//  void dispose() {
//    assetsAudioPlayer.dispose();
//    super.dispose();
//  }

//  @override
//  Widget build(BuildContext context) {
//    final shadow = BoxShadow(
//        offset: Offset(10, 25),
//        // color: Color.fromARGB(255, 80, 40, 255).withOpacity(0.45),
//        color: Colors.black.withOpacity(0.9),
//        blurRadius: 45);

//    final sliderProgress = Slider(
//      min: 0,
//      max: duration.inSeconds.toDouble(),
//      value: position.inSeconds.toDouble(),
//      activeColor: Color.fromRGBO(19, 41, 128, 1),
//      thumbColor: Colors.deepPurpleAccent,
//      onChanged: (value) async {
//        position = Duration(seconds: value.toInt());
//        await assetsAudioPlayer.seek(Duration(seconds: value.toInt()));
//      },
//    );

//    String formatTime(Duration duration) {
//      String twoDigits(int n) => n.toString().padLeft(2, '0');
//      final hours = twoDigits(duration.inHours.remainder(60));
//      final minutes = twoDigits(duration.inMinutes.remainder(60));
//      final seconds = twoDigits(duration.inSeconds.remainder(60));

//      return [
//        if (duration.inHours > 0) hours,
//        minutes,
//        seconds,
//      ].join(':');
//    }

//    final loopButton = Container(
//        child: CircleAvatar(
//            radius: 20,
//            backgroundColor:
//                isShuffle ? Colors.deepPurpleAccent.shade700 : Colors.white,
//            foregroundColor:
//                isShuffle ? Colors.white : Colors.deepPurpleAccent.shade700,
//            child: DecoratedBox(
//                decoration: BoxDecoration(boxShadow: [
//                  BoxShadow(
//                      spreadRadius: 0,
//                      blurRadius: 20,
//                      offset: Offset(5, 15),
//                      color: Colors.black.withOpacity(0.2))
//                ]),
//                child: IconButton(
//                  icon: Icon(isLoop ? Icons.loop_outlined : Icons.loop_sharp),
//                  iconSize: 20,
//                  onPressed: () async {},
//                ))));

//    final shuffleButton = Container(
//        child: CircleAvatar(
//            radius: 20,
//            backgroundColor:
//                isShuffle ? Colors.deepPurpleAccent.shade700 : Colors.white,
//            foregroundColor:
//                isShuffle ? Colors.white : Colors.deepPurpleAccent.shade700,
//            child: DecoratedBox(
//                decoration: BoxDecoration(boxShadow: [
//                  BoxShadow(
//                      spreadRadius: 0,
//                      blurRadius: 20,
//                      offset: Offset(5, 15),
//                      color: Colors.black.withOpacity(0.2))
//                ]),
//                child: IconButton(
//                  icon: Icon(isShuffle ? Icons.shuffle : Icons.shuffle_rounded),
//                  iconSize: 20,
//                  onPressed: () async {},
//                ))));

//    final timePlay = Padding(
//      padding: const EdgeInsets.symmetric(horizontal: 15),
//      child: Row(
//        mainAxisAlignment: MainAxisAlignment.spaceBetween,
//        children: [Text(formatTime(position)), Text(formatTime(duration))],
//      ),
//    );

//    final playButton = Container(
//        child: CircleAvatar(
//            radius: 30,
//            backgroundColor: Colors.deepPurpleAccent.shade700,
//            foregroundColor: Colors.white,
//            child: DecoratedBox(
//                decoration: BoxDecoration(boxShadow: [
//                  BoxShadow(
//                      spreadRadius: 0,
//                      blurRadius: 20,
//                      offset: Offset(5, 15),
//                      color: Colors.grey.withOpacity(0.4))
//                ]),
//                child: IconButton(
//                    icon: Icon(isPlaying ? Icons.pause : Icons.play_arrow),
//                    iconSize: 40,
//                    onPressed: () => isPlaying ? pauseMusic() : playMusic()))));

//    final nextButton = Container(
//        child: CircleAvatar(
//            radius: 25,
//            backgroundColor: Colors.deepPurpleAccent.shade700,
//            foregroundColor: Colors.white,
//            child: DecoratedBox(
//                decoration: BoxDecoration(boxShadow: [
//                  BoxShadow(
//                      spreadRadius: 0,
//                      blurRadius: 20,
//                      offset: Offset(5, 15),
//                      color: Colors.grey.withOpacity(0.4))
//                ]),
//                child: IconButton(
//                    icon: Icon(Icons.skip_next),
//                    iconSize: 30,
//                    onPressed: () => skipNext()))));

//    final previousButton = Container(
//        child: CircleAvatar(
//            radius: 25,
//            backgroundColor: Colors.deepPurpleAccent.shade700,
//            foregroundColor: Colors.white,
//            child: DecoratedBox(
//                decoration: BoxDecoration(boxShadow: [
//                  BoxShadow(
//                      spreadRadius: 0,
//                      blurRadius: 20,
//                      offset: Offset(5, 15),
//                      color: Colors.grey.withOpacity(0.4))
//                ]),
//                child: IconButton(
//                    icon: Icon(Icons.skip_previous),
//                    iconSize: 30,
//                    onPressed: () => skipPrevious()))));

//    final BackToHomePageButton = BackButton();

//    final playListName = Center(
//      child: Text(
//        'playlist_string',
//        style: TextStyle(
//            fontSize: 10,
//            color: Color.fromRGBO(62, 84, 172, 1),
//            fontWeight: FontWeight.w400),
//        textScaleFactor: 2,
//      ),
//    );

//    const sizeBox = SizedBox(
//      height: 10.0,
//      width: 10.0,
//    );

//    final borderedImageOfPlaylist = Center(
//      child: Container(
//          padding: EdgeInsets.all(10.0),
//          child: DecoratedBox(
//              decoration: BoxDecoration(boxShadow: [
//                BoxShadow(
//                    spreadRadius: -3,
//                    blurRadius: 30,
//                    offset: Offset(0, 20),
//                    color: Colors.black.withOpacity(0.2))
//              ]),
//              child: ClipRRect(
//                  borderRadius: BorderRadius.circular(15.0),
//                  child: Image(
//                    height: 180.0,
//                    image: Image.asset('assets/ErikDNLLI.jpeg').image,
//                    fit: BoxFit.fill,
//                  )))),
//    );

//    final nameOfSong = Center(
//      child: Text(
//        'songname_string',
//        style: TextStyle(
//            fontSize: 15, color: Colors.black, fontWeight: FontWeight.bold),
//        textScaleFactor: 2,
//      ),
//    );

//    final nameOfArtist = Center(
//      child: Text(
//        'artistname_string',
//        style: TextStyle(
//            fontSize: 10,
//            color: Color.fromRGBO(62, 84, 172, 1),
//            fontWeight: FontWeight.w200),
//        textScaleFactor: 2,
//      ),
//    );

//    final borderedPlayZone = Container(
//        height: 550.0,
//        padding: EdgeInsets.all(1.0),
//        child: DecoratedBox(
//            child: Center(
//              child: ListView(
//                padding: EdgeInsets.all(36.0),
//                children: <Widget>[
//                  playListName,
//                  sizeBox,
//                  borderedImageOfPlaylist,
//                  sizeBox,
//                  nameOfSong,
//                  nameOfArtist,
//                  sliderProgress,
//                  timePlay,
//                  sizeBox,
//                  Center(
//                    child: Row(
//                      mainAxisAlignment: MainAxisAlignment.spaceAround,
//                      crossAxisAlignment: CrossAxisAlignment.center,
//                      children: [
//                        shuffleButton,
//                        previousButton,
//                        playButton,
//                        nextButton,
//                        loopButton
//                      ],
//                    ),
//                  )
//                ],
//              ),
//            ),
//            decoration: BoxDecoration(
//              boxShadow: [shadow],
//              color: Color.fromRGBO(238, 238, 238, 5),
//              borderRadius: BorderRadius.circular(25.0),
//            )));

//    return MaterialApp(
//        home: Scaffold(
//            // backgroundColor: Color.fromRGBO(68, 54, 173, 0.98),

//            // backgroundColor: Color.fromARGB(248, 42, 42, 131).withOpacity(0.9),
//            body: Container(
//      width: double.infinity,
//      decoration: BoxDecoration(
//          gradient: LinearGradient(
//              begin: Alignment.topLeft,
//              end: Alignment.topRight,
//              colors: [
//            Color.fromARGB(255, 213, 160, 253),
//            Color.fromARGB(255, 140, 166, 219)
//            //hh
//            // Color.fromARGB(255, 132, 94, 194),
//            // Color.fromARGB(255, 214, 93, 177),
//            // Color.fromARGB(255, 255, 111, 145),
//            // Color.fromARGB(255, 255, 150, 113),
//            // Color.fromARGB(255, 255, 199, 195),
//            // Color.fromARGB(255, 249, 248, 113)
//            //hh
//          ])),
//      child: Center(
//        child: ListView(
//          shrinkWrap: true,
//          padding: EdgeInsets.all(30.0),
//          children: <Widget>[borderedPlayZone],
//        ),
//      ),
//    )));
//  }
//}
