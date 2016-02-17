package dao.serials;


import dao.parameters.serial.SerialListParameters;
import entities.Serial;

import java.util.List;

public interface ISeasonvarDAO {
	
	Serial createSerial(Serial Customer);
	Serial getSerial(String id);
	Serial updateSerial(Serial Customer);
	boolean removeSerial(String id);
	List<Serial> getSerialList(SerialListParameters parameters);
}
