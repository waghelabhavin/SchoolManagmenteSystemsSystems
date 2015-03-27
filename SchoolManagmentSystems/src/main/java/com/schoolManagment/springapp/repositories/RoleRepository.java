package com.schoolManagment.springapp.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolManagment.springapp.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Serializable>
{

}
