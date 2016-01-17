package donv7.android_easy_camera_crop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jdelorenzo.cameracrop.CropCamera;
import com.jdelorenzo.cameracrop.OnCameraCrop;


public class MainActivity extends AppCompatActivity implements OnCameraCrop {

    // upload your photo
    private static Uri mImageUri;
    private Bitmap mImageToUpload;
    private final int ACTION_CAMERA_CROP = 0;
    private boolean mImageChosen;

    private ImageView testImageView;
    private CropCamera mCropCamera;

    // region CTOR
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCropCamera = new CropCamera(this, this);

        final Button button = (Button) findViewById(R.id.testButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("MainActivity", "testButton clicked!");

                //Add camera stuff here
                mCropCamera.testCameraCrop();
            }
        });

        testImageView = (ImageView) findViewById(R.id.testImageView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCropCamera.handleActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCropFinished(Bitmap croppedImage, Boolean success) {
        if (success) {
            testImageView.setImageBitmap(croppedImage);
        }
    }
}
