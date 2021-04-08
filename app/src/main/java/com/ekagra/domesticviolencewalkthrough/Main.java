package com.ekagra.domesticviolencewalkthrough;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;


/**
 * Created by Dell on 11/4/2018.
 */

public class Main extends AppCompatActivity {

    private static final String TAG = "Mainviewgro";

    //SLIDER
    LottieAnimationView ani;
    private TextView[] dots;
    private int[] layouts;
    private ViewPager viewPager;
    private ViewGroupPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private Button bt;

    private TextView tx;

    //CALL
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE};

    //bools
    private boolean one = true, two = true, three =true, four =true;

    //Activity
    private Activity activity = Main.this;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(TAG, "oncreate; starting");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        mContext = Main.this;
        bt = (Button) (findViewById(R.id.btnext));
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        ani = (LottieAnimationView) findViewById(R.id.animation);

        layouts = new int[]{
                R.layout.slide1,
                R.layout.slide2,
                R.layout.slide3,
                R.layout.slide4,
                R.layout.slide5,
                R.layout.slide6,
                R.layout.slide7
        };

        myViewPagerAdapter = new ViewGroupPagerAdapter(layouts, mContext);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        // adding bottom dots
        addBottomDots(0);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:1091"));
                startActivity(callIntent);

            }
        });


    }


    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }



    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == 0 && one) {
                // last page. make button visible
                one = false; two = true; three = false; four = true;
                ani.setAnimation("start.json");
                ani.playAnimation();
                ani.loop(true);

            } if (position == 1 || position == 2 && two) {
                // last page. make button visible
                two = false; one = true; three = true; four = true;
                ani.setAnimation("girls.json");
                ani.playAnimation();
                ani.loop(true);
            }

            if (position >=3 && position <=5 && three) {
                // last page. make button visible
                three = false; one = true; two = true; four = true;
                ani.setAnimation("hand.json");
                ani.playAnimation();
                ani.loop(true);
            }
            if (position == 6) {
                // last page. make button visible
                three = true; one = true; two = true; four = false;
                ani.setAnimation("help.json");
                ani.playAnimation();
                ani.loop(true);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };


    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void onBackPressed (){
        super.onBackPressed();
        finish();
    }



}
