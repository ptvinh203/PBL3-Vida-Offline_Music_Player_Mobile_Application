import 'dart:convert';

import 'package:Vida/models/song_model_download.dart';
import 'package:Vida/services/config.dart';

import 'package:flutter/material.dart';

import 'package:http/http.dart' as http;

import 'package:permission_handler/permission_handler.dart';

class SongService {
  

  Future<bool> checkServerAvailability() async {
    final uri = Uri.http(api_url, "/songs/all");
    try {
      final response = await http
          .get(uri, headers: {"Accept": "application/json;charset=utf-8"});
      if (response.statusCode == 200) {
        // Server is available
        return true;
      } else {
        // Server is not available
        return false;
      }
    } catch (e) {
      // Exception thrown, server is not available
      return false;
    }
  }

  Future<List<SongModelDownload>> getAll() async {
    final uri = Uri.http(api_url, "/songs/all");
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

  //Future<List<String>> _fetchSuggestions(String searchValue) async {
  //  final uri = Uri.http(api_url, "/search/");
    

  //  return .where((element) {
  //    return element.toLowerCase().contains(searchValue.toLowerCase());
  //  }).toList();
  //}




}
