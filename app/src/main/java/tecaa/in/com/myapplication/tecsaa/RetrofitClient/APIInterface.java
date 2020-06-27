package tecaa.in.com.myapplication.tecsaa.RetrofitClient;

import tecaa.in.com.myapplication.tecsaa.ui.TeacherGallery.GalleryDataClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

        @GET("users/?per_page=12&page=1")
        Call<List<GalleryDataClass>> getGalleryData();

}
