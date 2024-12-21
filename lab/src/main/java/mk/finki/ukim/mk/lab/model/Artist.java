package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    @ManyToMany(mappedBy = "performers", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    List<Song> songs; //ova

    public Artist() {
    }

    public Artist(String firstName, String lastName, String bio) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.songs=new ArrayList<>();
    }

}

