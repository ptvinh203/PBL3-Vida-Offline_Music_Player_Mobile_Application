import 'package:assets_audio_player/assets_audio_player.dart';
import 'package:flutter/material.dart';

class Song {
  late int songID;
  late String songName;
  late String artist;
  late String album;
  late DateTime date;
  late String musicPath;
  late String backgroundImageFilePath;

  Song(
      {required this.songID,
      required this.songName,
      required this.artist,
      required this.date,
      required this.musicPath,
      required this.backgroundImageFilePath});

  //Audio toAudio() {
  //  return Audio.file(
  //    musicPath, 
  //    metas: Metas(album: )
  //  );
  //}
}


//Audio.file('./assets/Maroon-5-She-Will-Be-Loved.mp3',
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
//*//
