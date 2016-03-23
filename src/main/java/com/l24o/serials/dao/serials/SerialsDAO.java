package com.l24o.serials.dao.serials;

import com.l24o.serials.dao.parameters.serial.SerialListParameters;
import com.l24o.serials.entities.Serial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SerialsDAO implements ISerealsDAO {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void createSerial(Serial serial) {
        mongoOperations.save(serial);
    }

    @Override
    public Serial getSerial(String code) {
        return mongoOperations.findOne(Query.query(Criteria.where("code").is(code)), Serial.class);
    }

    @Override
    public void removeSerial(String code) {
        mongoOperations.remove(Query.query(Criteria.where("code").is(code)), Serial.class);
    }

    @Override
    public List<Serial> getSerialList(SerialListParameters parameters) {
        return mongoOperations.findAll(Serial.class);
    }
}
