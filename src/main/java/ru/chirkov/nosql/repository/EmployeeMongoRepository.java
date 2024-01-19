package ru.chirkov.nosql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.chirkov.nosql.entity.Employee;

public interface EmployeeMongoRepository extends MongoRepository<Employee, Integer> {

}
