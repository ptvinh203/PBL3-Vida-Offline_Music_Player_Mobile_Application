class LoginRequest {
  String? username;
  String? password;

  LoginRequest({this.username, this.password});
  factory LoginRequest.ByPassword(String userName, String password) {
    return LoginRequest(username: userName, password: password);
  }

  factory LoginRequest.fromJson(Map<String, dynamic> json) {
    return LoginRequest.ByPassword(json["username"], json["password"]);
  }

  Map<String, dynamic> toJson() {
    return {"username": this.username, "password": this.password};
  }
}
