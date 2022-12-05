package com.codecool.CodeCoolProjectGrande.event.event_provider;

import com.codecool.CodeCoolProjectGrande.event.event_provider.model.ExtendedEvent;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class EventStorage {
    private List<ExtendedEvent> items;

    public EventStorage() {
        items = new ArrayList<>();
    }



}
