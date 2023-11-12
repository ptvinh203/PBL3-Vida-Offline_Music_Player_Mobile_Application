import 'dart:convert';
import 'dart:io';
import 'package:http/http.dart' as http;
import 'package:Vida/models/user_model.dart';
import 'config.dart';

class UserService {
  UserModel? loggedInUser = null;
  static UserService? instance;
  UserService.__() {}
  static UserService getInstance() {
    if (instance == null) {
      instance = UserService.__();
    }
    return instance!;
  }

  Future<UserModel> login(String username, String password) async {
    try {
      http.Response response =
          await http.post(Uri.http(API_URL, "/users/login"),
              headers: {
                "Accept": "application/json;charset=utf-8",
                "Content-Type": "application/json;charset=utf-8"
              },
              body: jsonEncode({username: username, password: password}));
      if (response.statusCode != HttpStatus.ok) {
        return Future.error(Exception(
            "Status: ${response.statusCode} with body ${response.body}"));
      }
      UserModel userModel = UserModel.fromJson(jsonDecode(response.body));
      loggedInUser = userModel;
      return userModel;
    } catch (err) {
      return Future.error(err);
    }
  }

  Future<UserModel> register(UserModel userModel, String password) async {
    try {
      http.Response response = await http.post(Uri.http(API_URL, "/users"),
          headers: {
            "Accept": "application/json;charset=utf-8",
            "Content-Type": "application/json;charset=utf-8"
          },
          body: jsonEncode({
            ...userModel.toJson(),
            "password": password,
          }));
      if (response.statusCode != HttpStatus.ok) {
        return Future.error(Exception(
            "Status: ${response.statusCode} with body ${response.body}"));
      }
      return UserModel.fromJson(jsonDecode(response.body));
    } catch (err) {
      return Future.error(err);
    }
  }

  void logout() {
    loggedInUser = null;
  }
}
