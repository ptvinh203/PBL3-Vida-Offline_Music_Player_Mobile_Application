part of 'my_app_cubit.dart';

@freezed
class MyAppState with _$MyAppState {
  const factory MyAppState.initial({
    @Default(BottomTab.OFFLINE) BottomTab currentTab,
  }) = _Initial;
}
