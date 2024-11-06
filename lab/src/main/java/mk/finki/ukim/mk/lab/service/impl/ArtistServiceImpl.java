package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.repository.ArtistRepository;
import mk.finki.ukim.mk.lab.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> listArtists() {
        return this.artistRepository.findAll(); //vo zadacata bese listAll() -dali ke imat greska ak go ostam taka a ne findAll() zaradi so gi definiravme findAll() drugine
    }

    @Override
    public Optional<Artist> findById(Long id) {
        return this.artistRepository.findById(id);
    }

    @Override
    public Song addSongToArtist(Song song, Artist artist) { //ova
        return artistRepository.addSongToArtist(song, artist);
    }
}
