package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    List<Artist> listArtists();//vo zadacata bese listAll() -dali ke imat greska ak go ostam taka a ne findAll() zaradi so gi definiravme findAll() drugine
    Optional<Artist> findById(Long id);

    Song addSongToArtist(Song song,Artist artist);

}
