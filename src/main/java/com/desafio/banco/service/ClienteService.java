package com.desafio.banco.service;

import com.desafio.banco.login.Usuario;
import com.desafio.banco.model.Cliente;
import com.desafio.banco.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements UserDetailsService {


    private final PasswordEncoder passwordEncoder;
    private final ClienteRepository clienteRepository;

    public ClienteService(PasswordEncoder passwordEncoder, ClienteRepository clienteRepository) {
        this.passwordEncoder = passwordEncoder;
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrar(Cliente cliente) {
        Cliente clienteNovo = new Cliente();

        BeanUtils.copyProperties(cliente, clienteNovo, "id", "contas");

        String senhaCriptografada = criptografarSenha(cliente.getSenha());
        clienteNovo.setSenha(senhaCriptografada);

         return clienteRepository.save(clienteNovo);
    }

    private String criptografarSenha(String senha) {
        return passwordEncoder.encode(senha);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente buscar(Long id) {
        Optional<Cliente> op = clienteRepository.findById(id);
        if (op.isPresent()) {
            Cliente cliente = op.get();
            return cliente;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Cliente atualizar(Cliente cliente, Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND));
        }
        return cliente;
    }

    @Override
    public UserDetails loadUserByUsername(String cpfNumber) throws UsernameNotFoundException {
        return clienteRepository.findByCpfNumber(cpfNumber);
    }
}
