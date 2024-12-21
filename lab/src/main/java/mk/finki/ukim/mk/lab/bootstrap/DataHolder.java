package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist>artists=new ArrayList<>();
    public static List<Song>songs=new ArrayList<>();
    public static List<Album>albums=new ArrayList<>();
//    @PostConstruct
//    public void init(){
//        Album album1=new Album("album1","Pop","2021");
//        Album album2=new Album("album2","Rock","2022");
//        Album album3=new Album("album3","Folk","2023");
//        Album album4=new Album("album4","Pop","2024");
//        Album album5=new Album("album5","Rock","2024");
//
//        albums.add(album1);
//        albums.add(album2);
//        albums.add(album3);
//        albums.add(album4);
//        albums.add(album5);
//
//        artists.add(new Artist("Damjan","Karadakoski","Pop artist"));
//        artists.add(new Artist("Petar","Petreski","Rock artist"));
//        artists.add(new Artist("Naum","Ivanoski","Folk artist"));
//        artists.add(new Artist("Marija","Marjanoska","Pop artist"));
//        artists.add(new Artist("Ivanka","Petreska","Folk artist"));
//        songs.add(new Song("WorldSong","Pop",2022,album1));
//        songs.add(new Song("BestRock","Rock",2023,album2));
//        songs.add(new Song("SongSong","Folk",2024,album3));
//        songs.add(new Song("BestFolk","Folk",2020,album4));
//        songs.add(new Song("World","Pop",2021,album5));
//
//
//    }

}
