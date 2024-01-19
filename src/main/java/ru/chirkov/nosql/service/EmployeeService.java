package ru.chirkov.nosql.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import ru.chirkov.nosql.entity.DatabaseSequence;
import ru.chirkov.nosql.entity.Employee;
import ru.chirkov.nosql.repository.EmployeeMongoRepository;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeMongoRepository mongoRepository;
    private final MongoOperations mongoOperations;

    public Employee save(Employee employee){
        employee.setId(generateSequence());
        return mongoRepository.save(employee);
    }

    @CachePut(value = "employee", key ="#employee.getId()")
    public Employee update(Employee employee){
        return mongoRepository.save(employee);
    }

    @Cacheable(value = "employee", key ="#id")
    public Employee findById(Integer id){
        return mongoRepository.findById(id).orElse(null);
    }

    public List<Employee> findAll(){
        return mongoRepository.findAll();
    }

    @CacheEvict(cacheNames = "employee", key = "#id")
    public void delete(Integer id){
        mongoRepository.deleteById(id);
    }

    private int generateSequence() {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(Employee.SEQUENCE_NAME)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

}
