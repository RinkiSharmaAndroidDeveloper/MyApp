package tecaa.in.com.myapplication.tecsaa.ui.TeacherGallery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GalleryDataClass {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("pageNo")
    @Expose
    private Integer pageNo;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("totalRecords")
    @Expose
    private Integer totalRecords;
    @SerializedName("items")
    @Expose
    private List<GalleryItems> galleryItemsList = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<GalleryItems> getGalleryItemsList() {
        return galleryItemsList;
    }

    public void setGalleryItemsList(List<GalleryItems> galleryItemsList) {
        this.galleryItemsList = galleryItemsList;
    }
}
