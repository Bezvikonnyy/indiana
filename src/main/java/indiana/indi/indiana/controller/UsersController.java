package indiana.indi.indiana.controller;

import indiana.indi.indiana.controller.payload.NewUsersPayload;
import indiana.indi.indiana.entity.User;
import indiana.indi.indiana.repository.RoleRepository;
import indiana.indi.indiana.service.user.DefaultUserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UsersController {

    private final AuthenticationManager authenticationManager;

    private final DefaultUserDetailsService userDetailsService;

    private final RoleRepository roleRepository;

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("user", new NewUsersPayload(null, null, null));
        model.addAttribute("roles", roleRepository.findAll());
        return "registration";
    }

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        return "login";
    }

    @PostMapping("/registration")
    public String postRegistration(
            @Valid NewUsersPayload payload,
            BindingResult result,
            Model model
    ) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("roles", roleRepository.findAll());
            return "registration";
        }

        User user = this.userDetailsService.createUser(
                payload.username(),
                payload.password(),
                payload.roles()
        );
        return "redirect:/user/login";
    }
}
