package tecaa.in.com.myapplication.tecsaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import tecaa.in.com.myapplication.tecsaa.Admin.AdminMainDashActivity;
import tecaa.in.com.myapplication.tecsaa.Login.LoginActivity;

import java.util.HashMap;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    SessionManager sessionManager;
    HashMap<String, String> user;
    String token=null,type=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sessionManager = new SessionManager(this);
        user = new HashMap<String, String>();
        user= sessionManager.getUserDetails();
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                token = user.get("token");
                type = user.get("type");

                if(token!=null) {
                    if(type.equals("admin")){
                        Intent i = new Intent(SplashActivity.this, AdminMainDashActivity.class);
                        startActivity(i);
                    }else{
                        Intent i = new Intent(SplashActivity.this, MainDashBoardActivity.class);
                        startActivity(i);
                    }

                }else
                {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                finish();
            }

        },3000);
    }
}
