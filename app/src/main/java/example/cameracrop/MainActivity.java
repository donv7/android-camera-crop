package example.cameracrop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.github.donv7.cameracrop.CropCamera;
import com.github.donv7.cameracrop.OnCameraCrop;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    private CropCamera mCropCamera;
    @InjectView(R.id.testImageView) ImageView testImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mCropCamera = new CropCamera(this);
    }

    @OnClick({R.id.testButton})
    public void testButtonOnClick(View v) {
        mCropCamera.testCameraCrop(new OnCameraCrop() {
            @Override
            public void onCropFinished(Bitmap croppedImage, Boolean success) {
                if (success) {
                    testImageView.setImageBitmap(croppedImage);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCropCamera.handleActivityResult(requestCode, resultCode, data);
    }
}
