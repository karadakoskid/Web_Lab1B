package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.AlbumService;
import mk.finki.ukim.mk.lab.service.ArtistService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final SongService songService;

    public SongController(AlbumService albumService, ArtistService artistService, SongService songService) {
        this.albumService = albumService;
        this.artistService = artistService;
        this.songService = songService;
    }
    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model){
        if(error !=null && !error.isEmpty()){
                model.addAttribute("hasError",true);
                model.addAttribute("error",error);
        }
        List<Song>songList=this.songService.listSongs();
        model.addAttribute("songs",songList); //preku ova atributname ke gi prikazime vo HTML
        return "listSongs"; //ja vrajkat listSongs.html
    }

    @PostMapping
    public String selectedSong(@RequestParam String songname){
        if(songname !=null && !songname.isEmpty()){
            return "redirect:/artists/select?trackId=" +songname;
        }
        return "redirect:/songs?error=No song selected";
    }

//    @GetMapping("/details") OVA BESE ZA KO NEMAVME BAZAA VO SESIJATA
//    public String showSongDetails(@RequestParam Long trackId, Model model, HttpSession session){
//        Optional<Song> songOptional=songService.findByTrackId(trackId);
//
//        if(songOptional.isEmpty()){
//            return "redirect:/songs?error=Song not found";
//        }
//
//        Song song=songOptional.get();//ja zeame vaka pesnata po trackingid
//        model.addAttribute("song",song);//vaka ke ja prenesime vo html
//
//        Map<String,List<String>> selectedArtistMap=
//                (Map<String,List<String>>) session.getAttribute("selectedArtistsMap");
//
//        if(selectedArtistMap !=null){
//            List<String> selectedArtistIds=selectedArtistMap.get(trackId);
//
//            if(selectedArtistIds !=null){
//                List<Artist> artists=new ArrayList<>();
//                for(String artistId : selectedArtistIds){
//                    Optional<Artist> artistOptional = artistService.findById(Long.parseLong(artistId));
//                    artistOptional.ifPresent(artists::add);
//
//                }
//                model.addAttribute("artists",artists);
//            }
//        }
//        return "songDetails"; //ja vrakat songDetails.html
//    }
    @GetMapping("/details")
    public String showSongDetails(@RequestParam Long trackId, Model model, HttpSession session){
        Optional<Song> songOptional=songService.findByTrackId(trackId);
        if(songOptional.isPresent()){
            Song song=songOptional.get();
            model.addAttribute("song",song);
            model.addAttribute("artists",song.getPerformers());
            return "songDetails";
        }
        return "redirect:/songs";
    }




    @PostMapping("/add")
    public String saveSong(@RequestParam String title,@RequestParam String genre,@RequestParam int releaseYear,@RequestParam Long albumId){

        this.songService.save(title,genre,releaseYear,albumId);

        return "redirect:/songs";
    }

    @GetMapping("/edit-form/{songId}")
    public String getEditSongForm(@PathVariable Long songId, Model model) {
        Optional<Song> songOptional = this.songService.findByTrackId(songId);
        if (songOptional.isPresent()) {
            Song song = songOptional.get();
            List<Album> albums = this.albumService.findAll();
            model.addAttribute("album", albums); // Fixed attribute name
            model.addAttribute("song", song);
            return "add-song"; // Returns the form for editing
        }
        return "redirect:/songs?error=SongNotFound";
    }
    @GetMapping("/add-form")
    public String getAddSongPage(Model model){
        List<Album>albums=this.albumService.findAll();
        model.addAttribute("album",albums);
        return "add-song";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id){
        this.songService.deleteById(id);
        return "redirect:/songs";
    }
}
