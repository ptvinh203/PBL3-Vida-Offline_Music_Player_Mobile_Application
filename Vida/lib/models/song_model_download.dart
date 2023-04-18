import 'dart:ui';
import 'package:flutter/material.dart';

class SongModelDownload {
  late int id;
  late String artist;
  late String title;
  late String imgurl;
  SongModelDownload(
      {this.id = 0,
      this.artist = 'undefined',
      this.title = 'undefined',
      this.imgurl =
          'https://docs.flutter.dev/assets/images/dash/dash-fainting.gif'});

  int getId() => this.id;

  void setId(int id) => this.id = id;

  String getArtist() => this.artist;

  void setArtist(String artist) => this.artist = artist;

  String getTitle() => this.title;

  void setTitle(String title) => this.title = title;

  String getImgURL() => this.imgurl;

  void setImgURL(String imgurl) => this.imgurl = imgurl;
}
