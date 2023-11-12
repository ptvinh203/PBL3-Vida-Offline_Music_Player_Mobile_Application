import 'package:Vida/models/user_model.dart';
import 'package:bloc/bloc.dart';
import 'package:freezed_annotation/freezed_annotation.dart';

import '../../../services/user_service.dart';

part 'sign_up_screen_state.dart';
part 'sign_up_screen_cubit.freezed.dart';

class SignUpScreenCubit extends Cubit<SignUpScreenState> {
  SignUpScreenCubit() : super(SignUpScreenState.initial());
  final UserService _userService = UserService.getInstance();

  Future signUp(UserModel userModel, String password) async {
    try {
      UserModel result = await _userService.register(userModel, password);
      emit(state.copyWith(userModel: result, isSignUpSuccess: true));
    } catch (err) {
      emit(state.copyWith(isSignUpSuccess: false));
    }
  }

  update(SignUpScreenState Function(SignUpScreenState) onUpdate) =>
      emit(onUpdate(state));
}
