//package mk.finki.ukim.mk.lab.web.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.finki.ukim.mk.lab.model.Artist;
//import mk.finki.ukim.mk.lab.model.Song;
//import mk.finki.ukim.mk.lab.service.ArtistService;
//import mk.finki.ukim.mk.lab.service.SongService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.*;
//
//@WebServlet(name = "all-artists-servlet", urlPatterns = "/allArtists")
//public class AllArtistsServlet extends HttpServlet {
//    private final ArtistService artistService;
//    private final SpringTemplateEngine springTemplateEngine;
//    private final SongService songService;
//
//    public AllArtistsServlet(ArtistService artistService, SpringTemplateEngine springTemplateEngine, SongService songService) {
//        this.artistService = artistService;
//        this.springTemplateEngine = springTemplateEngine;
//        this.songService = songService;
//    }
//
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//
//        //Ja zemame mapa za selektiraniot artis za pesnata od sesijata
//        Map<String, List<String>> selectedArtistsMap = (Map<String, List<String>>) req.getSession().getAttribute("selectedArtistsMap");
//
//
//        Map<Artist, List<Song>> artistSongsMap = new HashMap<>();
//
//        if (selectedArtistsMap != null) {
//            for (Map.Entry<String, List<String>> entry : selectedArtistsMap.entrySet()) {
//                String trackId = entry.getKey(); //edna pesna
//                List<String> artistIds = entry.getValue();//se bara vo poveke artisti
//
//                Optional<Song> songOptional = songService.findByTrackId(trackId); //ja barame song so ova
//                if (songOptional.isPresent()) { //i aku ja najme
//                    Song song = songOptional.get(); //ja zemame
//                    for (String artistId : artistIds) { //gi vrtime site artisti i aku se sovpagnit selektiranoto id
//                        Optional<Artist> artistOptional = artistService.findById(Long.parseLong(artistId));
//                        artistOptional.ifPresent(artist -> artistSongsMap.computeIfAbsent(artist, k -> new ArrayList<>()).add(song));
//                        //togas so poslednava linija go dodavame vo mapata aku e tuka i mu dodavame pesni
//                    }
//                }
//            }
//        }
//
//        context.setVariable("selectedArtistsMap", artistSongsMap);
//
//        springTemplateEngine.process("allArtists.html", context, resp.getWriter());
//    }
//
//
//}