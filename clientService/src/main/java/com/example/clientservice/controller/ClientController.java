package com.example.clientservice.controller;

import com.example.clientservice.dto.ApiResponse;
import com.example.clientservice.dto.ClientDTO;
import com.example.clientservice.repository.ClientRepository;
import com.example.clientservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ClientController {
    final ClientRepository clientRepository;
    final ClientService clientService;

    //getAll
    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse response = clientService.getAll();
        return ResponseEntity.ok().body(response);
        //response entity status header body
//        return null;
    }

    //getOne
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        ApiResponse response = clientService.getOne(id);
        return ResponseEntity.ok().body(response);
    }

    //add
    @PostMapping
    public HttpEntity<?> add(@RequestBody ClientDTO dto) {
        ApiResponse response = clientService.add(dto);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody ClientDTO clientdto) {
        ApiResponse response = clientService.edit(id, clientdto);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        //rostanam o'chirish
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return ResponseEntity.ok().body("Deleted!");
        } else {
            return ResponseEntity.status(404).body("Client NOT found!");
        }

    }

}
