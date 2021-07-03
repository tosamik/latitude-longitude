package com.sam.proj.findlatlong.controllers;

import java.io.IOException;
import com.sam.proj.findlatlong.service.impl.LatLongServiceImpl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LatLongController {

    private static final String ONEMAP_URL = "https://developers.onemap.sg/commonapi/search?returnGeom=Y&getAddrDetails=Y&searchVal=";
    private static final String API_IS_DOWN = "OneMap API is down!";
    LatLongServiceImpl latLongService;
    
    LatLongController(LatLongServiceImpl latLongServiceImpl) {
        this.latLongService = latLongServiceImpl;
    }

    @GetMapping("/latitude-longitude")
    public String getLatitudeLongitude(@RequestParam String postalCode) {
        String response = null;
        try {
            String url = ONEMAP_URL.concat(postalCode);
            response = latLongService.getLatitudeLongitude(url);
        } catch (IOException e) {
            return API_IS_DOWN;
        }
        return response;
    }    
}