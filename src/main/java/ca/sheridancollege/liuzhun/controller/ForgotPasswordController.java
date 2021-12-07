package ca.sheridancollege.liuzhun.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.liuzhun.beans.Donor;
import ca.sheridancollege.liuzhun.beans.Student;
import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {
        
    @Autowired
	private JavaMailSender mailSender;
     
    @Autowired
    private ca.sheridancollege.liuzhun.security.DonorDetailsServiceImpl donorService;
    
    @Autowired
    private ca.sheridancollege.liuzhun.security.StudentDetailsServiceImpl studentService;
     
    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
    	return "forgot_password_form"; 
    }
 
    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model){
        String email = request.getParameter("email");
        String token = RandomString.make(30);        
       
    
        
        try {
        	donorService.updateResetPasswordToken(token, email);        	
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
             
        } catch (AccountNotFoundException ex) {
        	   try {        	
               	studentService.updateResetPasswordToken1(token, email);        	
                   String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
                   sendEmail(email, resetPasswordLink);
                   model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
                    
               } catch (AccountNotFoundException ex1) {
                   model.addAttribute("error", ex1.getMessage());
               } catch (UnsupportedEncodingException | MessagingException e) {
                   model.addAttribute("error", "Error while sending email");
               } 
               
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }
        
     
    
        return "forgot_password_form";
    }
     
    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();              
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setFrom("microloans2021test@gmail.com", "Support");
        helper.setTo(recipientEmail);
         
        String subject = "Here's the link to reset your password";
         
        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";
         
        helper.setSubject(subject);
         
        helper.setText(content, true);
         
        mailSender.send(message);
    }	
     
     
	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {		
		
		Donor donor = donorService.getByResetPasswordToken(token);
	    model.addAttribute("token", token);
	     
	    if (donor == null) {
	    	Student student = studentService.getByResetPasswordToken(token);
	    	if (student == null) {
	        model.addAttribute("message", "Invalid Token");
	        return "reset_password_form";
		}
	    }	    
	     
	    return "reset_password_form";
	}
     
    
    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
         
        Donor donor = donorService.getByResetPasswordToken(token);
        
         
        if (donor == null) {
        	Student student = studentService.getByResetPasswordToken(token);;
        	 if (student == null) {
                 model.addAttribute("message", "Invalid Token");
                 return "reset_password_form";
             } else {           
                studentService.updatePassword(student, password);
                  
                 model.addAttribute("message", "You have successfully changed your password.");
             }            
            return "login";
        } else {           
            donorService.updatePassword(donor, password);
             
            model.addAttribute("message", "You have successfully changed your password.");
        }
         
        return "login";
    }
}