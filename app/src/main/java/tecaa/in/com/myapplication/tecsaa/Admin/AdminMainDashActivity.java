package tecaa.in.com.myapplication.tecsaa.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tecaa.in.com.myapplication.tecsaa.R;
import tecaa.in.com.myapplication.tecsaa.ui.gallary.GallaryFragment;

public class AdminMainDashActivity extends AppCompatActivity {
Button teacherRegistration,studentRegistration,gallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_dash);
        teacherRegistration= findViewById(R.id.teacher);
        studentRegistration= findViewById(R.id.student);
        gallery= findViewById(R.id.gallery);
        teacherRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdminMainDashActivity.this, TeacherRegistationActivity.class);
                startActivity(intent);
            }
        });

        studentRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdminMainDashActivity.this, StudentRegistrationActivity.class);
                startActivity(intent);
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdminMainDashActivity.this, GallaryFragment.class);
                startActivity(intent);
            }
        });
    }
}
