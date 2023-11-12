import 'package:flutter/material.dart';

import '../../../shared/consts/colors.dart';

class EmptySongDownloadList extends StatelessWidget {
  const EmptySongDownloadList({super.key});

  @override
  Widget build(BuildContext context) {
    return Center(
        child: Column(
      children: [
        Padding(
          padding: const EdgeInsets.only(left: 25, top: 40),
          child: Image.asset('assets/image/dash_lost_connection.png',
              fit: BoxFit.cover),
        ),
        Container(
          height: 70.0,
          margin: EdgeInsets.symmetric(horizontal: 20.0, vertical: 10.0),
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
                    SizedBox(height: 14),
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
    ));
  }
}
