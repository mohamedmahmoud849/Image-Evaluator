package com.example.Task.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.Task.model.ImageModel;
import com.example.Task.model.UsersModel;
import com.example.Task.repository.ImageRepository;
import com.example.Task.service.ImageService;
import com.example.Task.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Base64;
import java.util.List;

@Controller
public class UsersController {



    private final UsersService usersService;
    public final ImageService imageService;

    public UsersController(UsersService usersService, ImageService imageService) {
        this.usersService = usersService;

        this.imageService = imageService;
    }

    @GetMapping("/")
    public String HomePage(){

        return "redirect:/Gallery";
    }

    @GetMapping("/Gallery")
    public String ShowAcceptedImages(Model model){

        List<ImageModel> products= imageService.GetAllAcceptedImages();
        model.addAttribute("products",products);

        return "/GalleryOfAccepted";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest",new UsersModel());
        return "register_page";

    }
    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest",new UsersModel());
        return "login_page";

    }
    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel,Model model){
        System.out.println("Login request: "+usersModel);
        UsersModel authenticated=usersService.authenticate(usersModel.getLogin(),usersModel.getPassword());
        if(authenticated==null){
            return "error_page";
        }else{
            if(usersModel.getLogin().equals("admin")){
                return "redirect:/Admin";
            }
            model.addAttribute("userLogin",authenticated.getLogin());

            return"Upload_page";
        }

    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel){
        System.out.println("register request: "+usersModel);
        UsersModel regesteredUser=usersService.registerUser(usersModel.getLogin(),usersModel.getPassword());
        return  regesteredUser==null ? "error_page" :"redirect:/login";


    }

    @GetMapping("/Upload")
    public String getUploadPage(Model model){
        model.addAttribute("UploadRequest",new ImageModel());
        return "Upload_page";

    }
    @PostMapping("/Upload")
    public String saveImage(@RequestParam("file")MultipartFile file,
                            @RequestParam("description") String description,
                            @RequestParam("category") String category ){

        imageService.SaveImgDb(file,description,category);
        System.out.println(file.getOriginalFilename());
        return "Upload_page";

    }
    @GetMapping("/Admin")
    public String showImages(Model model){

        List<ImageModel> products= imageService.GetAllUnprocessedImages();

        model.addAttribute("products",products);

        return "/admin_page";

    }
    @GetMapping("/Gallery/{id}")
    public String Image(@PathVariable("id") Integer id ,Model model){
        ImageModel im = imageService.FindImage(id);
        model.addAttribute("imageRequest",im);

        return "/ImagePage";

    }
    @GetMapping("/{id}")
    public String EvaluateImage(@PathVariable("id") Integer id ,Model model){
        ImageModel EvaluatedImage = imageService.FindImage(id);
        model.addAttribute("EvaluateRequest",EvaluatedImage);
        return "/EvaluateImagePage";

    }

    @GetMapping  ("/Admin/{id}")
    public String UpdateImageStatus(@RequestParam("status") Boolean status ,@PathVariable Integer id){

        imageService.UpdateImageStatus(id, status);
        return "redirect:/Admin";

    }


}
