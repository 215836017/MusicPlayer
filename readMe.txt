1. 在线程中使用Cursor cursor = context.getContentResolver().query(musicUri, null, null, null, sortOrder);的方式查找了手机sdcard中的全部音乐文件。并使用RecyclerView进行列表显示

2. 在RecyclerView点击item后，在service中使用mediaplayer进行播放 ---- 只是简单地实现了播放，其他是流程和逻辑并未实现。

2019.04.23

1. 没有更新代码，查询并添加了几个相关的资料连接。

2019.04.30


需要完成的有： 
1. “跳过”动画

2. 抽屉效果。

3. 主要添加了主界面相关的一些界面代码
2019.05.22

1. 主要完成了主界面中左侧菜单选项部分的代码。

2019.05.26

1. 主要修改了NewVersionStartActivity.java的代码

2. 完善了消息中心(NewsCentreActivity.java)中的部分代码，并且添加了NewsCentreActivity.java的相关Fragments

2019.05.27

1. 主要完成了程序启动的登录界面到注解的流程

2019.06.07

1. 主要修改了LoginActivity.java的代码。

2019.06.08

1. 添加了 “服务协议”的界面

2019.06.09

1. 修改了部分UI

2. 修改了AppMainActivity,java的代码。

2019.06.12


目标：
1. 在Android5上实现播放本地，包括：顺序播放、单曲循环、列表循环。
2. 在服务播放
3. 在Notifaction中显示控制布局