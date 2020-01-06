package com.home.realtor.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.realtor.models.Region;
import com.home.realtor.services.RegionService;

@RestController
@RequestMapping("/region")
public class RegionController {
    final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping
    Region create(@RequestBody Region region) {
        return regionService.create(region);
    }

    @PutMapping
    Region update(@RequestBody Region region) {
        return regionService.update(region);
    }

    @GetMapping("/id/{id}")
    Region getById(@PathVariable String id) {
        return regionService.getById(id);
    }

    @GetMapping("/company/id/{companyId}")
    List<Region> findByCompanyId(@PathVariable String companyId) {
        return regionService.findAllByCompanyId(companyId);
    }
}
