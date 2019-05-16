import 'package:event_bus/event_bus.dart';

EventBus eventBus =  EventBus();

class BleGetData{
  int Stall;
  bool Negative;
  bool SafetyLock;
  int dustLevel;
  double pmNum;
  int IAQI;
  int ScreenTime;
  int YuanColor;
  bool isAuto;

  BleGetData(this.Stall,this.Negative,this.SafetyLock,this.dustLevel,this.pmNum,this.IAQI,this.ScreenTime,this.YuanColor,this.isAuto);
//  List list = new List();
//  BleGetData(this.list);
}
class DeviceNameChange{
  bool isChangeDevice;
  String identifier;
  DeviceNameChange(this.isChangeDevice, this.identifier);
}

class counter{
  int pmList ;
  counter(this.pmList);
}
class DeviceName{
  String deviceName;
  DeviceName(this.deviceName);
}
class Tan{
  bool Connect;
  Tan(this.Connect);
}
