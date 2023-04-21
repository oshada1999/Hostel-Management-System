package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dao.SupperDAO;
import lk.ijse.hostel.entity.CustomEntity;

import java.util.List;

public interface QueryDAO extends SupperDAO {
    public List<CustomEntity> getLessPaidList();

    List<CustomEntity> getRoomReserveList();
}
