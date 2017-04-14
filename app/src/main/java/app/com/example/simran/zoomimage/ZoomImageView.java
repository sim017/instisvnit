package app.com.example.simran.zoomimage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Custom class to zoom image in android
 *

 */
public class ZoomImageView extends View implements GestureDetector.OnGestureListener {

    private static final int SCALING_FACTOR = 150;
    private final int LANDSCAPE = 1;
    private GestureDetector gestureDetector;
    private Drawable image = null;
    private Drawable image1=null;
    private int scalefactor = 0;
    private int orientation;
    private int zoomCtr = 0;
    private long lastTouchTime = 0;
    int anas=0;
    Context hi;
    int sx=30,sy=40;
    private int winX, winY, imageX, imageY, scrollX = 0, scrollY = 0, left,
            top, bottom, right;
    Canvas canvas1;

    public ZoomImageView(Context context, int orientation,int no) {
        super(context);
        setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT,
                FrameLayout.LayoutParams.FILL_PARENT));
        this.orientation = orientation;
        //Toast.makeText(context,no+"Yo" +anas, Toast.LENGTH_SHORT).show();
        gestureDetector = new GestureDetector(this);
    }


  public void callThis(Context context,int hi)
  {   anas=hi;
      int cnt=0;

      //Toast.makeText(,hi+"Yo" , Toast.LENGTH_SHORT).show();
     // Toast.makeText(context,"Yo" +anas, Toast.LENGTH_SHORT).show();

  }


    public void setImage(Drawable bitmap, Activity activity) {
        image = bitmap;
        imageSetting(activity);
    }

    public void setImage(Bitmap bitmap, Activity activity) {
        image = new BitmapDrawable(bitmap).getCurrent();

        imageSetting(activity);
    }

    /**
     * Works in both landscape and potrait mode.
     */
    private void imageSetting(Activity activity) {
        scrollX = scrollY = 0;
        scalefactor = 0;
        imageX = winX = activity.getWindow().getWindowManager()
                .getDefaultDisplay().getWidth();
        imageY = winY = activity.getWindow().getWindowManager()
                .getDefaultDisplay().getHeight();
        if (orientation == LANDSCAPE) {
            imageX = 3 * imageY / 4;
        }
        calculatePos();
    }

    public void calculatePos() {
        int tempx, tempy;
        tempx = imageX + imageX * scalefactor / 100;
        tempy = imageY + imageY * scalefactor / 100;
        left = (winX - tempx) / 2;
        top = (winY - tempy) / 2;
        right = (winX + tempx) / 2;
        bottom = (winY + tempy) / 2;
        invalidate();
    }


    /**
     * Redraws the bitmap when zoomed or scrolled.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (image == null)
            return;
        int count=0;
 {
    image.setBounds(left + scrollX, top + scrollY, right + scrollX, bottom
            + scrollY);
      /*{
         image.draw(canvas);
         image.setBounds(left + scrollX + 1, top + scrollY, right + scrollX, bottom
                 + scrollY);
         image.draw(canvas);
         image.setBounds(left + scrollX + 1, top + scrollY, right + scrollX, bottom
                 + scrollY);
         image.draw(canvas);
         image.setBounds(left + scrollX + 1, top + scrollY, right + scrollX, bottom
                 + scrollY);
         image.draw(canvas);
         image.setBounds(left + scrollX + 1, top + scrollY, right + scrollX, bottom
                 + scrollY);
         image.draw(canvas);
         image.setBounds(left + scrollX + 1
                 , top + scrollY, right + scrollX, bottom
                 + scrollY);
     }*/
}
        image.draw(canvas);




        //canvas.drawCircle(60, 60, 5, mPaint);


        count=count + 1;


        count=count+1;

        //canvas.restore();
        count=count+1;

        super.onDraw(canvas);
        count++;


    }

    public void zoomIn() {
        scalefactor += SCALING_FACTOR;
        calculatePos();
    }

    public void zoomOut() {
        if (scalefactor == 0)
            return;
        scrollX = scrollY = 0;
        scalefactor -= SCALING_FACTOR;
        calculatePos();
    }

    public void scroll(int x, int y) {
        scrollX += x / 5;
        scrollY += y / 5;
        if (scrollX + left > 0) {
            scrollX = 0 - left;
        } else if (scrollX + right < winX) {
            scrollX = winX - right;
        }
        if (scrollY + top > 0) {
            scrollY = 0 - top;
        } else if (scrollY + bottom < winY) {
            scrollY = winY - bottom;
        }
      //  anas=5;
       // Toast.makeText(hi,anas+"Yo" +anas+"Yo" +anas, Toast.LENGTH_SHORT).show();
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent me) {
        boolean onTouchEvent = gestureDetector.onTouchEvent(me);
        return onTouchEvent;
    }

    @Override
    public boolean onDown(MotionEvent arg0) {
        long thisTime = arg0.getEventTime();
        if (thisTime - lastTouchTime < 250) {
            lastTouchTime = -1;
            onDoubleTap();
            return true;
        }
        lastTouchTime = thisTime;
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        if (zoomCtr == 0)
            return false;
        scroll((int) (e2.getX() - e1.getX()), (int) (e2.getY() - e1.getY()));
        //scroll(100,150);

        return true;
    }

    private void onDoubleTap() {
        if (zoomCtr == 0) {
            zoomCtr++;
            zoomIn();
            return;
        }
        zoomCtr--;


        zoomOut();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }



    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // TODO Auto-generated method stub
        //scroll();
      //  Bitmap imageView = BitmapFactory.decodeResource(getResources(),
        //        R.drawable.marker);
        //int[] location = new int[2];
       // imageView.getLocationOnScreen(location);

        //int x = location[0];
        //int y = location[1];
        return true;
    }
}
