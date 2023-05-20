import 'dart:convert';
import 'dart:io';

import 'package:dart_tags/dart_tags.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:ionicons/ionicons.dart';
import 'package:on_audio_query/on_audio_query.dart';
import 'package:Vida/consts/colors.dart';
import 'package:Vida/consts/text_style.dart';
import 'package:Vida/controllers/player_controller.dart';
import 'package:Vida/views/player.dart';
import 'offline_page.dart' as offl;
import '../widget/custom_icon_button.dart';
import '../widget/loved_icon.dart';
import 'offline_page.dart';

class Favourite extends StatefulWidget {
  Favourite({super.key});

  @override
  State<Favourite> createState() => _FavouriteState();
}

class _FavouriteState extends State<Favourite> {
  var controller = Get.put(PlayerController());
  Future refresh() async {
    setState(() {});
  }

  List<Widget> buildTrailing(int index, bool isPlaying) {
    print("Building favourite page");    LovedIcon icon = LovedIcon(isLoved: controller.isLoveds[index]);
    var widgets = <Widget>[];
    widgets.add(
      IconButton(
          onPressed: () {
            controller.isLoveds[index].value =
                !controller.isLoveds[index].value;
          },
          icon: icon),
    );

    widgets.add(isPlaying
        ? Icon(
            Icons.play_arrow,
            color: purpButton,
            size: 32.0,
          )
        : Padding(
            padding: const EdgeInsets.only(left: 15),
          ));
    return widgets;
  }
  
    Future<String> getArtistName(SongModel songmd) async {
    print(songmd.data);
    final file = File(songmd.data);
    var tagString;
    final tagProcessor = TagProcessor();
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
        backgroundColor: blackBG,
        foregroundColor: littleWhite,
        title: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const SizedBox(height: 5),
            Row(
              children: [
                Icon(Ionicons.headset, color: purpButton, size: 40),
                SizedBox(
                  width: 5,
                ),
                Padding(
                  padding: const EdgeInsets.only(top: 9),
                  child: Text(
                    "Vida",
                    style: TextStyle(
                        color: flutterPurple,
                        fontSize: 25,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Comfortaa'),
                  ),
                ),
              ],
            ),
          ],
        ),
        actions: [
          CustomIconButton(
            icon: Icon(
              size: 25,
              Icons.search,
              color: flutterPurple,
            ),
          ),
        ],
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
              //controller.isLoveds = List.generate(
              //    snapshot.data?.length ?? 0, (index) => RxBool(false));

              //List<String> listTitle = List.generate(snapshot.data?.length ?? 0,
              //    (index) => snapshot.data![index].title);

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
                    child: Text("No favorite song found", style: ourStyle()));
              } else {
                print(
                    "~~~~~~~~~~~~~~~data is GOOD bro ${snapshot.data}~~~~~~~~~~~~~~~");
                var songs = <SongModel>[];
                var ids = <int>[];
                for (int i = 0; i < controller.isLoveds.length; i++) {
                  if (controller.isLoveds[i].isTrue) {
                    ids.add(i);
                    songs.add(snapshot.data![i].songModel);
                  }
                }
                return Padding(
                  padding: const EdgeInsets.all(10),
                  child: ListView.builder(
                      physics: BouncingScrollPhysics(
                          parent: AlwaysScrollableScrollPhysics()),
                      itemCount: ids.length,
                      itemBuilder: (BuildContext context, int index) {
                        final songIndex = ids[index];
                        final song = snapshot.data![songIndex];
                        return Container(
                          margin: const EdgeInsets.only(bottom: 8),
                          child: Obx(
                            () => ListTile(
                              shape: RoundedRectangleBorder(
                                  borderRadius: BorderRadius.circular(13.0)),
                              tileColor: blackTextFild,
                              title: Text(snapshot.data![songIndex].songModel.title,
                                  style: ourStyle(
                                      fontWeight: FontWeight.bold, size: 15.0)),
                              subtitle: Text(
                                  "${snapshot.data![songIndex].artistName ?? snapshot.data![index].songModel.artist!}",
                                  style: ourStyle(size: 15.0)),
                              leading: Container(
                                padding: const EdgeInsets.all(1.0),
                                decoration: BoxDecoration(
                                    borderRadius: BorderRadius.circular(50),
                                    color: purpButton),
                                child: QueryArtworkWidget(
                                  artworkQuality: FilterQuality.high,
                                  keepOldArtwork: true,
                                  id: snapshot.data![songIndex].songModel.id,
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
                                      songIndex,
                                      controller.playIndex.value == songIndex &&
                                          controller.isPlaying.value),
                                ),
                              ),
                              onTap: () {
                                Get.to(
                                  () => Player(songList: songs),
                                  transition: Transition.downToUp,
                                );
                                controller.playSong(
                                    snapshot.data![songIndex].songModel.uri, index);
                              },
                            ),
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
