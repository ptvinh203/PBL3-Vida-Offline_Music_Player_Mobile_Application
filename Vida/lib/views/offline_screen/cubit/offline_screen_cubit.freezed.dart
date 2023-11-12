// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: type=lint
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target, unnecessary_question_mark

part of 'offline_screen_cubit.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more information: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
mixin _$OfflineScreenState {
  List<SongModelExtended>? get listOfflineSongs =>
      throw _privateConstructorUsedError;
  List<SongModelExtended> get favouriteSongs =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function(List<SongModelExtended>? listOfflineSongs,
            List<SongModelExtended> favouriteSongs)
        initial,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult? Function(List<SongModelExtended>? listOfflineSongs,
            List<SongModelExtended> favouriteSongs)?
        initial,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function(List<SongModelExtended>? listOfflineSongs,
            List<SongModelExtended> favouriteSongs)?
        initial,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(_Initial value) initial,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult? Function(_Initial value)? initial,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(_Initial value)? initial,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;

  @JsonKey(ignore: true)
  $OfflineScreenStateCopyWith<OfflineScreenState> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $OfflineScreenStateCopyWith<$Res> {
  factory $OfflineScreenStateCopyWith(
          OfflineScreenState value, $Res Function(OfflineScreenState) then) =
      _$OfflineScreenStateCopyWithImpl<$Res, OfflineScreenState>;
  @useResult
  $Res call(
      {List<SongModelExtended>? listOfflineSongs,
      List<SongModelExtended> favouriteSongs});
}

/// @nodoc
class _$OfflineScreenStateCopyWithImpl<$Res, $Val extends OfflineScreenState>
    implements $OfflineScreenStateCopyWith<$Res> {
  _$OfflineScreenStateCopyWithImpl(this._value, this._then);

  // ignore: unused_field
  final $Val _value;
  // ignore: unused_field
  final $Res Function($Val) _then;

  @pragma('vm:prefer-inline')
  @override
  $Res call({
    Object? listOfflineSongs = freezed,
    Object? favouriteSongs = null,
  }) {
    return _then(_value.copyWith(
      listOfflineSongs: freezed == listOfflineSongs
          ? _value.listOfflineSongs
          : listOfflineSongs // ignore: cast_nullable_to_non_nullable
              as List<SongModelExtended>?,
      favouriteSongs: null == favouriteSongs
          ? _value.favouriteSongs
          : favouriteSongs // ignore: cast_nullable_to_non_nullable
              as List<SongModelExtended>,
    ) as $Val);
  }
}

/// @nodoc
abstract class _$$InitialImplCopyWith<$Res>
    implements $OfflineScreenStateCopyWith<$Res> {
  factory _$$InitialImplCopyWith(
          _$InitialImpl value, $Res Function(_$InitialImpl) then) =
      __$$InitialImplCopyWithImpl<$Res>;
  @override
  @useResult
  $Res call(
      {List<SongModelExtended>? listOfflineSongs,
      List<SongModelExtended> favouriteSongs});
}

/// @nodoc
class __$$InitialImplCopyWithImpl<$Res>
    extends _$OfflineScreenStateCopyWithImpl<$Res, _$InitialImpl>
    implements _$$InitialImplCopyWith<$Res> {
  __$$InitialImplCopyWithImpl(
      _$InitialImpl _value, $Res Function(_$InitialImpl) _then)
      : super(_value, _then);

  @pragma('vm:prefer-inline')
  @override
  $Res call({
    Object? listOfflineSongs = freezed,
    Object? favouriteSongs = null,
  }) {
    return _then(_$InitialImpl(
      listOfflineSongs: freezed == listOfflineSongs
          ? _value._listOfflineSongs
          : listOfflineSongs // ignore: cast_nullable_to_non_nullable
              as List<SongModelExtended>?,
      favouriteSongs: null == favouriteSongs
          ? _value._favouriteSongs
          : favouriteSongs // ignore: cast_nullable_to_non_nullable
              as List<SongModelExtended>,
    ));
  }
}

/// @nodoc

class _$InitialImpl implements _Initial {
  const _$InitialImpl(
      {final List<SongModelExtended>? listOfflineSongs,
      final List<SongModelExtended> favouriteSongs = const []})
      : _listOfflineSongs = listOfflineSongs,
        _favouriteSongs = favouriteSongs;

  final List<SongModelExtended>? _listOfflineSongs;
  @override
  List<SongModelExtended>? get listOfflineSongs {
    final value = _listOfflineSongs;
    if (value == null) return null;
    if (_listOfflineSongs is EqualUnmodifiableListView)
      return _listOfflineSongs;
    // ignore: implicit_dynamic_type
    return EqualUnmodifiableListView(value);
  }

  final List<SongModelExtended> _favouriteSongs;
  @override
  @JsonKey()
  List<SongModelExtended> get favouriteSongs {
    if (_favouriteSongs is EqualUnmodifiableListView) return _favouriteSongs;
    // ignore: implicit_dynamic_type
    return EqualUnmodifiableListView(_favouriteSongs);
  }

  @override
  String toString() {
    return 'OfflineScreenState.initial(listOfflineSongs: $listOfflineSongs, favouriteSongs: $favouriteSongs)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _$InitialImpl &&
            const DeepCollectionEquality()
                .equals(other._listOfflineSongs, _listOfflineSongs) &&
            const DeepCollectionEquality()
                .equals(other._favouriteSongs, _favouriteSongs));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType,
      const DeepCollectionEquality().hash(_listOfflineSongs),
      const DeepCollectionEquality().hash(_favouriteSongs));

  @JsonKey(ignore: true)
  @override
  @pragma('vm:prefer-inline')
  _$$InitialImplCopyWith<_$InitialImpl> get copyWith =>
      __$$InitialImplCopyWithImpl<_$InitialImpl>(this, _$identity);

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function(List<SongModelExtended>? listOfflineSongs,
            List<SongModelExtended> favouriteSongs)
        initial,
  }) {
    return initial(listOfflineSongs, favouriteSongs);
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult? Function(List<SongModelExtended>? listOfflineSongs,
            List<SongModelExtended> favouriteSongs)?
        initial,
  }) {
    return initial?.call(listOfflineSongs, favouriteSongs);
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function(List<SongModelExtended>? listOfflineSongs,
            List<SongModelExtended> favouriteSongs)?
        initial,
    required TResult orElse(),
  }) {
    if (initial != null) {
      return initial(listOfflineSongs, favouriteSongs);
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(_Initial value) initial,
  }) {
    return initial(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult? Function(_Initial value)? initial,
  }) {
    return initial?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(_Initial value)? initial,
    required TResult orElse(),
  }) {
    if (initial != null) {
      return initial(this);
    }
    return orElse();
  }
}

abstract class _Initial implements OfflineScreenState {
  const factory _Initial(
      {final List<SongModelExtended>? listOfflineSongs,
      final List<SongModelExtended> favouriteSongs}) = _$InitialImpl;

  @override
  List<SongModelExtended>? get listOfflineSongs;
  @override
  List<SongModelExtended> get favouriteSongs;
  @override
  @JsonKey(ignore: true)
  _$$InitialImplCopyWith<_$InitialImpl> get copyWith =>
      throw _privateConstructorUsedError;
}
