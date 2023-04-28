import 'package:Vida/services/song_service.dart';
import 'package:async/async.dart';

import 'package:flutter/material.dart';

import 'package:Vida/models/song_model_download.dart';
import 'package:flutter_file_downloader/flutter_file_downloader.dart';
import 'package:get/get_rx/src/rx_types/rx_types.dart';
import 'package:http/http.dart';
import 'package:ionicons/ionicons.dart';

import 'dart:io';
import '../consts/colors.dart';
import '../widget/custom_icon_button.dart';

class DownloadPage extends StatefulWidget {
  DownloadPage({super.key});

  @override
  State<DownloadPage> createState() => _DownloadPageState();
}

class _DownloadPageState extends State<DownloadPage> {
  double? _progress;
  static var httpClient = new HttpClient();
  List<SongModelDownload> songModelDownloadList = [];
  SongService service = SongService();
  var connectionChecked = false;
  CancelableOperation? cancelOperator = null;
  Future refresh() async {
    setState(() {});
  }

  void downloadSong(SongModelDownload songmd) async {
    if (songmd.isDownloaded == true) {
      var snackBar = SnackBar(
        backgroundColor: blackBG,
        content: Center(
            child: Text(
          'This song already exist',
          style: TextStyle(color: littleWhite),
        )),
      );
      ScaffoldMessenger.of(context).showSnackBar(snackBar);
      return;
    } else {
      setState(() {
        songmd.setIsDownloaded();
      });

      FileDownloader.downloadFile(
          url: songmd.linkDownload.toString(),
          onProgress: (name, progress) {
            var snackBar = SnackBar(
              backgroundColor: blackBG,
              content: Text(
                'DOWNLOADING',
                style: TextStyle(color: littleWhite),
              ),
              duration: Duration(seconds: 1),
            );
            setState(() {
              _progress = progress;
              songmd.setIsDownloaded();
            });
          },
          onDownloadError: (String error) {
            print('DOWNLOAD ERROR: $error');
          },
          onDownloadCompleted: (value) {
            print('path  $value ');

            var snackBar = SnackBar(
              backgroundColor: blackBG,
              content: Center(
                  child: Text(
                'DOWNLOADED',
                style: TextStyle(color: littleWhite),
              )),
              duration: Duration(seconds: 2),
            );
            ScaffoldMessenger.of(context).showSnackBar(snackBar);
            setState(() {
              _progress = null;
              songmd.setIsDownloaded();
            });
          });
    }
  }

  void GetAll() {
    try {
      cancelOperator =
          CancelableOperation.fromFuture(service.getAll().then((value) {
        songModelDownloadList = value;
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
                        children: List.generate(songModelDownloadList.length,
                            (index) {
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
                                    downloadSong(songModelDownloadList[index]);
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
                                                  BorderRadius.circular(100)),
                                          child: ClipRRect(
                                            borderRadius:
                                                BorderRadius.circular(100),
                                            child: Image.network(
                                              songModelDownloadList[index]
                                                  .imgurl,
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
                                                songModelDownloadList[index]
                                                    .title,
                                                style: TextStyle(
                                                    fontSize: 18,
                                                    fontWeight: FontWeight.bold,
                                                    color: white),
                                              ),
                                              const Spacer(),
                                              Text(
                                                songModelDownloadList[index]
                                                    .artist,
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
                    ]),
        ),
      ),
    );
  }
}
