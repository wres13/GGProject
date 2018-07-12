package com.dongdian.jj.gorgeous;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dongdian.jj.gorgeous.category.CategoryFragment;
import com.dongdian.jj.gorgeous.home.HomeFragment;
import com.dongdian.jj.gorgeous.utils.ImageUtil;
import com.tools.jj.tools.activity.BaseActivity;
import com.tools.jj.tools.basemvp.p.BasePresenter;
import com.tools.jj.tools.utils.WindowsUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnTouch;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnTouchListener {

    @Bind(R.id.fl_main)
    FrameLayout flMain;
    @Bind(R.id.ib_home)
    RadioButton ibHome;
    @Bind(R.id.ib_category)
    RadioButton ibCategory;
    @Bind(R.id.ig_main)
    RadioGroup igMain;
    //    @Bind(R.id.iv_main)
//    ImageView cvMain;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.main_nestedscrollview)
    NestedScrollView myNestedScrollView;
    @Bind(R.id.main_appbar_layout)
    AppBarLayout MyAppBarLayout;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setWindowAnimation();
        //底部按钮大小优化
        ImageUtil.mainBtnDispose(this, igMain);
        initDrawer();
        initData();
        //解决viewpager不能滑动的问题
        myNestedScrollView.setFillViewport(true);
    }

    private void initDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        drawer.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
    }



    @Override
    protected void initToolBar() {

        toolbar.setTitle("");
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "sadasdsa", Toast.LENGTH_SHORT).show();
            }
        });
        setSupportActionBar(toolbar);
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setWindowAnimation() {
        Explode explode = new Explode();
        explode.setDuration(getResources().getInteger(R.integer.splash_fade_animation_time));
        getWindow().setEnterTransition(explode);
    }

    public void initData() {
        repleasFragment(HomeFragment.class.getName());
    }

    @Override
    protected void registerListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//        MyAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                  if (verticalOffset<=10){
//                      drawer.setFitsSystemWindows(true);
//                  }else {
//                      drawer.setFitsSystemWindows(false);
//                  }
//            }
//        });
    }

    @Override
    protected void releaseMemory() {

    }

    @OnClick({R.id.ib_home, R.id.ib_category})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_home:
                repleasFragment(HomeFragment.class.getName());
                break;
            case R.id.ib_category:
                repleasFragment(CategoryFragment.class.getName());
                break;
//            case R.id.iv_main:
//                break;
            case R.id.fab:
                Toast.makeText(this, "sdsadsadsa", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
        }
    }

    //切换framgent
    private void repleasFragment(String fragmentClassName) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment tempFramgnet = getSupportFragmentManager().findFragmentByTag(fragmentClassName);
        if (null == tempFramgnet) {
            try {
                tempFramgnet = (Fragment) Class.forName(fragmentClassName).newInstance();
                transaction.add(R.id.fl_main, tempFramgnet, fragmentClassName);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (int i = 0; i < fragments.size(); i++) {
                Fragment fragment = fragments.get(i);
                if (fragment.getTag().equals(fragmentClassName)) {
                    transaction.show(fragment);
                } else
                    transaction.hide(fragment);
            }
        }
        transaction.commit();
    }

    @Override
    public void showRequestFail() {

    }

    @Override
    public void showRequestSuccess() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        return false;
    }
}
