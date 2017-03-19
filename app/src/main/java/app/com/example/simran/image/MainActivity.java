package app.com.example.simran.image;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                //Simply displays a toast
                Toast.makeText(getApplicationContext(), "Touch coordinates : " +
                        String.valueOf(ev.getX()) + "x" + String.valueOf(ev.getY()),Toast.LENGTH_SHORT).show();

                return true;
            }

        });

    }

}