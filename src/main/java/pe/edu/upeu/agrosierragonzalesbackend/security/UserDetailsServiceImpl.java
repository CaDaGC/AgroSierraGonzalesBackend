package pe.edu.upeu.agrosierragonzalesbackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upeu.agrosierragonzalesbackend.entity.Usuario;
import pe.edu.upeu.agrosierragonzalesbackend.repository.UsuarioRepository;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        var authorities = Stream.concat(
                Stream.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name())),
                usuario.getPrivilegios().stream().map(SimpleGrantedAuthority::new)
        ).collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(), authorities);
    }
}
