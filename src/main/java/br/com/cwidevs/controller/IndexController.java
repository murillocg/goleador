package br.com.cwidevs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author pedro.rohr
 */
@Controller
public class IndexController {

    @RequestMapping({"/dashboard", "/jogadores", "/jogador", "/jogador/*", "/goleadores", "/partidas"})
    public String indexPage() {
        return "forward:/index.html";
    }
}
