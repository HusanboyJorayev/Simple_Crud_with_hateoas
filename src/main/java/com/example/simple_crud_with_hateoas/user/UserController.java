package com.example.simple_crud_with_hateoas.user;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user/")
public class UserController implements UserService<Integer, UserDto> {
    private final USerServiceImpl uSerServiceImpl;

    @Override
    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody UserDto dto) {
        return this.uSerServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get/{id}")
    public ResponseEntity<EntityModel<UserDto>> get(@PathVariable("id") Integer id) {
        return this.uSerServiceImpl.get(id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable("id") Integer id) {
        return this.uSerServiceImpl.delete(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto dto, @PathVariable("id") Integer id) {
        return this.uSerServiceImpl.update(dto, id);
    }

    @Override
    @GetMapping("/getAll")
    public ResponseEntity<CollectionModel<EntityModel<UserDto>>> getAll() {
        return this.uSerServiceImpl.getAll();
    }
}
