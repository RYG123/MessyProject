import 'package:scoped_model/scoped_model.dart';

class BaseScopedModel extends Model{
  int counter = 0;
  int get _counter => counter;

  void increment(){
    counter++;
    notifyListeners();
  }

  BaseScopedModel of(context)=>ScopedModel.of<BaseScopedModel>(context);
}