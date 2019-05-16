这是我第一次做的flutter项目，在很多地方都很不成熟，而且特别特别多bug，因为自己平常想写blog，可是又怕自己写的不太好，虽然blog也是只是写给自己看的，
但是还是现在这里写一下，练下手：

1.这里的这个小项目主要用了几个东西，一个是tab+tabView+tabController的混合使用：


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
  我这里是先用一个赋值<Tab>类型的list，把需要的标题都给放上去：
  var tabs = <Tab>[
    Tab(child: new Text('数据'),),
    Tab(child: new Text("控制"),),
    Tab(child: new Text("设置"),),
  ];
  像这样，当然你也可以直接在Scaffold里面的tab：widget[....]进行一个一个的编写，看习惯
   body: TabBarView(
          controller: tabController,
          children: <Widget>[
            Center(child: data(_counter)),
            Center(child: controller(_counter)),
            Center(child: setting(_counter))
            ....
          ], 
        ),
  然后再Scafflod的body上进行页面的添加
  
  接着是定义tabController，tabController的作用是，进行页面的监听和进行动画的调整，当然假如你不想定义一个controller可以用官方自带的DefalutContrller，他会帮你完成动画效果，不过监听的话好像只能通过自定义的才能去监听。  
  TabController tabController;
  tabController = TabController(length: tabs.length, vsync: this)
  //这里需要给State的类添加一个"SingleTickerProviderStateMixin",以为vsync是动画用到的。所以添加单个的动画效果，让其切换页面的时候有一个动画效果
  return Scaffold(
        appBar: AppBar(
          title:Text(widget.title),
          bottom:TabBar(
            controller: tabController,//添加tabcontroller
            ...),
        ),
        body: TabBarView(
          controller: tabController,//添加tabcontroller（总共上下两处)
          .....
          ], 
        ),
    );
    进行页面监听，可以放在主页面的initState（）方法里面，这里每次切换页面就可以看到处于哪个界面：
     tabController = TabController(length: tabs.length, vsync: this)
        ..addListener(() {
        switch (tabController.index) {
          case 0 :
            print(1);
            break;
          case 1 :
            print(2);
            break;
          case 2 :
            print(3);
            break；
      }
        });
        但是现在就会出现一个问题，虽然在滑动界面的时候不会出现，但是当你通过点击上面的tab按钮，他就会进行打印两次。
        这不是我们想要的，所以要在 addlistener()方法里面添加一个判断条件,
         tabController = TabController(length: tabs.length, vsync: this)
        ..addListener(() {
      if(tabController.index.toDouble() == tabController.animation.value) {
        switch (tabController.index) {
         ....
        }
      }
        });
        在这样可以解决问题，为啥之前不加的时候点击会打印两次
        原因大致是因为： 点击时 在动画过程先后触发了 notifyListeners();
        主要问题：https://github.com/flutter/flutter/issues/13848
        
        现在这样就可以使用tab，可是，当tab跨页面跳转的时候，之前的页面又会重新被initState（），这个时候，我们可以给每个页面的
        State类 with 一个AutomaticKeepAliveClientMixin，并且将它的值设置成true，这样子就可以进行状态的保存。
        
        可是，我还是发现了一个问题，我在每个子页面的initState中进行打印，滑动的时候不会重新运行initState（），点击相邻的tab按钮不会进行initState（），可是，当跨tab点击的时候，就会发现会中间的所有页面和目的页面的initState（）都重新运行了。。。额，这个目前暂时没有解决方法，还的研究，可是我发现好像虽然initState（）运行，可是却不会把数据弄丢，数据依然在。。这的确是要给问题，有待研究。
        我上网搜了两个blog说是通过其他方式去解决保存问题的，可以参考一下：
        https://blog.csdn.net/j550341130/article/details/88102927 //这个是使用AutomaticKeepAliveClientMixin有可能发生的问题
        https://www.jianshu.com/p/cefe49a0ab7f //这是是使用tab+offstage+stack进行的，不过这个会一下子把所有页面初始化，这个好不好，看情况
        截止到这里，就是tab的使用：
        
2.路由跳转与回传参数：
  路由跳转有两种
