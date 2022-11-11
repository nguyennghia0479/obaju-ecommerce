package cybersoft.javabackend.java18.obajuecommerce.app_stock.service;

import cybersoft.javabackend.java18.obajuecommerce.app_stock.dto.StockCreateDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.dto.StockDTO;
import cybersoft.javabackend.java18.obajuecommerce.app_stock.dto.StockUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface StockService {
    List<StockDTO> findAll();

    StockDTO findById(UUID id);

    StockDTO save(StockCreateDTO stockCreateDTO);

    StockDTO update(StockUpdateDTO stockUpdateDTO);

    void deleteById(UUID id);
}
