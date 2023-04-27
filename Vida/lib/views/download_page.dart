import 'package:Vida/services/song_service.dart';
import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter/src/widgets/placeholder.dart';
import 'package:Vida/models/song_model_download.dart';
import 'package:ionicons/ionicons.dart';

import '../consts/colors.dart';
import '../widget/custom_icon_button.dart';

class DownloadPage extends StatefulWidget {
  DownloadPage({super.key});

  @override
  State<DownloadPage> createState() => _DownloadPageState();
}

class _DownloadPageState extends State<DownloadPage> {
  List<SongModelDownload> songModelDownloadList = [];
  SongService service = SongService();

  Future refresh() async {
    setState(() {});
  }

  void GetAll() {
    service.getAll().then((value) {
      songModelDownloadList = value;
      refresh();
    });
  }

  @override
  void initState() {
    GetAll();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
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
              children: [
                Column(
                  children:
                      List.generate(songModelDownloadList.length, (index) {
                    return Padding(
                      padding: const EdgeInsets.only(bottom: 10),
                      child: SizedBox(
                        height: 100,
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
                              service
                                  .downloadSong(songModelDownloadList[index]);
                            },
                            child: Padding(
                              padding: const EdgeInsets.all(2.0),
                              child: Row(
                                children: [
                                  Container(
                                    padding: EdgeInsets.all(2),
                                    decoration: BoxDecoration(
                                        color: purpButton,
                                        borderRadius:
                                            BorderRadius.circular(100)),
                                    child: ClipRRect(
                                      borderRadius: BorderRadius.circular(100),
                                      child: Image.network(
                                        songModelDownloadList[index].imgurl,
                                        height: 100,
                                        width: 100,
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
                                          songModelDownloadList[index].title,
                                          style: TextStyle(
                                              fontSize: 18,
                                              fontWeight: FontWeight.bold,
                                              color: white),
                                        ),
                                        const Spacer(),
                                        Text(
                                          songModelDownloadList[index].artist,
                                          style: TextStyle(
                                              fontSize: 13, color: littleWhite),
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
