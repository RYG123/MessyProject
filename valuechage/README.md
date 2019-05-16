我的第一篇blog
________________________________________________________________________________________________________________________________________
这是我第一次做的flutter项目，在很多地方都很不成熟，而且特别特别多bug，因为自己平常想写blog，可是又怕自己写的不太好，虽然blog也是只是写给自己看的，
但是还是现在这里写一下，练下手：

这里的这个小项目主要用了几个东西:

1.一个是tab+tabView+tabController的混合使用:

      class _MyHomePageState extends State<MyHomePage>with SingleTickerProviderStateMixin {
       TabController tabController;
       var tabs = <Tab>[
       Tab(child: new Text('1'),),
       Tab(child: new Text("2"),),
       Tab(child: new Text("3"),),
       ];
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
      //这里需要给State的类添加一个"SingleTickerProviderStateMixin",因为vsync是动画用到的。
      //所以添加单个的动画效果，让其切换页面的时候有一个动画效果
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
    //进行页面监听，可以放在主页面的initState（）方法里面，这里每次切换页面就可以看到处于哪个界面：
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
        
   但是现在就会出现一个问题，虽然在滑动界面的时候不会出现，但是当你通过点击上面的tab按钮，他就会进行打印两次。这不是我们想要的，所以要在 addlistener()方法里面添加一个判断条件,
   
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
   
        主要问题阐述网址：https://github.com/flutter/flutter/issues/13848
        
   现在这样就可以使用tab，可是，当tab跨页面跳转的时候，之前的页面又会重新被initState（），这个时候，我们可以给每个页面的State类 with 一个AutomaticKeepAliveClientMixin，并且将它的值设置成true，这样子就可以进行状态的保存。
   
        类似于：    class controllerState extends State<controller> with AutomaticKeepAliveClientMixin{
        
   可是，我还是发现了一个问题，我在每个子页面的initState中进行打印，滑动的时候不会重新运行initState（），点击相邻的tab按钮不会进行initState（），可是，当跨tab点击的时候，就会发现会中间的所有页面和目的页面的initState（）都重新运行了。。。额，这个目前暂时没有解决方法，还的研究，可是我发现好像虽然initState（）运行，可是却不会把数据弄丢，数据依然在。。这的确是要给问题，有待研究。
        我上网搜了两个blog说是通过其他方式去解决保存问题的，可以参考一下：
        
   这个是使用AutomaticKeepAliveClientMixin有可能发生的问题：
   
      https://blog.csdn.net/j550341130/article/details/88102927 
   这是是使用tab+offstage+stack进行状态保存的的，不过这个会一下子把所有页面初始化，这个好不好，看情况： 
   
      https://www.jianshu.com/p/cefe49a0ab7f 
 截止到这里，就是tab的使用：
        
        
        
        
        
        
        
        
2.路由跳转与回传参数：
  路由跳转有两种方法，一种是静态路由跳转，一种是动态路由跳转：
  静态路由跳转并不能进行参数传递但可以进行参数回传，而动态路由跳转可以进行参数传递和回传
  
  静态路由的注册：
  在新建一个MD风格的App的时候，可以传入一个routes参数来定义路由。但是这里定义的路由是静态的，它不可以向下一个页面传递参数。
            
    return new MaterialApp(
      title: 'Flutter Demo',
      theme: new ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: new MyHomePage(title: 'Flutter实例'),
      routes: <String, WidgetBuilder> {
        // 这里可以定义静态路由，不能传递参数
        '/router/second': (_) => new SecondPage(),
        '/router/home': (_) => new RouterHomePage(),
      },
    );
静态路由的使用：
在需要的进行跳转的地方加上这一句话，pushNamed（）里面写的是需要跳转到的界面，只能跳转到注册过的界面

      Navigator.of(context).pushNamed('/router/second');
 
      // 带返回值
            Navigator.of(context).pushNamed('/router/second')
            .then((value) {//通过写then方法进行回传值的获取
                    // dialog显示返回值
                    _showDialog(context, value);
                  })
参数回传：
pop界面     

      Navigator.of(context).pop('这个是要返回给上一个页面的数据');//第二个界面返回
      
动态路由跳转：
我个人觉得动态比较简单，方便，而且可以传递参数：
      
      //在需要的地方调用
      Navigator.of(context).push(new MaterialPageRoute(builder: (_) {
                    return new SecondPage(title: '路由是个好东西，要进一步封装');
                  }));
                  
      //除了push(),还有pushReplacement()，跳转页面并且在动画完成之后关闭自身页面，使其不存在，移出栈堆.
      还有一个pushAndRemoveUntil ：跳转到指定页面，并按顺序（从栈顶到栈底）移出之前的所有页面，直到predicate返回true。
      没用过- -，详情请看：
            https://www.jianshu.com/p/c45ee7996efc
           
      //动态跳转带返回值
      // 带返回值
            Navigator.of(context).pushNamed('/router/second')
            .then((value) {//通过写then方法进行回传值的获取
                    // dialog显示返回值
                    _showDialog(context, value);
                  })
动态路由参数回传与静态一样：

            Navigator.of(context).pop('这个是要返回给上一个页面的数据');//第二个界面返回
可是回传的参数只有一个，那假如要多参数的时候怎么办呢？
那当然是传其他类型的参数啊，你可以传list，map等等等，传过去之后在进行解析使用

            var map = {"name":name,"id":id}
            var list = List();
            ....
            Navigator.of(context).pop(map);//第二个界面返回
            
路由跳转也可使用自定义动画：
      
      详情：https://blog.csdn.net/weixin_34406796/article/details/87297489
      
      
      
      
      
      
      
      
3.EventBus的使用
好像flutter也有自己的RxDart可是我没有用过，只用过EventBus，后面用了再补上
事件总线EventBus，用来进行事件的分发，我在android的时候用的是rxjava，没用过eventbus，不知道android的怎样。
可是flutter里面，我第一次用eventbus的时候，感觉真的好简单。真的太简单了：
添加库：

      event_bus: ^1.0.1//要注意对齐
      
 EventBus的使用：
      
      import 'package:event_bus/event_bus.dart';//import 方法

      EventBus eventBus =  EventBus();//新建实例，eventBus可以全局使用
      
      class DeviceName{//新建一个需要分发的类
            String deviceName;
            DeviceName(this.deviceName);
      }
      
然后再需要进行事件分送的地方：
      
      eventBus.fire((new DeviceName('需要传的同类型参数')));
      
接受的地方:

      String s;
      ....
     eventBus.on<counter>().listen((event){
      setState(() {//接受后进行界面的改变
      
        s = event.deviceName;
      });
    });
    
这样就完成了一个eventbus的分发和接受了，是不是很简单。
然而这里有一个隐患，假如你是持续发送和不断接受的话，再界面跳转的时候，没有即时把event对象给dispose掉，然后页面关闭了，你还是在不断的进行
setState（）方法，这时候会报错，可是不会让程序崩溃，不过有可能让你的程序功能无效。
所以在这样改变接受的地方：
      
      StreamSubscription event1;
      String s;
      ....
    event1 = eventBus.on<counter>().listen((event){
      setState(() {//接受后进行界面的改变
      
        s = event.deviceName;
      });
    });
    
    
    void dispose(){
      if(event1 != null){
            event1.cannel();
      }
      super.dispose();
    }
这样就可以便解决有可能产生问题了。




4.VoidCallBack 和 ValueChange的使用
VoidCallBack 是用来回调方法的
      
      //简单使用
      hao((){
            print('');
      });
      
      hao(VoidCallBack voidCallBack){
            voidCallBack();
      }
      
在我的这个项目中是这样用的：
      
            @override
      Widget build(BuildContext context) {
      return Scaffold(
        ...
        ),
        body: TabBarView(
          controller: tabController,
          children: <Widget>[
            Center(child: data(_counter)),
            Center(child: controller(_counter)),
            Center(child: setting(_counter,
            //注意是这里`~~~~~~~~~~~~~~在setting类里面进行~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`
            (){
              Navigator.of(context).push( new MaterialPageRoute(builder: (context)=>new Pagger()))
                  .then((s){
                print("s:"+s.toString());
                ...
                });
                ...
              });
            })),
          ], 
        ),
       );
      }

