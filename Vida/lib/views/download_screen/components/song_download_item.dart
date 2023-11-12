import 'package:Vida/models/song_model_download.dart';
import 'package:Vida/services/config.dart';
import 'package:flutter/material.dart';

import '../../../shared/consts/colors.dart';

class SongDownloadItem extends StatelessWidget {
  const SongDownloadItem({
    super.key,
    this.onTap,
    required this.songModelDownload,
  });
  final VoidCallback? onTap;
  final SongModelDownload songModelDownload;

  @override
  Widget build(BuildContext context) {
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
            onTap: () => onTap?.call(),
            child: Padding(
              padding: const EdgeInsets.all(10.0),
              child: Row(
                children: [
                  Container(
                    padding: EdgeInsets.all(2),
                    decoration: BoxDecoration(
                        color: purpButton,
                        borderRadius: BorderRadius.circular(100)),
                    child: ClipRRect(
                      borderRadius: BorderRadius.circular(100),
                      child: Image.network(
                        songModelDownload.imgurl ?? IMAGE_DEFAULT_URL,
                        height: 80,
                        width: 80,
                        fit: BoxFit.cover,
                      ),
                    ),
                  ),
                  const SizedBox(width: 10),
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        const SizedBox(height: 10),
                        Text(
                          songModelDownload.title ?? MUSIC_DEFAULT_URL,
                          style: TextStyle(
                              fontSize: 18,
                              fontWeight: FontWeight.bold,
                              color: white),
                          overflow: TextOverflow.ellipsis,
                          maxLines: 1,
                        ),
                        const Spacer(),
                        Text(
                          songModelDownload.artist ?? "",
                          style: TextStyle(fontSize: 13, color: littleWhite),
                        ),
                        const SizedBox(height: 10),
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
                              style: TextStyle(fontSize: 15, color: purpButton),
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
  }
}
