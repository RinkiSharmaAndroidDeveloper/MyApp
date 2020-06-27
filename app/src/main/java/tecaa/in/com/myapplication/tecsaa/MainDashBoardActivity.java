package tecaa.in.com.myapplication.tecsaa;


import android.content.Intent;

import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import tecaa.in.com.myapplication.tecsaa.ui.classWork.ClassWorkFragment;
import tecaa.in.com.myapplication.tecsaa.ui.gallary.GallaryFragment;
import tecaa.in.com.myapplication.tecsaa.ui.home.HomeFragment;
import tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher.ActivityTeacherFragment;
import tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher.RequirtementFragment;
import tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher.TeacherAttendanceFragment;
import tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher.TeacherClassWork;
import tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher.TeacherHomeWorkFragment;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;


import android.view.Menu;

import android.widget.LinearLayout;


public class MainDashBoardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    HomeFragment homeFragment;
    GallaryFragment gallaryFragment;
    ClassWorkFragment classWorkFragment;
    TeacherClassWork teacherClassWork;
    TeacherAttendanceFragment teacherAttendanceFragment ;
    TeacherHomeWorkFragment teacherHomeWorkFragment;
    CircleImageView circleImageView;
    View navigation;
    LinearLayout linearLayout;
    private DrawerLayout drawer;
    ActivityTeacherFragment activityTeacherFragment;
    NavigationView navigationView;
    RequirtementFragment requirtementFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dash_board);
       /* Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
         drawer = findViewById(R.id.drawer_layout);
         navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
     /*   mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_gallery, R.id.nav_home, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_exam, R.id.home_work)
                .setDrawerLayout(drawer)
                .build();*/

        navigation =navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(MainDashBoardActivity.this);
 /*       NavController navController = Navigation.findNavController(this, R.id.frameContainer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);*/

        homeFragment =new HomeFragment();
        homeFragment.newInstance(MainDashBoardActivity.this, asyncResult);

        teacherHomeWorkFragment =new TeacherHomeWorkFragment();
        teacherHomeWorkFragment.newInstance(MainDashBoardActivity.this);

        activityTeacherFragment =new ActivityTeacherFragment();
        activityTeacherFragment.newInstance(MainDashBoardActivity.this);

        requirtementFragment =new RequirtementFragment();
        requirtementFragment.newInstance(MainDashBoardActivity.this);

        classWorkFragment =new ClassWorkFragment();
        classWorkFragment.newInstance(MainDashBoardActivity.this);

        teacherClassWork =new TeacherClassWork();
        teacherClassWork.newInstance(MainDashBoardActivity.this);

        teacherAttendanceFragment =new TeacherAttendanceFragment();
        teacherAttendanceFragment.newInstance(MainDashBoardActivity.this);



        circleImageView =(CircleImageView)navigation.findViewById(R.id.imageView);
        linearLayout =(LinearLayout)navigation.findViewById(R.id.layout);
        replaceFragment(homeFragment);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainDashBoardActivity.this,EditProfileActivity.class);
                startActivity(intent);
            }
        });


        AHBottomNavigation bottomNavigation = (AHBottomNavigation)findViewById(R.id.bottom_navigation);

// Create items
       AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.menu_home, R.drawable.home_white, R.color.transparent);
         AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.menu_requirment, R.drawable.requirment_white, R.color.transparent);
       AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.menu_home_work, R.drawable.home_work_white, R.color.transparent);
       AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.menu_class_schedule, R.drawable.class_schedule_white, R.color.transparent);
       AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.menu_activity, R.drawable.activity_white, R.color.transparent);

// Add itemsf
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);


// Set background color
        bottomNavigation.setDefaultBackgroundColor(R.drawable.gradiuent_background);

// Disable the translation inside the CoordinatorLayout
      //  bottomNavigation.setBehaviorTranslationEnabled(false);
     //   bottomNavigation.setNotificationTextColor(Color.parseColor("#FFFFFF"));
// Enable the translation of the FloatingActionButton
   //     bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);

// Change colors


// Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        bottomNavigation.setColored(true);


        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(final int position, boolean wasSelected) {
                // Do something cool here...
            //    if(position)
                setFragment( position);
               // Toast.makeText(MainDashBoardActivity.this,"pos clicked"+position,Toast.LENGTH_LONG).show();
                return true;
            }
        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override public void onPositionChange(int y) {
                // Manage the new y position
            }
        });


}

     AsyncResult<Fragment> asyncResult = new AsyncResult<Fragment>() {
         @Override
         public void success(Fragment fragment) {
             replaceFragment(fragment);
         }

         @Override
         public void error(String error) {

         }
     };

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

    }
  public void setFragment(final int position){
        switch (position){
            case 0:
                replaceFragment(homeFragment);
                break;
                case 1:
                replaceFragment(requirtementFragment);
                break;
                case 2:
                replaceFragment(teacherHomeWorkFragment);
                break;
                case 3:
                replaceFragment(classWorkFragment);
                break;

                case 4:
                replaceFragment(activityTeacherFragment);
                break;
        }

  }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_dash_board, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.frameContainer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawer.closeDrawer(GravityCompat.START);

        switch (menuItem.getItemId()) {
            case R.id.imageView: {
                Intent intent=new Intent(MainDashBoardActivity.this,EditProfileActivity.class);
                startActivity(intent);
                break;
            }

        }
        return false;
    }
}
