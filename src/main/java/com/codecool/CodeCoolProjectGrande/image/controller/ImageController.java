package com.codecool.CodeCoolProjectGrande.image.controller;

import com.codecool.CodeCoolProjectGrande.image.Image;
import com.codecool.CodeCoolProjectGrande.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;

@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("/api/images/")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "upload-image")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws IOException {
        Optional<Image> image = imageService.addImageToEvent(name, file);
        if (image.isPresent()) {
            return new ResponseEntity<>(String.format("File '%s' uploaded successfully.", file.getOriginalFilename()), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
