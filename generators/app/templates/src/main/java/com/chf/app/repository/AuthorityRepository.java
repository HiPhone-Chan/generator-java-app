package <%=package%>.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import <%=package%>.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
