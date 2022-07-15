package com.example.ghRepos;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class MainController {

    private final MainService mainService;
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }


    @GetMapping("")
    public String homepage() {
        return "<html><body><h1>Hello</h1> <h3>Get example repository data: <a href='repositories/facebook/react'>React </a></h3> </body> </html>";
    }

    @GetMapping("/repositories/{owner}/{repositoryName}")
    public GHRepository hello(@PathVariable String owner, @PathVariable String repositoryName, @RequestHeader("accept-language") String language) {
        return mainService.getRepository(owner, repositoryName, language);
    }
}
