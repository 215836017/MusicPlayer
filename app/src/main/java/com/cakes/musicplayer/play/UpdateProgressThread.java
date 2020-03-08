package com.cakes.musicplayer.play;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

public class UpdateProgressThread {

    private HandlerThread handlerThread;
    private Handler workerHandler;
    private OnProgressListener progressListener;
    private boolean isPause;

    private final int MSG_UPDATE = 0x10;
    private final int UPDATE_INTERVAL = 1000;

    public UpdateProgressThread(OnProgressListener progressListener) {

        this.progressListener = progressListener;
        handlerThread = new HandlerThread("updateProgress");
        handlerThread.start();
        workerHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMsg(msg);
            }
        };
    }

    private void handleMsg(Message msg) {

        if (msg.what == MSG_UPDATE) {
            if (!isPause) {
                progressListener.onUpdateProgress();
            }
            workerHandler.sendEmptyMessageDelayed(MSG_UPDATE, UPDATE_INTERVAL);
        }
    }

    public void startUpdateProgress() {
        isPause = false;
        workerHandler.sendEmptyMessage(MSG_UPDATE);
    }

    public void pauseUpdateProgress() {
        isPause = true;
    }

    public void release() {
        workerHandler.removeCallbacksAndMessages(null);
        handlerThread.quitSafely();
        workerHandler = null;
        handlerThread = null;
    }

    public interface OnProgressListener {
        void onUpdateProgress();
    }
}
