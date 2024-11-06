package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {

    public List<Artist> findAll(){
        return DataHolder.artists;
    }
    public Optional<Artist> findById(Long id){
        return DataHolder.artists.stream().filter(i ->i.getId().equals(id)).findFirst();
    }

    public Song addSongToArtist(Song song,Artist artist){
        Optional<Artist> optionalArtist=findById(artist.getId());//aku go najdime artistot

        if(optionalArtist.isPresent()){//aku e prisuten
            Artist existingArtist=optionalArtist.get();//ja zeame cela klasa artist
            existingArtist.getSongs().add(song);//ja dodavame pesnata vo listata
            return song;
        }
        return null;//aku ne null vrajkame
    }



}
