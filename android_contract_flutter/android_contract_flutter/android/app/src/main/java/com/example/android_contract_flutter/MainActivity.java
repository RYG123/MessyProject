package com.example.android_contract_flutter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.FrameLayout;

import io.flutter.app.FlutterActivity;
import io.flutter.app.FlutterFragmentActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.FlutterPluginCounter;
import io.flutter.plugins.FlutterPluginJumpToAct;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.view.FlutterView;
import io.flutter.*;

  public class MainActivity extends FlutterActivity {
  private static final String CHANNEL ="android_contract_flutter";
  private static final String TAG ="MyFlutterViewActivity";
  private static final int REQUEST_CODE = 1000;
  FlutterView flutterView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);
    registerCoutomPlugin(this);
  }

    private static void registerCoutomPlugin(PluginRegistry registry) {
      FlutterPluginJumpToAct.registerWith(registry.registrarFor(FlutterPluginJumpToAct.CHANNEL));
      FlutterPluginCounter.registerWith(registry.registrarFor(FlutterPluginJumpToAct.CHANNEL));
    }
  }
