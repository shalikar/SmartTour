package com.team3.smarttour;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class Controller {

@Autowired
FusekiService fusekiService;

    @RequestMapping(value = "/artworks/{floor}/{room}", method = RequestMethod.GET)
    public List<Artwork> getArtworks(@PathVariable String floor, @PathVariable String room){
        System.out.println(floor+" "+room);
        return fusekiService.getArtworks(floor,room);

    }

    @RequestMapping(value = "/artworks/recommended/", method = RequestMethod.POST)
    public List<Artwork> getRecommendedArtworks(@RequestBody Artwork artwork){

        return fusekiService.getRecommendedArtworks(artwork);

    }

}
