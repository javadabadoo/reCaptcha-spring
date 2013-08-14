package doo.daba.java.pruebas.controller;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Gerardo Aquino
 * Date: 14/08/13
 */
@Controller
public class CaptchaController {


    @Value("${key.private}")
    private String reCaptcha_privateKey;
    @Value("${key.public}")
    private String reCaptcha_publicKey;

    @ResponseBody
    @RequestMapping(value = "/captcha/render", produces = "text/html")
    public String generate() {

        ReCaptcha captcha = ReCaptchaFactory.newReCaptcha(
                this.reCaptcha_publicKey,
                this.reCaptcha_privateKey,
                false
        );

        return captcha.createRecaptchaHtml(null, null);

    }


    @ResponseBody
    @RequestMapping(value="captcha/verify", method=RequestMethod.POST)
    public boolean checkCaptcha(
            @RequestParam(value = "recaptcha_challenge_field") String captchaChallenge,
            @RequestParam(value = "recaptcha_response_field") String captchaResponse,
            HttpServletRequest request
    ) {
        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
        reCaptcha.setPrivateKey(this.reCaptcha_privateKey);
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(
                request.getRemoteAddr(),
                captchaChallenge,
                captchaResponse);

        return reCaptchaResponse.isValid();

    }

}
