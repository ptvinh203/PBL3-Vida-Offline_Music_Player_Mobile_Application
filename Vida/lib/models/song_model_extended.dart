import 'package:on_audio_query/on_audio_query.dart';
import 'package:uuid/uuid.dart';

class SongModelExtended {
  String? id;
  SongModel songModel;
  String artistName;
  bool isLoved;

  SongModelExtended(this.songModel, this.artistName, {this.isLoved = false})
      : id = const Uuid().v4();
}
