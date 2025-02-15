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
        model.addAttribute("newLink", "/users/newUser");
        return "entity_list";
    }

    @RequestMapping("/users/newUser")
    public String ViewNewUserPage(Model model) {
        User user = new User();
        model.addAttribute("entity", user);
        model.addAttribute("base_link", "/users");
        model.addAttribute("option", "create");
        return "change_entity";
    }

    @RequestMapping("/users/editUser/{id}")
    public ModelAndView editUser(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("change_entity");
        User user = userService.getUser(String.valueOf(id));
        mav.addObject("entity", user);
        mav.addObject("base_link", "/users");
        mav.addObject("option", "edit");
        return mav;
    }

    @RequestMapping("/parkingSlots")
    public String ViewParkingSlotsPage(Model model, @Param("keyword") String keyword) {
        List<ParkingSlot> listParkingSlots = parkingSlotService.listAll(keyword);
        model.addAttribute("listEntities", listParkingSlots);
        model.addAttribute("entityType", "ParkingSlot");
        model.addAttribute("keyword", keyword);
        model.addAttribute("baseLink", "/parkingSlots");
        model.addAttribute("editLink", "/parkingSlots/editParkingSlot/");
        model.addAttribute("newLink", "/parkingSlots/newParkingSlot");
        return "entity_list";
    }

    @RequestMapping("/parkingSlots/newParkingSlot")
    public String ViewNewParkingSlotPage(Model model) {
        ParkingSlot parkingSlot = new ParkingSlot();
        model.addAttribute("entity", parkingSlot);
        model.addAttribute("base_link", "/parkingSlots");
        model.addAttribute("option", "create");
        return "change_entity";
    }

    @RequestMapping("/parkingSlots/editParkingSlot/{id}")
    public ModelAndView editParkingSlot(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("change_entity");
        ParkingSlot parkingSlot = parkingSlotService.getParkingSlot(String.valueOf(id));
        mav.addObject("entity", parkingSlot);
        mav.addObject("base_link", "/parkingSlots");
        mav.addObject("option", "edit");
        return mav;
    }

    @RequestMapping("/reservations")
    public String ViewReservationsPage(Model model) {
        List<Reservation> listReservations = reservationService.listAll();
        model.addAttribute("listEntities", listReservations);
        model.addAttribute("entityType", "Reservation");
        model.addAttribute("baseLink", "/reservations");
        model.addAttribute("editLink", "/reservations/editReservation/");
        model.addAttribute("newLink", "/reservations/newReservation");
        return "entity_list";
    }

    @RequestMapping("/reservations/newReservation")
    public String ViewNewReservationPage(Model model) {
        Reservation reservation = new Reservation();
        model.addAttribute("entity", reservation);
        model.addAttribute("base_link", "/reservations");
        model.addAttribute("option", "create");
        return "change_entity";
    }

    @RequestMapping("/reservations/editReservation/{id}")
    public ModelAndView editReservation(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("change_entity");
        Reservation reservation = reservationService.getReservation(String.valueOf(id));

        mav.addObject("entity", reservation);
        mav.addObject("base_link", "/reservations");
        mav.addObject("option", "edit");
        return mav;
    }

    @RequestMapping("/aboutAuthor")
    public String aboutAuthor() {
        return "about_author";
    }

}

