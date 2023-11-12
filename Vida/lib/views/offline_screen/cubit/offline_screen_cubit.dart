import 'dart:io';

import 'package:Vida/controllers/player_controller.dart';
import 'package:Vida/models/song_model_extended.dart';
import 'package:bloc/bloc.dart';
import 'package:dart_tags/dart_tags.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:get/get.dart';
import 'package:on_audio_query/on_audio_query.dart';

part 'offline_screen_state.dart';
part 'offline_screen_cubit.freezed.dart';

class OfflineScreenCubit extends Cubit<OfflineScreenState> {
  OfflineScreenCubit() : super(const OfflineScreenState.initial());
  final PlayerController _controller = Get.put(PlayerController());

  Future<void> getOfflineSongs() async {
    List<SongModel> songs = await _controller.audioQuery.querySongs(
        ignoreCase: true,
        orderType: OrderType.ASC_OR_SMALLER,
        sortType: null,
        uriType: UriType.EXTERNAL);
    List<SongModelExtended> result = [];

    for (SongModel songModel in songs) {
      String artistName = await _getArtistName(songModel);
      result.add(SongModelExtended(songModel, artistName));
    }

    List<String> favouriteSongId =
        state.favouriteSongs.map((e) => e.songModel.displayName).toList();

    result = result.map((e) {
      e.isLoved = favouriteSongId.contains(e.songModel.displayName);
      return e;
    }).toList();

    final favouriteSongs = result.where((element) => element.isLoved).toList();
    emit(state.copyWith(
        listOfflineSongs: result, favouriteSongs: favouriteSongs));
  }

  Future<bool> deleteFile(File file, SongModelExtended song) async {
    bool isExist = await file.exists();
    if (isExist) {
      await file.delete();
      emit(state.copyWith(
          listOfflineSongs: state.listOfflineSongs
              ?.where((element) => element.id != song.id)
              .toList(),
          favouriteSongs: state.favouriteSongs
              .where((element) => element.id != song.id)
              .toList()));
      return true;
    } else {
      return false;
    }
  }

  Future<String> _getArtistName(SongModel songmd) async {
    final file = File(songmd.data);
    final tagProcessor = TagProcessor();
    final bytes = await file.readAsBytes();
    final List<Tag> tags =
        await tagProcessor.getTagsFromByteArray(Future.value(bytes));
    return tags.elementAt(0).tags["artist"] as String;
  }

  updateOfflineSongs({
    required SongModelExtended Function(SongModelExtended item) onUpdate,
    required bool Function(SongModelExtended item) condition,
  }) {
    List<SongModelExtended> temp = [...state.listOfflineSongs ?? []];
    temp = temp.map((e) => condition(e) ? onUpdate(e) : e).toList();
    emit(state.copyWith(listOfflineSongs: temp));
  }

  void addFavouriteSong(SongModelExtended newFavouriteSong) {
    List<SongModelExtended> temp = [...state.favouriteSongs];
    temp.add(newFavouriteSong);
    emit(state.copyWith(favouriteSongs: temp));
  }

  void removeFavouriteSong(bool Function(SongModelExtended item) condition) {
    List<SongModelExtended> temp =
        state.favouriteSongs.where((element) => !condition(element)).toList();
    emit(state.copyWith(favouriteSongs: temp));
  }

  updateState(OfflineScreenState Function(OfflineScreenState) onUpdate) =>
      emit(onUpdate(state));
}
