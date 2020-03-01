目标：
一、Android5及以下机型中：
1. 查找本地所有歌曲文件，并以列表显示。
2. 播放本地歌曲，包括：顺序播放、单曲循环、列表循环。（在Service中播放）。
3. 添加播放界面，用来显示歌词等正在播放的相关内容。
4. 在Notifaction中显示控制布局。

日志：
1. 在线程中使用Cursor cursor = context.getContentResolver().query(musicUri, null, null, null, sortOrder);
的方式查找了手机sdcard中的全部音乐文件。并使用RecyclerView进行列表显示。
2002.27


