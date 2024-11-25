package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

@Data
public class Song {
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    List<Artist> performers;
    private Long id;
    private Album album;
//    String alphabets="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public Song(String title, String genre, int releaseYear,Album album) {
//        this.trackId= RandomGeneratorFactory.of(alphabets).create(5).toString();
        this.trackId = Long.toString((long)(Math.random() *1000));
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();
        this.id=((long)(Math.random() *1000));
        this.album=album;
    }

    public String getTrackId() {
        return trackId;
    }
}
