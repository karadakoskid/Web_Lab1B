package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song,String> {
    Optional<Song>findByTrackId(Long trackId);
    List<Song> findAllByAlbum_Id(Long albumId);
    void deleteByTrackId(Long Id);
    void deleteByTitle(String title);
}
