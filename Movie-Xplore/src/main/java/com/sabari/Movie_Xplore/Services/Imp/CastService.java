package com.sabari.Movie_Xplore.Services.Imp;

import com.sabari.Movie_Xplore.DTO.CastDto;
import com.sabari.Movie_Xplore.DTO.Response;
import com.sabari.Movie_Xplore.Entity.Cast;
import com.sabari.Movie_Xplore.Exception.OurException;
import com.sabari.Movie_Xplore.Repo.CastRepo;
import com.sabari.Movie_Xplore.Services.AWS3Service;
import com.sabari.Movie_Xplore.Services.Interf.ICastService;
import com.sabari.Movie_Xplore.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CastService implements ICastService {

    @Autowired
    private CastRepo castRepo;

    @Autowired
    private AWS3Service awsS3Service;
    public Response getAllCast() {
        Response response = new Response();
        try {
            List<Cast> castList = castRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
            List<CastDto> castDto = Utils.mapCastListEntityToCastListDTO(castList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setCastDtoList(castDto);
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Getting all cast: " + e.getMessage());

        }
        return response;
    }

    public Response getCastById(int id) {
        Response response = new Response();

        try{
            Cast cast = castRepo.findById(id).orElseThrow(() -> new OurException("Cast Not Found"));
            CastDto castDto = Utils.mapCastEntityToCastDto(cast);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setCastDto(castDto);
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a Cast " + e.getMessage());
        }
        return response;
    }

    public Response addNewCast(MultipartFile photo, String name) {
        Response response = new Response();

        try{
            String file = awsS3Service.saveImageToS3(photo);
            Cast cast = new Cast();
            cast.setName(name);
            cast.setImageURL(file);

            Cast savedCast = castRepo.save(cast);
            CastDto castDto = Utils.mapCastEntityToCastDto(savedCast);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setCastDto(castDto);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
    }

    public Response updateCast(int id, String name, MultipartFile photo) {
        Response response = new Response();

        try {
            String imageUrl = null;
            if (photo != null && !photo.isEmpty()) {
                imageUrl = awsS3Service.saveImageToS3(photo);
            }
            Cast cast = castRepo.findById(id).orElseThrow(() -> new OurException("Cast Not Found"));
            if (imageUrl != null) cast.setImageURL(imageUrl);
            if (name != null) cast.setName(name);

            Cast updatedCast = castRepo.save(cast);
            CastDto castDto = Utils.mapCastEntityToCastDto(updatedCast);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setCastDto(castDto);
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
    }

    public Response deleteCast(int id) {
        Response response = new Response();

        try{
            castRepo.findById(id).orElseThrow(() -> new OurException("Cast Not Found"));
            castRepo.deleteById(id);
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a Cast " + e.getMessage());
        }
        return response;
    }
}
