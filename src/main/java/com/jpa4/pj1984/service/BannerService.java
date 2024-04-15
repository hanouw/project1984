package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.Banner;
import com.jpa4.pj1984.domain.ProductFile;
import com.jpa4.pj1984.dto.BannerDTO;
import com.jpa4.pj1984.dto.BannerForm;
import com.jpa4.pj1984.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BannerService {
    private final BannerRepository bannerRepository;
    private final FileUploadService fileUploadService;

    //저장
    public void save(BannerForm bannerForm) throws IOException{
        String projectPath = System.getProperty("user.dir") + "";

        ProductFile imgFile = fileUploadService.saveFile(bannerForm.getBannerImg());
        Banner entity = bannerForm.toEntity();
        entity.setBannerImgOrg(imgFile.getOrgFileName());
        entity.setBannerImgStored(imgFile.getStoredFileName());
        Banner bannerSaved = bannerRepository.save(entity);
    }

    //목록조회
    public List<BannerDTO> findAll(){
        List<Banner> all = bannerRepository.findAll();
        List<BannerDTO> list = all.stream()
                .map(b -> new BannerDTO(b))
                .collect(Collectors.toList());
        return list;
    }

    //조회(1)
    public BannerDTO findOne(Long id){
        Banner banner = bannerRepository.findById(id).orElse(null);
        return new BannerDTO(banner);
    }

    public void updateOne(BannerForm bannerForm) {
        Banner banner = bannerRepository.findById(bannerForm.getBannerId()).orElse(null);
        banner.setBannerTitle(bannerForm.getBannerTitle());
    }

    //수정

}
