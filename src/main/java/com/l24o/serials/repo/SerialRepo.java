package com.l24o.serials.repo;

import com.l24o.serials.entities.Serial;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by l24o on 23.03.16.
 */
public interface SerialRepo extends CrudRepository<Serial, String> {

    @Query("{'name' : 'Игра престолов / Game of Throne'}")
    public Iterable<Serial> searchByCode(String name);

}
