package com.praticandojava.sistemaCadastro.Resource;


import com.praticandojava.sistemaCadastro.Model.Cadastro;
import com.praticandojava.sistemaCadastro.Repository.CadastroRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cadastro")
public class CadastroResource {

    @Autowired
    private CadastroRepository cadastroRepository;

    @GetMapping
    public List <Cadastro> listarTodos(){
        return cadastroRepository.findAll();
    }

    @PostMapping
    @ResponseStatus
    public ResponseEntity<Cadastro> salvarCadastro(@RequestBody Cadastro cadastro, HttpServletResponse response){

        Cadastro cadastroSalvo = cadastroRepository.save(cadastro);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{cadastro}")
                .buildAndExpand(cadastroSalvo.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(cadastroSalvo);

    }
    @GetMapping("/{id}")
    public Cadastro buscarPorId(Long id){
        return cadastroRepository.findById(id).get();
    }
}
