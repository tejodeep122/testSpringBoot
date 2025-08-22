
package com.tejo.appdemo.Service;

import com.tejo.appdemo.entity.TejoEntity;
import com.tejo.appdemo.repository.TejoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TejoServiceImpl implements TejoService {

    @Autowired
    private TejoRepo tejoRepo;

    @Override
    public TejoEntity saveTejo(TejoEntity tejo) {
        return tejoRepo.save(tejo);
    }

    @Override
    public List<TejoEntity> getAllTejos() {
        return tejoRepo.findAll();
    }

    @Override
    public Optional<TejoEntity> getTejoById(Integer id) {
        return tejoRepo.findById(id);
    }

    @Override
    public TejoEntity updateTejo(Integer id, TejoEntity tejo) {
        tejoRepo.findById(id).orElseThrow();
        tejo.setId(id);
        return tejoRepo.save(tejo);
    }

    @Override
    public void deleteTejo(Integer id) {
        tejoRepo.deleteById(id);
    }
}
