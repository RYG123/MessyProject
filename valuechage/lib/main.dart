import 'dart:async';

import 'package:flutter/material.dart';
import 'package:valuechange/EventData.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);
  final String title;
  @override
  _MyHomePageState createState() => _MyHomePageState();
}
class _MyHomePageState extends State<MyHomePage>with SingleTickerProviderStateMixin {

  TabController tabController;
  var tabs = <Tab>[
    Tab(child: new Text('数据'),),
    Tab(child: new Text("控制"),),
    Tab(child: new Text("设置"),),
  ];
  int _counter = 0;
  bool tan = false;
  void initState() {
    super.initState();

      tabController = TabController(length: tabs.length, vsync: this)
        ..addListener(() {
      if(tabController.index.toDouble() == tabController.animation.value) {
        switch (tabController.index) {
          case 0 :
            print(1);
            break;
          case 1 :
            print(2);
            break;
          case 2 :
            print(3);
            break;
        }
      }
        });
  }
  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title:Text(widget.title),
          bottom:TabBar(
            controller: tabController,
            tabs: tabs,),
        ),
        body: TabBarView(
          controller: tabController,
          children: <Widget>[
            Center(child: data(_counter)),
            Center(child: controller(_counter)),
            Center(child: setting(_counter,(){
              Navigator.of(context).push( new MaterialPageRoute(builder: (context)=>new Pagger()))
                  .then((s){
                print("s:"+s.toString());
                setState(() {
                  _counter = s["counter"];
                  tan = s["tan"];
                });
                eventBus.fire((new counter(_counter)));
                eventBus.fire((new Tan(tan)));
              });
            })),
          ], 
        ),
    );
  }

}
class controller extends StatefulWidget{
  int _counter = 0;
  controller(int _counter){
    this._counter = _counter;
    print("重新controller build");
  }
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return new controllerState(_counter);
  }

}

class controllerState extends State<controller>{
  int _counter  = 0;
  controllerState(this._counter);
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    print("initState() controllerState");
  }
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Container(
      child: Center(
        child: Text('${_counter + 1}'),
      ),
    );
  }

  @override
  // TODO: implement wantKeepAlive
  bool get wantKeepAlive => true;
}
class data extends StatefulWidget{
  int _counter = 0;
  data(int _counter){
    this._counter = _counter;
    print("重新data build");
  }
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return new dataState(_counter.toString());



  }

}

class dataState extends State<data>{
  String _counter  ;
  dataState(this._counter);
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    print("initState() dataState");
    hao((e){
      _counter = e;
      print(e);

    });
  }
  hao(ValueChanged voidCallback){
    setState(() {
      voidCallback("fanhui ");
    });


  }
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Container(
      child: Center(
        child: Text('${_counter}'),
      ),
    );
  }

  @override
  // TODO: implement wantKeepAlive
  bool get wantKeepAlive => true;
}


class Pagger extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return new PaggerState();
  }

}
class setting extends StatefulWidget {
  VoidCallback goPagger;
  int _counter = 0;
  setting(int _counter,VoidCallback goPagger){
    this._counter = _counter;
    this.goPagger = goPagger;
    print("重新setting build");
  }
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return new settingState(_counter);
  }
}

class settingState extends State<setting> {
  StreamSubscription event1;
  StreamSubscription event2;
  int _counter  = 0;
  bool tan = false;
  settingState(this._counter);
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    print("initState() setting");
    liste();
  }
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Container(
      child: Column(
        children: <Widget>[
          Text('${_counter + 2}'),
          RawMaterialButton(
            child: Text("setting按钮"),
            onPressed: (){
              widget.goPagger();
            },
          )
        ],
      ),
    );
  }

  void liste() {
    event1 = eventBus.on<counter>().listen((event){
      setState(() {
        _counter = event.pmList;
      });
    });
    event2 = eventBus.on<Tan>().listen((event){
      setState(() {
        tan = event.Connect;
        if(tan){
          Navigator.of(context).push( new MaterialPageRoute(builder: (context)=>new NamePager()))
              .then((s){
           setState(() {
             _counter = s;
           });

          });
        }
      });
    });
  }

  @override
  // TODO: implement wantKeepAlive
  bool get wantKeepAlive => true;
@override
  void dispose() {
    // TODO: implement dispose
  if(event1!=null) {
    event1.cancel();
  }
  if(event2 != null) {
    event2.cancel();
  }
    super.dispose();
  }
}

class NamePager extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        title: Text("namepage"),
      ),
      body:RawMaterialButton(onPressed: (){
        Navigator.of(context).pop(10);
      },
      child: Text("返回按钮"),),
      
    );
  }
}
class PaggerState extends State<Pagger>{
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        title: Text("pagger"),
      ),
      body: new Container(
        child: Column(
          children: <Widget>[
            Text("Pagger"),
            RawMaterialButton(
              child: Text("返回按钮"),
              onPressed: (){
                var map = {"counter":10,"tan":true};
                Navigator.of(context).pop(map);
              },
            )
          ],
        ),
      ),
    );
  }
}
