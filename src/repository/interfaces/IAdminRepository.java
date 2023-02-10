package repository.interfaces;

import enteties.Member;

import java.util.List;

public interface IAdminRepository {
    boolean createMember(Member person);
    Member getMemberInfo(String iin);
    List<Member> getAllMembers();
}
