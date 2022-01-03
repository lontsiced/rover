package com.aneo.rover.service.impl;

import com.aneo.rover.model.Plateau;
import com.aneo.rover.service.PlateauService;
import org.springframework.stereotype.Service;

@Service
public class PlateauServiceImpl implements PlateauService {
    @Override
    public Plateau createPlateau(String dimensions) {
        String[] parts = dimensions.split(" ");
        int dimX = Integer.parseInt(parts[0]);
        int dimY = Integer.parseInt(parts[1]);
        return new Plateau(dimX, dimY);
    }
}
