package <%=package%>.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import <%=package%>.domain.OperationLog;

public interface OperationLogRepository extends JpaRepository<OperationLog, String> {

}
