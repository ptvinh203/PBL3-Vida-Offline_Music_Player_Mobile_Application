class SongModelDownload {
  late int id;
  late String artist;
  late String title;
  late String imgurl;
  String linkDownload = "";
  bool isDownloaded = false;
  SongModelDownload({
    this.id = 0,
    this.artist = 'undefined',
    this.title = 'undefined',
    this.imgurl =
        'https://pbs.twimg.com/media/FKNlhKZUcAEd7FY?format=jpg&name=4096x4096',
    required this.linkDownload,
  });

  SongModelDownload.fromJson(Map<String, dynamic> json) {
    this.id = json["songId"];
    this.artist = json['artistName'] ?? 'undefined';
    this.title = json["songName"] ?? "undefined";
    this.imgurl = json["backgroundImageFileUrl"] ??
        "https://pbs.twimg.com/media/FKNlhKZUcAEd7FY?format=jpg&name=4096x4096";
    this.linkDownload =
        json["musicFileUrl"] ?? "https://nghenhac123.com/download/ENwwBWpgDXnz";
  }

  int getId() => this.id;

  void setId(int id) => this.id = id;

  String getArtist() => this.artist;

  void setArtist(String artist) => this.artist = artist;

  String getTitle() => this.title;

  void setTitle(String title) => this.title = title;

  String? getImgURL() => this.imgurl;

  void setImgURL(String imgurl) => this.imgurl = imgurl;

  String? getLinkDownload() => this.linkDownload;

  void setLinkDownload(String linkDownload) => this.linkDownload = linkDownload;

  bool getIsDownloaded() => this.isDownloaded;

  void setIsDownloaded() => this.isDownloaded = true;
}
