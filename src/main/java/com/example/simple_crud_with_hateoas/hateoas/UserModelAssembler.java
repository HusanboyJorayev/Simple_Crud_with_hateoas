package com.example.simple_crud_with_hateoas.hateoas;

import com.example.simple_crud_with_hateoas.user.UserController;
import com.example.simple_crud_with_hateoas.user.UserDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<UserDto, EntityModel<UserDto>> {

    @Override
    public EntityModel<UserDto> toModel(UserDto user) {
        Link selfRel = linkTo(methodOn(UserController.class).get(user.getId())).withSelfRel();
        Link relation = linkTo(methodOn(UserController.class).getAll()).withRel("users");
        return EntityModel.of(user, selfRel, relation);
    }

    @Override
    public CollectionModel<EntityModel<UserDto>> toCollectionModel(Iterable<? extends UserDto> entities) {
        List<EntityModel<UserDto>> modelList = new ArrayList<>();
        entities.forEach(e -> modelList.add(toModel(e)));
        Link relation = linkTo(methodOn(UserController.class).getAll()).withRel("users");
        return CollectionModel.of(modelList, relation);
    }
}
