package com.moodle.project.controller;

import com.moodle.project.dto.CreateUsuarioDTO;
import com.moodle.project.entity.Usuario;
import com.moodle.project.enums.Role;
import com.moodle.project.repository.UsuariosRepository;
import com.moodle.project.service.CustomUserDetailsService;
import com.moodle.project.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    @Autowired
    private UsuariosRepository repository;

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

    /**
     * Ruta que sirve para ir a la vista en la que podemos añadir un profesor
     *
     * @return plantilla html
     */

    @GetMapping(path = {"/register/teach"})
    public String registerTeach() {
        return "registerTeach";
    }

    /**
     * Vista del alumno
     *
     * @return plantilla de html
     */
    @GetMapping(path = {"/alumno"})
    public String alumno() {
        return "alumno";
    }

    @GetMapping("profesor")
    public String profesor() {
        return "profesor";
    }

    @GetMapping(path = {"/createalumno"})
    public String crear(Model model) {
        CreateUsuarioDTO usuario = new CreateUsuarioDTO();
        model.addAttribute("titulo", "Nuevo cliente");
        model.addAttribute("usuario", usuario);
        return "añadirAlumno";
    }

    @PostMapping("save")
    public String guardar(@ModelAttribute CreateUsuarioDTO usuario) {
        service.nuevoUsuario(usuario);
        return "redirect:listalumnos";
    }

    @GetMapping(path = {"/edit/{id}"})
    public String editar(@PathVariable Long id, Model model) {
        Optional<Usuario> usuario = service.findUserById(id);
        model.addAttribute("titulo", "Editar Alumno");
        model.addAttribute("usuario", usuario);
        return "añadirAlumno";
    }

    @GetMapping("findusername")
    public String findByUsername(@RequestBody String username, Model model) {
        Optional<Usuario> usuario = service.findUserByUsername(username);
        model.addAttribute("username", usuario);
        return "findusername";

    }

    @GetMapping("listalumnos")
    public String listaAlumnos(Model model) {
        List<Usuario> alumnos = service.getAlumnos();
        model.addAttribute("listaAlumnos", alumnos);
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
}

