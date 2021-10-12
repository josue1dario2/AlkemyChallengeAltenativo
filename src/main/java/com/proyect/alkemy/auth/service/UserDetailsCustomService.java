package com.proyect.alkemy.auth.service;

import com.proyect.alkemy.auth.dto.UsuarioDto;
import com.proyect.alkemy.auth.entity.Usuario;
import com.proyect.alkemy.auth.repository.UsuarioRepository;
import com.proyect.alkemy.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsername(userName);
        if(usuario == null){
            throw new UsernameNotFoundException("Username or password not found");
        }
        return new User(usuario.getUsername(),usuario.getPassword(), Collections.emptyList());
    }

    public boolean save(UsuarioDto usuarioDto){
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDto.getUsername());
        usuario.setPassword(usuarioDto.getPassword());
        usuario = usuarioRepository.save(usuario);
        if(usuario != null) {
            emailService.sendWelcomeEmailTo(usuario.getUsername());
        }
        return usuario != null;
    }
}
