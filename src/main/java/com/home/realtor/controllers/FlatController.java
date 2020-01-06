package com.home.realtor.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.realtor.models.Flat;
import com.home.realtor.models.criteries.FlatCriteria;
import com.home.realtor.services.FlatService;

@RestController
@RequestMapping("/flat")
public class FlatController {
    final FlatService flatService;

    public FlatController(FlatService flatService) {
        this.flatService = flatService;
    }

    @PostMapping
    Flat creat(@RequestBody Flat flat) {
        return flatService.create(flat);
    }

    @PutMapping
    Flat update(@RequestBody Flat flat) {
        return flatService.update(flat);
    }

    @GetMapping("/{id}")
    Flat getById(@PathVariable String id){
        return flatService.getById(id);
    }

    @GetMapping("/company/id/{id}")
    List<Flat> findAllByCompanyId(@PathVariable String id){
        return flatService.findAllByCompanyId(id);
    }

    @PostMapping("/criteria")
    List<Flat> findAllByCriteria(@RequestBody FlatCriteria flatCriteria) {
        return flatService.findByCriteria(flatCriteria);
    }

}
