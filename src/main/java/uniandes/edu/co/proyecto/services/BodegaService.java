package uniandes.edu.co.proyecto.services;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.repositories.BodegaRepository;
import uniandes.edu.co.proyecto.repositories.BodegaRepository.respuestaDocumento;

@Service
public class BodegaService {
    private BodegaRepository bodegaRepository;

    public BodegaService(BodegaRepository bodegaRepository) {
        this.bodegaRepository = bodegaRepository;
    }
    /*
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public Collection<respuestaDocumento> getDocumentoIngresoS(long id, long idsucursal) throws Exception {
        try {
            Collection<respuestaDocumento> respuesta= bodegaRepository.consultarInfoBodega(id,idsucursal);
            Thread.sleep(30000);
            return respuesta;
            
        } catch (InterruptedException e) {
            throw new Exception("Error en la espera", e);
        }
        catch (Exception ex) {
            throw new Exception("Error en la consulta. Se hizo Rollback", ex);
        }
    }
    
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Collection<respuestaDocumento> getDocumentoIngresoRC(long id, long idsucursal) throws Exception {
        try {
            Thread.sleep(30000);
            return bodegaRepository.consultarInfoBodega(id,idsucursal);
        } catch (InterruptedException e) {
            throw new Exception("Error en la espera", e);
        }
        catch (Exception ex) {
            throw new Exception("Error en la consulta. Se hizo Rollback", ex);
        }
    }
    */
    
    
}
