package tecaa.in.com.myapplication.tecsaa.Admin;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import tecaa.in.com.myapplication.tecsaa.R;
import tecaa.in.com.myapplication.tecsaa.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TeacherRegistationActivity extends AppCompatActivity {
EditText teachername,mobile,alternateMobile,emailId,address,Password,userId,lastName;
    CircleImageView userImageUrl;
    ImageView editImage;
    TextView studentDate,teacher_doj;
    String schoolId,classId,sectionId,passcode,teacherName,teacherJoin,fatherNameSt,motherNameSt,DOB,Gender,MobileNum,AltMobNum,EmailId,TransportType,UserID,UserImage,userAddress,lastname;
    Button Confirm,Discard;
    String transportType;
    LinearLayout dateLayout,joiningDate;

    RadioGroup radioSexGroup,transportRadioGrp;
    ProgressBar progressBar;
    SessionManager sessionManager;
    HashMap<String, String> user;
    String token=null;
    Calendar dateSelected;
    private DatePickerDialog datePickerDialog;
    private int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_registation);

        sessionManager = new SessionManager(this);
        user = new HashMap<String, String>();
        user= sessionManager.getUserDetails();

        teachername = findViewById(R.id.edit_teacher_name);
        lastName = findViewById(R.id.edit_teacher_name_last);

        dateLayout = findViewById(R.id.date_layout);
        mobile = findViewById(R.id.mobile_number);
        alternateMobile = findViewById(R.id.alternate_mobile_number);
        Password = findViewById(R.id.password);
        emailId = findViewById(R.id.email_edit);
        address  = findViewById(R.id.address_edit);
        userId  = findViewById(R.id.user_id);
        studentDate  = findViewById(R.id.student_dob);
        userImageUrl  = findViewById(R.id.imageView);
        editImage  = findViewById(R.id.edit_image);
        progressBar  = findViewById(R.id.progress);
        teacher_doj  = findViewById(R.id.teacher_doj);
        joiningDate  = findViewById(R.id.joining_date_layout);
        Confirm  = findViewById(R.id.confirm_btn);
        progressBar  = findViewById(R.id.progress);
        Discard  = findViewById(R.id.discard_btn);
        radioSexGroup=(RadioGroup)findViewById(R.id.radioGroup);
        transportRadioGrp=(RadioGroup)findViewById(R.id.radioTransport);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
             dateSelected = Calendar.getInstance();
        }
        dateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  setDateTimeField() ;
                // Get Current Date
                final Calendar c;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);
                }



                DatePickerDialog datePickerDialog = new DatePickerDialog(TeacherRegistationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                DOB = year +"-"+ (monthOfYear + 1)+"-"+dayOfMonth+"T13:26:38.163Z";
                                studentDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        joiningDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  setDateTimeField() ;
                // Get Current Date
                final Calendar c;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);
                }



                DatePickerDialog datePickerDialog = new DatePickerDialog(TeacherRegistationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                teacherJoin= year +"-"+ (monthOfYear + 1)+"-"+dayOfMonth+"T13:26:38.163Z";
                                teacher_doj.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        Discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId=radioSexGroup.getCheckedRadioButtonId();
                int selectedTransportId=transportRadioGrp.getCheckedRadioButtonId();
                // find the radio button by returned id
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                RadioButton tranport = (RadioButton) findViewById(selectedTransportId);

                transportType = String.valueOf(tranport.getText());
                Gender=String.valueOf(radioButton.getText());
                EmailId =emailId.getText().toString();
                passcode =Password.getText().toString();
                teacherName =teachername.getText().toString();
                lastname =lastName.getText().toString();

                MobileNum =mobile.getText().toString();
                AltMobNum =alternateMobile.getText().toString();
                UserID =userId.getText().toString();
                userAddress = address.getText().toString();
                if(TextUtils.isEmpty(teacherName)){
                    teachername.setError("Please fill valid teacher name.");
                    return;
                }
                if(TextUtils.isEmpty(lastname)){
                    lastName.setError("Please fill valid last name.");
                    return;
                }
                if(TextUtils.isEmpty(EmailId)){
                    emailId.setError("Please fill valid email address.");
                    return;
                }
                if(TextUtils.isEmpty(passcode)){
                    Password.setError("Please fill valid password.");
                    return;
                }
                if(TextUtils.isEmpty(MobileNum)){
                    mobile.setError("Please fill valid mobile number.");
                    return;
                } if(TextUtils.isEmpty(UserID)){
                    userId.setError("Please fill valid user id.");
                    return;
                } if(TextUtils.isEmpty(userAddress)){
                    address.setError("Please fill valid address.");
                    return;
                } if(TextUtils.isEmpty(transportType)){
                 Toast.makeText(TeacherRegistationActivity.this,"Please select transport type",Toast.LENGTH_LONG);
                    return;
                }

                if(TextUtils.isEmpty(DOB)){

                    Toast.makeText(TeacherRegistationActivity.this,"Please select the date of birth.",Toast.LENGTH_LONG);
                    return;
                }
                if(TextUtils.isEmpty(Gender)){
                    Toast.makeText(TeacherRegistationActivity.this,"Please select gender",Toast.LENGTH_LONG);
                    return;
                }

                if(TextUtils.isEmpty(teacherJoin)){
                    Password.setError("Please select the date of joining.");
                    return;
                }
                sendTeacherRegistrationData();
            }
        });


    }

    public void sendTeacherRegistrationData(){
        progressBar.setVisibility(View.VISIBLE);
        Map<String, String> params = new HashMap<String, String>();
        params.put("firstName", teacherName);
        params.put("lastName",lastname);
        params.put("email", EmailId);
        params.put("type", "teacher");
        params.put("mobile", MobileNum);
        params.put("joiningDate", teacherJoin);
        params.put("dob", DOB);
        params.put("gender", Gender);
        params.put("address", userAddress);
        params.put("alternateMobile", AltMobNum);
        params.put("imgUrl", "");
        params.put("fatherName", fatherNameSt);
        params.put("motherName", motherNameSt);
        params.put("fatherOccupation", "");
        params.put("motherOccupation", "");
        params.put("fatherQualification", "");
        params.put("motherQualification", "");
        params.put("scholarNo", "");
        params.put("username", UserID);
        params.put("admissionNo", "");
        params.put("transportType", transportType.toLowerCase());
        params.put("schoolId",   user.get("schoolId"));
        params.put("classId",  "");
        params.put("sectionId", "");
        params.put("password", passcode);



        JsonObjectRequest request_json = new JsonObjectRequest("http://13.233.167.170/api/users/", new JSONObject(params),
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


                                Toast.makeText(TeacherRegistationActivity.this,"Registration successful ",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(getIntent());
                            }else{

                                Toast.makeText(TeacherRegistationActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error is ", "" + error);
                Toast.makeText(TeacherRegistationActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }) {
            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                token = user.get("token");
                params.put("x-access-token", token);
                return params;
            }


            };
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request_json);


        }
}
