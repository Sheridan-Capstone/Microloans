package ca.sheridancollege.liuzhun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.liuzhun.beans.Role;

public interface RoleRepository extends JpaRepository <Role, Long> {
	
	public Role findByRolename(String rolename);

}
