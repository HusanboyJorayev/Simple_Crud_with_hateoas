package com.example.simple_crud_with_hateoas.user;

import com.example.simple_crud_with_hateoas.hateoas.UserModelAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class USerServiceImpl implements UserService<Integer, UserDto> {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserModelAssembler userModelAssembler;

    @Override
    public ResponseEntity<UserDto> create(UserDto dto) {
        var user = this.userMapper.toEntity(dto);
        user.setCreatedAt(LocalDateTime.now());
        this.userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.userMapper.toDto(user));
    }

    @Override
    public ResponseEntity<EntityModel<UserDto>> get(Integer id) {
        return this.userRepository.findUserById(id)
                .map(user -> ResponseEntity.status(HttpStatus.OK)
                        .body(this.userModelAssembler.toModel(this.userMapper.toDto(user))))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @Override
    public ResponseEntity<UserDto> delete(Integer id) {
        return this.userRepository.findUserById(id)
                .map(user -> {
                    user.setDeletedAt(LocalDateTime.now());
                    this.userRepository.delete(user);
                    return ResponseEntity.status(HttpStatus.OK).body(this.userMapper.toDto(user));
                })
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @Override
    public ResponseEntity<UserDto> update(UserDto dto, Integer id) {
        return this.userRepository.findUserById(id)
                .map(user -> {
                    user.setUpdatedAt(LocalDateTime.now());
                    this.userMapper.update(user, dto);
                    var updateUser = this.userRepository.save(user);
                    return ResponseEntity.status(HttpStatus.OK).body(this.userMapper.toDto(updateUser));
                })
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @Override
    public ResponseEntity<CollectionModel<EntityModel<UserDto>>> getAll() {
        List<User> allUsers = this.userRepository.findAllUsers();
        if (allUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.userModelAssembler.toCollectionModel(allUsers.stream()
                        .map(this.userMapper::toDto).toList()));
    }
}
