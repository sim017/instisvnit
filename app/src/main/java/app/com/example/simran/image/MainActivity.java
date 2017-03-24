package app.com.example.simran.image;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ZoomImageView imageView = new ZoomImageView(this, getWindow()
                .getWindowManager().getDefaultDisplay().getOrientation());
        imageView.setImage(this.getResources().getDrawable(R.drawable.temp),
                this);

        this.setContentView(imageView);
    }
}
