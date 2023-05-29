import 'dart:convert';

import 'package:Vida/models/login_request.dart';
import 'package:Vida/models/user_model.dart';
import 'package:flutter/cupertino.dart';

import 'config.dart';
import 'package:http/http.dart' as http;

class UserService {
  static UserService instance = UserService.__();

  UserService.__(){

  }

  Future<UserModel> login(LoginRequest request) {
    return http
        .post(Uri.http(api_url, "/users/login"),
            headers: {"Accept": "application/json;charset=utf-8"},
            body: jsonEncode(request.toJson()))
        .then((value) {
      UserModel userModel = UserModel.fromJson(jsonDecode(value.body));
      loggedInUser = userModel;
      return userModel;
    });
  }
  
  Future<UserModel> register(LoginRequest request){
    return http
        .post(Uri.http(api_url, "/users"),
            headers: {"Accept": "application/json;charset=utf-8"},
            body: jsonEncode(request.toJson()))
        .then((value) {
      UserModel userModel = UserModel.fromJson(jsonDecode(value.body));
      return userModel;
    });
  }

  UserModel? loggedInUser = null;
}
