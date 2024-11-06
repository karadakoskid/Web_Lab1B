package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.ArtistService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "songDetails-servlet",urlPatterns = "/songDetails")
public class songDetailsServlet extends HttpServlet {
    private final SongService songService;
    private final ArtistService artistService;
    private final SpringTemplateEngine springTemplateEngine;

    public songDetailsServlet(SongService songService, ArtistService artistService, SpringTemplateEngine springTemplateEngine) {
        this.songService = songService;
        this.artistService = artistService;
        this.springTemplateEngine = springTemplateEngine;
    }

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    IWebExchange webExchange = JakartaServletWebApplication
            .buildApplication(getServletContext())
            .buildExchange(req, resp);

    WebContext context = new WebContext(webExchange);
    String trackId = req.getParameter("trackId");


    Optional<Song> songOptional = songService.findByTrackId(trackId);
    if (songOptional.isPresent()) {
        Song song = songOptional.get();//ja naojgame selektiranata pesna vo ovoj cel del
        context.setVariable("song", song);
    } else {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Song not found");
        return;
    }


    Map<String, List<String>> selectedArtistsMap = (Map<String, List<String>>) req.getSession().getAttribute("selectedArtistsMap");

    if (selectedArtistsMap == null) {
        selectedArtistsMap = new HashMap<>();
        req.getSession().setAttribute("selectedArtistsMap", selectedArtistsMap);
    }


    List<String> selectedArtistIds = selectedArtistsMap.get(trackId);//se zema lista od artisti povrzani so pesnata

    if (selectedArtistIds != null) {//aku go najdi go
        List<Artist> artists = new ArrayList<>();//dodava vo ovaa lista (obajnsuvanje za ovoj blok)
        for (String artistId : selectedArtistIds) {
            Optional<Artist> artistOptional = artistService.findById(Long.parseLong(artistId));
            artistOptional.ifPresent(artists::add);
        }
        context.setVariable("artists", artists);
    }


    springTemplateEngine.process("songDetails.html", context, resp.getWriter());
}


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
