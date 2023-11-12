import 'package:flutter/material.dart';

import '../consts/colors.dart';

Future showMyDialog(
  BuildContext context, {
  String? title,
  String? content,
  bool hasButton = true,
}) {
  return showDialog(
      context: context,
      builder: (_) {
        return AlertDialog(
          title: Text(title ?? "Notifications"),
          content: Text(content ?? ""),
          actions: [
            if (hasButton)
              TextButton(
                style: TextButton.styleFrom(
                  textStyle: Theme.of(context).textTheme.labelLarge,
                ),
                child: Text('Cancel', style: TextStyle(color: purpButton)),
                onPressed: () {
                  Navigator.of(_).pop();
                },
              ),
          ],
        );
      });
}

Future showLoginDialog(
  BuildContext context, {
  String? title,
  String? content,
  void Function()? onLogin,
  void Function()? onCancel,
}) {
  return showDialog(
      context: context,
      builder: (_) {
        return AlertDialog(
          title: Text(title ?? "Notifications"),
          content: Text(content ?? ""),
          actions: [
            TextButton(
              style: TextButton.styleFrom(
                textStyle: Theme.of(context).textTheme.labelLarge,
              ),
              child: Text('Log in', style: TextStyle(color: purpButton)),
              onPressed: () {
                Navigator.pop(_);
                onLogin?.call();
              },
            ),
            TextButton(
              style: TextButton.styleFrom(
                textStyle: Theme.of(context).textTheme.labelLarge,
              ),
              child: Text('Cancel', style: TextStyle(color: purpButton)),
              onPressed: () {
                onCancel?.call ?? Navigator.of(_).pop();
              },
            ),
          ],
        );
      });
}

Future showConfirmDialog(
  BuildContext context, {
  String? title,
  String? content,
  void Function()? onAccept,
  void Function()? onCancel,
}) {
  return showDialog(
      context: context,
      builder: (_) {
        return AlertDialog(
          title: Text(title ?? "Notifications"),
          content: Text(content ?? ""),
          actions: [
            TextButton(
              style: TextButton.styleFrom(
                textStyle: Theme.of(context).textTheme.labelLarge,
              ),
              child: Text('Accept', style: TextStyle(color: purpButton)),
              onPressed: () {
                Navigator.pop(_);
                onAccept?.call();
              },
            ),
            TextButton(
              style: TextButton.styleFrom(
                textStyle: Theme.of(context).textTheme.labelLarge,
              ),
              child: Text('Cancel', style: TextStyle(color: purpButton)),
              onPressed: () {
                onCancel?.call ?? Navigator.of(_).pop();
              },
            ),
          ],
        );
      });
}
