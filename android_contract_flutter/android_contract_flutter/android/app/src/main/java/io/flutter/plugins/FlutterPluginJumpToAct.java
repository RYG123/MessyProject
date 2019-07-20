package io.flutter.plugins;

import android.app.Activity;
import android.content.Intent;

import com.example.android_contract_flutter.TwoActivity;
import com.example.android_contract_flutter.oneActivity;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

public class FlutterPluginJumpToAct implements MethodChannel.MethodCallHandler {
    public static String CHANNEL ="android_contract_flutter";
    static MethodChannel channel;
    private Activity activity;
    private FlutterPluginJumpToAct(Activity activity){
        this.activity = activity;
    }
    public static void registerWith(PluginRegistry.Registrar registrar){
        channel = new MethodChannel(registrar.messenger(),CHANNEL);
        FlutterPluginJumpToAct instance = new FlutterPluginJumpToAct(registrar.activity());
        channel.setMethodCallHandler(instance);
    }
    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        if(methodCall.method.equals("oneAct")){
            Intent intent = new Intent(activity, oneActivity.class);
            activity.startActivity(intent);
            result.success("success");
        }else if(methodCall.method.equals("twoAct")){
            String text = methodCall.argument("flutter");

            Intent intent = new Intent(activity, TwoActivity.class);
            intent.putExtra(TwoActivity.VALUE,text);
            activity.startActivity(intent);
            result.success("success");
        }else{
            result.notImplemented();
        }
    }
}
