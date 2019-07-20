import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:scoped_model/scoped_model.dart';

import 'base_scoped_model.dart';

class UnderScreen extends StatefulWidget{
  final String title;
  UnderScreen({Key key, this.title}):super(key:key);
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return _underScreen();
  }

}

class _underScreen extends State<UnderScreen>{
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return ScopedModelDescendant<BaseScopedModel>(builder: (context,child,model){
      return Scaffold(
        appBar: new AppBar(
          title: new Text(widget.title),
        ),
        body: new Center(
          child: new Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              new Text("you have push the button this many times:",style: TextStyle(fontSize: 20.0),),
              new Text("${model.counter}",style: TextStyle(fontSize: 36.0),),
            ],
          ),
        ),
        floatingActionButton: new FloatingActionButton(onPressed: ()=>model.increment(),
        tooltip: 'Increment',
        child: new Icon(Icons.add),),
      );
    },);
  }
}