package com.example.parkingspace.controller;
import com.example.parkingspace.service.*;
import com.example.parkingspace.model.*;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
    @Autowired
    private UserService userService;

    @Autowired
    private ParkingSlotService parkingSlotService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private RoleService roleService;


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
        model.addAttribute("entityType", "User");
        model.addAttribute("base_link", "/users");
        model.addAttribute("option", "create");
        return "change_entity";
    }

    @RequestMapping("/users/editUser/{id}")
    public ModelAndView editUser(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("change_entity");
        User user = userService.getUser(String.valueOf(id));
        mav.addObject("entity", user);
        mav.addObject("entityType", "User");
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
        model.addAttribute("entityType", "ParkingSlot");
        model.addAttribute("base_link", "/parkingSlots");
        model.addAttribute("option", "create");
        return "change_entity";
    }

    @RequestMapping("/parkingSlots/editParkingSlot/{id}")
    public ModelAndView editParkingSlot(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("change_entity");
        ParkingSlot parkingSlot = parkingSlotService.getParkingSlot(String.valueOf(id));
        mav.addObject("entity", parkingSlot);
        mav.addObject("entityType", "ParkingSlot");
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
        model.addAttribute("entityType", "Reservation");
        model.addAttribute("base_link", "/reservations");
        model.addAttribute("option", "create");
        return "change_entity";
    }

    @RequestMapping("/reservations/editReservation/{id}")
    public ModelAndView editReservation(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("change_entity");
        Reservation reservation = reservationService.getReservation(String.valueOf(id));

        mav.addObject("entity", reservation);
        mav.addObject("entityType", "Reservation");
        mav.addObject("base_link", "/reservations");
        mav.addObject("option", "edit");
        return mav;
    }

    @RequestMapping("/vehicles")
    public String ViewVehiclesPage(Model model, @Param("keyword") String keyword) {
        List<Vehicle> listVehicles = vehicleService.listAll(keyword);
        model.addAttribute("listEntities", listVehicles);
        model.addAttribute("keyword", keyword);
        model.addAttribute("entityType", "Vehicle");
        model.addAttribute("baseLink", "/vehicles");
        model.addAttribute("editLink", "/vehicles/editVehicle/");
        model.addAttribute("newLink", "/vehicles/newVehicle");
        return "entity_list";
    }

    @RequestMapping("/vehicles/newVehicle")
    public String ViewNewVehiclePage(Model model) {
        Vehicle vehicle = new Vehicle();
        model.addAttribute("entity", vehicle);
        model.addAttribute("entityType", "Vehicle");
        model.addAttribute("base_link", "/vehicles");
        model.addAttribute("option", "create");
        return "change_entity";
    }

    @RequestMapping("/vehicles/editVehicle/{id}")
    public ModelAndView editVehicle(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("change_entity");
        Vehicle vehicle = vehicleService.getVehicle(String.valueOf(id));
        mav.addObject("entity", vehicle);
        mav.addObject("entityType", "Vehicle");
        mav.addObject("base_link", "/vehicles");
        mav.addObject("option", "edit");
        return mav;
    }

    @RequestMapping("/roles")
    public String ViewRolesPage(Model model) {
        List<Role> listRoles = roleService.listAll();
        model.addAttribute("listEntities", listRoles);
        model.addAttribute("entityType", "Role");
        model.addAttribute("baseLink", "/roles");
        model.addAttribute("editLink", "/roles/editRole/");
        model.addAttribute("newLink", "/roles/newRole");
        return "entity_list";
    }

    @RequestMapping("/roles/newRole")
    public String ViewNewRolePage(Model model) {
        Role role = new Role();
        model.addAttribute("entity", role);
        model.addAttribute("entityType", "Role");
        model.addAttribute("base_link", "/roles");
        model.addAttribute("option", "create");
        return "change_entity";
    }

    @RequestMapping("/roles/editRole/{id}")
    public ModelAndView editRole(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("change_entity");
        Role role = roleService.getRole(String.valueOf(id));
        mav.addObject("entity", role);
        mav.addObject("entityType", "Role");
        mav.addObject("base_link", "/roles");
        mav.addObject("option", "edit");
        return mav;
    }

    @RequestMapping("/myReservations")
    public String ViewMyReservationsPage(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.getUserByLogin(username);
        List<Vehicle> vehicles = vehicleService.findByUserId(user.getId());
        List<Reservation> myReservations = reservationService.listAll().stream().filter(reservation -> vehicles.stream()
                .anyMatch(vehicle -> vehicle.getId().equals(reservation.getVehicle().getId()))).collect(Collectors.toList());

        model.addAttribute("listEntities", myReservations);
        model.addAttribute("newLink", "/myReservations/newMyReservation");
        model.addAttribute("editLink", "/myReservations/editMyReservation/");
        model.addAttribute("entityType", "Reservation");
        model.addAttribute("baseLink", "/myReservations");
        return "entity_list";
    }

    @RequestMapping("/myReservations/newMyReservation")
    public String ViewNewMyReservationPage(Model model) {
        Reservation reservation = new Reservation();
        model.addAttribute("entity", reservation);
        model.addAttribute("entityType", "Reservation");
        model.addAttribute("base_link", "/myReservations");
        model.addAttribute("option", "create");
        return "change_entity";
    }

    @RequestMapping("/myReservations/editMyReservation/{id}")
    public ModelAndView editMyReservation(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("change_entity");
        Reservation reservation = reservationService.getReservation(String.valueOf(id));
        mav.addObject("entity", reservation);
        mav.addObject("entityType", "Reservation");
        mav.addObject("base_link", "/myReservations");
        mav.addObject("option", "edit");
        return mav;
    }

    @RequestMapping("/aboutAuthor")
    public String aboutAuthor() {
        return "about_author";
    }

}

