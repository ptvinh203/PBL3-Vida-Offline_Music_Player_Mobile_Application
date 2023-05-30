class RegisterRequest {
  String? username;
  String? password;
  String? fullName;
  String? phoneNumber;

  RegisterRequest(
      {this.username, this.password, this.fullName, this.phoneNumber});
  factory RegisterRequest.ByPassword(
      String userName, String password, String fullName, String phoneNumber) {
    return RegisterRequest(
        username: userName,
        password: password,
        fullName: fullName,
        phoneNumber: phoneNumber);
  }

  factory RegisterRequest.fromJson(Map<String, dynamic> json) {
    return RegisterRequest.ByPassword(json["username"], json["password"],
        json["fullName"], json["phoneNumber"]);
  }

  Map<String, dynamic> toJson() {
    return {
      "username": this.username,
      "password": this.password,
      "fullName": this.fullName,
      "phoneNumber": this.phoneNumber
    };
  }
}
