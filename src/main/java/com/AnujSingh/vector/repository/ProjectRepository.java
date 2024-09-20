package com.AnujSingh.vector.repository;

import com.AnujSingh.vector.model.Project;
import com.AnujSingh.vector.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long>
{
//    List<Project> findByOwner(User user);

    List<Project> findByNameContainingAndTeamContains(String partialName, User team);

//    @Query("SELECT p from Project p join p.team t where t=:user")
//    List<Project> findProjectByTeam(@Param("User ") User user);

    List<Project> findByTeamContainingOrOwner(User user,User owner);
}
