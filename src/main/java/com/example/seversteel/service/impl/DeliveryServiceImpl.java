package com.example.seversteel.service.impl;

import com.example.seversteel.dto.DeliveryItemRequest;
import com.example.seversteel.dto.DeliveryRequest;
import com.example.seversteel.models.Delivery;
import com.example.seversteel.models.DeliveryItem;
import com.example.seversteel.models.Product;
import com.example.seversteel.models.Supplier;
import com.example.seversteel.repository.DeliveryRepository;
import com.example.seversteel.repository.ProductRepository;
import com.example.seversteel.repository.SupplierRepository;
import com.example.seversteel.service.DeliveryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryServiceImpl(SupplierRepository supplierRepository, ProductRepository productRepository, DeliveryRepository deliveryRepository) {
        this.supplierRepository = supplierRepository;
        this.deliveryRepository = deliveryRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void createDelivery(DeliveryRequest request) {
        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Поставщик не найден"));

        Delivery delivery = new Delivery();
        delivery.setSupplier(supplier);
        delivery.setDeliveryDate(request.getDeliveryDate());

        for (DeliveryItemRequest itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Продукт не найден"));

            DeliveryItem item = new DeliveryItem();
            item.setProduct(product);
            item.setWeight(itemRequest.getWeight());
            item.setDelivery(delivery);
            delivery.getItems().add(item);
        }

        deliveryRepository.save(delivery);
    }
}