package com.eurodesign09.windowproject.controller;
import com.eurodesign09.windowproject.entity.*;
import com.eurodesign09.windowproject.mailSender.MyConstants;
import com.eurodesign09.windowproject.service.CallMeFormService;
import com.eurodesign09.windowproject.service.FeedbackApprovedService;
import com.eurodesign09.windowproject.service.FeedbackService;
import com.eurodesign09.windowproject.service.WorkDoneService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.mail.SendFailedException;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Controller
public class HomePageController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private CallMeFormService callMeFormService;

    @Autowired
    private FeedbackApprovedService feedbackApprovedService;

    @Autowired
    private WorkDoneService workDoneService;

    @Autowired
    private JavaMailSender emailSender;




    @RequestMapping("/")
    public String showHomePage(ModelMap map, String message){
        Feedback feedback = new Feedback();
        CallMeForm callMeForm = new CallMeForm();
        List<FeedbackApproved> listApprovedFeedback = feedbackApprovedService.listAllApprovedFeedback();
        List<WorkDone> listWorkDone = workDoneService.listAllWork();

        if(message != null){
            map.addAttribute("message", message);
        }

        map.addAttribute("feedback", feedback);
        map.addAttribute("callMeForm", callMeForm);
        map.addAttribute("listApprovedFeedback", listApprovedFeedback);
        map.addAttribute("listWorkDone", listWorkDone);
        return "index";
    }

    public String showHomePage(ModelMap map) {
        return showHomePage(map, null);
    }

    @RequestMapping(value = "/save_callMeForm", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8") //, produces = "text/plain;charset=UTF-8"
    public String saveCallMeForm(ModelMap map,  @ModelAttribute("callMeForm") CallMeForm callMeForm) throws SendFailedException {
        callMeFormService.saveCallMeForm(callMeForm);

        String type = "callMeForm";
        sendEmailAsync(callMeForm.getClientName(), callMeForm.getClientPhone(), type);

        String message = "Спасибо! Ваша заявка успешно отправлена! Наши специалисты свяжутся с Вами в ближайшее время!";
        return showHomePage(map, message);



    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8") //, produces = "text/plain;charset=UTF-8"
    public String saveFeedback(ModelMap map, @ModelAttribute("feedback") Feedback feedback) {
        String message = "Благодарим Вас за отзыв! Ваш отзыв появится на сайте в ближайшее время!";
        feedbackService.saveFeedback(feedback);

        String type = null;

        sendEmailAsync(feedback.getFeedbackName(), feedback.getFeedbackText(), type);

        return showHomePage(map, message);
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/login_failure")
    public String loginFailure(Model model) {
        String message = "Не удалось войти в учетную запись!";
        model.addAttribute("message",message);
        return "login";
    }

    public CompletableFuture<Void> sendEmailAsync(String name, String feedbackOrNumber, String type) {
        return CompletableFuture.runAsync(() -> sendEmail(name, feedbackOrNumber, type));
    }

    @ResponseBody
    @RequestMapping("/sendEmail")
    public void sendEmail(String name, String feedbackOrNumber, String type)  {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(MyConstants.EMAIL_DESTINATION);

        if(type != null) {
            message.setSubject("Заявка на консультацию!");
            message.setText("Вам поступила заявка от клиента.  Имя:  " + name + "  \nНомер телефона:   " + feedbackOrNumber);
        }
        else {
            message.setSubject("Отзыв от клиента!");
            message.setText("Вам поступил отзыв от клиента.  Имя:  " + name + "  \nТекст отзыва:   " + feedbackOrNumber);
        }
        this.emailSender.send(message);
    }
}
