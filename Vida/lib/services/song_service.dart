import 'dart:convert';

import 'package:Vida/models/song_model_download.dart';
import 'package:Vida/services/config.dart';
import 'package:http/http.dart' as http;
import 'package:permission_handler/permission_handler.dart';

class SongService {
  static SongService? instance;
  SongService.__() {}
  static SongService getInstance() {
    if (instance == null) {
      instance = SongService.__();
    }
    return instance!;
  }

  Future<bool> checkServerAvailability() async {
    final uri = Uri.http(API_URL, "/songs/all");
    try {
      final response = await http
          .get(uri, headers: {"Accept": "application/json;charset=utf-8"});
      if (response.statusCode == 200) {
        return true;
      } else {
        return false;
      }
    } catch (e) {
      return false;
    }
  }

  Future<List<SongModelDownload>> getAll() async {
    final uri = Uri.http(API_URL, "/songs/all");
    try {
      var response = await http
          .get(uri, headers: {"Accept": "application/json;charset=utf-8"});

      var json = jsonDecode(response.body);
      List<SongModelDownload> songs = [];
      for (var jsonSong in json)
        songs.add(SongModelDownload.fromJson(jsonSong));
      await Permission.storage.request();
      return songs;
    } catch (e) {
      return [];
    }
  }
}
