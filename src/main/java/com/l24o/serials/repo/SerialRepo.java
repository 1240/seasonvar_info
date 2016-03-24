package com.l24o.serials.repo;

import com.l24o.serials.entities.Serial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by l24o on 23.03.16.
 */
public interface SerialRepo extends CrudRepository<Serial, String> {

    @Query("{code : {$regex : '^?0', $options: 'i'}}")
    public Iterable<Serial> searchByCode(String code);

    @Query("{name :  {$regex : '^?0', $options: 'i'}}")
    public Iterable<Serial> searchByName(String name);

    @Query("{name :  {$regex : '^?0', $options: 'i'}}")
    public Page<Serial> searchByName(String name, Pageable pageable);

}
