package app.com.example.simran.zoomimage;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class ZoomImage extends AppCompatActivity {
    private RelativeLayout mRelativeLayout;
    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mDrawerTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle = "hello";
    private ArrayAdapter<String> mAdapter;
    private NavigationView navigationView;
    private View navHeader;
    private Toolbar toolbar;
    private int count=0;
    public static int navItemIndex = 0;

    private Handler mHandler;
    private FloatingActionButton fab;
    private ZoomImageView imageView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Anas");
        setSupportActionBar(toolbar);


        //((AppCompatActivity)this).getSupportActionBar().setTitle("Home");
       // getSupportActionBar().setTitle("title");

       // ((AppCompatActivity)getActivity()).setSupportActionBar();
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true)
       // getSupportActionBar().setHomeButtonEnabled(true);
       // setSupportActionBar(toolbar);
       //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_list);


        mHandler = new Handler();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navHeader = navigationView.getHeaderView(0);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        mPlanetTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        mRelativeLayout  = (RelativeLayout) findViewById(R.id.relative_layout);

         imageView = new ZoomImageView(this, getWindow()
                .getWindowManager().getDefaultDisplay().getOrientation(),count);

        imageView.setImage(this.getResources().getDrawable(R.drawable.temp),
               this);


        // ZoomImageView v=new ZoomImageView(this,getWindow().getWindowManager().getDefaultDisplay().getOrientation());
        // v.setImage(this.getResources().getDrawable(R.drawable.marker),this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // load nav menu header data
        //loadNavHeader();

        // initializing navigation menu
     //  selectItem(0);
        setUpNavigationView();
        mRelativeLayout.addView(imageView);
    }



        private void setToolbarTitle()
        {
            getSupportActionBar().setTitle(mPlanetTitles[navItemIndex]);
        }

        private void selectNavMenu() {
            navigationView.getMenu().getItem(navItemIndex).setChecked(true);
        }
    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Toast.makeText(getBaseContext(), "clicking navigation view", Toast.LENGTH_SHORT).show();
                //Check to see which item was being clicked and perform appropriate action
                Toast.makeText(getBaseContext(), "menuItme"+menuItem.getItemId(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getBaseContext(), R.id.nav_hostel+"Hello", Toast.LENGTH_SHORT).show();
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_department:
                        navItemIndex = 0;
                        //Toast.makeText(getBaseContext(), "index"+navItemIndex, Toast.LENGTH_SHORT).show();

                        //CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_hostel:
                        navItemIndex = 1;
                        //Toast.makeText(getBaseContext(), "index"+navItemIndex, Toast.LENGTH_SHORT).show();
                        selectItem(navItemIndex);
                        //CURRENT_TAG = TAG_PHOTOS;
                        break;
                    case R.id.nav_library:
                        navItemIndex = 2;
                        //Toast.makeText(getBaseContext(), "index"+navItemIndex, Toast.LENGTH_SHORT).show();
                        selectItem(navItemIndex);
                       // CURRENT_TAG = TAG_MOVIES;
                        break;
                    case R.id.nav_canteen:
                        navItemIndex = 3;
                        //CURRENT_TAG = TAG_NOTIFICATIONS;
                        break;
                    case R.id.nav_banks:
                        navItemIndex = 4;
                        //CURRENT_TAG = TAG_SETTINGS;
                        break;
                    case R.id.nav_sports:
                        navItemIndex = 4;
                        //CURRENT_TAG = TAG_SETTINGS;
                        break;
                    case R.id.nav_about_us:
                        // launch new intent instead of loading fragment
                        //startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                        //drawer.closeDrawers();
                        return true;
                    case R.id.nav_privacy_policy:
                        // launch new intent instead of loading fragment
                        //startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
                        //drawer.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                //loadHomeFragment();

                return true;
            }
        });
        mDrawerToggle = new ActionBarDrawerToggle(this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        );
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar, R.string.openDrawer, R.string.closeDrawer) {
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank

                super.onDrawerClosed(drawerView);
            }


            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar, R.string.openDrawer, R.string.closeDrawer) {
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank

                super.onDrawerClosed(drawerView);
            }


            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
                navigationView.bringToFront();
                mDrawerLayout.requestLayout();
            }
        };
        //Setting the actionbarToggle to drawer layout
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            Toast.makeText(getBaseContext(), "clicking textview", Toast.LENGTH_SHORT).show();
            return;

        }
        super.onBackPressed();
    }

   /* mTitle = mDrawerTitle = getTitle();
        //mPlanetTitles = getResources().getStringArray(R.array.planet_titles);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //mDrawerLayout.setScrimColor(Color.parseColor("#00FFFFF"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this,                  /* host Activity */
              //  mDrawerLayout,         /* DrawerLayout object */
               // R.string.drawer_open,  /* "open drawer" description */
                //R.string.drawer_close  /* "close drawer" description */
        //)
       // {

            /** Called when a drawer has settled in a completely closed state. */
            /*public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);invalidateOptionsMenu();
            }*/

            //    mDrawerList = (ListView)findViewById(R.id.left_drawer);

            /** Called when a drawer has settled in a completely open state. */
           // public void onDrawerOpened(View drawerView) {
             //   super.onDrawerOpened(drawerView);
               // Log.d("drawer", "onDrawerOpened: ");
                /*drawerView.bringToFront();
                getWindow().getDecorView().requestLayout();
                getWindow().getDecorView().invalidate();
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
       // mDrawerList.setAdapter(new ArrayAdapter<String>(this,
         //       R.layout.drawer_list_item, mPlanetTitles));

        // Set the drawer toggle as the DrawerListener
        // selectItem(0);
        //mDrawerLayout.setDrawerListener(mDrawerToggle);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //this.setContentView(imageView);*/





   @Override
    protected void onPostCreate(Bundle savedInstanceState) {
       super.onPostCreate(savedInstanceState);
       // Sync the toggle state after onRestoreInstanceState has occurred.
       mDrawerToggle.syncState();
   }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            Toast.makeText(getBaseContext(), "option selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


    private void selectItem(int position) {
       // mDrawerList.setItemChecked(position, true);
        //Toast.makeText(getBaseContext(), "psoition"+position, Toast.LENGTH_SHORT).show();
        setTitle(mPlanetTitles[position]);
        TextView tvUserName = (TextView) findViewById(R.id.content);

        tvUserName.setShadowLayer(15, 0, 0, Color.RED);
        switch (position) {
            case 1:

                tvUserName.setText("No event going on for now!!! "
                        );
                // mRelativeLayout.addView(R.id.relative_layout);
                tvUserName.setVisibility(tvUserName.VISIBLE);
                tvUserName.bringToFront();
                count=count+1;
                //imageView.callThis(this,2);
                count=0;
                //Toast.makeText(getBaseContext(), "inside hostel", Toast.LENGTH_SHORT).show();
                break;

            case 2:
                //TextView tvUserName = (TextView) findViewById(R.id.nav_hostel);

                tvUserName.setText("Library will be open on saturday only till 5");
                // mRelativeLayout.addView(R.id.relative_layout);
                tvUserName.setVisibility(tvUserName.VISIBLE);
                tvUserName.bringToFront();
                //imageView.callThis(this,2);
                // tvUserName.setText("My Name");
                //Toast.makeText(getBaseContext(), "inside library", Toast.LENGTH_SHORT).show();
                break;
        }

            mDrawerLayout.closeDrawers();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            //Toast.makeText(getBaseContext(), "menu1", Toast.LENGTH_SHORT).show();
        }

        // when fragment is notifications, load the menu created for notifications

        return true;
    }
    private void toggleFab() {
        if (navItemIndex == 0)
            fab.show();
        else
            fab.hide();
    }
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }


    // @Override
    // public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    //   int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    // if (id == R.id.action_settings) {
    //   return true;
    //}

//        return super.onOptionsItemSelected(item);
    //  }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
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