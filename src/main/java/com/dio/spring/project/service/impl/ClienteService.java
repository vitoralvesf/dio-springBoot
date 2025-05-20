package com.dio.spring.project.service.impl;

import com.dio.spring.project.model.Clienet;

public interface ClienteService {

    Iterable<Clienet> buscarTodos();

    Clienet buscarPorId(Long id);

    void inserir(Clienet cliente);

    void atualizar(Long id, Clienet cliente);

    void deletar(Long id);
}
