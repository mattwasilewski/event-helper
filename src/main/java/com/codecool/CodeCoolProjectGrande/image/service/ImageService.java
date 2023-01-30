package com.codecool.CodeCoolProjectGrande.image.service;

import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.event.repository.EventRepository;
import com.codecool.CodeCoolProjectGrande.image.Image;
import com.codecool.CodeCoolProjectGrande.image.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Service
public class ImageService {

    private final EventRepository eventRepository;
    private final ImageRepository imageRepository;


    @Autowired
    public ImageService(EventRepository eventRepository, ImageRepository imageRepository) {
        this.eventRepository = eventRepository;
        this.imageRepository = imageRepository;
    }

    public Optional<Image> addImageToEvent(String name, MultipartFile file) throws IOException {
        Optional<Event> event = eventRepository.findEventByName(name);
        if (event.isPresent()) {
            Image image = new Image();
            image.setName(name);
            image.setImageData(file.getBytes());
            imageRepository.save(image);
            event.get().setImage(image);
            eventRepository.save(event.get());
            return Optional.of(image);
        } else {
            return Optional.empty();
        }

    }

}
