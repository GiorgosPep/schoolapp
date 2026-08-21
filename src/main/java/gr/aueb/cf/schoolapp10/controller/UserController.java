package gr.aueb.cf.schoolapp10.controller;

import gr.aueb.cf.schoolapp10.core.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp10.core.exceptions.EntityInvalidArgumentException;
import gr.aueb.cf.schoolapp10.dto.RoleReadOnlyDTO;
import gr.aueb.cf.schoolapp10.dto.UserInsertDTO;
import gr.aueb.cf.schoolapp10.dto.UserReadOnlyDTO;
import gr.aueb.cf.schoolapp10.model.Role;
import gr.aueb.cf.schoolapp10.service.IRoleService;
import gr.aueb.cf.schoolapp10.service.IUserService;
import gr.aueb.cf.schoolapp10.service.RoleServiceImpl;
import gr.aueb.cf.schoolapp10.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.plaf.PanelUI;
import java.security.PublicKey;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final IRoleService roleService;

    @GetMapping("/register")
    public String getUserForm(Model model) {
        model.addAttribute("userInsertDTO", UserInsertDTO.empty());
        return "user-form";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userInsertDTO") UserInsertDTO userInsertDTO,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        // user validator business rules TODO

        if (bindingResult.hasErrors()) {
            return "user-form";
        }

        try {
            UserReadOnlyDTO readOnlyDTO = userService.saveUser(userInsertDTO);
            redirectAttributes.addFlashAttribute("userReadOnlyDTO", readOnlyDTO);
            return "redirect:/users/success";
        } catch (EntityAlreadyExistsException | EntityInvalidArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "user-form";
        }
    }

    @GetMapping("/success")
    public String success(Model model) {
        return "user-success";
    }

    @ModelAttribute("roles")
    public List<RoleReadOnlyDTO> roles() {
        return roleService.findAllRolesSortedByName();
    }

}
