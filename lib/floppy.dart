
import 'dart:async';

import 'package:flutter/services.dart';

typedef FloppyDelegate = Future<dynamic> Function(dynamic arguments);

const MethodChannel _channel = MethodChannel('org.axen.floppy');

class Floppy {

  static Floppy? _intance;

  static Floppy get instance {
    return _intance ??= Floppy._();
  }

  Floppy._() {
    _channel.setMethodCallHandler(_handleMethodCall);
  }
  
  final Map<String, FloppyDelegate> _delegates = {};
  
  Future<T?> invoke<T>(String method, [dynamic arguments]) {
    return _channel.invokeMethod<T?>(method, arguments);
  }

  Future<dynamic> _handleMethodCall(MethodCall call) async {
    String method = call.method;
    final FloppyDelegate? delegate = _delegates[method];
    if (delegate != null) {
      return delegate.call(call.arguments);
    }
  }

  void define(String method, FloppyDelegate delegate) {
    _delegates[method] = delegate;
  }

  void remove(String method) {
    _delegates.remove(method);
  }
}
