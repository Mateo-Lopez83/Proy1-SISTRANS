package uniandes.edu.co.proyecto.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.bson.Document;
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
    
    public void eliminarBodegaSucursal(int idSucursal, int idBodega) {
        Query query = new Query(Criteria.where("_id").is(idSucursal));
        Update update = new Update().pull("BODEGA", idBodega);
        mongoTemplate.updateFirst(query, update, Sucursal.class);
    }

    public List<Document> obtenerInfoProductosSucursal(int idsucursal) {
        List<Document> pipeline = List.of(
            // $match stage
            new Document("$match", new Document("_id", idsucursal)),
    
            // $unwind stage for "INVENTARIOS"
            new Document("$unwind", 
                new Document("path", "$INVENTARIOS")
                    .append("includeArrayIndex", "indice")
                    .append("preserveNullAndEmptyArrays", true)
            ),
    
            // $lookup stage
            new Document("$lookup", 
                new Document("from", "PRODUCTOS")
                    .append("localField", "INVENTARIOS.CODIDIGOBARRAS")
                    .append("foreignField", "CODBARRAS")
                    .append("as", "producto")
            ),
    
            // $unwind stage for "producto"
            new Document("$unwind", 
                new Document("path", "$producto")
                    .append("preserveNullAndEmptyArrays", true)
            ),
    
            // $group stage
            new Document("$group", 
                new Document("_id", 
                    new Document("CODIDIGOBARRAS", "$INVENTARIOS.CODIDIGOBARRAS")
                        .append("IDBODEGA", "$INVENTARIOS.IDBODEGA")
                )
                .append("nombre_producto", new Document("$first", "$producto.NOMBRE"))
                .append("totalCantidadOcupada", new Document("$sum", "$INVENTARIOS.CANTIDAD_OCUPADA"))
                .append("avgCostoGrupoProducto", new Document("$avg", "$INVENTARIOS.COSTO_GRUPO_PRODUCTO"))
                .append("minimoRecompra", new Document("$first", "$INVENTARIOS.MINIMO_RECOMPRA"))
            ),
    
            // $project stage
            new Document("$project", 
                new Document("IDBODEGA", 1)
                    .append("totalCantidadOcupada", 1)
                    .append("nombre_producto", 1)
                    .append("avgCostoGrupoProducto", 1)
                    .append("minimoRecompra", 1)
            )
        );
    
        return mongoTemplate.getCollection("SUCURSALES").aggregate(pipeline).into(new java.util.ArrayList<>());
    }
    
}