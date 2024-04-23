package dw.wholesale_company.service;

import dw.wholesale_company.model.Mileage;
import dw.wholesale_company.repository.MileageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MileageService {
    @Autowired
    MileageRepository mileageRepository;

    public List<Mileage> getMileageAll() { return mileageRepository.findAll(); }
}
