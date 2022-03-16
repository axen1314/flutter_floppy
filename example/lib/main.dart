import 'dart:async';

import 'package:floppy/floppy.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String text = "使用Floppy跳转Activity";

  @override
  void initState() {
    super.initState();
    Floppy.instance.addDelegate("changeText", _handleChangeText);
  }

  @override
  void dispose() {
    super.dispose();
    Floppy.instance.removeDelegate("changeText");
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: GestureDetector(
            onTap: () {
              Floppy.instance.invoke("toSecondActivity");
            },
            child: Text(text),
          ),
        ),
      ),
    );
  }

  Future _handleChangeText(dynamic arguments) async {
    setState(() {
      text = arguments["text"];
    });
  }
}
