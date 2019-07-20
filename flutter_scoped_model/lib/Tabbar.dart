import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:scoped_model/scoped_model.dart';

import 'UnderScreen.dart';
import 'base_scoped_model.dart';

class TopScreen extends StatefulWidget{
  final Widget child;
  TopScreen({this.child});
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return _TopScreenState(child);
  }

}

class _TopScreenState extends State<TopScreen>{
  final Widget child;
  _TopScreenState(this.child);

  Model getModel(BuildContext context){
    final countModel = BaseScopedModel().of(context);

    countModel.increment();
    return countModel;
  }
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return ScopedModelDescendant<BaseScopedModel>(
      builder: (context,child,model){
        return Scaffold(
          appBar:AppBar(
            title:Text("Top Screen"),
          ),
          body: Center(
            child: this.child,
          ),
          floatingActionButton: FloatingActionButton(
              onPressed:(){
                Navigator.of(context).push(MaterialPageRoute(builder: (BuildContext context){
                  return UnderScreen(title:"Under Screen",);
                }));
              } ,
          child: Icon(Icons.forward),),
        );
      },
    );
  }
}