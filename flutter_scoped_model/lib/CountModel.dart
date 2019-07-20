import 'base_scoped_model.dart';

class CountModel extends BaseScopedModel{
  int counter = 0;
  int get _counter => counter;

  void increment(){
    counter+2;
    notifyListeners();
  }
}