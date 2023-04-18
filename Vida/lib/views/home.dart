import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:on_audio_query/on_audio_query.dart';
import 'package:Vida/consts/colors.dart';
import 'package:Vida/consts/text_style.dart';
import 'package:Vida/controllers/player_controller.dart';
import 'package:Vida/views/player.dart';

class Home extends StatelessWidget {
  const Home({super.key});

  @override
  Widget build(BuildContext context) {
    var controller = Get.put(PlayerController());
    return Scaffold(
      backgroundColor: homeBGColor,
      appBar: AppBar(
        backgroundColor: bgDarkColor,
        actions: [
          IconButton(
              onPressed: () {
                //search di
              },
              icon: Icon(Icons.search, color: whiteColor))
        ],
        leading: IconButton(
            onPressed: () {
              //menutab
            },
            icon: Icon(Icons.sort_rounded, color: whiteColor)),
        title: Text(
          "Vida",
          style: ourStyle(size: 18.0, fontWeight: FontWeight.bold),
        ),
      ),
      body: FutureBuilder<List<SongModel>>(
          future: controller.audioQuery.querySongs(
              ignoreCase: true,
              orderType: OrderType.ASC_OR_SMALLER,
              sortType: null,
              uriType: UriType.EXTERNAL),
          builder: (BuildContext context, snapshot) {
            print("Debug list view");
            if (snapshot.data == null) {
              return const Center(
                  child: CircularProgressIndicator(
                backgroundColor: Colors.amberAccent,
              ));
            } else if (snapshot.data!.isEmpty) {
              print(
                  "~~~~~~~~~~~~~~~data is empty bro ${snapshot.data}~~~~~~~~~~~~~~~");
              return Center(child: Text("No song found", style: ourStyle()));
            } else {
              print(
                  "~~~~~~~~~~~~~~~data is NOT empty bro ${snapshot.data}~~~~~~~~~~~~~~~");

              return Padding(
                padding: const EdgeInsets.all(10),
                child: ListView.builder(
                    physics: const BouncingScrollPhysics(),
                    itemCount: snapshot.data!.length,
                    itemBuilder: (BuildContext context, int index) {
                      return Container(
                        margin: const EdgeInsets.only(bottom: 4),
                        child: Obx(
                          () => ListTile(
                            shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(12.0)),
                            tileColor: Colors.transparent.withOpacity(0.2),
                            title: Text(snapshot.data![index].title,
                                style: ourStyle(
                                    fontWeight: FontWeight.bold, size: 15.0)),
                            subtitle: Text("${snapshot.data![index].artist}",
                                style: ourStyle(size: 15.0)),
                            leading: QueryArtworkWidget(
                              id: snapshot.data![index].id,
                              type: ArtworkType.AUDIO,
                              nullArtworkWidget: const Icon(
                                Icons.music_note,
                                color: whiteColor,
                                size: 32,
                              ),
                            ),
                            trailing: controller.playIndex.value == index &&
                                    controller.isPlaying.value
                                ? const Icon(
                                    Icons.play_arrow,
                                    color: whiteColor,
                                    size: 32.0,
                                  )
                                : null,
                            onTap: () {
                              //controller.playSong(
                              //    snapshot.data![index].uri, index);
                              Get.to(
                                () => Player(songList: snapshot.data!),
                                transition: Transition.downToUp,
                              );
                              controller.playSong(
                                  snapshot.data![index].uri, index);
                            },
                          ),
                        ),
                      );
                    }),
              );
            }
          }),
    );
  }
}
