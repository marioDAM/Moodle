package com.moodle.project.controller;

import com.moodle.project.dto.CreateUsuarioDTO;
import com.moodle.project.dto.GetUsuarioDTO;
import com.moodle.project.entity.Usuario;
import com.moodle.project.enums.Role;
import com.moodle.project.mapper.UsuarioMapper;
import com.moodle.project.repository.UsuariosRepository;
import com.moodle.project.security.jwt.JwtTokenProvider;
import com.moodle.project.service.CustomUserDetailsService;
import com.moodle.project.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

import static com.moodle.project.enums.Role.STUDE;

/**
 * Clase controladora, que nos define la ruta donde se va a visualizar nuestro
 * front-end en HTML
 *
 * @author Mario Valverde
 */
@Controller
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private UsuarioService service;

    List<Usuario> usuarios = new ArrayList<>();


    CustomUserDetailsService userDetailService;

    /**
     * La ruta que vemos en @GetMapping, será donde veamos la manera de indentificar a los usuarios
     *
     * @return El login que hemos diseñado en la carpeta templates
     */
    @GetMapping(path = {"/login", "/"})
    public String login(Model model) {
        model.addAttribute("mensaje", "Inicie sesión");

        return "loginOld";
    }

    /**
     * La ruta que vemos en @GetMapping, será donde veamos la interfaz del profesor(administrador), donde podrá realizar sus acciones
     *
     * @return La vista donde se encentra la interfaz del administrador
     */
    @RequestMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("titulo", "Listar clientes");
        model.addAttribute("clientes", service.getUsers());
        return "admin";
    }

    /**
     * La ruta que vemos en @GetMapping, será donde veamos la interfaz del profesor para poder añadir alumnos al instituto
     *
     * @return La vista donde se encentra la interfaz del administrador
     */

    @GetMapping(path = {"/register"})
    public String register() {
        return "register";
    }

    @GetMapping(path = {"/register/teach"})
    public String registerTeach() {
        return "registerTeach";
    }

    @GetMapping(path = {"/alumno"})
    public String alumno(Model model) {

        return "alumnos";
    }


    @PostMapping("/file")
    @Transactional
    public String cargarCSV(@RequestParam("file") MultipartFile mfile, RedirectAttributes redirectAttributes) throws IOException {
        //   storageService.store(file);

        redirectAttributes.addFlashAttribute("message", "Por favor seleccione un");
        InputStream in = new BufferedInputStream(mfile.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        if (!mfile.isEmpty()) {
            int indexPointCsv = mfile.getOriginalFilename().indexOf(".");
            String fileType = mfile.getOriginalFilename().substring(indexPointCsv + 1, mfile.getOriginalFilename().length());

            String line;

            if (fileType.equals("csv")) {
                br.readLine();
            }

            while ((line = br.readLine()) != null) {

                String[] fields = line.split(";");
                fields = removeTrailingQuotes(fields);
                if (fields.length != 0) {
                    CreateUsuarioDTO dto = new CreateUsuarioDTO();

                    dto.setUsername(fields[0]);
                    dto.setAvatar(fields[1]);
                    dto.setFullname(fields[2]);
                    dto.setEmail(fields[3]);
                    dto.setDni(fields[4]);
                    dto.setPassword(fields[5]);
                    dto.setPassword2(fields[13]);
                    Set<Role> roles = Collections.singleton(STUDE);
                    Usuario usuario = new Usuario();
                    usuario.setTerminated(Boolean.parseBoolean(fields[6]));
                    usuario.setNote(Integer.parseInt(fields[8]));
                    usuario.setTest(Integer.parseInt(fields[9]));
                    usuario.setSubjects(fields[10]);
                    usuario.setCreatedAt(LocalDateTime.now());
                    usuario.setLastPasswordChangeAt(LocalDateTime.now());
                    usuario.setUsername(dto.getUsername());
                    usuario.setAvatar(dto.getAvatar());
                    usuario.setFullName(dto.getFullname());
                    usuario.setEmail(dto.getEmail());
                    usuario.setDni(dto.getDni());
                    usuario.setRoles(roles);
                    usuario.setPassword(dto.getPassword());
                    usuario.setEntryDate(LocalDateTime.now());

                    usuarios.add(usuario);
                }
            }
            service.nuevosAlumnos(usuarios);
        }
        return "redirect:/admin";
    }

    private static String[] removeTrailingQuotes(String[] fields) {
        String result[] = new String[fields.length];
        for (int i = 0; i < result.length; i++) {
            final String BARRA = "\"";

            result[i] = fields[i].replaceAll("^" + BARRA, "").replaceAll(BARRA + "$", "");
        }
        return result;
    }

    @GetMapping("/auth")
    public void hola(@AuthenticationPrincipal Usuario userDetails, HttpServletResponse response) throws IOException {
        System.out.println("HHHHHHHHHHHolaaaa");
        userDetails = (Usuario) userDetailService.loadUserById(userDetails.getId());
        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")) || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("TEACH"))) {
            response.sendRedirect("/admin");
        } else {
            response.sendRedirect("/alumno");
        }
    }


    private final UsuariosRepository usuariosRepository = new UsuariosRepository() {
        @Override
        public List<Usuario> findAll() {
            return null;
        }

        @Override
        public List<Usuario> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<Usuario> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public List<Usuario> findAllById(Iterable<Long> longs) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(Usuario entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Long> longs) {

        }

        @Override
        public void deleteAll(Iterable<? extends Usuario> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends Usuario> S save(S entity) {
            return null;
        }

        @Override
        public <S extends Usuario> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public Optional<Usuario> findById(Long aLong) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Usuario> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends Usuario> List<S> saveAllAndFlush(Iterable<S> entities) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<Usuario> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Long> longs) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Usuario getOne(Long aLong) {
            return null;
        }

        @Override
        public Usuario getById(Long aLong) {
            return null;
        }

        @Override
        public Usuario getReferenceById(Long aLong) {
            return null;
        }

        @Override
        public <S extends Usuario> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Usuario> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Usuario> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public <S extends Usuario> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Usuario> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Usuario> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends Usuario, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }

        @Override
        public Optional<Usuario> findByUsername(String username) {
            return Optional.empty();
        }
    };
    private PasswordEncoder passwordEncoder = new PasswordEncoder() {
        @Override
        public String encode(CharSequence rawPassword) {
            return null;
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return false;
        }
    };

    private AuthenticationManager authenticationManager = new AuthenticationManager() {
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            return null;
        }
    };
    private UsuarioMapper ususuarioMapper = new UsuarioMapper();

    private JwtTokenProvider tokenProvider = new JwtTokenProvider();
}

