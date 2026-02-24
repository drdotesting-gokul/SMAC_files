package com.alloy.Controller;

import com.alloy.Model.AlloyRequest;
import com.alloy.Model.AlloyResponse;
import com.alloy.Service.AlloyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alloy")
public class AlloyController {

    private final AlloyService alloyService;

    public AlloyController(AlloyService alloyService) {
        this.alloyService = alloyService;
    }

    @PostMapping("/calculate")
    public AlloyResponse calculate(@RequestBody AlloyRequest request) {
        return alloyService.calculate(request);
    }
}