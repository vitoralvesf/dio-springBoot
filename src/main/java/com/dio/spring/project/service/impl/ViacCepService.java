package com.dio.spring.project.service.impl;


import com.dio.spring.project.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface  ViacCepService {

    @GetMapping("/{cep}/json")
    Endereco consultarCep(@PathVariable("cep") String cep);



}
