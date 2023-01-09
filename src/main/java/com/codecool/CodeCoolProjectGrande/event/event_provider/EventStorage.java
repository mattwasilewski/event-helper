package com.codecool.CodeCoolProjectGrande.event.event_provider;

import com.codecool.CodeCoolProjectGrande.event.event_provider.wroclaw_model.WroclawEvent;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
public class EventStorage {
    private Set<WroclawEvent> items;
    public EventStorage() {
        items = new HashSet<>();
    }



}
