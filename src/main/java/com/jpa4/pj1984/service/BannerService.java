package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.Banner;
import com.jpa4.pj1984.domain.ProductFile;
import com.jpa4.pj1984.dto.BannerDTO;
import com.jpa4.pj1984.dto.BannerForm;
import com.jpa4.pj1984.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
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
    //수정
    public void updateOne(BannerForm bannerForm) throws IOException{
        Banner banner = bannerRepository.findById(bannerForm.getBannerId()).orElse(null);
        System.out.println("배너서비스실행 됬습니둥");

        if (bannerForm. getBannerImg() != null) {

            if (banner != null && !banner.getBannerImgStored().isEmpty()) {
                // 기존 이미지 파일 삭제
                boolean deleteImg = fileUploadService.deleteFile(banner.getBannerImgStored());
                System.out.println("이미지삭제요청");
                if (deleteImg) {
                    // 새 이미지 파일 저장
                    ProductFile bannerImg = fileUploadService.saveFile(bannerForm.getBannerImg());
                    banner.setBannerImgOrg(bannerImg.getOrgFileName());
                    System.out.println("bannerForm = " + bannerImg.getOrgFileName());
                    banner.setBannerImgStored(bannerImg.getStoredFileName());
                    System.out.println("bannerForm = " + banner.getBannerImgStored());
                    System.out.println("이미지저장요청");
                }
            }
        }
        // 저장
        banner.setBannerTitle(bannerForm.getBannerTitle());
        banner.setBannerDetail(bannerForm.getBannerDetail());
        banner.setBannerOrder(bannerForm.getBannerOrder());
        banner.setBannerLink(bannerForm.getBannerLink());
        banner.setBannerStatus(bannerForm.getBannerStatus());
        System.out.println("배너서비스실행 - 처리 - 됬습니둥");
    }



}
