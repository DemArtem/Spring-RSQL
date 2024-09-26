package org.itstep.controller;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.itstep.repository.UserRepository;
import org.itstep.rsql.CustomRsqlVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import org.itstep.entity.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping("/")
    Iterable<User> all() {
        return repository.findAll();
    }
    //http://localhost:8080/user

    @GetMapping("/filter")
    public List<User> filter(@RequestParam(value="search") String search){
        Node rootNode = new RSQLParser().parse(search);
        Specification<User> spec = rootNode.accept(new CustomRsqlVisitor<User>());
        return repository.findAll(spec);
    }
    //http://localhost:8080/user/filter?search=id==2
}