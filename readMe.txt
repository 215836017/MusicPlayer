1. 在线程中使用Cursor cursor = context.getContentResolver().query(musicUri, null, null, null, sortOrder);的方式查找了手机sdcard中的全部音乐文件。并使用RecyclerView进行列表显示

2. 在RecyclerView点击item后，在service中使用mediaplayer进行播放 ---- 只是简单地实现了播放，其他是流程和逻辑并未实现。

2019.04.23