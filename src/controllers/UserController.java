package controllers;

import models.User;
import repositories.users.IUserRepository;

import java.util.List;

public class UserController {
    private final IUserRepository userRepo;

    public UserController(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public String create(String name, String surname) {
        User user = new User(name, surname);

        boolean created = userRepo.create(user);

        if (created)
            return "A new user " + name + " " + surname + " was created successfully!";

        return "User cannot be created!";
    }

    public String getById(int id) {
        User user = userRepo.get(id);

        if (user == null)
            return "User with id = " + id + " does not exist!";

        return user.toString();
    }

    public String removeById(int id) {
        User user = userRepo.get(id);

        if (user == null)
            return "User with id = " + id + " does not exist!";

        boolean removed = userRepo.delete(id);

        if (removed)
            return "User " + user.getName() + " " + user.getSurname() + " was removed successfully!";

        return "User cannot be removed!";
    }

    public String getAll() {
        List<User> users = userRepo.getAll();

        if (users.isEmpty())
            return "User`s list is empty";

        StringBuilder response = new StringBuilder();
        for (User user : users)
            response.append(user).append("\n");

        return response.toString();
    }
}
