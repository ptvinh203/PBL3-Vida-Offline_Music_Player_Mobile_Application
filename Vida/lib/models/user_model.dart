class UserModel {
  int? userId;
  String? username;
  bool? authentication;
  String? fullName;
  String? phoneNumber;
  UserModel({
    this.userId,
    this.username,
    this.authentication,
    this.fullName,
    this.phoneNumber,
  });

  factory UserModel.fromJson(Map<String, dynamic> json) {
    return UserModel(
      userId: json["userId"] as int,
      username: json["username"],
      authentication: json["authentication"] as bool,
      fullName: json["fullName"],
      phoneNumber: json["phoneNumber"],
    );
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
