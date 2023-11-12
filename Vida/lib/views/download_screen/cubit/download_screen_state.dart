part of 'download_screen_cubit.dart';

@freezed
class DownloadScreenState with _$DownloadScreenState {
  const factory DownloadScreenState.initial({
    @Default([]) List<SongModelDownload> listSongDownload,
    @Default([]) List<SongModelDownload> searchSongs,
    bool? connectionChecked,
    String? errorMessage,
    bool? fileExisted,
    bool? downloadSuccess,
    bool? isDownloading,
    BuildContext? downloadingContext,
  }) = _Initial;
}
