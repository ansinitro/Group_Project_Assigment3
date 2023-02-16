package controllers;

import enteties.Member;
import repository.interfaces.IAdminRepository;

import java.util.List;

public class AdminController {
    private final IAdminRepository repository;

    public AdminController(IAdminRepository repository) {
        this.repository = repository;
    }

    public String getMemberInfo(String iin){
        Member member = repository.getMemberInfo(iin);

        return(member == null ? "Member not found" : member.toString());
    }

    public List<Member> getAllMembers() {
        return repository.getAllMembers();
    }

    public boolean settlement(String name, String surname, String phone_number, String iin, int apartment, int room){
        Member member = new Member(name, surname, phone_number, iin, apartment, room);
        return repository.settlement(member);
    }

    public boolean eviction_by_iin(){
        return true;
    }
}
