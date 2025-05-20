package com.dio.spring.project.controller;


import com.dio.spring.project.model.Clienet;
import com.dio.spring.project.service.impl.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Iterable<Clienet>> buscarTodos() {
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clienet> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Clienet> inserir(@RequestBody Clienet cliente) {
        clienteService.inserir(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clienet> atualizar(@PathVariable Long id, @RequestBody Clienet cliente) {
        clienteService.atualizar(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.ok().build();
    }


}
