package tecaa.in.com.myapplication.tecsaa.ui.gallary;

import android.graphics.Bitmap;

import java.util.List;

public class GalleryData {
    String albumName;
    String id;
    List<AlbumsFiles> albumsFilesList;

    public GalleryData(String albumName, String id, List<AlbumsFiles> albumsFilesList) {
        this.albumName = albumName;
        this.id = id;
        this.albumsFilesList = albumsFilesList;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<AlbumsFiles> getAlbumsFilesList() {
        return albumsFilesList;
    }

    public void setAlbumsFilesList(List<AlbumsFiles> albumsFilesList) {
        this.albumsFilesList = albumsFilesList;
    }
}
