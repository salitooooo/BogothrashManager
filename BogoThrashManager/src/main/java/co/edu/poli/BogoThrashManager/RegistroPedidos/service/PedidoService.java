package co.edu.poli.BogoThrashManager.RegistroPedidos.service;

import co.edu.poli.BogoThrashManager.Notificaciones.modelo.Notificacion;
import co.edu.poli.BogoThrashManager.RegistroInventario.modelo.Producto;
import co.edu.poli.BogoThrashManager.RegistroInventario.service.ProductoService;
import co.edu.poli.BogoThrashManager.RegistroPedidos.dto.PedidoInsertDto;
import co.edu.poli.BogoThrashManager.RegistroPedidos.modelo.DetallePedido;
import co.edu.poli.BogoThrashManager.RegistroPedidos.modelo.Pedido;
import co.edu.poli.BogoThrashManager.RegistroPedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private RestTemplate restTemplate;

    public Pedido createPedido(PedidoInsertDto dto) throws Exception {
        // Create a new Pedido entity
        Pedido pedidoEntity = new Pedido();
        
        // Map fields from DTO to entity
        pedidoEntity.setCliente(dto.getCliente());
        pedidoEntity.setCorreo(dto.getCorreo());
        pedidoEntity.setBarista(dto.getBarista());
        pedidoEntity.setFormaDePago(dto.getFormaDePago());
        
        // Set default values for fields not in DTO
        pedidoEntity.setCupon(false);  // Default to false; you can change this if needed
        pedidoEntity.setFechaCompra(new Date().toString());  // Set to current date as string
        
        if (dto.getDetalle() != null) {
            DetallePedido detalleEntity = new DetallePedido();  // Create a new DetallePedido entity
           Double precioTotal = 0.0;
            
            if (dto.getDetalle().getProductos() != null) {
                List<Long> productIds = new ArrayList<>();  // List to hold product IDs
                for (Producto incomingProducto : dto.getDetalle().getProductos()) {
                	Long cantidad = incomingProducto.getCantidad()-1;
                    incomingProducto.setCantidad(cantidad);
                    Producto existingOrNew = productoService.findOrCreateProducto(incomingProducto);
                    if(existingOrNew.getCantidad()<= 5){
                    	Notificacion n = new Notificacion();
                    	productoService.alertarCantidad(n, existingOrNew);
                    }
                    precioTotal = precioTotal + existingOrNew.getPrecio();
                    productIds.add(existingOrNew.getIdProducto());
                    
                }
                Double iva = precioTotal * 0.8;
                //TODO implement if statement for college discount
                precioTotal = precioTotal + iva;
                detalleEntity.setProductos(productIds);
                detalleEntity.setPrecioTotal(precioTotal);// Set the list of product IDs
            }
            
            pedidoEntity.setDetalle(detalleEntity);  // Set the detalle in the Pedido entity
        }
        
        // Save the Pedido entity (this will also save the DetallePedido due to CascadeType.ALL)
        return pedidoRepository.save(pedidoEntity);
    }
    public Pedido createPedido(PedidoInsertDto dto, String u) throws Exception {
        // Create a new Pedido entity
        Pedido pedidoEntity = new Pedido();
        
        // Map fields from DTO to entity
        pedidoEntity.setCliente(dto.getCliente());
        pedidoEntity.setCorreo(dto.getCorreo());
        pedidoEntity.setBarista(dto.getBarista());
        pedidoEntity.setFormaDePago(dto.getFormaDePago());
        
        // Set default values for fields not in DTO
        pedidoEntity.setCupon(false);  // Default to false; you can change this if needed
        pedidoEntity.setFechaCompra(new Date().toString());  // Set to current date as string
        
        if (dto.getDetalle() != null) {
            DetallePedido detalleEntity = new DetallePedido();  // Create a new DetallePedido entity
           Double precioTotal = 0.0;
            
            if (dto.getDetalle().getProductos() != null) {
                List<Long> productIds = new ArrayList<>();  // List to hold product IDs
                for (Producto incomingProducto : dto.getDetalle().getProductos()) {
                	Long cantidad = incomingProducto.getCantidad()-1;
                    incomingProducto.setCantidad(cantidad);
                    Producto existingOrNew = productoService.findOrCreateProducto(incomingProducto);
                    if(existingOrNew.getCantidad()<= 5){
                    	Notificacion n = new Notificacion();
                    	productoService.alertarCantidad(n, existingOrNew);
                    }
                    precioTotal = precioTotal + existingOrNew.getPrecio();
                    productIds.add(existingOrNew.getIdProducto());
                    
                }
                Double iva = precioTotal * 0.8;
                //TODO implement if statement for college discount
                if(verificarConvenioUniversidad(u)) {
                	System.out.println("yo phone lingin");
                	precioTotal = (precioTotal + iva) * 0.8;
                	pedidoEntity.setCupon(true);
                }else precioTotal = precioTotal + iva;
                
                detalleEntity.setProductos(productIds);
                detalleEntity.setPrecioTotal(precioTotal);// Set the list of product IDs
            }
            
            pedidoEntity.setDetalle(detalleEntity);  // Set the detalle in the Pedido entity
        }
        
        // Save the Pedido entity (this will also save the DetallePedido due to CascadeType.ALL)
        return pedidoRepository.save(pedidoEntity);
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> updatePedido(Long id, Pedido updatedPedido) {
        return pedidoRepository.findById(id).map(pedido -> {
            if (updatedPedido.getDetalle() != null) {
                if (pedido.getDetalle() == null) {
                    pedido.setDetalle(updatedPedido.getDetalle());
                } else {
                    // Update nested fields inside detalle
                    pedido.getDetalle().setPrecioTotal(
                        updatedPedido.getDetalle().getPrecioTotal() != null ? 
                        updatedPedido.getDetalle().getPrecioTotal() : 
                        pedido.getDetalle().getPrecioTotal()
                    );

                    if (updatedPedido.getDetalle().getProductos() != null) {
                        List<Long> existingProductos = pedido.getDetalle().getProductos();
                        List<Long> updatedProductos = updatedPedido.getDetalle().getProductos();
                        existingProductos.clear();  // Remove all old elements
                        existingProductos.addAll(updatedProductos);  // Add new elements
                    }
                }
            }

            pedido.setCliente(updatedPedido.getCliente() != null ? updatedPedido.getCliente() : pedido.getCliente());
            pedido.setBarista(updatedPedido.getBarista() != null ? updatedPedido.getBarista() : pedido.getBarista());
            pedido.setFechaCompra(updatedPedido.getFechaCompra() != null ? updatedPedido.getFechaCompra() : pedido.getFechaCompra());
            pedido.setCupon(updatedPedido.isCupon());
            pedido.setFormaDePago(updatedPedido.getFormaDePago() != null ? updatedPedido.getFormaDePago() : pedido.getFormaDePago());

            return pedidoRepository.save(pedido);
        });
    }

    public boolean deletePedido(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Pedido> getPedidoById(Long id) {
        return pedidoRepository.findById(id);
    }
    
    public boolean verificarConvenioUniversidad(String u) {
    	String url = "http://universities.hipolabs.com/search?name="+u;
    	List<UniversitiesDto> dtoList = Arrays.asList(
    		    restTemplate.getForObject(url, UniversitiesDto[].class));
    	if (!dtoList.isEmpty() && dtoList.toString().contains("Colombia")) {
    		return true;
    	} else return false;
    }
}