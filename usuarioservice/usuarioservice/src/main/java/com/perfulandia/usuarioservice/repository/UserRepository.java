package com.perfulandia.usuarioservice.repository;

import com.perfulandia.usuarioservice.modelo.User;
import org.springframework.data.jpa.repository.JpaRepository;

//findAll()
//findById(id)
//save()
//Delete()

public interface UserRepository extends JpaRepository<User,Long> {
}
