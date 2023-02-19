package repository.interfaces;

import enteties.Member;

import java.util.List;

public interface IAdminRepository {
    Member getMemberInfo(String iin);
    List<Member> getAllMembers();
    boolean settlement(Member member);
    boolean eviction_by_iin(String iin);
}