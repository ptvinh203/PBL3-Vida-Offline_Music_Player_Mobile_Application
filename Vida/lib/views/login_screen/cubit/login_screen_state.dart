part of 'login_screen_cubit.dart';

@freezed
class LoginScreenState with _$LoginScreenState {
  const factory LoginScreenState.initial({
    bool? isLogin,
    UserModel? userModel,
  }) = _Initial;
}
