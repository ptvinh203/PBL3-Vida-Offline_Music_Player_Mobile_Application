import 'dart:io';

import 'package:Vida/services/config.dart';
import 'package:Vida/services/song_service.dart';
import 'package:async/async.dart';

import 'package:flutter/material.dart';
import 'package:dio/dio.dart';
import 'package:downloads_path_provider_28/downloads_path_provider_28.dart';

import 'package:permission_handler/permission_handler.dart';
import 'package:Vida/models/song_model_download.dart';

import 'package:ionicons/ionicons.dart';

import '../../consts/colors.dart';
import '../../widget/custom_icon_button.dart';

class DownloadPage extends StatefulWidget {
  DownloadPage({super.key});

  @override
  State<DownloadPage> createState() => _DownloadPageState();
}

class _DownloadPageState extends State<DownloadPage> {
  double? _progress;
  final searchController = TextEditingController();
  //static var httpClient = new HttpClient();
  List<SongModelDownload> songModelDownloadList = [];
  List<SongModelDownload> songs = [];
  SongService service = SongService();
  var connectionChecked = false;
  CancelableOperation? cancelOperator = null;
  Future refresh() async {
    setState(() {});
  }

  void searchSong(String query) {
    final suggestion = songModelDownloadList.where((song) {
      final songTitle = song.title.toLowerCase();
      final input = query.toLowerCase();
      return songTitle.contains(input);
    }).toList();
    setState(() => songs = suggestion);
  }

  void GetAll() {
    try {
      cancelOperator =
          CancelableOperation.fromFuture(service.getAll().then((value) {
        songModelDownloadList = value;
        songs = songModelDownloadList;
        connectionChecked = true;
        refresh();
      }));
    } on Exception catch (e) {
      print('Error caught: : $e');
      connectionChecked = false;
      refresh();
    }
  }

