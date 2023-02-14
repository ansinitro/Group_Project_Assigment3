package controllers;

import enteties.Member;
import repository.interfaces.IAdminRepository;

import java.util.List;

public class AdminController {
    private final IAdminRepository repository;

    public AdminController(IAdminRepository repository) {
        this.repository = repository;
    }

    public boolean settlement(String name, String surname, String phone_number, String iin, int apartment, int room){
        Member member = new Member(name, surname, phone_number, iin, apartment, room);
        boolean created = repository.settlement(member);

        return created;
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
