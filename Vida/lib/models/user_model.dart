import 'dart:ffi';

class UserModel {
  int? userId;
  String? username;
  Bool? authentication;

  UserModel(
    this.userId,
    this.username,
    this.authentication,
  );
  factory UserModel.fromJson(Map<String, dynamic> json) {
    return UserModel(json["userId"], json["username"], json["authentication"]);
  }

  Map<String, dynamic> toJson({int depth = 0}) => {
        "userId": userId,
        "username": username,
        "authentication": authentication,
      };
  @override
  String toString() => toJson().toString();
}
