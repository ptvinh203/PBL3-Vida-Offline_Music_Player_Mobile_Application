import 'dart:convert';
import 'dart:io';
import 'package:Vida/models/register_request.dart';
import 'package:http/http.dart' as http;

import 'package:Vida/models/login_request.dart';
import 'package:Vida/models/user_model.dart';
import 'package:flutter/cupertino.dart';

import 'config.dart';

class UserService {
  static UserService instance = UserService.__();

  UserService.__() {}

  Future<UserModel> login(LoginRequest request) {
    return http
        .post(Uri.http(api_url, "/users/login"),
            headers: {
              "Accept": "application/json;charset=utf-8",
              "Content-Type": "application/json;charset=utf-8"
            },
            body: jsonEncode(request.toJson()))
        .then((value) {
      if (value.statusCode != HttpStatus.ok)
        return Future.error(Exception(
            "Status: " + value.statusCode.toString() + ". " + value.body));
      UserModel userModel = UserModel.fromJson(jsonDecode(value.body));
      loggedInUser = userModel;
      print("-----" + loggedInUser!.toJson().toString());
      return userModel;
    });
  }

  Future<UserModel> register(RegisterRequest request) {
    print(request.toString());
    print(jsonEncode(request.toJson()));
    return http
        .post(Uri.http(api_url, "/users"),
            headers: {
              "Accept": "application/json;charset=utf-8",
              "Content-Type": "application/json;charset=utf-8"
            },
            body: jsonEncode(request.toJson()))
        .then((value) {
      if (value.statusCode != HttpStatus.ok)
        return Future.error(Exception(
            "Status: " + value.statusCode.toString() + ". " + value.body));
      UserModel userModel = UserModel.fromJson(jsonDecode(value.body));
      return userModel;
    });
  }

  void logout() {
    loggedInUser = null;
  }

  UserModel? loggedInUser = null;
}
