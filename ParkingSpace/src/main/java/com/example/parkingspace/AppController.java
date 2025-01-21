package com.example.parkingspace;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppController {
    @Autowired
    private UserService userService;

    @Autowired
    private ParkingSlotService parkingSlotService;


    @RequestMapping("/")
    public String ViewHomePage(Model model) {
        return "index";
    }

    @RequestMapping("/users")
    public String ViewUsersPage(Model model, @Param("keyword") String keyword) {
        List<User> listUsers = userService.listAll(keyword);
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("keyword", keyword);
        return "users";
    }

    @RequestMapping("/users/newUser")
    public String ViewNewUserPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    @RequestMapping(value = "/users/createUser", method = RequestMethod.POST)
    public String createUser(@Valid @ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "new_user";
        }

        Optional<User> existingUser = userService.findDuplicates(user);
        if (existingUser.isPresent()) {
            bindingResult.rejectValue("vehicleRegistrationNumber", "error.vehicleRegistrationNumber", "Такой регистрационный номер уже есть в системе.");
            return "new_user";
        }

        try {
            userService.save(user);
            redirectAttrs.addFlashAttribute("success", "Новый пользоаватель успешно создан.");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Произошла ошибка при создании нового пользователя.");
        }


        return "redirect:/";
    }

    @RequestMapping("/users/editUser/{id}")
    public ModelAndView editUser(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_user");
        User user = userService.getUser(String.valueOf(id));
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/users/saveEditedUser", method = RequestMethod.POST)
    public String saveEditedUser(@Valid @ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "edit_user";
        }

        Optional<User> existingUser = userService.findDuplicates(user);
        if (existingUser.isPresent() && !existingUser.get().getId().equals(user.getId())) {
            bindingResult.rejectValue("vehicleRegistrationNumber", "error.vehicleRegistrationNumber", "Такой регистрационный номер уже есть в системе.");
            return "edit_user";
        }

        try {
            userService.updateUser(user);
            redirectAttrs.addFlashAttribute("success", "Данные пользователя успешно обновлены.");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Произошла ошибка при обновлении данных пользователя.");
        }

        return "redirect:/";

    }

    @RequestMapping("/users/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.delete(String.valueOf(id));
        return "redirect:/";
    }

    @RequestMapping("/parkingSlots")
    public String ViewParkingSlotsPage(Model model, @Param("keyword") String keyword) {
        List<ParkingSlot> listParkingSlots = parkingSlotService.listAll(keyword);
        model.addAttribute("listParkingSlots", listParkingSlots);
        model.addAttribute("keyword", keyword);
        return "parking_slots";
    }


    @RequestMapping("/aboutAuthor")
    public String aboutAuthor() {
        return "about_author";
    }

}

