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

@WebServlet(name = "artist-servlet", urlPatterns = "/artist")
public class АrtistServlet extends HttpServlet {
    private final SongService songService;
    private final ArtistService artistService;
    private final SpringTemplateEngine springTemplateEngine;

    public АrtistServlet(SongService songService, ArtistService artistService, SpringTemplateEngine springTemplateEngine) {
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
        String trackId = req.getParameter("trackId");//ova ni e za da go zejme idto od kliknation radiobutton za da go prikazime ovde
        List<Artist> artists = artistService.listArtists();
        context.setVariable("idto", trackId);
        context.setVariable("artists", artists);
        springTemplateEngine.process("artistsList.html", context, resp.getWriter());

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedArtistId = req.getParameter("artistList");
        String trackId = req.getParameter("trackId");

        if (selectedArtistId != null && trackId != null) {

            Map<String, List<String>> selectedArtistsMap = (Map<String, List<String>>) req.getSession().getAttribute("selectedArtistsMap");

            if (selectedArtistsMap == null) {
                selectedArtistsMap = new HashMap<>();
            }

            //kreirame lista za dadenion track id aku nemat
            List<String> selectedArtists = selectedArtistsMap.getOrDefault(trackId, new ArrayList<>());

            if (!selectedArtists.contains(selectedArtistId)) {
                selectedArtists.add(selectedArtistId);
            }

            selectedArtistsMap.put(trackId, selectedArtists);

            //zacuvuvanje na mapata
            req.getSession().setAttribute("selectedArtistsMap", selectedArtistsMap);

            resp.sendRedirect("/songDetails?trackId=" + trackId);

        } else {

            resp.sendRedirect("/artist");
        }
    }
}

