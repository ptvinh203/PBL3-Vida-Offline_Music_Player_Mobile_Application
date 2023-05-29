class LoginRequest {
  String? userName;
  String? password;

  LoginRequest({this.userName, this.password});
  factory LoginRequest.ByPassword(String userName, String password) {
    return LoginRequest(userName: userName, password: password);
  }

  factory LoginRequest.fromJson(Map<String, dynamic> json) {
    return LoginRequest.ByPassword(json["userName"], json["password"]);
  }

  Map<String, dynamic> toJson() {
    return {"userName": this.userName, "password": this.password};
  }
}
