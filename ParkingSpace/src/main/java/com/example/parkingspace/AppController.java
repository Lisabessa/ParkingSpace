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

    @Autowired
    private ReservationService reservationService;


    @RequestMapping("/")
    public String ViewHomePage(Model model) {
        return "index";
    }

    @RequestMapping("/users")
    public String ViewUsersPage(Model model, @Param("keyword") String keyword) {
        List<User> listUsers = userService.listAll(keyword);
        model.addAttribute("listEntities", listUsers);
        model.addAttribute("keyword", keyword);
        model.addAttribute("entityType", "User");
        model.addAttribute("baseLink", "/users");
        model.addAttribute("editLink", "/users/editUser/");
        model.addAttribute("deleteLink", "/users/deleteUser/");
        model.addAttribute("newLink", "/users/newUser");
        return "entity_list";
    }

    @RequestMapping("/users/newUser")
    public String ViewNewUserPage(Model model) {
        User user = new User();
        model.addAttribute("entity", user);
        model.addAttribute("action", "/api/users/");
        model.addAttribute("base_link", "/users");
        model.addAttribute("option", "create");
        return "change_entity";
    }

//    @RequestMapping(value = "/users/createUser", method = RequestMethod.POST)
//    public String createUser(@Valid @ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
//        if (bindingResult.hasErrors()) {
//            return "change_entity";
//        }
//
//        Optional<User> existingUser = userService.findDuplicates(user);
//        if (existingUser.isPresent()) {
//            bindingResult.rejectValue("vehicleRegistrationNumber", "error.vehicleRegistrationNumber", "Такой регистрационный номер уже есть в системе.");
//            return "change_entity";
//        }
//
//        try {
//            userService.save(user);
//            redirectAttrs.addFlashAttribute("success", "Новый пользоаватель успешно создан.");
//        } catch (Exception e) {
//            redirectAttrs.addFlashAttribute("error", "Произошла ошибка при создании нового пользователя.");
//        }
//
//
//        return "redirect:/users";
//    }

    @RequestMapping("/users/editUser/{id}")
    public ModelAndView editUser(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("change_entity");
        User user = userService.getUser(String.valueOf(id)); // TODO: Нужно что бы была проверка существования элемента - если нет можно выдавать false а при обработке и постронении страницы выдавать на пример 404 но не 500. 500 ошибка поломка приложения на стороне клиента (в веб это недопустимо).
        mav.addObject("entity", user);
        mav.addObject("action", "/users/saveEditedUser");
        mav.addObject("base_link", "/users");
        mav.addObject("option", "edit");
        return mav;
    }

    @RequestMapping(value = "/users/saveEditedUser", method = RequestMethod.POST)
    public String saveEditedUser(@Valid @ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "change_entity";
        }

        Optional<User> existingUser = userService.findDuplicates(user);
        if (existingUser.isPresent() && !existingUser.get().getId().equals(user.getId())) {
            bindingResult.rejectValue("vehicleRegistrationNumber", "error.vehicleRegistrationNumber", "Такой регистрационный номер уже есть в системе.");
            return "change_entity";
        }

        try {
            userService.updateUser(user);
            redirectAttrs.addFlashAttribute("success", "Данные пользователя успешно обновлены.");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Произошла ошибка при обновлении данных пользователя.");
        }

        return "redirect:/users";

    }

    @RequestMapping("/users/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.delete(String.valueOf(id));
        return "redirect:/users";
    }

    @RequestMapping("/parkingSlots")
    public String ViewParkingSlotsPage(Model model, @Param("keyword") String keyword) {
        List<ParkingSlot> listParkingSlots = parkingSlotService.listAll(keyword);
        model.addAttribute("listEntities", listParkingSlots);
        model.addAttribute("entityType", "ParkingSlot");
        model.addAttribute("keyword", keyword);
        model.addAttribute("baseLink", "/parkingSlots");
        model.addAttribute("editLink", "/parkingSlots/editParkingSlot/");
        model.addAttribute("deleteLink", "/parkingSlots/deleteParkingSlot/");
        model.addAttribute("newLink", "/parkingSlots/newParkingSlot");
        return "entity_list";
    }

    @RequestMapping("/parkingSlots/newParkingSlot")
    public String ViewNewParkingSlotPage(Model model) {
        ParkingSlot parkingSlot = new ParkingSlot();
        model.addAttribute("entity", parkingSlot);
        model.addAttribute("action", "/parkingSlots/createParkingSlot");
        model.addAttribute("base_link", "/parkingSlots");
        model.addAttribute("option", "create");
        return "change_entity";
    }

    @RequestMapping(value = "/parkingSlots/createParkingSlot", method = RequestMethod.POST)
    public String createParkingSlot(@Valid @ModelAttribute ParkingSlot parkingSlot, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "change_entity";
        }

        Optional<ParkingSlot> existingParkingSlot = parkingSlotService.findDuplicates(parkingSlot);
        if (existingParkingSlot.isPresent()) {
            bindingResult.rejectValue("slotCode", "error.slotCode", "Такой слот уже есть в системе.");
            return "change_entity";
        }

        try {
            parkingSlotService.save(parkingSlot);
            redirectAttrs.addFlashAttribute("success", "Новый парковочный слот успешно создан.");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Произошла ошибка при создании нового парковочного слота.");
        }


        return "redirect:/parkingSlots";
    }

    @RequestMapping("/parkingSlots/editParkingSlot/{id}")
    public ModelAndView editParkingSlot(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("change_entity");
        ParkingSlot parkingSlot = parkingSlotService.getParkingSlot(String.valueOf(id));
        mav.addObject("entity", parkingSlot);
        mav.addObject("action", "/parkingSlots/saveEditedParkingSlot");
        mav.addObject("base_link", "/parkingSlots");
        mav.addObject("option", "edit");
        return mav;
    }

    @RequestMapping(value = "/parkingSlots/saveEditedParkingSlot", method = RequestMethod.POST)
    public String saveEditedParkingSlot(@Valid @ModelAttribute ParkingSlot parkingSlot, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "change_entity";
        }

        Optional<ParkingSlot> existingParkingSlot = parkingSlotService.findDuplicates(parkingSlot);
        if (existingParkingSlot.isPresent() && !existingParkingSlot.get().getId().equals(parkingSlot.getId())) {
            bindingResult.rejectValue("slotCode", "error.slotCode", "Такой слот уже есть в системе.");
            return "change_entity";
        }

        try {
            parkingSlotService.updateParkingSlot(parkingSlot);
            redirectAttrs.addFlashAttribute("success", "Данные о парковочном слоте успешно обновлены.");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Произошла ошибка при обновлении данных о парковочном слоте.");
        }

        return "redirect:/parkingSlots";

    }

    @RequestMapping("/parkingSlots/deleteParkingSlot/{id}")
    public String deleteParkingSlot(@PathVariable int id) {
        parkingSlotService.delete(String.valueOf(id));
        return "redirect:/parkingSlots";
    }

    @RequestMapping("/aboutAuthor")
    public String aboutAuthor() {
        return "about_author";
    }

    @RequestMapping("/reservations")
    public String ViewReservationsPage(Model model) {
        List<Reservation> listReservations = reservationService.listAll();
        model.addAttribute("listEntities", listReservations);
        model.addAttribute("entityType", "Reservation");
        model.addAttribute("baseLink", "/reservations");
        model.addAttribute("editLink", "/reservations/editReservation/");
        model.addAttribute("deleteLink", "/reservations/deleteReservation/");
        model.addAttribute("newLink", "/reservations/newReservation");
        return "entity_list";
    }

    @RequestMapping("/reservations/deleteReservation/{id}")
    public String deleteReservation(@PathVariable int id) {
        reservationService.delete(String.valueOf(id));
        return "redirect:/reservations";
    }

    @RequestMapping("/reservations/newReservation")
    public String ViewNewReservationPage(Model model) {
        Reservation reservation = new Reservation();
        model.addAttribute("entity", reservation);
        model.addAttribute("action", "/reservations/createReservation");
        model.addAttribute("base_link", "/reservations");
        model.addAttribute("option", "create");
        return "change_entity";
    }

    @RequestMapping(value = "/reservations/createReservation", method = RequestMethod.POST)
    public String createReservation(@Valid @ModelAttribute Reservation reservation, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "change_entity";
        }
        try {
            reservationService.save(reservation);
            redirectAttrs.addFlashAttribute("success", "Новый пользоаватель успешно создан.");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Произошла ошибка при создании нового пользователя.");
        }

        return "redirect:/reservations";
    }

    @RequestMapping("/reservations/editReservation/{id}")
    public ModelAndView editReservation(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("change_entity");
        Reservation reservation = reservationService.getReservation(String.valueOf(id));

        mav.addObject("entity", reservation);
        mav.addObject("action", "/reservations/saveEditedReservation");
        mav.addObject("base_link", "/reservations");
        mav.addObject("option", "edit");
        return mav;
    }

    @RequestMapping(value = "/reservations/saveEditedReservation", method = RequestMethod.POST)
    public String saveEditedReservation(@Valid @ModelAttribute Reservation reservation, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
//        if (bindingResult.hasErrors()) {
//            return "new_entry";
//        }

//        Optional<Reservation> existingReservation = reservationService.findDuplicates(reservation);
//        if (existingReservation.isPresent() && !existingReservation.get().getId().equals(reservation.getId())) {
//            bindingResult.rejectValue("slotCode", "error.slotCode", "Такой слот уже есть в системе.");
//            return "new_entry";
//        }

        try {
            reservationService.updateReservation(reservation);
            redirectAttrs.addFlashAttribute("success", "Данные о бронировании успешно обновлены.");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Произошла ошибка при обновлении данных о бронировании.");
        }

        return "redirect:/reservations";

    }

}

