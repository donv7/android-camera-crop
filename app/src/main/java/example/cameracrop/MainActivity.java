package example.cameracrop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.github.donv7.cameracrop.CropCamera;
import com.github.donv7.cameracrop.Callback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private CropCamera mCameraCrop;
    @InjectView(R.id.testImageView) ImageView testImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mCameraCrop = new CropCamera(this);
    }

    @OnClick({R.id.testButton})
    public void testButtonOnClick(View v) {
        mCameraCrop.cameraOrGalleryAndCrop(new Callback() {
            @Override
            public void success(Bitmap croppedImage) {
                testImageView.setImageBitmap(croppedImage);
            }

            @Override
            public void failure(Throwable throwable) {
                Log.e(LOG_TAG, "CameraCrop failed!", throwable);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCameraCrop.handleActivityResult(requestCode, resultCode, data);
    }
}
