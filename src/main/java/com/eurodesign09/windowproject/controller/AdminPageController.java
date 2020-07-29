package com.eurodesign09.windowproject.controller;

import com.eurodesign09.windowproject.entity.CallMeForm;
import com.eurodesign09.windowproject.entity.Feedback;
import com.eurodesign09.windowproject.entity.FeedbackApproved;
import com.eurodesign09.windowproject.entity.WorkDone;
import com.eurodesign09.windowproject.service.CallMeFormService;
import com.eurodesign09.windowproject.service.FeedbackApprovedService;
import com.eurodesign09.windowproject.service.FeedbackService;
import com.eurodesign09.windowproject.service.WorkDoneService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class AdminPageController {

    @Autowired
    private FeedbackApprovedService feedbackApprovedService;
    @Autowired
    private WorkDoneService workDoneService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private CallMeFormService callMeFormService;

    @RequestMapping(value = "/admin/", method = RequestMethod.GET)
    public String showAdminPage(ModelMap map, String message) {
        List<Feedback> listFeedback = feedbackService.listAllFeedback();
        List<FeedbackApproved> listApprovedFeedback = feedbackApprovedService.listAllApprovedFeedback();
        List<WorkDone> listWorkDone = workDoneService.listAllWork();
        List<CallMeForm> listCallMeForm = callMeFormService.listAllCallMeForms();
        WorkDone workDone = new WorkDone();

        if(message != null){
            map.addAttribute("message",message);
        }

        map.addAttribute("listFeedback", listFeedback);
        map.addAttribute("listApprovedFeedback", listApprovedFeedback);
        map.addAttribute("listWorkDone", listWorkDone);
        map.addAttribute("workDone", workDone);
        map.addAttribute("listCallMeForm", listCallMeForm);
        return "admin/index";
    }

    public String showAdminPage(ModelMap map) {
        return showAdminPage(map, null);
    }

    @RequestMapping(value = "/admin/save_workDone")
    public String saveWorkDoneImage(ModelMap map, @RequestParam("imageFile") MultipartFile multipartFile, @ModelAttribute("workDone") WorkDone workDone) throws IOException {
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String message;

        if(extension.equals("jpg") || extension.equals("jpeg")) {
            byte[] imageBytes = multipartFile.getBytes();
            workDone.setWorkImage(imageBytes);
            workDoneService.saveWork(workDone);
            return "redirect:/admin/";
        }
        else {
            message = "Недопустимый формат фотографии! Возможна загрузка только форматов: jpg и jpeg";
            return showAdminPage(map, message);
        }
    }

    @RequestMapping(value = "/admin/delete_workDone/{id}")
    public String deleteWorkDoneImage (@PathVariable(name = "id") Integer imageId) {
        workDoneService.deleteWork(imageId);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/admin/edit_callMeForm/{id}")
    public ModelAndView showEditCallMeForm (@PathVariable(name="id") Integer client_id) {
        ModelAndView mav = new ModelAndView("admin/edit_callMeForm");
        CallMeForm callMeForm = callMeFormService.getCallMeForm(client_id);
        mav.addObject("callMeForm", callMeForm);
        return mav;
    }

    @RequestMapping(value = "/admin/delete_callMeForm/{id}")
    public String deleteCallMeForm (@PathVariable(name = "id") Integer client_id) {
        callMeFormService.deleteCallMeForm(client_id);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/admin/save_feedback", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8") //, produces = "text/plain;charset=UTF-8"
    public String saveFeedback(ModelMap map, @ModelAttribute("feedback") Feedback feedback) {
        feedbackService.saveFeedback(feedback);

        return "redirect:/admin/";
    }

    @RequestMapping(value = "/admin/publish_approvedFeedback/{id}")
    public String publishApprovedFeedback (@PathVariable(name="id") Integer feedbackAppId) {
        Feedback feedback = feedbackService.getFeedback(feedbackAppId);
        FeedbackApproved approvedFeedback = new FeedbackApproved();
        approvedFeedback.setFeedbackAppName(feedback.getFeedbackName());
        approvedFeedback.setFeedbackAppText(feedback.getFeedbackText());
        approvedFeedback.setFeedbackAppDate(new Date());

        feedbackApprovedService.saveApprovedFeedback(approvedFeedback);

        feedbackService.deleteFeedback(feedbackAppId);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/admin/edit_approvedFeedback/{id}")
    public ModelAndView showEditApprovedFeedbackForm (@PathVariable(name="id") Integer feedbackAppId) {
        ModelAndView mav = new ModelAndView("admin/edit_feedback");

        String message = "Редактирование опубликованного отзыва";

        FeedbackApproved feedbackApproved = feedbackApprovedService.getApprovedFeedback(feedbackAppId);
        mav.addObject("feedbackApproved", feedbackApproved);
        mav.addObject("message", message);
        return mav;
    }

    @RequestMapping(value = "/admin/save_approvedFeedback", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8") //, produces = "text/plain;charset=UTF-8"
    public String saveApprovedFeedback(ModelMap map, @ModelAttribute("feedbackApproved") FeedbackApproved feedbackApproved) {
        feedbackApproved.setFeedbackAppDate(new Date());
        feedbackApprovedService.saveApprovedFeedback(feedbackApproved);
        return showAdminPage(map);
    }

    @RequestMapping(value = "/admin/delete_approvedFeedback/{id}")
    public String removeApprovedFeedbackFromWebsite (@PathVariable(name = "id") Integer feedbackAppId) {
        feedbackApprovedService.deleteApprovedFeedback(feedbackAppId);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/admin/edit/{id}")
    public ModelAndView showEditFeedbackForm (@PathVariable(name="id") Integer feedback_id) {
        ModelAndView mav = new ModelAndView("admin/edit_feedback");
        Feedback feedback = feedbackService.getFeedback(feedback_id);
        mav.addObject("feedback", feedback);
        return mav;
    }

    @RequestMapping(value = "/admin/delete/{id}")
    public String deleteFeedback (@PathVariable(name = "id") Integer feedback_id) {
        feedbackService.deleteFeedback(feedback_id);
        return "redirect:/admin/";
    }

    @GetMapping("/admin/403")
    public String error403(){
        return "/admin/403";
    }

}
