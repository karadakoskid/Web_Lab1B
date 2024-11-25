package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SongRepository {

    public List<Song> findAll(){
        return DataHolder.songs;
    }

    public Optional<Song> findByTrackId(String trackId){
        return DataHolder.songs.stream().filter(i->i.getTrackId().equals(trackId)).findFirst();
    }
    public Artist addArtistToSong(Artist artist, Song song){
        Optional<Song> optionalSong=findByTrackId(song.getTrackId());//Ja naogjame pesnata preku id

        if(optionalSong.isPresent()){//aku e postoecka pesnata
            Song existingSong=optionalSong.get(); //togass ja zeam cela classa i so e = najdenata pesna zaradi so edna pesna ja sostavuvat delovite vo klasata
            existingSong.getPerformers().add(artist);//i posle dodavame vo listata od clasata Song performers
            return artist;//vrajkame artist
        }
        return null; //ako ne vrajkame null
    }

    public Optional<Song> save(String title,String genre, Integer releaseYear, Album album){
        DataHolder.songs.removeIf(i->i.getTitle().equals(title));
        Song song=new Song(title,genre,releaseYear,album);
        DataHolder.songs.add(song);
        return Optional.of(song);
    }

    public void deleteById(Long id){
        DataHolder.songs.removeIf(i->i.getId().equals(id));
    }

}
