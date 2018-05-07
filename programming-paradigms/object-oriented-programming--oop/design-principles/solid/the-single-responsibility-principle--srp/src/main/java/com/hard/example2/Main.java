package com.hard.example2;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

        User user = userRepository.getById(1);
    }
}

/**
 * Bad example
 */

//class User {
//    private String name;
//
//    public User getById(int id) {
//        return null;
//    }
//
//    public void save() {
//        System.out.println("save user");
//    }
//}

/**
 * Good example
 */

class User {
    private String name;
}

class UserRepository {
    public User getById(int id) {
        return null;
    }

    public void save(User user) {
        System.out.println("save user");
    }
}