然后再setting类中:
      
      class setting extends StatefulWidget {
            //这类进行VoidCallBack的声明:
            VoidCallback goPagger;
            int _counter = 0;
            
            setting(int _counter,VoidCallback goPagger){
            this._counter = _counter;
            this.goPagger = goPagger;
            print("重新setting build");
            }
            @override
            State<StatefulWidget> createState() {
            // TODO: implement createState
            return new settingState(_counter);
                  }
             }
             
在settingState类中：
      
      class settingState extends State<setting> with AutomaticKeepAliveClientMixin{
      
            ...
            
            @override
            Widget build(BuildContext context) {
            // TODO: implement build
            return Container(
                  child: Column(
                        children: <Widget>[
                              Text('${_counter + 2}'),
                              RawMaterialButton(
                                    child: Text("setting按钮"),
                                    onPressed: (){
                                    widget.goPagger();//这里调用了父类的goPagger（）
                                    },
                                    )
                              ],
                             ),
                        );
                  }
                  ....
                  }
                  
解析一下：
      这里通过在setting中声明了VoidCallBack goPagger;
      然后再main方法里面，把整个的路由转跳方法传给setting类，也就是把路由转跳赋值给了goPagger；
      所以，当需要跳到NamePager的时候，就可以直接使用widget.goPagger进行跳转，然后回传参数的话就可以使用eventbus和pop进行参数回传跟新界面
      
ValueChange的使用：
这个方法可以进行调值：
      
       hao((e){ 

            print("e");

            });


      hao(ValueChange valueChange){
            valueChange("11111111");
      }
