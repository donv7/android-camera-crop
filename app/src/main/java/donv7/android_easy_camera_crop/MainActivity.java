package donv7.android_easy_camera_crop;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jdelorenzo.cameracrop.CropCameraActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    // upload your photo
    private static Uri mImageUri;
    private Bitmap mImageToUpload;
    private final int ACTION_REQUEST_GALLERY = 0;
    private final int ACTION_REQUEST_CAMERA = 1;
    private final int CROP_INTENT_RESULT_CODE = 2;
    private boolean mImageChosen;

    private ImageView testImageView;

    // region CTOR
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.testButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("MainActivity", "testButton clicked!");

                //Add camera stuff here
//                CropCameraActivity cropCameraActivity = new CropCameraActivity();
//                cropCameraActivity.testCameraCrop();
                //testCameraCrop();
            }
        });

        testImageView = (ImageView) findViewById(R.id.testImageView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
