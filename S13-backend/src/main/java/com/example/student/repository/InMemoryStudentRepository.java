package com.example.student.repository;

import com.example.student.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryStudentRepository implements StudentRepository {

    private final Map<Long, Student> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public Student save(Student student) {
        if (student.getId() == null) {
            student.setId(idGenerator.incrementAndGet());
        }
        storage.put(student.getId(), student);
        return student;
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}
