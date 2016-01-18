package com.github.donv7.cameracrop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CameraCrop {

    // region VARS
    private final int ACTION_REQUEST_GALLERY = 0;
    private final int ACTION_REQUEST_CAMERA = 1;
    private Callback mCallback;
    private Activity mActivity;
    private Uri mImageUri;
    // endregion

    // region CTOR
    public CameraCrop(Activity callingActivity) {
        mActivity = callingActivity;
    }
    // endregion

    // region CAMERA OR GALLERY AND CROP
    public void cameraOrGalleryAndCrop(Callback callback) {
        mCallback = callback;

        // create a file for the image
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        String timeStamp = dateFormat.format(new Date());
        String imageFileName = "full_" + timeStamp + ".jpg";
        File path = mActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File photo = new File(path, imageFileName);
        mImageUri = Uri.fromFile(photo);

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Choose Image Source");
        builder.setItems(new CharSequence[]{"Gallery", "Camera"},
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                // get the image from gallery
                                Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intent.setType("image/*");
                                mActivity.startActivityForResult(intent, ACTION_REQUEST_GALLERY);
                                break;

                            case 1:
                                // take a picture with the camera
                                Intent getCameraImage = new Intent("android.media.action.IMAGE_CAPTURE");
                                getCameraImage.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
                                mActivity.startActivityForResult(getCameraImage, ACTION_REQUEST_CAMERA);
                                break;
                        }
                    }
                });

        builder.show();

    }
    // endregion

    // region HANDLE ACTIVITY RESULT
    public void handleActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK &&
                (requestCode == ACTION_REQUEST_GALLERY ||
                        requestCode == ACTION_REQUEST_CAMERA ||
                        requestCode == Crop.REQUEST_CROP)) {
            mCallback.failure(new Exception("Bad request code!"));
            return;
        }

        switch(requestCode){
            case ACTION_REQUEST_GALLERY:
                Crop.of(data.getData(), mImageUri).asSquare().start(mActivity);
                break;
            case ACTION_REQUEST_CAMERA:
                Crop.of(mImageUri, mImageUri).asSquare().start(mActivity);
                break;
            case Crop.REQUEST_CROP:
                try {
                    mCallback.success(MediaStore.Images.Media.getBitmap(
                            mActivity.getContentResolver(), mImageUri));
                } catch(Exception e) {
                    mCallback.failure(e);
                }
                break;
        }
    }
    // endregion

}
