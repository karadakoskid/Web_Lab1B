package mk.finki.ukim.mk.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.model.exceptions.AlbumNotFoundException;
import mk.finki.ukim.mk.lab.repository.jpa.AlbumRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ArtistRepository;
import mk.finki.ukim.mk.lab.repository.jpa.SongRepository;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {


    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository  artistRepository;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    @Transactional
    public Artist addArtistToSong(Artist artist, Song song) {
        Optional<Song> optionalSong=findByTrackId(song.getTrackId());//Ja naogjame pesnata preku id

        if(optionalSong.isPresent()){//aku e postoecka pesnata
            Song existingSong=optionalSong.get(); //togass ja zeam cela classa i so e = najdenata pesna zaradi so edna pesna ja sostavuvat delovite vo klasata
            existingSong.getPerformers().add(artist);
            songRepository.save(existingSong);//i posle dodavame vo listata od clasata Song performers
            return artist;//vrajkame artist
        }
        return null; //ako ne vrajkame null
    }


    @Override
    public Optional<Song> findByTrackId(Long trackId) {
        return songRepository.findByTrackId(trackId);
    }


    @Override
    @Transactional
    public Optional<Song> save(String title, String genre, Integer releaseYear, Long albumId) {
        Album album=this.albumRepository.findById(albumId).orElseThrow(()->new AlbumNotFoundException(albumId));
        this.songRepository.deleteByTitle(title);

        return Optional.of(this.songRepository.save(new Song(title,genre,releaseYear,album)));
    }

    @Override
    public void deleteById(Long id) {
        this.songRepository.deleteById(String.valueOf(id));
    }


    @Override
    public List<Song> findAllByAlbum_Id(Long albumId) {
        return songRepository.findAllByAlbum_Id(albumId);
    }


}
