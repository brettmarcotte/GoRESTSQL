package com.careerdevs.gorestsqlv3.Repos;


import com.careerdevs.gorestsqlv3.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {


}