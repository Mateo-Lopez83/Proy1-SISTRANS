package uniandes.edu.co.proyecto.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import uniandes.edu.co.proyecto.modelo.Sucursal;

@Repository
public class SucursalRepositoryCustom {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertarBodegaEnSucursal(int idSucursal, int idBodega) {
        Query query = new Query(Criteria.where("_id").is(idSucursal));
        Update update = new Update().push("BODEGA", idBodega);
        mongoTemplate.updateFirst(query, update, Sucursal.class);
    }
}