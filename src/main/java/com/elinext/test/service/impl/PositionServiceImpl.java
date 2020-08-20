package com.elinext.test.service.impl;

import com.elinext.test.domain.Position;
import com.elinext.test.exception.EntityNotFoundException;
import com.elinext.test.repository.PositionRepository;
import com.elinext.test.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    @Transactional
    @Override
    public Position save(Position position) {
        return positionRepository.save(position);
    }

    @Transactional
    @Override
    public Position update(Position position) {
        return positionRepository.saveAndFlush(position);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if(positionRepository.findById(id).isPresent()) {
            positionRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(Position.class, id);
        }
    }

    @Override
    public Position findById(Long id) {
        Optional<Position> result = positionRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(Position.class, id);
        }
    }

    @Override
    public Position findByName(String name) {
        return positionRepository.findByName(name);
    }

    @Override
    public List<String> findAllPositionsQuery() {
        return positionRepository.findAllPositionsQuery();
    }
}
