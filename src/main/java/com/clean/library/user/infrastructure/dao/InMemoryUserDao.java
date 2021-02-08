package com.clean.library.user.infrastructure.dao;

import com.clean.library.user.domain.User;
import com.clean.library.user.domain.UserDao;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class InMemoryUserDao implements UserDao {
    private final static String USER_FILE_NAME = "users.txt";
    private final static String ENCODING = "utf-8";
    private final static String SEPARATOR = ",";
    private final static String DEFAULT_ROLE = "guest";
    private String userLogin;

    public InMemoryUserDao(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean isUserExist() {
        Path path = Paths.get(USER_FILE_NAME);
        return Files.exists(path) && isUserExistInFile();
    }

    @Override
    public User loadUser() {
        User user = new User();
        try{
            Optional<String> userLine = findUserLine();
            user = createUser(userLine);
        } catch (IOException e){
            e.printStackTrace();
        }
        return user;
    }

    private User createUser(Optional<String> userLine) {
        User user;
        if(userLine.isEmpty()){
            return new User(userLogin, DEFAULT_ROLE);
        }
        String[] userData = userLine.get().split(SEPARATOR);
        var userLogin = userData[0];
        var userRole = userData[1];
        user = new User(userLogin, userRole);
        return user;
    }

    private boolean isUserExistInFile() {
        try{
            Optional<String> currentUserLine = findUserLine();
            return currentUserLine.isPresent();
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    private Optional<String> findUserLine() throws IOException {
        List<String> fileLines = FileUtils.readLines(new File(USER_FILE_NAME), String.valueOf(StandardCharsets.UTF_8));
        return fileLines.stream().filter(line -> line.contains(userLogin)).findFirst();
    }


}
