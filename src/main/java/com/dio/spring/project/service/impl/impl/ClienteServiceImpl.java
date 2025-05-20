package com.dio.spring.project.service.impl.impl;

import com.dio.spring.project.model.Clienet;
import com.dio.spring.project.model.ClienteRepository;
import com.dio.spring.project.model.Endereco;
import com.dio.spring.project.model.EnderecoRepository;
import com.dio.spring.project.service.impl.ClienteService;
import com.dio.spring.project.service.impl.ViacCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViacCepService viaCepService;

    @Override
    public Iterable<Clienet> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Clienet buscarPorId(Long id) {
        Optional<Clienet> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Clienet cliente) {
    String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);


    }

    @Override
    public void atualizar(Long id, Clienet cliente) {
        Optional<Clienet> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
        }

    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Clienet cliente) {
        // Verificar se o Endereco do Cliente já existe (pelo CEP).
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        // Inserir Cliente, vinculando o Endereco (novo ou existente).
        clienteRepository.save(cliente);
    }
}
