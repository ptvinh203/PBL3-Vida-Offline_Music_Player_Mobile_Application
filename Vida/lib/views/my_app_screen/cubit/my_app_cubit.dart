import 'package:bloc/bloc.dart';
import 'package:freezed_annotation/freezed_annotation.dart';

import '../my_app_screen.dart';

part 'my_app_state.dart';
part 'my_app_cubit.freezed.dart';

class MyAppCubit extends Cubit<MyAppState> {
  MyAppCubit() : super(MyAppState.initial());

  update(MyAppState Function(MyAppState) onUpdate) => emit(onUpdate(state));
}
