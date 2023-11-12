class SongModelDownload {
  int? id;
  String? artist;
  String? title;
  String? imgurl;
  String? linkDownload;
  SongModelDownload({
    this.id,
    this.artist,
    this.title,
    this.imgurl,
    required this.linkDownload,
  });

  SongModelDownload.fromJson(Map<String, dynamic> json) {
    this.id = json["songId"] as int;
    this.artist = json['artistName'];
    this.title = json["songName"];
    this.imgurl = json["backgroundImageFileUrl"];
    this.linkDownload = json["musicFileUrl"];
  }
}
