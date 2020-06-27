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

public class StudentRegistrationActivity extends AppCompatActivity {

    EditText teachername,fatherName,motherName,mobile,alternateMobile,emailId,address,Password,userId,lastName,fatherOccupation,motherOccupation,fatherQualification,motherQualification,scholorNo,admissionNo,className,Section;
    CircleImageView userImageUrl;
    ImageView editImage;
    TextView studentDate,studentAdmissionDate;
    String schoolId,classId,sectionId,passcode,teacherName,fatherNameSt,motherNameSt,DOB,Gender,MobileNum,AltMobNum,EmailId,TransportType,UserID,UserImage,userAddress,lastname,fatherOccu,motherOccu,fatherQuali,motherQuali,addmissionSt,dateOfJoining;
    Button Confirm,Discard;
    String transportType;
    LinearLayout dateLayout,dateAdmission;

    RadioGroup radioSexGroup,transportRadioGrp;
    ProgressBar progressBar;
    SessionManager sessionManager;
    HashMap<String, String> user;
    String token=null;
    Calendar dateSelected;
    private DatePickerDialog datePickerDialog;
    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        sessionManager = new SessionManager(this);
        user = new HashMap<String, String>();
        user= sessionManager.getUserDetails();

        teachername = findViewById(R.id.edit_teacher_name);
        lastName = findViewById(R.id.edit_teacher_name_last);
        fatherName = findViewById(R.id.edit_father_name);
        motherName = findViewById(R.id.edit_mother_name);
        dateLayout = findViewById(R.id.date_layout);
        dateAdmission = findViewById(R.id.joining_date_layout);
        studentAdmissionDate = findViewById(R.id.student_doj);
        mobile = findViewById(R.id.mobile_number);
        alternateMobile = findViewById(R.id.alternate_mobile_number);
        Password = findViewById(R.id.password);
        emailId = findViewById(R.id.email_edit);
        address  = findViewById(R.id.address_edit);
        userId  = findViewById(R.id.userIdd);
        studentDate  = findViewById(R.id.student_dob);
        userImageUrl  = findViewById(R.id.imageView);
        editImage  = findViewById(R.id.edit_image);
        fatherOccupation  = findViewById(R.id.father_occupation_edit);
        motherOccupation  = findViewById(R.id.mother_occupation);
        fatherQualification  = findViewById(R.id.father_qualification_edit);
        motherQualification  = findViewById(R.id.mother_qualification);
        scholorNo  = findViewById(R.id.scolor_no);
        admissionNo  = findViewById(R.id.admision_no);
        className  = findViewById(R.id.class_name);
        Section  = findViewById(R.id.section_no);

        progressBar  = findViewById(R.id.progress);
        Confirm  = findViewById(R.id.confirm_btn);
        Discard  = findViewById(R.id.disss);
        progressBar  = findViewById(R.id.progress);
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



                DatePickerDialog datePickerDialog = new DatePickerDialog(StudentRegistrationActivity.this,
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


        dateAdmission.setOnClickListener(new View.OnClickListener() {
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



                DatePickerDialog datePickerDialog = new DatePickerDialog(StudentRegistrationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dateOfJoining=year +"-"+ (monthOfYear + 1)+"-"+dayOfMonth+"T13:26:38.163Z";
                                studentAdmissionDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

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
                // radioSexGroup= (RadioButton) findViewById(selectedId);



                // find the radio button by returned id
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                RadioButton tranport = (RadioButton) findViewById(selectedTransportId);
                transportType = String.valueOf(tranport.getText());
                Gender=String.valueOf(radioButton.getText());
/*
                Toast.makeText(TeacherRegistationActivity.this,
                        radioButton.getText(), Toast.LENGTH_SHORT).show();*/
                //  String isMale = String.valueOf(radioSexGroup);
                EmailId =emailId.getText().toString();
                passcode =Password.getText().toString();
                teacherName =teachername.getText().toString();
                lastname =lastName.getText().toString();
                fatherNameSt =fatherName.getText().toString();
                motherNameSt =motherName.getText().toString();
                MobileNum =mobile.getText().toString();
                AltMobNum =alternateMobile.getText().toString();
                UserID =userId.getText().toString();
                fatherOccu =fatherOccupation.getText().toString();
                motherOccu =motherOccupation.getText().toString();
                fatherQuali =fatherQualification.getText().toString();
                motherQuali =motherQualification.getText().toString();
                schoolId =scholorNo.getText().toString();
                sectionId =Section.getText().toString();
                schoolId =scholorNo.getText().toString();
                addmissionSt =admissionNo.getText().toString();
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

                if(TextUtils.isEmpty(fatherNameSt)){
                    fatherName.setError("Please fill valid father name.");
                    return;
                }  if(TextUtils.isEmpty(motherNameSt)){
                    motherName.setError("Please fill valid mother name.");
                    return;
                }  if(TextUtils.isEmpty(MobileNum)){
                    mobile.setError("Please fill valid mobile number.");
                    return;
                } if(TextUtils.isEmpty(UserID)){
                    userId.setError("Please fill valid user id.");
                    return;
                } if(TextUtils.isEmpty(userAddress)){
                    address.setError("Please fill valid address.");
                    return;
                } if(TextUtils.isEmpty(sectionId)){
                    Section.setError("Please fill valid section name.");
                    return;
                } if(TextUtils.isEmpty(schoolId)){
                    scholorNo.setError("Please fill valid scholar no.");
                    return;
                } if(TextUtils.isEmpty(addmissionSt)){
                    admissionNo.setError("Please fill valid admission no.");
                    return;
                } if(TextUtils.isEmpty(classId)){
                    className.setError("Please fill valid class name.");
                    return;
                } if(TextUtils.isEmpty(transportType)){
                    Toast.makeText(StudentRegistrationActivity.this,"Please select transport type",Toast.LENGTH_LONG);
                    return;
                }
                if(TextUtils.isEmpty(DOB)){

                    Toast.makeText(StudentRegistrationActivity.this,"Please select the date of birth.",Toast.LENGTH_LONG);
                    return;
                }
                if(TextUtils.isEmpty(Gender)){
                    Toast.makeText(StudentRegistrationActivity.this,"Please select gender",Toast.LENGTH_LONG);
                    return;
                }

                if(TextUtils.isEmpty(dateOfJoining)){
                    Toast.makeText(StudentRegistrationActivity.this,"Please select the date of admission.",Toast.LENGTH_LONG);

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
        params.put("lastName", lastname);
        params.put("email", EmailId);
        params.put("type", "student");
        params.put("mobile", MobileNum);
        params.put("dob", DOB);
        params.put("gender", Gender);
        params.put("address", userAddress);
        params.put("alternateMobile", AltMobNum);
        params.put("imgUrl", "");
        params.put("joiningDate", dateOfJoining);
        params.put("fatherName", fatherNameSt);
        params.put("motherName", motherNameSt);
        params.put("fatherOccupation", fatherOccu);
        params.put("motherOccupation", motherOccu);
        params.put("fatherQualification", fatherQuali);
        params.put("motherQualification", motherQuali);
        params.put("scholarNo", schoolId);
        params.put("username", UserID);
        params.put("admissionNo",addmissionSt);
        params.put("transportType", transportType.toLowerCase());
        params.put("schoolId", user.get("schoolId"));
        params.put("classId",  classId);
        params.put("sectionId",sectionId);
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


                                Toast.makeText(StudentRegistrationActivity.this,"Registration successful ",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(getIntent());
                            }else{

                                Toast.makeText(StudentRegistrationActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error is ", "" + error);
                Toast.makeText(StudentRegistrationActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
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
