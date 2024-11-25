package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();
    Artist addArtistToSong(Artist artist, Song song);
    Optional<Song> findByTrackId(String trackId);
    Optional<Song> save(String title, String genre, Integer releaseYear,Long albumId);
    void deleteById(Long id);

}
