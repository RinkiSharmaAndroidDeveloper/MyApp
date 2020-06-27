package tecaa.in.com.myapplication.tecsaa.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import tecaa.in.com.myapplication.tecsaa.Admin.AdminMainDashActivity;
import tecaa.in.com.myapplication.tecsaa.MainDashBoardActivity;
import tecaa.in.com.myapplication.tecsaa.R;
import tecaa.in.com.myapplication.tecsaa.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
  EditText UserName, password;
  TextView forgot_password;
  Button login_button;
  String Email=null,passcode=null;
  ProgressBar progressBar;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManager= new SessionManager(this);
        UserName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        forgot_password = findViewById(R.id.forgot_passcode);
        login_button = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progress);


        login_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.login_button):
                Email =UserName.getText().toString();
                passcode =password.getText().toString();
                if(Email!=null && passcode!=null){
                    progressBar.setVisibility(View.VISIBLE);
                    Login();
                }else{
                    UserName.setError("Please enter email.");
                    password.setError("Please enter password.");
                }
                break;
        }
    }
    public void Login() {
        RequestQueue queue = null;
        queue = Volley.newRequestQueue(this);

        final String URL = "http://13.233.167.170/api/users/signin/";
// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", Email);
        params.put("password", passcode);


        JsonObjectRequest request_json = new JsonObjectRequest(URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        progressBar.setVisibility(View.GONE);
                        Log.e("Response", response.toString());
                        String responsemessage = null;
                        try {


                            JSONObject userData = response.getJSONObject("data");
                            String resposne_sucess = response.getString("isSuccess");
                            if(resposne_sucess.equals("true"))
                            {
                                String token = userData.getString("token");
                                String schoolId = userData.getString("schoolId");
                                String type = userData.getString("type");

                                sessionManager.createLoginSession("token",token);
                                sessionManager.createLoginSession("schoolId",schoolId);
                                sessionManager.createLoginSession("type",type);
                                if(type.equals("admin")){
                                    Intent intent = new Intent(LoginActivity.this, AdminMainDashActivity.class);
                                    startActivity(intent);
                                }else {
                                    Intent intent = new Intent(LoginActivity.this, MainDashBoardActivity.class);
                                    startActivity(intent);
                                }


                            }else{

                                Toast.makeText(LoginActivity.this,"UserId and password are invalid",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this,"UserId and password are invalid",Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request_json);
    }

}
