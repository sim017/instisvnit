package app.com.example.simran.image;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;



import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnTouchListener {
    Matrix matrix = new Matrix();
    private static final String TAG = "Touch";
    float centreX,centreY;
    Matrix savedMatrix = new Matrix();

    // We can be in one of these 3 states
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    // Remember some things for zooming
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView image=(ImageView) findViewById(R.id.image);
        // Matrix m;
        matrix = image.getImageMatrix();
        centreX=image.getX() + image.getWidth()  / 2;
        centreY=image.getY() + image.getHeight() / 2;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        RectF drawableRect = new RectF(centreX, centreY, image.getWidth()/2, image.getHeight());
        RectF viewRect = new RectF(0, 0, width/2, height/2);
        matrix.setRectToRect(drawableRect, viewRect, Matrix.ScaleToFit.CENTER);
        //imageView.setImageMatrix(m);
        // matrix.postScale(1.5f, 1.5f, centreX, centreY);
        image.setImageMatrix(matrix);
        image.setOnTouchListener(this);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ImageView view = (ImageView) findViewById(R.id.image);
        float dx; // postTranslate X distance
        float dy; // postTranslate Y distance
        float[] matrixValues = new float[9];
        float matrixX = 0; // X coordinate of matrix inside the ImageView
        float matrixY = 0; // Y coordinate of matrix inside the ImageView
        float width = 0; // width of drawable
        float height = 0; // height of drawable
        // Dump touch event to log
        dumpEvent(event);

        // Handle touch events here...
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                Log.d(TAG, "mode=DRAG");
                mode = DRAG;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                Log.d(TAG, "oldDist=" + oldDist);
                if (oldDist > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                    Log.d(TAG, "mode=ZOOM");
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                Log.d(TAG, "mode=NONE");
                break;
           /* case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    // ...
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x,
                            event.getY() - start.y);
                }
                else if (mode == ZOOM) {
                    float newDist = spacing(event);
                    Log.d(TAG, "newDist=" + newDist);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float scale = newDist / oldDist;
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                }
                break;*/
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                   /* matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);*/
                    matrix.set(savedMatrix);

                    matrix.getValues(matrixValues);
                    matrixX = matrixValues[2];
                    matrixY = matrixValues[5];
                    width = matrixValues[0] * (((ImageView) view).getDrawable()
                            .getIntrinsicWidth());
                    height = matrixValues[4] * (((ImageView) view).getDrawable()
                            .getIntrinsicHeight());

                    dx = (event.getX() - start.x);
                    dy = (event.getY() - start.y);

                    //if image will go outside left bound
                    if (matrixX + dx < 0){
                        dx = -matrixX/2;
                    }
                    //if image will go outside right bound
                    if(matrixX + dx + width > view.getWidth()){
                        dx = (view.getWidth() - matrixX - width)*2;
                    }
                    //if image will go oustside top bound
                    if (matrixY + dy < 0){
                        dy = -matrixY;
                    }
                    //if image will go outside bottom bound
                    if(matrixY + dy + height > view.getHeight()){
                        dy = view.getHeight() - matrixY - height;
                    }
                    matrix.postTranslate(dx, dy);
                } else if (mode == ZOOM) {
                    float[] f = new float[9];
                    float newDist = spacing(event);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float tScale = newDist / oldDist;
                        matrix.postScale(tScale, tScale, mid.x, mid.y);
                    }
                    matrix.getValues(f);
                    float scaleX = f[Matrix.MSCALE_X];
                    float scaleY = f[Matrix.MSCALE_Y];
                    if (scaleX <= 0.5f) {
                        matrix.postScale((0.5f) / scaleX, (0.5f) / scaleY, mid.x, mid.y);
                    } else if (scaleX >= 6.0f) {
                        matrix.postScale((6.0f) / scaleX, (6.0f) / scaleY, mid.x, mid.y);
                    }
                }
                break;
        }

        view.setImageMatrix(matrix);
        return true; // indicate event was handled
    }

    /** Show an event in the LogCat view, for debugging */
    private void dumpEvent(MotionEvent event) {
        String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
                "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
        StringBuilder sb = new StringBuilder();
        int action = event.getAction();
        int actionCode = action & MotionEvent.ACTION_MASK;
        sb.append("event ACTION_").append(names[actionCode]);
        if (actionCode == MotionEvent.ACTION_POINTER_DOWN
                || actionCode == MotionEvent.ACTION_POINTER_UP) {
            sb.append("(pid ").append(
                    action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
            sb.append(")");
        }
        sb.append("[");
        for (int i = 0; i < event.getPointerCount(); i++) {
            sb.append("#").append(i);
            sb.append("(pid ").append(event.getPointerId(i));
            sb.append(")=").append((int) event.getX(i));
            sb.append(",").append((int) event.getY(i));
            if (i + 1 < event.getPointerCount())
                sb.append(";");
        }
        sb.append("]");
        //Log.d(TAG, sb.toString());
    }

    /** Determine the space between the first two fingers */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float)Math.sqrt(x * x + y * y);
    }

    /** Calculate the mid point of the first two fingers */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }
}


