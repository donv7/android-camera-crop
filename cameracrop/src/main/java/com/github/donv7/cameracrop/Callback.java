package com.github.donv7.cameracrop;

import android.graphics.Bitmap;

public interface Callback {
    void success(Bitmap croppedImage);
    void failure(Throwable throwable);
}
