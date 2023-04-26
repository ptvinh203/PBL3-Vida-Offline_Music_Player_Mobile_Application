import 'dart:convert';

import 'package:Vida/models/song_model_download.dart';
import 'package:Vida/services/config.dart';
import 'package:flutter_downloader/flutter_downloader.dart';
import 'package:http/http.dart' as http;
import 'package:path_provider/path_provider.dart';

class SongService {
  Future<List<SongModelDownload>> getAll() async {
    var uri = Uri.http(api_url, "/songs/all");
    var response = await http
        .get(uri, headers: {"Accept": "application/json;charset=utf-8"});
    var json = jsonDecode(response.body);
    List<SongModelDownload> songs = [];
    for (var jsonSong in json) songs.add(SongModelDownload.fromJson(jsonSong));
    return songs;
  }

  void downloadSong(SongModelDownload song) async {
    await FlutterDownloader.enqueue(
            url: song.linkDownload ?? "",
            savedDir: (await getApplicationDocumentsDirectory()).path,
            showNotification: true,
            openFileFromNotification: true)
        .then((value) => {});
  }
}

void main(List<String> args) {
  var service = SongService();
  service.getAll();
}
