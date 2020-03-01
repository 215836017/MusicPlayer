package com.cakes.musicplayer.utils;

import java.io.File;

public class FileUtil {

    /**
     * 删除指定文件
     *
     * @param path 文件的绝对路径
     * @return true:删除成功; false:路径对应的文件不存在或删除失败
     */
    public boolean deleteMusicFile(String path) {
        File file2Del = new File(path);
        if (file2Del.exists()) {
            return file2Del.delete();
        }
        return false;
    }
}
