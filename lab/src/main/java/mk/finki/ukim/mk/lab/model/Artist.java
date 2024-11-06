package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Artist {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    List<Song> songs; //ova
    public Artist(String firstName, String lastName, String bio) {
        this.id=(long)(Math.random() *1000);
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.songs=new ArrayList<>();
    }

}

