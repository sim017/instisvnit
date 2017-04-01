package app.com.example.simran.zoomimage;

import android.app.Activity;
import android.os.Bundle;


public class ZoomImage extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ZoomImageView imageView = new ZoomImageView(this, getWindow()
                .getWindowManager().getDefaultDisplay().getOrientation());
        imageView.setImage(this.getResources().getDrawable(R.drawable.temp),
                this);
        ZoomImageView v=new ZoomImageView(this,getWindow().getWindowManager().getDefaultDisplay().getOrientation());
        v.setImage(this.getResources().getDrawable(R.drawable.marker),this);

        this.setContentView(imageView);
    }
}
/*public class ZoomImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);
    }
}
*/