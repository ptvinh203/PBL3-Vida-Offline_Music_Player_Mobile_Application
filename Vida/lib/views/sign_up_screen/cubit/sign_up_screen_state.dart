part of 'sign_up_screen_cubit.dart';

@freezed
class SignUpScreenState with _$SignUpScreenState {
  const factory SignUpScreenState.initial({
    bool? isSignUpSuccess,
    UserModel? userModel,
  }) = _Initial;
}
