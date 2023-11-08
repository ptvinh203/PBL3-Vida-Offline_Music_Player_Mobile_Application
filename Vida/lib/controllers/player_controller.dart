import 'package:get/get.dart';
import 'package:just_audio/just_audio.dart';
import 'package:on_audio_query/on_audio_query.dart';

class PlayerController extends GetxController {
  final audioQuery = new OnAudioQuery();
  final audioPlayer = new AudioPlayer();
  bool hasPermission = false;
  List<RxBool> isLoveds = [];
  var playIndex = 0.obs;
  var isPlaying = false.obs;
  var duration = ''.obs;
  var position = ''.obs;
  var max = 0.0.obs;
  var value = 0.0.obs;
  @override
  void onInit() {
    super.onInit;
    checkAndRequestPermissions();
  }

  String transformString(int seconds) {
    String minuteString =
        '${(seconds / 60).floor() < 10 ? 0 : ''}${(seconds / 60).floor()}';
    String secondString = '${seconds % 60 < 10 ? 0 : ''}${seconds % 60}';
    return '$minuteString:$secondString'; // Returns a string with the format mm:ss
  }

  changeDurationToSecond(seconds) {
    var duration = Duration(seconds: seconds);
    audioPlayer.seek(duration);
  }

  updatePosition() {
    audioPlayer.durationStream.listen((du) {
      duration.value = transformString(du!.inSeconds);
      max.value = du.inSeconds.toDouble();
      //transformString(du!.inSeconds);
    });
    audioPlayer.positionStream.listen((po) {
      position.value = transformString(po.inSeconds);
      //transformString(po!.inSeconds);
      value.value = po.inSeconds.toDouble();
    });
  }

  playSong(String? uri, index) {
    playIndex.value = index;
    try {
      audioPlayer.setAudioSource(AudioSource.uri(Uri.parse(uri!)));
      print("____________________________${Uri.parse(uri).authority}");
      audioPlayer.play();
      isPlaying(true);
      updatePosition();
    } on Exception catch (e) {
      print("Catched Exception playSong part: ${e.toString()}");
    }
  }

  checkAndRequestPermissions({bool retry = false}) async {
    hasPermission = await audioQuery.checkAndRequest(
      retryRequest: retry,
    );
  }
}
