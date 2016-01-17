package com.github.donv7.cameracrop;

import android.graphics.Bitmap;

//interface defining callbacks for library
public interface OnCameraCrop {
    void onCropFinished(Bitmap croppedImage, Boolean success);
}
