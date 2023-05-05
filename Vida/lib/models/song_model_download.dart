import 'package:get/get.dart';

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

//List<SongModelDownload> songModelDownloadList = [
//  SongModelDownload(
//      linkDownload:
//          'https://dl28.dlmate17.xyz/?file=M3R4SUNiN3JsOHJ6WWQ2a3NQS1Y5ZGlxVlZIOCtyaDJ5b1Z1bFVCeFV1Qmk0NjU4a3NYd0xjNEVHcTlLN3R6bk1JOGZ2aC9OZHNXWUpFVE1sY3hqR3lQRTBvZDRteDJLcXRscmNZeFVCMVBhdXFPQ3hUZzl6Q1NoTzRHZFVKc0NLQzhvclVjbzBDbUZ5ZU9mOGxEYjV6ajA5d2ltUFdFb2szNHFhdUNiOHRwdGhDenBUckxGaElWT25tTzhzZDlvaXZuV2swVFZrUGNjNnRJN1UxUW5OWlpVMVovS3ovSFlyUnBjMHI4RjFWaXd2T0NqUTh3VEViWE5QR1ZXT0dkYnorRGpCU0ZLN1M4aDZXNlR1S0kxdlNZT2RLOG8za3p5L09yNWJ5MmRhc3Y3U3N6VmZMRHY5SkdxdTZ3NStWUEVvTnpFbEo5RG5WWDJEdHYyWE5SVjVCaHo4L1hVc1lvbDFBdTkyRmM9'),
//  SongModelDownload(
//      linkDownload:
//          'https://dl28.dlmate17.xyz/?file=M3R4SUNiN3JsOHJ6WWQ2a3NQS1Y5ZGlxVlZIOCtyaDJ5b1Z1bFVCeFV1Qmk0NjU4a3NYd0xjNEVHcTlLN3R6bk1JOGZ2aC9OZHNXWUpFVE1sY3hqR3lQRTBvZDRteDJLcXRscmNZeFVCMVBhdXFPQ3hUZzl6Q1NoTzRHZFVKc0NLQzhvclVjbzBDbUZ5ZU9mOGxEYjV6ajA5d2ltUFdFb2szNHFhdUNiOHRwdGhDenBUckxGaElWT25tTzhzZDlvaXZuV2swVFZrUGNjNnRJN1UxUW5OWlpVMVovS3ovSFlyUnBjMHI4RjFWaXd2T0NqUTh3VEViWE5QR1ZXT0dkYnorRGpCU0ZLN1M4aDZXNlR1S0kxdlNZT2RLOG8za3p5L09yNWJ5MmRhc3Y3U3N6VmZMRHY5SkdxdTZ3NStWUEVvTnpFbEo5RG5WWDJEdHYyWE5SVjVCaHo4L1hVc1lvbDFBdTkyRmM9'),
//  SongModelDownload(
//      linkDownload:
//          'https://dl28.dlmate17.xyz/?file=M3R4SUNiN3JsOHJ6WWQ2a3NQS1Y5ZGlxVlZIOCtyaDJ5b1Z1bFVCeFV1Qmk0NjU4a3NYd0xjNEVHcTlLN3R6bk1JOGZ2aC9OZHNXWUpFVE1sY3hqR3lQRTBvZDRteDJLcXRscmNZeFVCMVBhdXFPQ3hUZzl6Q1NoTzRHZFVKc0NLQzhvclVjbzBDbUZ5ZU9mOGxEYjV6ajA5d2ltUFdFb2szNHFhdUNiOHRwdGhDenBUckxGaElWT25tTzhzZDlvaXZuV2swVFZrUGNjNnRJN1UxUW5OWlpVMVovS3ovSFlyUnBjMHI4RjFWaXd2T0NqUTh3VEViWE5QR1ZXT0dkYnorRGpCU0ZLN1M4aDZXNlR1S0kxdlNZT2RLOG8za3p5L09yNWJ5MmRhc3Y3U3N6VmZMRHY5SkdxdTZ3NStWUEVvTnpFbEo5RG5WWDJEdHYyWE5SVjVCaHo4L1hVc1lvbDFBdTkyRmM9'),
//  SongModelDownload(
//      linkDownload:
//          'https://dl28.dlmate17.xyz/?file=M3R4SUNiN3JsOHJ6WWQ2a3NQS1Y5ZGlxVlZIOCtyaDJ5b1Z1bFVCeFV1Qmk0NjU4a3NYd0xjNEVHcTlLN3R6bk1JOGZ2aC9OZHNXWUpFVE1sY3hqR3lQRTBvZDRteDJLcXRscmNZeFVCMVBhdXFPQ3hUZzl6Q1NoTzRHZFVKc0NLQzhvclVjbzBDbUZ5ZU9mOGxEYjV6ajA5d2ltUFdFb2szNHFhdUNiOHRwdGhDenBUckxGaElWT25tTzhzZDlvaXZuV2swVFZrUGNjNnRJN1UxUW5OWlpVMVovS3ovSFlyUnBjMHI4RjFWaXd2T0NqUTh3VEViWE5QR1ZXT0dkYnorRGpCU0ZLN1M4aDZXNlR1S0kxdlNZT2RLOG8za3p5L09yNWJ5MmRhc3Y3U3N6VmZMRHY5SkdxdTZ3NStWUEVvTnpFbEo5RG5WWDJEdHYyWE5SVjVCaHo4L1hVc1lvbDFBdTkyRmM9'),
//  SongModelDownload(
//      linkDownload:
//          'https://dl28.dlmate17.xyz/?file=M3R4SUNiN3JsOHJ6WWQ2a3NQS1Y5ZGlxVlZIOCtyaDJ5b1Z1bFVCeFV1Qmk0NjU4a3NYd0xjNEVHcTlLN3R6bk1JOGZ2aC9OZHNXWUpFVE1sY3hqR3lQRTBvZDRteDJLcXRscmNZeFVCMVBhdXFPQ3hUZzl6Q1NoTzRHZFVKc0NLQzhvclVjbzBDbUZ5ZU9mOGxEYjV6ajA5d2ltUFdFb2szNHFhdUNiOHRwdGhDenBUckxGaElWT25tTzhzZDlvaXZuV2swVFZrUGNjNnRJN1UxUW5OWlpVMVovS3ovSFlyUnBjMHI4RjFWaXd2T0NqUTh3VEViWE5QR1ZXT0dkYnorRGpCU0ZLN1M4aDZXNlR1S0kxdlNZT2RLOG8za3p5L09yNWJ5MmRhc3Y3U3N6VmZMRHY5SkdxdTZ3NStWUEVvTnpFbEo5RG5WWDJEdHYyWE5SVjVCaHo4L1hVc1lvbDFBdTkyRmM9'),
//  SongModelDownload(
//      linkDownload:
//          'https://dl28.dlmate17.xyz/?file=M3R4SUNiN3JsOHJ6WWQ2a3NQS1Y5ZGlxVlZIOCtyaDJ5b1Z1bFVCeFV1Qmk0NjU4a3NYd0xjNEVHcTlLN3R6bk1JOGZ2aC9OZHNXWUpFVE1sY3hqR3lQRTBvZDRteDJLcXRscmNZeFVCMVBhdXFPQ3hUZzl6Q1NoTzRHZFVKc0NLQzhvclVjbzBDbUZ5ZU9mOGxEYjV6ajA5d2ltUFdFb2szNHFhdUNiOHRwdGhDenBUckxGaElWT25tTzhzZDlvaXZuV2swVFZrUGNjNnRJN1UxUW5OWlpVMVovS3ovSFlyUnBjMHI4RjFWaXd2T0NqUTh3VEViWE5QR1ZXT0dkYnorRGpCU0ZLN1M4aDZXNlR1S0kxdlNZT2RLOG8za3p5L09yNWJ5MmRhc3Y3U3N6VmZMRHY5SkdxdTZ3NStWUEVvTnpFbEo5RG5WWDJEdHYyWE5SVjVCaHo4L1hVc1lvbDFBdTkyRmM9'),
//  SongModelDownload(
//      linkDownload:
//          'https://dl28.dlmate17.xyz/?file=M3R4SUNiN3JsOHJ6WWQ2a3NQS1Y5ZGlxVlZIOCtyaDJ5b1Z1bFVCeFV1Qmk0NjU4a3NYd0xjNEVHcTlLN3R6bk1JOGZ2aC9OZHNXWUpFVE1sY3hqR3lQRTBvZDRteDJLcXRscmNZeFVCMVBhdXFPQ3hUZzl6Q1NoTzRHZFVKc0NLQzhvclVjbzBDbUZ5ZU9mOGxEYjV6ajA5d2ltUFdFb2szNHFhdUNiOHRwdGhDenBUckxGaElWT25tTzhzZDlvaXZuV2swVFZrUGNjNnRJN1UxUW5OWlpVMVovS3ovSFlyUnBjMHI4RjFWaXd2T0NqUTh3VEViWE5QR1ZXT0dkYnorRGpCU0ZLN1M4aDZXNlR1S0kxdlNZT2RLOG8za3p5L09yNWJ5MmRhc3Y3U3N6VmZMRHY5SkdxdTZ3NStWUEVvTnpFbEo5RG5WWDJEdHYyWE5SVjVCaHo4L1hVc1lvbDFBdTkyRmM9'),
//  SongModelDownload(
//      linkDownload:
//          'https://dl28.dlmate17.xyz/?file=M3R4SUNiN3JsOHJ6WWQ2a3NQS1Y5ZGlxVlZIOCtyaDJ5b1Z1bFVCeFV1Qmk0NjU4a3NYd0xjNEVHcTlLN3R6bk1JOGZ2aC9OZHNXWUpFVE1sY3hqR3lQRTBvZDRteDJLcXRscmNZeFVCMVBhdXFPQ3hUZzl6Q1NoTzRHZFVKc0NLQzhvclVjbzBDbUZ5ZU9mOGxEYjV6ajA5d2ltUFdFb2szNHFhdUNiOHRwdGhDenBUckxGaElWT25tTzhzZDlvaXZuV2swVFZrUGNjNnRJN1UxUW5OWlpVMVovS3ovSFlyUnBjMHI4RjFWaXd2T0NqUTh3VEViWE5QR1ZXT0dkYnorRGpCU0ZLN1M4aDZXNlR1S0kxdlNZT2RLOG8za3p5L09yNWJ5MmRhc3Y3U3N6VmZMRHY5SkdxdTZ3NStWUEVvTnpFbEo5RG5WWDJEdHYyWE5SVjVCaHo4L1hVc1lvbDFBdTkyRmM9'),
//  SongModelDownload(
//      linkDownload:
//          'https://dl28.dlmate17.xyz/?file=M3R4SUNiN3JsOHJ6WWQ2a3NQS1Y5ZGlxVlZIOCtyaDJ5b1Z1bFVCeFV1Qmk0NjU4a3NYd0xjNEVHcTlLN3R6bk1JOGZ2aC9OZHNXWUpFVE1sY3hqR3lQRTBvZDRteDJLcXRscmNZeFVCMVBhdXFPQ3hUZzl6Q1NoTzRHZFVKc0NLQzhvclVjbzBDbUZ5ZU9mOGxEYjV6ajA5d2ltUFdFb2szNHFhdUNiOHRwdGhDenBUckxGaElWT25tTzhzZDlvaXZuV2swVFZrUGNjNnRJN1UxUW5OWlpVMVovS3ovSFlyUnBjMHI4RjFWaXd2T0NqUTh3VEViWE5QR1ZXT0dkYnorRGpCU0ZLN1M4aDZXNlR1S0kxdlNZT2RLOG8za3p5L09yNWJ5MmRhc3Y3U3N6VmZMRHY5SkdxdTZ3NStWUEVvTnpFbEo5RG5WWDJEdHYyWE5SVjVCaHo4L1hVc1lvbDFBdTkyRmM9'),
//  SongModelDownload(
//      linkDownload:
//          'https://dl28.dlmate17.xyz/?file=M3R4SUNiN3JsOHJ6WWQ2a3NQS1Y5ZGlxVlZIOCtyaDJ5b1Z1bFVCeFV1Qmk0NjU4a3NYd0xjNEVHcTlLN3R6bk1JOGZ2aC9OZHNXWUpFVE1sY3hqR3lQRTBvZDRteDJLcXRscmNZeFVCMVBhdXFPQ3hUZzl6Q1NoTzRHZFVKc0NLQzhvclVjbzBDbUZ5ZU9mOGxEYjV6ajA5d2ltUFdFb2szNHFhdUNiOHRwdGhDenBUckxGaElWT25tTzhzZDlvaXZuV2swVFZrUGNjNnRJN1UxUW5OWlpVMVovS3ovSFlyUnBjMHI4RjFWaXd2T0NqUTh3VEViWE5QR1ZXT0dkYnorRGpCU0ZLN1M4aDZXNlR1S0kxdlNZT2RLOG8za3p5L09yNWJ5MmRhc3Y3U3N6VmZMRHY5SkdxdTZ3NStWUEVvTnpFbEo5RG5WWDJEdHYyWE5SVjVCaHo4L1hVc1lvbDFBdTkyRmM9'),
//];
