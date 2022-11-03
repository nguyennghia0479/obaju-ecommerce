package cybersoft.javabackend.java18.obajuecommerce.app_stock.service;

import cybersoft.javabackend.java18.obajuecommerce.app_stock.dto.StockCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.dto.StockDTO;

import java.util.List;
import java.util.UUID;

public interface StockService {
    List<StockDTO> findAll();

    StockDTO save(StockCreateDTO stockCreateDTO);

    void deleteById(UUID id);
}
