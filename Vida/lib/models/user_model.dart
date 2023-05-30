import 'dart:ffi';

class UserModel {
  int? userId;
  String? username;
  bool? authentication;
  String? fullName;
  String? phoneNumber;
  UserModel(
    this.userId,
    this.username,
    this.authentication,
    this.fullName,
    this.phoneNumber
  );
  factory UserModel.fromJson(Map<String, dynamic> json) {
    return UserModel(json["userId"], json["username"], json["authentication"], json["fullName"], json["phoneNumber"]);
  }

  Map<String, dynamic> toJson({int depth = 0}) => {
        "userId": userId,
        "username": username,
        "authentication": authentication,
        "fullName": fullName,
        "phoneNumber": phoneNumber
      };
  @override
  String toString() => toJson().toString();
}
