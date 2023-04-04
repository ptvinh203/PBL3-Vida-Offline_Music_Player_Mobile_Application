import 'package:flutter/material.dart';
import 'package:assets_audio_player/assets_audio_player.dart';


  Widget artistText(RealtimePlayingInfos realtimePlayingInfos) {
    return Text(
      realtimePlayingInfos.current?.audio?.audio?.metas?.artist ?? 'undefined',
      style: TextStyle(color: Colors.grey[600], fontSize: 15),
    );
  }