import 'package:Vida/models/user_model.dart';
import 'package:bloc/bloc.dart';
import 'package:freezed_annotation/freezed_annotation.dart';

import '../../../services/user_service.dart';

part 'login_screen_state.dart';
part 'login_screen_cubit.freezed.dart';

class LoginScreenCubit extends Cubit<LoginScreenState> {
  LoginScreenCubit() : super(LoginScreenState.initial());
  final UserService _userService = UserService.getInstance();
  Future login(String username, String password) async {
    try {
      UserModel userModel = await _userService.login(username, password);
      emit(state.copyWith(userModel: userModel, isLogin: true));
    } catch (err) {
      emit(state.copyWith(isLogin: false));
    }
  }

  update(LoginScreenState Function(LoginScreenState) onUpdate) =>
      emit(onUpdate(state));
}
