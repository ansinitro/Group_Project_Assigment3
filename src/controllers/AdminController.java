package controllers;

import enteties.Member;
import repository.interfaces.IAdminRepository;

import java.util.List;

public class AdminController {
    private final IAdminRepository repository;

    public AdminController(IAdminRepository repository) {
        this.repository = repository;
    }

    public String createMember(String name, String surname, String phone_number, String iin){
        Member person = new Member(name, surname, phone_number, iin);
        boolean created = repository.createMember(person);

        return (created ? "Member was created" : "Member creation wa failed");
    }

    public String getMemberInfo(String iin){
        Member member = repository.getMemberInfo(iin);

        return(member == null ? "Member not found" : member.toString());
    }

    public List<Member> getAllMembers() {
        List<Member> members = repository.getAllMembers();
        return members;
    }
}
