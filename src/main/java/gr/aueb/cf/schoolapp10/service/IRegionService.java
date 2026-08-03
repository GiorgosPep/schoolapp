package gr.aueb.cf.schoolapp10.service;

import gr.aueb.cf.schoolapp10.dto.RegionReadOnlyDTO;

import java.util.List;

public interface IRegionService {
    List<RegionReadOnlyDTO> findAllRegionsSortedByName();
}
