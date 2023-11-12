import 'package:Vida/models/user_model.dart';
import 'package:bloc/bloc.dart';
import 'package:freezed_annotation/freezed_annotation.dart';

part 'profile_screen_state.dart';
part 'profile_screen_cubit.freezed.dart';

class ProfileScreenCubit extends Cubit<ProfileScreenState> {
  ProfileScreenCubit() : super(ProfileScreenState.initial());
  update(ProfileScreenState Function(ProfileScreenState) onUpdate) =>
      emit(onUpdate(state));
}
