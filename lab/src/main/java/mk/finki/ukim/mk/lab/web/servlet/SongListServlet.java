//package mk.finki.ukim.mk.lab.web.servlet;
//
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.finki.ukim.mk.lab.model.Song;
//import mk.finki.ukim.mk.lab.service.SongService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.ISpringWebFluxTemplateEngine;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "song-servlet",urlPatterns = "/listSongs")
//public class SongListServlet extends HttpServlet {
//
//    private final SongService songService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//    public SongListServlet(SongService songService, SpringTemplateEngine springTemplateEngine) {
//        this.songService = songService;
//        this.springTemplateEngine = springTemplateEngine;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//        List<Song> songList=songService.listSongs();
//        context.setVariable("songs",songList);
//        springTemplateEngine.process("listSongs.html",context,resp.getWriter());
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String SelectedtrackId=req.getParameter("songname");    //gledame dali e kliknato
//        if(SelectedtrackId !=null){//ako e kliknato togas se zema vrednosta od radio button preku name i se prajka
//            resp.sendRedirect("/artist?trackId="+SelectedtrackId);//na slednata stranica vaka
//        }
//    }
//}
