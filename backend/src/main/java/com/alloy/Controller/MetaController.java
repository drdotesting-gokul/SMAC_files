package com.alloy.Controller;

import com.alloy.Service.InteractionMatrixService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class MetaController {

    private final InteractionMatrixService matrixService;

    public MetaController(InteractionMatrixService matrixService) {
        this.matrixService = matrixService;
    }

    @GetMapping("/metal-count")
    public int getMetalCount() {
        return matrixService.getMetalCount();
    }

    @GetMapping("/metals")
    public List<String> getMetals() {
        return matrixService.getAllMetals();
    }
}