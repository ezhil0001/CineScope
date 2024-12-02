package com.sabari.Movie_Xplore.Controller;

import com.sabari.Movie_Xplore.DTO.Response;
import com.sabari.Movie_Xplore.Services.Interf.ICastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cast")
public class CastController {

    @Autowired
    private ICastService castService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")

    public ResponseEntity<Response> getAllCast() {
        Response response = castService.getAllCast();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/cast-by-id/{castId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getCastById(int id) {
        Response response = castService.getCastById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> addNewCast(
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam(value = "name", required = false) String name) {
        if (photo == null || photo.isEmpty()) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage("Please provide values for all fields(photo)");
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
        Response response = castService.addNewCast(photo, name);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @PutMapping("/update/{castId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateCast(
            @PathVariable int castId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "photo", required = false) MultipartFile photo
    ) {
        Response response = castService.updateCast(castId, name, photo);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/cast-by-id/{castId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteCast(@PathVariable int id) {
        Response response = castService.deleteCast(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
