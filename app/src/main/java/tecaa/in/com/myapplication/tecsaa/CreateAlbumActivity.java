package tecaa.in.com.myapplication.tecsaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import in.mayanknagwanshi.imagepicker.ImageSelectActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import  tecaa.in.com.myapplication.tecsaa.Adapters.CreatePostImagesAdapter;
import tecaa.in.com.myapplication.tecsaa.Util.VolleyMultipartRequest;
import tecaa.in.com.myapplication.tecsaa.ui.gallary.AlbumsFiles;
import tecaa.in.com.myapplication.tecsaa.ui.gallary.GalleryData;

import org.json.JSONException;
import org.json.JSONObject;
import tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher.HomeWorkModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static tecaa.in.com.myapplication.tecsaa.Util.MasterFile.getFileDataFromDrawable;
import static tecaa.in.com.myapplication.tecsaa.Util.MasterFile.runtimePermissionsRequest;


public class CreateAlbumActivity extends AppCompatActivity {
   Button postData;
   EditText editText;
   RecyclerView recyclerView;
    CreatePostImagesAdapter mAdapter;
    Bitmap bitmap;
    int count =1;
    List<GalleryData> galleryData;
    ProgressBar progressBar;
    SessionManager sessionManager;
    HashMap<String, String> user;
    String token = null;
    static String albumId = null;
    public static boolean task1Finished = false;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_album);
        sessionManager = new SessionManager(CreateAlbumActivity.this);
        user = new HashMap<String, String>();
        user = sessionManager.getUserDetails();
        recyclerView =findViewById(R.id.viewGallary);
        postData=findViewById(R.id.manual_submit);
        editText=findViewById(R.id.edit_text);
        progressBar=findViewById(R.id.progress);
        galleryData= new ArrayList<>();
        mAdapter = new CreatePostImagesAdapter(CreateAlbumActivity.this,galleryData.get(0).getAlbumsFilesList(), clickCameraDataAsyncResult,  deleteCameraDataAsyncResult);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new GridLayoutManager(CreateAlbumActivity.this, 2, GridLayout.VERTICAL, false));

        recyclerView.setAdapter(mAdapter);
        postData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendImageData();
            }
        });



    }

    public void deleteAlbumImage(String id){
        progressBar.setVisibility(View.VISIBLE);
        JsonObjectRequest request_json = new JsonObjectRequest(Request.Method.DELETE,"http://13.233.167.170/api/albums/files/"+id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        progressBar.setVisibility(View.GONE);
                        Log.e("Response", response.toString());
                        String responsemessage = null;
                        try {


                            String resposne_sucess = response.getString("isSuccess");
                            if(resposne_sucess.equals("true")) {

                                getWorksResult();
                                Toast.makeText(CreateAlbumActivity.this,"Image deleted!!",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error is ", "" + error);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(CreateAlbumActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
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
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request_json);


    }
   public void sendImageData(){
       if(TextUtils.isEmpty(editText.getText().toString())){
           editText.setError("Please enter album name first!!");
           return;
       }
       progressBar.setVisibility(View.VISIBLE);
       String albumName=editText.getText().toString();
       String userId = user.get("schoolId");

       Map<String, String> params = new HashMap<String, String>();
       params.put("name",albumName);
       params.put("schoolId", userId);

       JsonObjectRequest request_json = new JsonObjectRequest("http://13.233.167.170/api/albums", new JSONObject(params),
               new Response.Listener<JSONObject>() {
                   @Override
                   public void onResponse(JSONObject response) {
                       System.out.println(response);
                       progressBar.setVisibility(View.GONE);
                       Log.e("Response", response.toString());
                       String responsemessage = null;
                       try {
                           String resposne_sucess = response.getString("isSuccess");
                           JSONObject resposne = response.getJSONObject("data");

                           if (resposne_sucess.equals("true")) {
                               albumId = resposne.getString("id");

                           }

                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }
               }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Log.e("error is ", "" + error);
               Toast.makeText(CreateAlbumActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
       RequestQueue queue = Volley.newRequestQueue(this);
       queue.add(request_json);


   }

    public void uploadFileOnServer(final String id, final Bitmap bitmap) {
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(
                Request.Method.POST,
                "http://13.233.167.170/api/albums/files/" + id,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        progressBar.setVisibility(View.GONE);
                        getWorksResult();
                        //Handle response
                       // uploadedImage.setVisibility(View.GONE);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Handle error
                    }
                }
        ) {
            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                token = user.get("token");
                params.put("x-access-token", token);
                return params;
            }


            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                byte[] data = getFileDataFromDrawable(bitmap);
                params.put("media", new DataPart("image" + id + ".jpg", data));
                return params;
            }
        };

        //I used this because it was sending the file twice to the server
        volleyMultipartRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        0,
                        -1,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(volleyMultipartRequest);
    }
    public void getWorksResult() {
        RequestQueue queue = null;
        queue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        final String URL = "http://13.233.167.170/api/albums";
        // Post params to be sent to the server
        if(galleryData.size()>0){
            galleryData.clear();
        }
        JsonObjectRequest request_json = new JsonObjectRequest(URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);

                        Log.e("Response", response.toString());
                        String responsemessage = null;
                        try {
                            progressBar.setVisibility(View.GONE);
                            JSONArray array=response.getJSONArray("items");

                            String resposne_sucess = response.getString("isSuccess");
                            if(resposne_sucess.equals("true"))
                            {
                                for(int i=0;i<array.length();i++) {
                                    List<AlbumsFiles> albumsFilesList =new ArrayList<>();
                                    JSONObject object1=array.getJSONObject(i);
                                    String id = String.valueOf(object1.getInt("id"));
                                    String title =object1.getString("name");
                                    if(albumId.equals(id)){
                                        JSONArray array2=response.getJSONArray("files");
                                        AlbumsFiles albumsFiles1 =new AlbumsFiles(String.valueOf(""),"");
                                        albumsFilesList.add(albumsFiles1);
                                        for(int j=0;j<array2.length();j++) {
                                            JSONObject object2=array2.getJSONObject(i);
                                            int imageId =object2.getInt("id");
                                            String imageName =object2.getString("name");
                                            AlbumsFiles albumsFiles =new AlbumsFiles(String.valueOf(imageId),imageName);
                                            albumsFilesList.add(albumsFiles);
                                        }
                                        GalleryData galleryData1= new GalleryData(title, id,albumsFilesList);
                                        galleryData.add(galleryData1);
                                        mAdapter.notifyDataSetChanged();
                                    }


                                }

                            }else{

                                Toast.makeText(CreateAlbumActivity.this,"Something went wrong!!",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(CreateAlbumActivity.this,"Something went wrong!!",Toast.LENGTH_SHORT).show();
                    }
                })
        {
            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                token = user.get("token");
                params.put("x-access-token", token);
                return params;
            }


        };
        queue.add(request_json);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1213){
            String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            if(bitmap != null) {
                this.bitmap=bitmap;
                uploadFileOnServer(albumId,bitmap);
               // count++;
                mAdapter.notifyDataSetChanged();
            }else{
                Toast.makeText(CreateAlbumActivity.this,"Picture not taken!",Toast.LENGTH_SHORT).show();
            }
        }
    }
    AsyncResult<String> clickCameraDataAsyncResult =new AsyncResult<String >() {
        @Override
        public void success(String  galleryData) {

            if (!runtimePermissionsRequest(CreateAlbumActivity.this)) {
                if(albumId!=null){
                    Intent intent = new Intent(CreateAlbumActivity.this, ImageSelectActivity.class);
                    intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
                    intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                    startActivityForResult(intent, 1213);
                }


            }
        }

        @Override
        public void error(String error) {

        }
    };


    AsyncResult<String> deleteCameraDataAsyncResult =new AsyncResult<String >() {
        @Override
        public void success(String  galleryData) {

            deleteAlbumImage(galleryData);

        }

        @Override
        public void error(String error) {

        }
    };


}
