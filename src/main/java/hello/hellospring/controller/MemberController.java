package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class MemberController {

  //  @Autowired private MemberService memberService; // 필드주입 - 권장되지 않음

    /*
    private MemberService memberService;

    @Autowired // 세터주입(세터 인젝션) - 퍼블릭으로 만들어야하는데 이게 단점
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    */

    // * 생성자 주입 - 프로젝트가 올라올때 최초로 등록되고 건드리지 않는게 제일 좋기때문에 권장
    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
