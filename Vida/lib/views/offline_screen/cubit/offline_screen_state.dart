part of 'offline_screen_cubit.dart';

@freezed
class OfflineScreenState with _$OfflineScreenState {
  const factory OfflineScreenState.initial({
    List<SongModelExtended>? listOfflineSongs,
    @Default([]) List<SongModelExtended> favouriteSongs,
  }) = _Initial;
}
