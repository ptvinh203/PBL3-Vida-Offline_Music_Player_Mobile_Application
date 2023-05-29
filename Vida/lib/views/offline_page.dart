import 'dart:convert';
import 'dart:io';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:ionicons/ionicons.dart';
import 'package:on_audio_query/on_audio_query.dart';
import 'package:Vida/consts/colors.dart';
import 'package:Vida/consts/text_style.dart';
import 'package:Vida/controllers/player_controller.dart';
import 'package:Vida/views/player.dart';
import 'package:Vida/widget/loved_icon.dart';
import '../widget/custom_icon_button.dart';
import 'package:dart_tags/dart_tags.dart';

class SongModelExtended {
  SongModel songModel;
  String artistName;

  SongModelExtended(this.songModel, this.artistName);
}

class OfflinePage extends StatefulWidget {
  OfflinePage({super.key});

  @override
  State<OfflinePage> createState() => _OfflinePageState();
}

class _OfflinePageState extends State<OfflinePage> {
  var controller = Get.put(PlayerController());
  Future refresh() async {
    setState(() {});
  }

  Route<Object?> _dialogDoubleCheckBuilder(BuildContext context, File file) {
    return DialogRoute<void>(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text('Notification'),
          content: const Text("Are you sure?"),
          actions: <Widget>[
            TextButton(
              style: TextButton.styleFrom(
                textStyle: Theme.of(context).textTheme.labelLarge,
              ),
              child: const Text('Cancel'),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
            TextButton(
              style: TextButton.styleFrom(
                textStyle: Theme.of(context).textTheme.labelLarge,
              ),
              child: const Text('Delete'),
              onPressed: () {
                deleteFile(file).then((value) {
                  Navigator.of(context).pop();
                  setState(() {});
                });
                //service.update().then((value) {
                //  Navigator.of(context).pop();
                //  setState(() {});
                //  this.widget.callback?.call();
                //});
              },
            ),
          ],
        );
      },
    );
  }

  List<Widget> buildTrailing(int index, bool isPlaying) {
    print("Building");
    LovedIcon icon = LovedIcon(isLoved: controller.isLoveds[index]);
    var widgets = <Widget>[];
    widgets.add(isPlaying
        ? Icon(
            Icons.play_arrow,
            color: purpButton,
            size: 32.0,
          )
        : Padding(
            padding: const EdgeInsets.all(16),
          ));
    widgets.add(
      IconButton(
          onPressed: () {
            controller.isLoveds[index].value =
                !controller.isLoveds[index].value;
          },
          icon: icon),
    );

    return widgets;
  }

  //Future<String> getArtist(SongModel song) async {
  //  final file = File(song.data);
  //  final tagProcessor = TagProcessor();
  //  final tags = tagProcessor.getTagsFromByteArray(file.readAsBytes()).then((value) => null);
  //  return tags[0].toString();
  //}
  Future<String> getArtistName(SongModel songmd) async {
    print(songmd.data);
    final file = File(songmd.data);
    var tagString;
    final tagProcessor = TagProcessor();
    //tagProcessor.putTagsToByteArray(bytes)
    return file.readAsBytes().then((bytes) {
      final futureBytes = Future.value(bytes);
      return tagProcessor.getTagsFromByteArray(futureBytes).then((tags) {
        tagString = tags.elementAt(0).tags["artist"];
        return utf8.decode(tagString.toString().runes.toList());
      });
    });
    //var tags = await ;

    //print("000000000000000000000000000000000000");
    //print(tags);
    //print("doc metadata bang thu vien moi: " +
    //    tagString.toString()); // doc metadata bang thu vien moi
    //print("doc metadata bang thu vien cu: " +
    //    songmd.artist.toString()); // doc bang thu vien cu
    //print("000000000000000000000000000000000000");

    //return tagString.toString();
  }

  Future<void> deleteFile(File file) async {
    return file.exists().then((exist) {
      if (!exist) return Future.error("File not exists!");
      return file.delete();
    });
  }

  Future<List<SongModelExtended>> __future() async {
    var songs = await controller.audioQuery.querySongs(
        ignoreCase: true,
        orderType: OrderType.ASC_OR_SMALLER,
        sortType: null,
        uriType: UriType.EXTERNAL);
    List<Future<SongModelExtended>> futures = [];
    songs.forEach((s) {
      futures
          .add(getArtistName(s).then((value) => SongModelExtended(s, value)));
    });
    //for (int i = 0; i < songs.length; i++) {
    //  var s = songs[i];
    //  String aName = await getArtistName(s);
    //  nSongs.add(SongModelExtended(s, aName));
    //}
    return Future.wait(futures);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: blackBG,
      appBar: AppBar(
        elevation: 0,
        toolbarHeight: 100,
        backgroundColor: blackBG,
        foregroundColor: littleWhite,
        title: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const SizedBox(height: 5),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Icon(Ionicons.headset, color: purpButton, size: 60),
                SizedBox(
                  width: 5,
                ),
                Padding(
                  padding: const EdgeInsets.only(top: 12),
                  child: Text(
                    "Vida",
                    style: TextStyle(
                        color: flutterPurple,
                        fontSize: 42,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Comfortaa'),
                  ),
                ),
              ],
            ),
          ],
        ),
      ),
      body: RefreshIndicator(
        onRefresh: refresh,
        backgroundColor: purpButton,
        color: white,
        strokeWidth: 4,
        displacement: 50,
        child: FutureBuilder<List<SongModelExtended>>(
            future: __future(),
            builder: (BuildContext context, snapshot) {
              //String searchValue = '';
              if (controller.isLoveds.isEmpty)
                controller.isLoveds = List.generate(
                    snapshot.data?.length ?? 0, (index) => RxBool(false));
              //<RxBool>[snapshot.data?.length];
              List<String> listTitle = List.generate(snapshot.data?.length ?? 0,
                  (index) => snapshot.data![index].songModel.title);
              print("Debug list view");
              if (snapshot.data == null) {
                return const Center(
                    child: CircularProgressIndicator(
                  backgroundColor: Colors.deepPurpleAccent,
                ));
              } else if (snapshot.data!.isEmpty) {
                print(
                    "~~~~~~~~~~~~~~~data is empty bro ${snapshot.data}~~~~~~~~~~~~~~~");
                return Center(
                    child: Text("No song found in local storage",
                        style: ourStyle()));
              } else {
                print(
                    "~~~~~~~~~~~~~~~data is GOOD bro ${snapshot.data}~~~~~~~~~~~~~~~");

                return Padding(
                  padding: const EdgeInsets.all(10),
                  child: ListView.builder(
                      physics: BouncingScrollPhysics(
                          parent: AlwaysScrollableScrollPhysics()),
                      itemCount: snapshot.data!.length,
                      itemBuilder: (BuildContext context, int index) {
                        return Container(
                          margin: const EdgeInsets.only(bottom: 8),
                          child: Obx(
                            () {
                              return ListTile(
                                shape: RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(13.0)),
                                tileColor: blackTextFild,
                                title:
                                    Text(snapshot.data![index].songModel.title,
                                        style: ourStyle(
                                          color: white,
                                          fontWeight: FontWeight.bold,
                                          size: 15.0,
                                        )),
                                subtitle: Text(
                                    snapshot.data![index].artistName ??
                                        snapshot.data![index].songModel.artist!,
                                    style: ourStyle(
                                        size: 15.0, color: littleWhite)),
                                leading: Container(
                                  padding: const EdgeInsets.all(1.0),
                                  decoration: BoxDecoration(
                                      borderRadius: BorderRadius.circular(50),
                                      color: purpButton),
                                  child: QueryArtworkWidget(
                                    artworkQuality: FilterQuality.high,
                                    quality: 100,
                                    keepOldArtwork: true,
                                    id: snapshot.data![index].songModel.id,
                                    type: ArtworkType.AUDIO,
                                    nullArtworkWidget: const Icon(
                                      Icons.music_note,
                                      color: whiteColor,
                                      size: 32,
                                    ),
                                  ),
                                ),
                                trailing: SizedBox(
                                  width: 86,
                                  child: Row(
                                    children: buildTrailing(
                                        index,
                                        controller.playIndex.value == index &&
                                            controller.isPlaying.value),
                                  ),
                                ),
                                onLongPress: () async {
                                  //String filePath =
                                  //    snapshot.data![index].songModel.data;

                                  //File file = File(filePath);
                                  File file = File(
                                      snapshot.data![index].songModel.data);
                                  print("DELETING ${file}");
                                  Navigator.of(context).push(
                                    _dialogDoubleCheckBuilder(context, file),
                                  );
                                },
                                onTap: () async {
                                  Get.to(
                                    () => Player(
                                        songList: snapshot.data!
                                            .map<SongModel>((e) => e.songModel)
                                            .toList()),
                                    transition: Transition.downToUp,
                                  );
                                  print(
                                      "_________________________________________________________________________________________________________");
                                  final file = File(
                                      snapshot.data![index].songModel.data);

                                  final tagProcessor = TagProcessor();
                                  final bytes = file.readAsBytesSync().toList();
                                  final futureBytes = Future.value(bytes);
                                  final tags = await tagProcessor
                                      .getTagsFromByteArray(futureBytes);
                                  var tagString =
                                      tags.elementAt(0).tags["artist"];
                                  print(tagString);

                                  print(tags);
                                  // print tag title here please

                                  print(
                                      "__________________________________________________________________________");
                                  controller.playSong(
                                      snapshot.data![index].songModel.uri,
                                      index);
                                },
                              );
                            },
                          ),
                        );
                      }),
                );
              }
            }),
      ),
    );
  }
}
