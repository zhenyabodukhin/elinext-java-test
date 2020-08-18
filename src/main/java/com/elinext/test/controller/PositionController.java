package com.elinext.test.controller;

import com.elinext.test.domain.Position;
import com.elinext.test.request.PositionRequest;
import com.elinext.test.service.impl.PositionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/position")
@RequiredArgsConstructor
public class PositionController {

    private final PositionServiceImpl positionService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Position>> getPositions() {
        return new ResponseEntity<>(positionService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Position> createPosition(@RequestBody @Valid PositionRequest request) {
        Position position = new Position();

        position.setPositionName(request.getPositionName());

        return new ResponseEntity<>(positionService.save(position), HttpStatus.CREATED);
    }
}
