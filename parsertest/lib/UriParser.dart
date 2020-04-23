class UriParser{
  static UriParser INSTANCE;

  void routeUrl({String uri}){
    var parser = Uri.parse(uri);
    switch(parser.host){
      case "vc":
        goViewController();
        break;
      case "activity":
        goActivity();
        break;
      case "flutter":
        goFlutterView();
        break;
    }
  }

  UriParser getInstance(){
    if(INSTANCE == null){
        INSTANCE = UriParser();
    }
    return INSTANCE;
  }

  void goViewController() {}

  void goActivity() {}

  void goFlutterView(){}
}