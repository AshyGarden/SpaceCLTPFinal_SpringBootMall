package store.spacecl.mall.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import store.spacecl.mall.entity.user.Member;
import store.spacecl.mall.forms.MemberForm;
import store.spacecl.mall.service.MemberService;
import store.spacecl.mall.service.UserSecurityService;
import store.spacecl.mall.utils.global.ErrorCode;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final UserSecurityService userSecurityService;

    @GetMapping("/signup") //페이지 진입
    public String signup(MemberForm memberForm) {
        return "signup";
    }

    @PostMapping("/signup") //회원가입 버튼 누를시 작동
    public String signup(@Valid MemberForm memberForm, //@Vaild = 유효성검사
                         BindingResult bindingResult) { //bindingResult = 검사가 끝난 결과를 지님

        System.out.println(memberForm);

        //유효성 검사에 문제가 있을 때
        if(bindingResult.hasErrors()) {
            return "signup";
        }

        //비밀번호 일치하지 않을 때
        if(!memberForm.getPassword1().equals(memberForm.getPassword2())) {
            bindingResult.rejectValue("password1",
                    ErrorCode.INVALID_PASSWORD.getCode(),
                    ErrorCode.INVALID_PASSWORD.getMessage());
            return "signup";
        }

        //userName중복체크
        if(memberService.create(memberForm)==null) {
            bindingResult.rejectValue("password1",
                    ErrorCode.ALREADY_EXIST_UNSERNAME.getCode(),
                    ErrorCode.ALREADY_EXIST_UNSERNAME.getMessage());
            return "signup";
        }

        return "redirect:/"; //home으로 재연결
    }

    @GetMapping("/")
    public String gotoIndex(){
        return "index";
    }

    @GetMapping("/login") //login화면으로 보내는 함수
    public String login() {
        return "user/login";
    }

    //login의 post방식의 경우 SecurityConfig에서 이미 선언해둠!
    //해당 로직을 처리해놓았다면 login post방식을 선언해주지않아도된다!

    @GetMapping("/test") //현재 로그인한 유저의 정보를 확인(Principal)
    public String test(Principal principal) {
        System.out.println(principal.getName());
        Member member = userSecurityService.getUser(principal.getName());
        System.out.println(member);
        return "redirect:/";
    }

}
