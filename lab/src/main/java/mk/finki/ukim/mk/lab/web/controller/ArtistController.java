package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.ArtistService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }


    @GetMapping("/select")
    public String selectArtistsPage(@RequestParam String trackId,Model model){
        List<Artist> artists=artistService.listArtists();
        model.addAttribute("idto",trackId);
        model.addAttribute("artists",artists);
        return "artistsList";
    }

    @PostMapping("/select")
    public String saveSelectedArtist(@RequestParam List<String> artistList, @RequestParam String trackId, HttpSession session) {
        // Retrieve or create the selectedArtistsMap
        Map<String, List<String>> selectedArtistMap = (Map<String, List<String>>) session.getAttribute("selectedArtistsMap");

        if (selectedArtistMap == null) {
            selectedArtistMap = new HashMap<>();
        }

        // Get or create the list of selected artists for the given trackId
        List<String> selectedArtists = selectedArtistMap.getOrDefault(trackId, new ArrayList<>());

        // Add the selected artists to the list (avoid duplicates)
        for (String artistId : artistList) {
            if (!selectedArtists.contains(artistId)) {
                selectedArtists.add(artistId);
            }
        }

        // Save the updated list back to the session
        selectedArtistMap.put(trackId, selectedArtists);
        session.setAttribute("selectedArtistsMap", selectedArtistMap);

        return "redirect:/songs/details?trackId=" + trackId;
    }

    @GetMapping("/all")
    public String showAllArtistsAndSongs(HttpSession session, Model model) {
        Map<String, List<String>> selectedArtistsMap =
                (Map<String, List<String>>) session.getAttribute("selectedArtistsMap");

        Map<Artist, List<Song>> artistSongsMap = new HashMap<>();

        if (selectedArtistsMap != null) {
            for (Map.Entry<String, List<String>> entry : selectedArtistsMap.entrySet()) {
                String trackId = entry.getKey();
                List<String> artistIds = entry.getValue();

                songService.findByTrackId(trackId).ifPresent(song -> {
                    for (String artistId : artistIds) {
                        artistService.findById(Long.parseLong(artistId))
                                .ifPresent(artist -> artistSongsMap
                                        .computeIfAbsent(artist, k -> new ArrayList<>())
                                        .add(song));
                    }
                });
            }
        }

        model.addAttribute("selectedArtistsMap", artistSongsMap);
        return "allArtists";
    }
}
