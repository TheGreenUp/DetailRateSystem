package com.bsuir.green.service;


import com.bsuir.green.common.command.createCommands.AddStuffCommand;
import com.bsuir.green.common.command.DeleteStuffCommand;
import com.bsuir.green.common.command.UpdateStuffCommand;
import com.bsuir.green.common.model.Stuff;
import com.bsuir.green.common.response.*;
import com.bsuir.green.database.dao.StuffDao;
import com.bsuir.green.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.ArrayList;

@Slf4j
public class StuffService {

    private final StuffDao stuffDao;
    private static final StuffService stuffService = new StuffService();

    private StuffService() {
        stuffDao = StuffDao.getInstance();
    }

    public static StuffService getInstance() {
        return stuffService;
    }

    public Response getStuff() throws SQLException {
        ArrayList<Stuff> all = stuffDao.getAll();
        return new StuffListResponse(all);
    }
    public Response getSpecialists() throws SQLException {
        ArrayList<Stuff> all = stuffDao.getSpecialists();
        return new SpecialistListResponse(all);
    }

    public Response createStuff(AddStuffCommand addStuffCommand) throws SQLException {
        stuffDao.createUser(addStuffCommand.getStuff());
        return new StuffCreationResponse();
    }

    public Response updateStuff(UpdateStuffCommand updateStuffCommand) throws SQLException {
        Stuff updatedUser = updateStuffCommand.getStuff();
        Integer id = updatedUser.getId();
        log.info("Update user with values {}", updatedUser);
        try {
            Stuff existing = stuffDao.getById(id);
            existing.setFname(updatedUser.getFname());
            existing.setLname(updatedUser.getLname());
            existing.setEmail(updatedUser.getEmail());
            existing.setPassword(updatedUser.getPassword());
            existing.setRole(updatedUser.getRole());
            stuffDao.updateStuff(existing);
            return new UpdateStuffResponse();
        } catch (UserNotFoundException e) {
            log.error("User not found by id {}", id);
            return new ErrorResponse("Пользователь с id " + id + " не найден ");
        }
    }
    public Response deleteStuff(DeleteStuffCommand deleteStuffCommand) throws SQLException {
        try {
            stuffDao.deleteStuff(deleteStuffCommand.getStuff());
            return new DeleteStuffResponse();
        }
        catch (Exception e) {
            return new ErrorResponse("Не получилось удалить пользователя!");
        }
    }
}
