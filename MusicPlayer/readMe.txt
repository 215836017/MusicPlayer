第三方库(框架)
1. ButterKnife(2017.08.15)




1. 目标：做一个仿酷狗音乐的APP

2017.05.09



1. 完成了TestActivity，做了一个简单的测试。

2017.05.16



1. 关于Service：
 (1):onstart()方法和onStartCommand()方法的区别：
    onstart()方法是在android2.0一下的版本中使用。而在android2.0以上则使用onstartCommand()方法。它们两个方法放在一起使用时，不会产生冲突。
 
(2):onStartComand使用时，返回的是一个(int)整形。
    这个整形可以有四个返回值：start_sticky、start_no_sticky、START_REDELIVER_INTENT、START_STICKY_COMPATIBILITY。
    它们的含义分别是：
       1):START_STICKY：如果service进程被kill掉，保留service的状态为开始状态，但不保留递送的intent对象。随后系统会尝试重新创建service，由于服务状态为开始状态，所以创建服务后一定会调用onStartCommand(Intent,int,int)方法。如果在此期间没有任何启动命令被传递到service，那么参数Intent将为null。
       2):START_NOT_STICKY：“非粘性的”。使用这个返回值时，如果在执行完onStartCommand后，服务被异常kill掉，系统不会自动重启该服务
       3):START_REDELIVER_INTENT：重传Intent。使用这个返回值时，如果在执行完onStartCommand后，服务被异常kill掉，系统会自动重启该服务，并将Intent的值传入。
      4):START_STICKY_COMPATIBILITY：START_STICKY的兼容版本，但不保证服务被kill后一定能重启。
 
(3)intentservice的用法
  Android的Service阻塞时，通过Looper和Thread来解决标准Service中处理逻辑的阻塞问题。在android中，Service和activity是运行在同一个主线程中的。当其中的service程序出现睡眠5秒时，activity也会当机。
  IntenService是service的子类，用来处理异步请求。客户端通过startService(Intent)方法传递请求给IntentService，IntentService通过worker thread处理每个intent对象，执行完后自动停止Service。
  intentService需要实现两个方法：一个构造方法，复写一个onHandleIntent()方法。



217.05.21



1. 完成了基本的查询并且做了简单的数据适配显示来验证代码的基本逻辑和效果

2. 在程序运行的第一步就崩溃了，一直提示从数据库中读取失败，找了好久没有发现问题，fuck。。。

2017.06.12


1. 在MusicUtil.java中修改了刚开始读取数据就会崩溃的问题，是因为在getGlobalMusicList()方法中将URI写成了MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI，而应该是MediaStore.Audio.Media.INTERNAL_CONTENT_URI。

2. 在MusicUtil.java中修改了遍历Cursor时错误的bug，在getMusicList()方法中将cursor.moveToNext()方法for循环内的最后即可。

3. 完成了基本的数据读取操作，并且在TestActivity2.java中进行了简单的验证。

2017.06.25


1. 完成了MainView.java的代码部分，还没有测试。

2017.07.08



1. 完成了MainView.java的测试，还有一部分问题。
2. 完成了外层的ViewPaper和“看”Fragment中的ViewPaper，但是ViewPaper和ScrollView的滑动事件冲突了，这个还没有解决。

2017.07.09


1. 解决了smoothScrollTo()方法无效的问题，方法是将smoothScrollTo()方法用this.post()包裹，具体什么原理现在还不清楚。

2017.07.12



1. 主要在MainActivity.java中写了左侧菜单栏中各个组件点击事件的流程，搭了个基本的架子。并在com.fzq.musicplayer.ui.leftmenu包中定义相关的Activity。

2017.08.15



1. 创建了com.fzq.musicplayer.ui.topfrags.LocalMusicActivity.java界面，使用之前的activity_local.xml并将里面的ListView改为RecyclerView。

2017.08.18


1. 主要写了在主界面中点击本地乐库后，进入本地音乐界面的四个Fragment，其中只把单曲的Fragment的数据显示出来了。

2017.08.19


1. 主要测试并实现了com.fzq.musicplayer.ui.fragments.localmusic.DanQuAdapter的Item中多个控件的点击事件。目前认为是比较好的一种实现方式。

2017.09.03



1. 新加了正在播放的界面：PlayingActivity.java。

2. 完成了播放相关的逻辑。

2017.09.16



1. 播放的时候日志打印的逻辑流程没有问题，但就是播放没有声音。原来是忘了在AndroidManifest文件中声明MusicService.java了，fuck。。。。。

2017.09.17