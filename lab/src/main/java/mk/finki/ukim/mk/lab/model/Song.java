package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

@Data
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long trackId;

    private String title;
    private String genre;
    private int releaseYear;
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable( //vaka ko ke iam manytomany za dve isti klasi i za da ne se napraet dupli tabeli
            name = "song_artists",//ova e imeto na tabelata
            joinColumns = @JoinColumn(name="song_id"), //ova e owning side kolona zato vaka se pisit
            inverseJoinColumns = @JoinColumn(name="artist_id")//ova e foreign kolona zato ova poinaku se pisit
    )
    private List<Artist> performers;
    @ManyToOne
    private Album album;

    public Song() {
    }

    public Song(String title, String genre, int releaseYear, Album album) {

        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();

        this.album=album;
    }

    public Long getTrackId() {
        return trackId;
    }


    //da go napram so save so id, i da go donapram details
}
