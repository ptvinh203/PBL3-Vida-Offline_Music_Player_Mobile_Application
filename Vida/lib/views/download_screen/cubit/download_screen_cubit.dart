import 'dart:io';

import 'package:Vida/services/song_service.dart';
import 'package:bloc/bloc.dart';
import 'package:dio/dio.dart';
import 'package:downloads_path_provider_28/downloads_path_provider_28.dart';
import 'package:flutter/material.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:permission_handler/permission_handler.dart';

import '../../../models/song_model_download.dart';

part 'download_screen_state.dart';
part 'download_screen_cubit.freezed.dart';

class DownloadScreenCubit extends Cubit<DownloadScreenState> {
  DownloadScreenCubit() : super(DownloadScreenState.initial());
  final SongService _songService = SongService.getInstance();

  Future getSongDownloads() async {
    try {
      final list = await _songService.getAll();
      emit(state.copyWith(
          connectionChecked: true, listSongDownload: list, searchSongs: list));
    } catch (err) {
      emit(state.copyWith(connectionChecked: false));
    }
  }

  Future download(SongModelDownload song) async {
    Map<Permission, PermissionStatus> statuses =
        await [Permission.storage].request();

    if (statuses[Permission.storage]!.isGranted) {
      var dir = await DownloadsPathProvider.downloadsDirectory;
      if (dir != null) {
        String saveName = "${song.title}".replaceAll(" ", "-");
        String savePath = dir.path + "/${saveName}.mp3";
        bool fileExisted = await File(savePath).exists();
        if (fileExisted) {
          emit(state.copyWith(fileExisted: true));
          return;
        }
        emit(state.copyWith(isDownloading: true));
        try {
          await Dio().download(song.linkDownload!, savePath,
              onReceiveProgress: (received, total) {
            if (total != -1) {
              print((received / total * 100).toStringAsFixed(0) + "%");
            }
          });
          emit(state.copyWith(downloadSuccess: true, isDownloading: false));
        } catch (e) {
          emit(
              state.copyWith(errorMessage: e.toString(), isDownloading: false));
        }
      }
    } else {
      print("No permission to read and write.");
    }
  }

  void searchSong(String textSearch) {
    final list = state.listSongDownload
        .where((song) =>
            (song.title ?? "").toLowerCase().contains(textSearch.toLowerCase()))
        .toList();
    emit(state.copyWith(searchSongs: list));
  }

  update(DownloadScreenState Function(DownloadScreenState) onUpdate) =>
      emit(onUpdate(state));
}
