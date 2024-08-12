package com.woorifisa.backend.ex;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MemberRepository extends JpaRepository<MemberEx, Long> {

    @Modifying
	@Query("update MemberEx m set m.name=:name where m.id=:id")
    public void updateMemberNameJPQL(@Param("id") int id,
								     @Param("name") String name);

    @Modifying
    @Query(value = "UPDATE member_ex SET name = :name WHERE id = :id", nativeQuery = true)
    public void updateMemberNameNative(@Param("id") int id, 
                                @Param("name") String name);


}
