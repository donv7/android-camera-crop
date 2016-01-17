package com.jdelorenzo.cameracrop;

import android.graphics.Bitmap;

//interface defining callbacks for library
public interface OnCameraCrop {
    void onCropFinished(Bitmap croppedImage, Boolean success);
}
