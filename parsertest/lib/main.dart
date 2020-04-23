import 'package:flutter/material.dart';
import 'package:parsertest/seekbar.dart';


void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  int abc = 8;

  void _incrementCounter() {
    setState(() {
      // This call to setState tells the Flutter framework that something has
      // changed in this State, which causes it to rerun the build method below
      // so that the display can reflect the updated values. If we changed
      // _counter without calling setState(), then the build method would not be
      // called again, and so nothing would appear to happen.
      _counter++;
    });
    var uri = "http://vc/ABC?foo=10&id=100";
    Uri parser = Uri.parse(uri);
    print("parser, parser.scheme : ${parser.scheme}\n, parser.host: ${parser.host}\n,"
        " parser.path: ${parser.path}\n, parser.fragment: ${parser.fragment}\n, parser.origin: ${parser.origin}\n, "
        + "parser.data: ${parser.data}\n, parser.authority: ${parser.authority}\n, parser.hasAuthority: ${parser.hasAuthority}\n,"
            " parser.query: ${parser.query}\n, parser.queryParser: ${parser.queryParameters["foo"]}\n"
            ", parser.queryAll: ${parser.queryParametersAll}\n, parser.hasAbsolutePath: ${parser.hasAbsolutePath}\n,"
            " parser.hasEmptyPath: ${parser.hasEmptyPath}\n, parser.hasFragment: ${parser.hasFragment}\n,"
            " parser.pathSegments:${parser.pathSegments}\n, parser.hasPort: ${parser.hasPort}\n,"
            " parser.isAbsolute: ${parser.isAbsolute}\n, parser.port: ${parser.port}\n "
            "parser.userInfo: ${parser.userInfo} \n parser.runtimeType: ${parser.runtimeType}\n parser.hashCode: ${parser.hashCode}");

    var encoded = Uri.encodeFull(uri);
    print("encode: $encoded");

    var decoded = Uri.decodeFull(encoded);
    print("decoded: $decoded");

//    assert(uri.scheme   == 'http');
//    assert(uri.host     == 'example.org');
//    assert(uri.path     == '/foo/bar');
//    assert(uri.fragment == 'frag');
//    assert(uri.origin   == 'http://example.org:8080');


  }

  @override
  Widget build(BuildContext context) {
    // This method is rerun every time setState is called, for instance as done
    // by the _incrementCounter method above.
    //
    // The Flutter framework has been optimized to make rerunning build methods
    // fast, so that you can just rebuild anything that needs updating rather
    // than having to individually change instances of widgets.
    return Scaffold(
      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text(widget.title),
      ),
      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child:Container(
          child: SeekBar(
            min: 0,
            max: 10,
            progresseight: 8,
            value: abc.toDouble(),
            sectionCount: 10,
            showSectionText: false,
            sectionTextMarginTop: 0,
            sectionUnSelecteColor: Colors.white,
            sectionDecimal: 0,
            sectionTextColor: Colors.white,
            sectionSelectTextColor: Color(
                0xffD0D2D5),
            sectionTextSize: 14,
            hideBubble: false,
            bubbleRadius: 0,
            backgroundColor: Colors.grey,
            progressColor: Colors.white,
            bubbleColor: Colors.white,
            bubbleTextColor: Colors.white,
            bubbleTextSize: 14,
            bubbleMargin: 20,
            bubbleHeight: 0,
            alwaysShowBubble: true,
            afterDragShowSectionText: true,
            indicatorColor: Colors.white,
            indicatorRadius: 10.5,
            onValueDragEnd: (v) {

            },
            onValueChanged: (v) {
              setState(() {
                abc = v.value.toInt();
              });
            },
          ),
          width: 200,
          height: 50,
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
