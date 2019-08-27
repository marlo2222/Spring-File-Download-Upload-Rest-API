package com.api.usuario.controller;

import com.api.usuario.model.Usuario;
import com.api.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(value = "/")
    public String home(){
        return "Hello World";
    }

    @GetMapping(value = "/usuario/listar/")
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    @GetMapping(value = "/usuario/listar/{id}")
    public Usuario listarUsuarioId(@PathVariable("id") long id){
        return usuarioRepository.getOne(id);
    }

    @PostMapping(value = "/usuario/salvar")
    public Usuario salvarUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @DeleteMapping(value = "/usuario/deletar/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable("id") long id){
        usuarioRepository.deleteById(id);
        return new ResponseEntity<String>("Usuario deletado com sucesso", HttpStatus.OK);
    }

    @PutMapping(value = "/usuario/atualizar")
    public Usuario atualizarUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

}