  @override
  void initState() {
    GetAll();

    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    cancelOperator?.cancel();
    return Scaffold(
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
      backgroundColor: blackBG,
      body: RefreshIndicator(
        onRefresh: refresh,
        backgroundColor: purpButton,
        color: white,
        strokeWidth: 4,
        displacement: 50,
        child: SafeArea(
          child: ListView(
              physics: BouncingScrollPhysics(
                  parent: AlwaysScrollableScrollPhysics()),
              padding: const EdgeInsets.all(14),
              children: (connectionChecked == false ||
                      songModelDownloadList.isEmpty)
                  ? <Widget>[
                      Center(
                          child: Column(
                        children: [
                          Padding(
                            padding: const EdgeInsets.only(left: 25, top: 150),
                            child: Image.asset(
                              'assets/image/dash_lost_connection.png',
                              fit: BoxFit.cover,
                            ),
                          ),
                          Container(
                            height: 70.0,
                            margin: EdgeInsets.symmetric(
                              horizontal: 20.0,
                              vertical: 10.0,
                            ),
                            decoration: BoxDecoration(
                              color: blackTextFild,
                              borderRadius: BorderRadius.circular(20.0),
                            ),
                            child: Row(
                              mainAxisAlignment: MainAxisAlignment.center,
                              children: [
                                Container(
                                  width: 200,
                                  child: Column(
                                    children: [
                                      SizedBox(height: 15),
                                      Text(
                                        'Lost connection to server.',
                                        style: TextStyle(
                                            color: littleWhite,
                                            fontWeight: FontWeight.bold,
                                            fontSize: 16),
                                      ),
                                      Text(
                                        'Please check your network.',
                                        style: TextStyle(
                                            color: littleWhite,
                                            fontWeight: FontWeight.bold,
                                            fontSize: 16),
                                      ),
                                    ],
                                  ),
                                ),
                              ],
                            ),
                          )
                        ],
                      )),
                    ]
                  : [
                      Column(
                        children: [
                          Container(
                            margin: EdgeInsets.fromLTRB(16, 16, 16, 16),
                            child: TextField(
                              controller: searchController,
                              decoration: InputDecoration(
                                  prefixIconColor: Colors.deepPurpleAccent,
                                  prefixIcon: Icon(Icons.search),
                                  hintText: "Search song title",
                                  enabledBorder: OutlineInputBorder(
                                    borderSide:
                                        BorderSide(width: 2, color: purpButton),
                                    borderRadius: BorderRadius.circular(
                                        18), //<-- SEE HERE
                                  ),
                                  floatingLabelStyle:
                                      TextStyle(color: littleWhite),
                                  hintStyle: TextStyle(color: littleWhite),
                                  border: OutlineInputBorder(
                                    borderSide: BorderSide(
                                        width: 2,
                                        color: Colors.deepPurpleAccent),
                                    borderRadius: BorderRadius.circular(18),
                                  )),
                              onChanged: searchSong,
                            ),
                          ),
                          Column(
                            children: List.generate(songs.length, (index) {
                              // 1 widget download
                              return Padding(
                                padding: const EdgeInsets.only(bottom: 10),
                                child: SizedBox(
                                  height: 120,
                                  width: double.maxFinite,
                                  child: Card(
                                    color: blackTextFild,
                                    elevation: 0.4,
                                    shape: RoundedRectangleBorder(
                                      borderRadius: BorderRadius.circular(12),
                                    ),
                                    child: InkWell(
                                      borderRadius: BorderRadius.circular(100),
                                      onTap: () async {
                                        Map<Permission, PermissionStatus>
                                            statuses = await [
                                          Permission.storage,
                                        ].request();

                                        if (statuses[Permission.storage]!
                                            .isGranted) {
                                          var dir = await DownloadsPathProvider
                                              .downloadsDirectory;
                                          if (dir != null) {
                                            String saveName =
                                                "${songs[index].title}";
                                            String savePath =
                                                dir.path + "/${saveName}.mp3";
                                            print(savePath);

                                            //  /storage/emulated/0/Download/file.mp3

                                            try {
                                              await Dio().download(
                                                  //"http://" + api_url + "/file/downloadFile/97"

                                                  songs[index].linkDownload ??
                                                      "http://${api_url}/songs/33",
                                                  savePath, onReceiveProgress:
                                                      (received, total) {
                                                if (total != -1) {
                                                  print((received / total * 100)
                                                          .toStringAsFixed(0) +
                                                      "%");
                                                }
                                              });

                                              print(
                                                  "File is saved to download folder.");
                                            } on DioError catch (e) {
                                              print(e.message);
                                            }
                                          }
                                        } else {
                                          print(
                                              "No permission to read and write.");
                                        }
                                      },
                                      child: Padding(
                                        padding: const EdgeInsets.all(10.0),
                                        child: Row(
                                          children: [
                                            Container(
                                              padding: EdgeInsets.all(2),
                                              decoration: BoxDecoration(
                                                  color: purpButton,
                                                  borderRadius:
                                                      BorderRadius.circular(
                                                          100)),
                                              child: ClipRRect(
                                                borderRadius:
                                                    BorderRadius.circular(100),
                                                child: Image.network(
                                                  songs[index].imgurl,
                                                  height: 80,
                                                  width: 80,
                                                  fit: BoxFit.cover,
                                                ),
                                              ),
                                            ),
                                            const SizedBox(width: 10),
                                            Expanded(
                                              child: Column(
                                                crossAxisAlignment:
                                                    CrossAxisAlignment.start,
                                                children: [
                                                  const SizedBox(height: 10),
                                                  Text(
                                                    songs[index].title,
                                                    style: TextStyle(
                                                        fontSize: 18,
                                                        fontWeight:
                                                            FontWeight.bold,
                                                        color: white),
                                                    overflow:
                                                        TextOverflow.ellipsis,
                                                    maxLines: 1,
                                                  ),
                                                  const Spacer(),
                                                  Text(
                                                    songs[index].artist,
                                                    style: TextStyle(
                                                        fontSize: 13,
                                                        color: littleWhite),
                                                  ),
                                                  const SizedBox(height: 10),
                                                  // DISTANCE WIDGET

                                                  Row(
                                                    children: [
                                                      Icon(
                                                        Icons.download,
                                                        color: white,
                                                        size: 30,
                                                      ),
                                                      const Spacer(),
                                                      Text(
                                                        'Click to download',
                                                        style: TextStyle(
                                                            fontSize: 15,
                                                            color: purpButton),
                                                      ),
                                                      SizedBox(
                                                        width: 20,
                                                        height: 10,
                                                      )
                                                    ],
                                                  )
                                                ],
                                              ),
                                            )
                                          ],
                                        ),
                                      ),
                                    ),
                                  ),
                                ),
                              );
                            }),
                          ),
                        ],
                      ),
                    ]),
        ),
      ),
    );
  }
}
