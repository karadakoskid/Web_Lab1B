package mk.finki.ukim.mk.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.repository.impl.InMemoryArtistRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ArtistRepository;
import mk.finki.ukim.mk.lab.repository.jpa.SongRepository;
import mk.finki.ukim.mk.lab.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository, SongRepository songRepository) {
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
    }


    @Override
    public List<Artist> listArtists() {
        return this.artistRepository.findAll(); //vo zadacata bese listAll() -dali ke imat greska ak go ostam taka a ne findAll() zaradi so gi definiravme findAll() drugine
    }

    @Override
    public Optional<Artist> findById(Long id) {
        return this.artistRepository.findById(id);
    }

//    @Override
//    @Transactional
//    public Song addSongToArtist(Song song, Artist artist) { //ova
//        Optional<Artist> optionalArtist=findById(artist.getId());//aku go najdime artistot
//
//        if(optionalArtist.isPresent()){//aku e prisuten
//            Artist existingArtist=optionalArtist.get();//ja zeame cela klasa artist
//            existingArtist.getSongs().add(song);//ja dodavame pesnata vo listata
//            return song;
//        }
//        return null;//aku ne null vrajkame
//    }
@Override
@Transactional
public Song addSongToArtist(Song song, Artist artist) {
    Optional<Song> optionalSong = songRepository.findById(String.valueOf(song.getTrackId()));
    Optional<Artist> optionalArtist = artistRepository.findById(artist.getId());

    if (optionalSong.isPresent() && optionalArtist.isPresent()) {
        Song existingSong = optionalSong.get();
        Artist existingArtist = optionalArtist.get();

        // Add the artist to the song's performers list if not already added
        if (!existingSong.getPerformers().contains(existingArtist)) {
            existingSong.getPerformers().add(existingArtist);
            existingArtist.getSongs().add(existingSong); // Ensure bi-directional relationship is maintained
        }

        // Save both the artist and the song
        artistRepository.save(existingArtist); // This ensures the artist's changes are persisted
        songRepository.save(existingSong); // This ensures the song's changes are persisted

        return existingSong;
    }

    return null;
}


}
