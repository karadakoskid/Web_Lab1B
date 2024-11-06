package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist>artists=new ArrayList<>();
    public static List<Song>songs=new ArrayList<>();

    @PostConstruct
    public void init(){
        artists.add(new Artist("Damjan","Karadakoski","Pop artist"));
        artists.add(new Artist("Petar","Petreski","Rock artist"));
        artists.add(new Artist("Naum","Ivanoski","Folk artist"));
        artists.add(new Artist("Marija","Marjanoska","Pop artist"));
        artists.add(new Artist("Ivanka","Petreska","Folk artist"));
        songs.add(new Song("WorldSong","Pop",2022));
        songs.add(new Song("BestRock","Rock",2023));
        songs.add(new Song("SongSong","Folk",2024));
        songs.add(new Song("BestFolk","Folk",2020));
        songs.add(new Song("World","Pop",2021));

    }

}
